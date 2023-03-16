/**
 * Copyright (C) 2023 Red Hat, Inc. (https://github.com/Commonjava/indy-ui-service)
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
package org.commonjava.indy.ui.jaxrs.promote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commonjava.indy.promote.model.PathsPromoteResult;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.ApplicationContent;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Api( value = "Content Promotion",
      description = "Promote content from a source repository to a target repository or group." )
@Path( "/api/promotion" )
@Produces( { ApplicationContent.application_json } )
public class PromoteResource
        implements IndyUIResources
{

    @ApiOperation( "Promote paths from a source repository into a target repository/group (subject to validation)." )
    @ApiResponse( code = 200, message = "Promotion operation finished (consult response content for success/failure).",
                  response = PathsPromoteResult.class )
    @ApiImplicitParam( name = "body", paramType = "body",
                       value = "JSON request specifying source and target, with other configuration options",
                       allowMultiple = false, required = true,
                       dataType = "org.commonjava.indy.promote.model.PathsPromoteRequest" )
    @Path( "/paths/promote" )
    @POST
    @Consumes( ApplicationContent.application_json )
    public Response promotePaths( final @Context HttpServletRequest request, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Rollback promotion of any completed paths to a source repository from a target repository/group." )
    @ApiResponse( code = 200, message = "Promotion operation finished (consult response content for success/failure).",
                  response = PathsPromoteResult.class )
    @ApiImplicitParam( name = "body", paramType = "body",
                       value = "JSON result from previous attempt, specifying source and target, with other configuration options",
                       allowMultiple = false, required = true,
                       dataType = "org.commonjava.indy.promote.model.PathsPromoteResult" )
    @Path( "/paths/rollback" )
    @POST
    @Consumes( ApplicationContent.application_json )
    public Response rollbackPaths( @Context final HttpServletRequest request, @Context final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

}
