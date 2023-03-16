/**
 * Copyright (C) 2011-2022 Red Hat, Inc. (https://github.com/Commonjava/indy)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.ui.boot;

import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.util.ImmediateInstanceFactory;
//import org.commonjava.indy.ui.conf.UIConfiguration;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.jaxrs.RestProvider;
import org.commonjava.indy.ui.jaxrs.UnhandledThrowableHandler;
import org.commonjava.indy.ui.jaxrs.UnhandledWebApplicationExceptionHandler;
import org.commonjava.indy.ui.util.DeploymentInfoUtils;
import org.commonjava.indy.ui.web.UIServlet;
import org.commonjava.propulsor.deploy.resteasy.helper.CdiInjectorFactoryImpl;
import org.commonjava.propulsor.deploy.resteasy.helper.RequestScopeListener;
import org.commonjava.propulsor.deploy.resteasy.jackson.CDIJacksonProvider;
import org.commonjava.propulsor.deploy.undertow.ui.UIConfiguration;
import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@ApplicationScoped
public class IndyUIDeployment
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    public static final String API_PREFIX = "api";

    @Inject
    private Instance<IndyUIResources> resources;

    @Inject
    private Instance<RestProvider> providerInstances;

    @Inject
    private Instance<IndyUIDeploymentProvider> deployments;

    @Inject
    private UIServlet ui;

    @Inject
    private UIConfiguration uiConfiguration;

    private Set<Class<? extends IndyUIResources>> resourceClasses;

    private Set<Class<? extends RestProvider>> providerClasses;

    private Set<IndyUIDeploymentProvider> deploymentProviders;

    protected IndyUIDeployment()
    {
    }

    public IndyUIDeployment( final Set<Class<? extends IndyUIResources>> resourceClasses,
                             final Set<IndyUIDeploymentProvider> deploymentProviders, final UIServlet ui )
    {
        this.resourceClasses = resourceClasses;
        this.deploymentProviders = deploymentProviders;
        this.ui = ui;
        this.providerClasses = Collections.emptySet();
        this.classes = getClasses();
    }

    @PostConstruct
    public void cdiInit()
    {
        providerClasses = new HashSet<>();
        resourceClasses = new HashSet<>();
        for ( final IndyUIResources indyResources : resources )
        {
            resourceClasses.add( indyResources.getClass() );
        }

        for ( final RestProvider restProvider : providerInstances )
        {
            providerClasses.add( restProvider.getClass() );
        }

        deploymentProviders = new HashSet<>();
        for ( final IndyUIDeploymentProvider fac : deployments )
        {
            logger.info( "Found deployment provider: {}", fac );
            deploymentProviders.add( fac );
        }

        classes = getClasses();
    }

    private Set<Class<?>> classes;

    public DeploymentInfo getDeployment( final String contextRoot )
    {
        final ResteasyDeployment deployment = new ResteasyDeployment();

        Application application = new Application()
        {
            public Set<Class<?>> getClasses()
            {
                return classes;
            }
        };
        deployment.setApplication( application );

        deployment.setInjectorFactoryClass( CdiInjectorFactoryImpl.class.getName() );

        final ServletInfo resteasyServlet = Servlets.servlet( "REST", HttpServlet30Dispatcher.class )
                                                    .setAsyncSupported( true )
                                                    .setLoadOnStartup( 1 )
                                                    .addMapping( "/api*" )
                                                    .addMapping( "/api/*" )
                                                    .addMapping( "/api-docs*" )
                                                    .addMapping( "/api-docs/*" );




        final DeploymentInfo di = new DeploymentInfo().addListener( Servlets.listener( RequestScopeListener.class ) )
                                                      //.addInitParameter( "resteasy.scan", Boolean.toString( true ) )
                                                      .setContextPath( contextRoot )
                                                      .addServletContextAttribute( ResteasyDeployment.class.getName(),
                                                                                   deployment )
                                                      .addServlet( resteasyServlet )
                                                      .setDeploymentName( "Indy-UI" )
                                                      .setClassLoader( ClassLoader.getSystemClassLoader() );
        //.addOuterHandlerChainWrapper( new HeaderDebugger().new
        // Wrapper() );

        if ( deploymentProviders != null )
        {
            DeploymentInfoUtils.mergeFromProviders( di, deploymentProviders, contextRoot, application );
        }

        if ( uiConfiguration.isEnabled() )
        {
            // Add UI servlet at the end so its mappings don't obscure any from add-ons.
            final ServletInfo uiServlet = Servlets.servlet( "UI", UIServlet.class )
                                                  .setAsyncSupported( true )
                                                  .setLoadOnStartup( 99 )
                                                  .addMappings( UIServlet.PATHS );

            uiServlet.setInstanceFactory( new ImmediateInstanceFactory<Servlet>( ui ) );
            di.addServlet( uiServlet );
        }

        return di;
    }

    public Set<Class<?>> getClasses()
    {
        final Set<Class<?>> classes = new LinkedHashSet<>();
        classes.addAll( providerClasses );
        classes.addAll( resourceClasses );
        deploymentProviders.forEach( di -> classes.addAll( di.getAdditionalClasses() ) );
        classes.add( CDIJacksonProvider.class );
        classes.add( UnhandledWebApplicationExceptionHandler.class );
        classes.add( UnhandledThrowableHandler.class );
//        classes.add( FlightRecorderFilter.class );
        return classes;
    }

}
