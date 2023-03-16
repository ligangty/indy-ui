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
package org.commonjava.indy.ui.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.commonjava.util.jhttpc.HttpFactory;
import org.commonjava.util.jhttpc.HttpFactoryIfc;
import org.commonjava.util.jhttpc.INTERNAL.util.HttpUtils;
import org.commonjava.util.jhttpc.JHttpCException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@ApplicationScoped
public class HttpProvider
{

    private HttpFactoryIfc httpFactory;

    private Http http;

    @Inject
    private SiteConfigLookup siteConfigLookup;

    private PasswordManagerDelegate passwordManager;

    protected HttpProvider()
    {
    }

    public HttpProvider( SiteConfigLookup siteConfigLookup )
    {
        this.siteConfigLookup = siteConfigLookup;
        setup();
    }

    @PostConstruct
    public void setup()
    {
        passwordManager = new AttributePasswordManager();
        http = new Http( passwordManager );

        httpFactory =
                new HttpFactory( new org.commonjava.util.jhttpc.auth.AttributePasswordManager( siteConfigLookup ) );
    }

    @Produces
    @Default
    @Singleton
    public PasswordManagerDelegate getPasswordManager()
    {
        return passwordManager;
    }

    @Produces
    @Default
    @Singleton
    public Http getHttp()
    {
        return http;
    }

    @Produces
    @Default
    @Singleton
    public HttpFactoryIfc getHttpFactory()
    {
        return httpFactory;
    }

    /**
     * Create http request context and apply site config.
     * @param siteId ID to represent the site. It is generally the hostname of target site.
     * @return
     * @throws HttpException
     */
    public HttpClientContext createContext( final String siteId )
            throws HttpException
    {
        try
        {
            return httpFactory.createContext( siteConfigLookup.lookup( siteId ) );
        }
        catch ( JHttpCException e )
        {
            throw new HttpException( "Failed to create http client context: %s", e, e.getMessage() );
        }
    }

    /**
     * Create http client and apply site config.
     * @param siteId ID to represent the site. It is generally the hostname of target site.
     * @return
     * @throws HttpException
     */
    public CloseableHttpClient createClient( final String siteId )
            throws HttpException
    {
        try
        {
            return httpFactory.createClient( siteConfigLookup.lookup( siteId ) );
        }
        catch ( JHttpCException e )
        {
            throw new HttpException( "Failed to create http client: %s", e, e.getMessage() );
        }
    }

    public void cleanup( final CloseableHttpClient client, final HttpUriRequest request,
                         final CloseableHttpResponse response )
    {
        HttpUtils.cleanupResources( client, request, response );
    }

}
