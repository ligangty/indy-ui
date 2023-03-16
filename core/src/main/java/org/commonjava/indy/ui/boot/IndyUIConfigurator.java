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

import org.commonjava.propulsor.boot.BootOptions;
import org.commonjava.propulsor.config.ConfigurationException;
import org.commonjava.propulsor.config.Configurator;
import org.commonjava.propulsor.config.ConfiguratorException;
import org.commonjava.propulsor.config.dotconf.DotConfConfigurationReader;
import org.commonjava.propulsor.config.io.ConfigFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ApplicationScoped
public class IndyUIConfigurator
        implements Configurator
{
    @Inject
    DotConfConfigurationReader configurationReader;

    @Override
    public void load( BootOptions options )
            throws ConfiguratorException
    {
        String config = options.getConfig();
        File configFile = new File( config );

        try (InputStream stream = ConfigFileUtils.readFileWithIncludes( configFile ))
        {
            configurationReader.loadConfiguration( stream );
        }
        catch ( ConfigurationException | IOException e )
        {
            throw new ConfiguratorException( "Failed to read configuration: %s. Reason: %s", e, config,
                                             e.getMessage() );
        }

        Properties sysprops = System.getProperties();
        System.setProperties( sysprops );
    }

}
