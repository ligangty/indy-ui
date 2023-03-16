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
package org.commonjava.indy.ui.jaxrs.koji;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.commonjava.indy.koji.model.KojiMultiRepairResult;
import org.commonjava.indy.koji.model.KojiRepairRequest;
import org.commonjava.indy.koji.model.KojiRepairResult;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.ApplicationContent;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Api( value = "Koji repairVolume operation", description = "Repair Koji remote repositories." )
@Path( "/api/repair/koji" )
@Produces( ApplicationContent.application_json )
public class KojiRepairResource
        implements IndyUIResources
{

    @ApiOperation( "Repair koji repository remote url /vol." )
    @ApiResponse( code = 200, message = "Operation finished (consult response content for success/failure).",
                  response = KojiRepairResult.class )
    @ApiImplicitParam( name = "body", paramType = "body",
                       value = "JSON request specifying source and other configuration options", required = true,
                       dataType = "org.commonjava.indy.koji.model.KojiRepairRequest" )
    @POST
    @Path( "/vol" )
    @Consumes( ApplicationContent.application_json )
    public KojiRepairResult repairVolumes( final KojiRepairRequest request,
                                           final @Context HttpServletRequest servletRequest,
                                           final @Context SecurityContext securityContext,
                                           final @Context UriInfo uriInfo )
    {
        return null;
    }

    @ApiOperation( "Repair koji repository path masks." )
    @ApiResponse( code = 200, message = "Operation finished (consult response content for success/failure).",
                  response = KojiRepairResult.class )
    @ApiImplicitParam( name = "body", paramType = "body",
                       value = "JSON request specifying source and other configuration options", required = true,
                       dataType = "org.commonjava.indy.koji.model.KojiRepairRequest" )
    @POST
    @Path( "/mask" )
    @Consumes( ApplicationContent.application_json )
    public KojiRepairResult repairPathMasks( final KojiRepairRequest request,
                                             final @Context HttpServletRequest servletRequest,
                                             final @Context SecurityContext securityContext,
                                             final @Context UriInfo uriInfo )
    {
        return null;
    }

    @ApiOperation( "Repair koji repository path masks for ALL koji remote repositories." )
    @ApiResponse( code = 200, message = "Operation finished (consult response content for success/failure).",
                  response = KojiMultiRepairResult.class )
    @POST
    @Path( "mask/all" )
    @Consumes( ApplicationContent.application_json )
    public KojiMultiRepairResult repairAllPathMasks( final @Context HttpServletRequest servletRequest,
                                                     final @Context SecurityContext securityContext,
                                                     final @Context UriInfo uriInfo )
    {
        return null;
    }

    @ApiOperation( "Repair koji repository metadata timeout to \"never timeout(-1)\"." )
    @ApiResponse( code = 200, message = "Operation finished (consult response content for success/failure).",
                  response = KojiRepairResult.class )
    @ApiImplicitParam( name = "body", paramType = "body",
                       value = "JSON request specifying source and other configuration options", required = true,
                       dataType = "org.commonjava.indy.koji.model.KojiRepairRequest" )
    @POST
    @Path( "metadata/timeout" )
    @Consumes( ApplicationContent.application_json )
    public KojiRepairResult repairMetadataTimeout( final KojiRepairRequest request,
                                                   final @Context HttpServletRequest servletRequest,
                                                   final @Context SecurityContext securityContext )
    {
        return null;
    }

    @ApiOperation(
            "Repair koji repository metadata timeout to \"never timeout(-1)\" for all koji remote repositories." )
    @ApiImplicitParam( name = "isDryRun", paramType = "query",
                       value = "boolean value to specify if this request is a dry run request", defaultValue = "false",
                       dataType = "java.lang.Boolean" )
    @ApiResponse( code = 200, message = "Operation finished (consult response content for success/failure).",
                  response = KojiMultiRepairResult.class )
    @POST
    @Path( "metadata/timeout/all" )
    @Consumes( ApplicationContent.application_json )
    public KojiMultiRepairResult repairAllMetadataTimeout( final @Context HttpServletRequest servletRequest,
                                                           final @QueryParam( "isDryRun" ) Boolean isDryRun,
                                                           final @Context SecurityContext securityContext )
    {

        return null;
    }

}
