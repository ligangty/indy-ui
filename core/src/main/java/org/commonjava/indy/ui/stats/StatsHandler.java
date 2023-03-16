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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commonjava.indy.ui.IndyUIException;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.jaxrs.ResponseHelper;
import org.commonjava.indy.ui.util.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Api( description = "Various read-only operations for retrieving information about the system.",
      value = "Generic Infrastructure Queries (UI Support)" )
@Path( "/api/stats" )
public class StatsHandler
        implements IndyUIResources
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @Inject
    private StatsController statsController;

    @Inject
    private ResponseHelper responseHelper;

    @ApiOperation(
            "Aggregate javascript content for all add-ons and format as a single Javascript stream (this gives the UI a static URL to load add-on logic)" )
    @ApiResponse( code = 200, message = "The add-on Javascript wrapped as a JSON object" )
    @Path( "/addons/active.js" )
    @GET
    @Produces( ApplicationContent.application_json )
    public Response getAddonInjectionJavascript()
    {
        Response response;
        try
        {
            response = responseHelper.formatOkResponseWithEntity( statsController.getActiveAddOnsJavascript(),
                                                                  ApplicationContent.application_json );
        }
        catch ( final IndyUIException e )
        {
            logger.error(
                    String.format( "Failed to format active-addons javascript: %s", responseHelper.formatEntity( e ) ),
                    e );
            response = responseHelper.formatResponse( e );
        }
        return response;
    }

}
