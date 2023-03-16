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
package org.commonjava.indy.ui.boot;

import org.commonjava.propulsor.boot.BootException;
import org.commonjava.propulsor.boot.BootOptions;
import org.commonjava.propulsor.boot.Booter;
import org.commonjava.propulsor.boot.WeldBootInterface;
import org.commonjava.propulsor.deploy.DeployException;
import org.commonjava.propulsor.lifecycle.AppLifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

import static org.commonjava.propulsor.boot.BootStatus.ERR_LOAD_BOOT_OPTIONS;
import static org.commonjava.propulsor.boot.BootStatus.ERR_START;

public class UIBooter
        extends Booter
        implements WeldBootInterface
{
    private static final Logger logger = LoggerFactory.getLogger( UIBooter.class );

    public static void main( String[] args )
    {
        setDefaultUncaughtExceptionHandler();
        BootOptions boot;
        try
        {
            boot = loadFromSysProps( "indy-ui", "indy-ui.boot.defaults", "indy-ui.home" );
        }
        catch ( final BootException e )
        {
            logger.error( "ERROR: ", e );
            System.exit( ERR_LOAD_BOOT_OPTIONS );
            return;
        }

        try
        {
            if ( boot.parseArgs( args ) )
            {
                Booter booter = new UIBooter();
                booter.runAndWait( boot );
            }
        }
        catch ( final BootException e )
        {
            logger.error( "ERROR: ", e );
            System.exit( ERR_START );
        }
    }

    private static void setDefaultUncaughtExceptionHandler()
    {
        Thread.setDefaultUncaughtExceptionHandler( ( thread, error ) -> {
            if ( error instanceof InvocationTargetException )
            {
                final InvocationTargetException ite = (InvocationTargetException) error;
                logger.error( "IN: {} ({}), caught InvocationTargetException", thread.getName(), thread.getId() );
                ite.getTargetException().printStackTrace();

                logger.error( "...via:" );
                error.printStackTrace();
            }
            else
            {
                logger.error( "IN: {} ({}) Uncaught error:", thread.getName(), thread.getId() );
                error.printStackTrace();
            }
        } );
    }

    @Override
    public void startLifecycle()
            throws AppLifecycleException
    {
        logger.info( "No lifecycle..." );
        super.startLifecycle();
    }

    @Override
    public void deploy()
            throws DeployException
    {
        IndyUIDeployer deployer = getContainer().select( IndyUIDeployer.class ).get();
        if ( deployer == null )
        {
            logger.warn( "No deployer found!" );
            return;
        }
        logger.info( "Deployer: {}", deployer.getClass() );
        deployer.deploy( options );
    }

}
