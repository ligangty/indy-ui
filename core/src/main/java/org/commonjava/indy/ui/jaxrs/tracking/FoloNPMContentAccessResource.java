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
package org.commonjava.indy.ui.jaxrs.tracking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.Constants;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

@Api( value = "FOLO Tracked Content Access and Storage For NPM related artifacts. Tracks retrieval and management of file/artifact content." )
@Path( "/api/folo/track/{id}/npm/{type: (hosted|group|remote)}/{name}" )
public class FoloNPMContentAccessResource
        implements IndyUIResources
{

    @ApiOperation( "Store and track NPM file/artifact content under the given artifact store (type/name) and path." )
    @ApiResponses( { @ApiResponse( code = 201, message = "Content was stored successfully" ), @ApiResponse( code = 400,
                                                                                                            message = "No appropriate storage location was found in the specified store (this store, or a member if a group is specified)." ) } )
    @PUT
    @Path( "/{packageName}" )
    public Response doCreate( @ApiParam( "User-assigned tracking session key" ) @PathParam( "id" ) final String id,
                              @ApiParam( allowableValues = "hosted,group,remote", required = true ) @PathParam( "type" )
                              final String type, @PathParam( "name" ) final String name,
                              @PathParam( "packageName" ) final String packageName,
                              @Context final HttpServletRequest request, @Context final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Store NPM artifact content under the given artifact store (type/name), packageName and versionTarball (/version or /-/tarball)." )
    @ApiResponses( { @ApiResponse( code = 201, message = "Content was stored successfully" ), @ApiResponse( code = 400,
                                                                                                            message = "No appropriate storage location was found in the specified store (this store, or a member if a group is specified)." ) } )
    @PUT
    @Path( "/{packageName}/{versionTarball: (.*)}" )
    public Response doCreate( @ApiParam( "User-assigned tracking session key" ) @PathParam( "id" ) final String id,
                              final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                              @PathParam( "type" ) String type,
                              final @ApiParam( required = true ) @PathParam( "name" ) String name,
                              final @PathParam( "packageName" ) String packageName,
                              final @PathParam( "versionTarball" ) String versionTarball,
                              final @Context UriInfo uriInfo, final @Context HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Store and track file/artifact content under the given artifact store (type/name) and path." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ), @ApiResponse( code = 200,
                                                                                                     message = "Header metadata for content (or rendered listing when path ends with '/index.html' or '/'" ), } )
    @HEAD
    @Path( "/{packageName}" )
    public Response doHead( @ApiParam( "User-assigned tracking session key" ) @PathParam( "id" ) final String id,
                            final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type,
                            final @ApiParam( required = true ) @PathParam( "name" ) String name,
                            final @PathParam( "packageName" ) String packageName,
                            final @QueryParam( Constants.CHECK_CACHE_ONLY ) Boolean cacheOnly, final @Context UriInfo uriInfo,
                            final @Context HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Store and track file/artifact content under the given artifact store (type/name) and path." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ), @ApiResponse( code = 200,
                                                                                                     message = "Header metadata for content (or rendered listing when path ends with '/index.html' or '/'" ), } )
    @HEAD
    @Path( "/{packageName}/{versionTarball: (.*)}" )
    public Response doHead( @ApiParam( "User-assigned tracking session key" ) @PathParam( "id" ) final String id,
                            @ApiParam( allowableValues = "hosted,group,remote", required = true ) @PathParam( "type" )
                            final String type, @PathParam( "name" ) final String name,
                            final @PathParam( "packageName" ) String packageName,
                            final @PathParam( "versionTarball" ) String versionTarball,
                            final @QueryParam( Constants.CHECK_CACHE_ONLY ) Boolean cacheOnly,
                            @Context final HttpServletRequest request, @Context final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve and track NPM file/artifact content under the given artifact store (type/name) and path." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ),
            @ApiResponse( code = 200, response = String.class,
                          message = "Rendered content listing (when path ends with '/index.html' or '/')" ),
            @ApiResponse( code = 200, response = StreamingOutput.class, message = "Content stream" ), } )
    @GET
    @Path( "/{packageName}" )
    public Response doGet( @ApiParam( "User-assigned tracking session key" ) @PathParam( "id" ) final String id,
                           @ApiParam( allowableValues = "hosted,group,remote", required = true ) @PathParam( "type" )
                           final String type, @PathParam( "name" ) final String name,
                           final @PathParam( "packageName" ) String packageName,
                           @Context final HttpServletRequest request, @Context final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve and track NPM file/artifact content under the given artifact store (type/name) and path." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ),
            @ApiResponse( code = 200, response = String.class,
                          message = "Rendered content listing (when path ends with '/index.html' or '/')" ),
            @ApiResponse( code = 200, response = StreamingOutput.class, message = "Content stream" ), } )
    @GET
    @Path( "/{packageName}/{versionTarball: (.*)}" )
    public Response doGet( @ApiParam( "User-assigned tracking session key" ) @PathParam( "id" ) final String id,
                           @ApiParam( allowableValues = "hosted,group,remote", required = true ) @PathParam( "type" )
                           final String type, @PathParam( "name" ) final String name,
                           final @PathParam( "packageName" ) String packageName,
                           final @PathParam( "versionTarball" ) String versionTarball,
                           @Context final HttpServletRequest request, @Context final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

}
