/**
 * Copyright (C) 2023 Red Hat, Inc.
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

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.commonjava.indy.ui.util.HttpUtil;
import org.commonjava.util.jhttpc.HttpFactory;
import org.commonjava.util.jhttpc.HttpFactoryIfc;
import org.commonjava.util.jhttpc.JHttpCException;
import org.commonjava.util.jhttpc.model.SiteConfig;
import org.commonjava.util.jhttpc.model.SiteConfigBuilder;
import org.commonjava.util.jhttpc.model.SiteTrustType;

public class Http
{
    private final PasswordManagerDelegate passwords;

    private final HttpFactoryIfc httpFactory;

    private final LocationLookup locationLookup;

    public CloseableHttpClient createClient()
            throws JHttpCException
    {
        return createClient( null );
    }

    @SuppressWarnings( "OptionalUsedAsFieldOrParameterType" )
    public Http( final PasswordManagerDelegate passwords )
    {
        this.passwords = passwords;
        this.locationLookup = new LocationLookup();
        this.httpFactory = new HttpFactory( new HttpFactoryPasswordDelegate( passwords, locationLookup ) );
    }

    public CloseableHttpClient createClient( final HttpLocation location )
            throws JHttpCException
    {
        if ( location != null )
        {
            locationLookup.register( location );

            int maxConnections = LocationUtils.getMaxConnections( location );
            SiteConfigBuilder configBuilder = new SiteConfigBuilder( location.getName(), location.getUri() );
            configBuilder.withAttributes( location.getAttributes() )
                         .withKeyCertPem( location.getKeyCertPem() )
                         .withServerCertPem( location.getServerCertPem() )
                         .withProxyHost( location.getProxyHost() )
                         .withProxyPort( location.getProxyPort() )
                         .withProxyUser( location.getProxyUser() )
                         .withRequestTimeoutSeconds( LocationUtils.getTimeoutSeconds( location ) )
                         .withUser( location.getUser() )
                         .withIgnoreHostnameVerification( location.isIgnoreHostnameVerification() )
                         .withMaxConnections( maxConnections );

            if ( location.getTrustType() != null )
            {
                configBuilder.withTrustType( SiteTrustType.getType( location.getTrustType().name() ) );
            }

            SiteConfig config = configBuilder.build();

            return httpFactory.createClient( config );
        }
        else
        {
            return httpFactory.createClient();
        }
    }

    private int getProxyPort( final HttpLocation location )
    {
        int port = location.getProxyPort();
        if ( port < 1 )
        {
            port = -1;
        }

        return port;
    }

    public HttpClientContext createContext()
    {
        return createContext( null );
    }

    public HttpClientContext createContext( final HttpLocation location )
    {
        final HttpClientContext ctx = HttpClientContext.create();

        if ( location != null )
        {
            final CredentialsProvider creds = new BasicCredentialsProvider();
            final AuthScope as = new AuthScope( location.getHost(), location.getPort() );

            if ( location.getUser() != null )
            {
                final String password =
                        passwords.getPassword( new PasswordEntry( location, PasswordEntry.USER_PASSWORD ) );
                creds.setCredentials( as, new UsernamePasswordCredentials( location.getUser(), password ) );
            }

            if ( location.getProxyHost() != null && location.getProxyUser() != null )
            {
                final String password =
                        passwords.getPassword( new PasswordEntry( location, PasswordEntry.PROXY_PASSWORD ) );
                creds.setCredentials( new AuthScope( location.getProxyHost(), getProxyPort( location ) ),
                                      new UsernamePasswordCredentials( location.getProxyUser(), password ) );
            }

            ctx.setCredentialsProvider( creds );
        }

        return ctx;
    }

    public void cleanup( final CloseableHttpClient client, final HttpUriRequest request,
                         final CloseableHttpResponse response )
    {
        HttpUtil.cleanupResources( client, request, response );
    }

    public void close()
    {
    }

}
