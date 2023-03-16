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
package org.commonjava.indy.ui.stats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.commonjava.indy.model.spi.AddOnListing;
import org.commonjava.indy.stats.IndyVersioning;
import org.commonjava.indy.ui.IndyUIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;

@ApplicationScoped
public class StatsController
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Inject
    private IndyVersioning versioning;

    @Inject
    private ObjectMapper serializer;

    protected StatsController()
    {
    }

    public AddOnListing getActiveAddOns()
    {
        //TODO: There is no addon in new UI!

        return new AddOnListing( Collections.emptyList() );
    }

    public IndyVersioning getVersionInfo()
    {
        return versioning;
    }

    public String getActiveAddOnsJavascript()
            throws IndyUIException
    {
        try
        {
            //TODO: there is no addon in new Indy UI
            String template = "'use strict'\n" + "var addons = %s;\n\n"
                    + "var indyAddons = angular.module('indy.addons', ['ngResource']);";
            final String json = serializer.writeValueAsString( getActiveAddOns() );

            return String.format( template, json );
        }
        catch ( final JsonProcessingException e )
        {
            throw new IndyUIException( "Failed to render javascript wrapper for active addons. Reason: %s", e,
                                       e.getMessage() );
        }
    }

}
