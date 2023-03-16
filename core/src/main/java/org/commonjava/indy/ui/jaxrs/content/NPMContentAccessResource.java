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
package org.commonjava.indy.ui.jaxrs.content;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.Constants;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
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

@Api( value = "NPM Content Access and Storage",
      description = "Handles retrieval and management of NPM artifact content. This is the main point of access for NPM users." )
@Path( "/api/content/npm/{type: (hosted|group|remote)}/{name}" )
@ApplicationScoped
public class NPMContentAccessResource
        implements IndyUIResources
{

    @ApiOperation( "Store NPM artifact content under the given artifact store (type/name) and packageName." )
    @ApiResponses( { @ApiResponse( code = 201, message = "Content was stored successfully" ), @ApiResponse( code = 400,
                                                                                                            message = "No appropriate storage location was found in the specified store (this store, or a member if a group is specified)." ) } )
    @PUT
    @Path( "/{packageName}" )
    public Response doCreate( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                              @PathParam( "type" ) String type,
                              final @ApiParam( required = true ) @PathParam( "name" ) String name,
                              final @PathParam( "packageName" ) String packageName, final @Context UriInfo uriInfo,
                              final @Context HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Store NPM artifact content under the given artifact store (type/name), packageName and versionTarball (/version or /-/tarball)." )
    @ApiResponses( { @ApiResponse( code = 201, message = "Content was stored successfully" ), @ApiResponse( code = 400,
                                                                                                            message = "No appropriate storage location was found in the specified store (this store, or a member if a group is specified)." ) } )
    @PUT
    @Path( "/{packageName}/{versionTarball: (.*)}" )
    public Response doCreate( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                              @PathParam( "type" ) String type,
                              final @ApiParam( required = true ) @PathParam( "name" ) String name,
                              final @PathParam( "packageName" ) String packageName,
                              final @PathParam( "versionTarball" ) String versionTarball,
                              final @Context UriInfo uriInfo, final @Context HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Delete NPM package and metadata content under the given artifact store (type/name) and packageName." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ),
            @ApiResponse( code = 204, message = "Content was deleted successfully" ) } )
    @DELETE
    @Path( "/{path: (.*)}" )
    public Response doDelete( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                              @PathParam( "type" ) String type,
                              final @ApiParam( required = true ) @PathParam( "name" ) String name,
                              final @PathParam( "path" ) String path,
                              final @ApiParam( name = Constants.CHECK_CACHE_ONLY, value = "true or false" )
                              @QueryParam( Constants.CHECK_CACHE_ONLY ) Boolean cacheOnly )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Retrieve NPM package header metadata content under the given artifact store (type/name) and packageName." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Metadata Content is not available" ),
            @ApiResponse( code = 200, message = "Header metadata for package metadata content" ), } )
    @HEAD
    @Path( "/{packageName}" )
    public Response doHead( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type,
                            final @ApiParam( required = true ) @PathParam( "name" ) String name,
                            final @PathParam( "packageName" ) String packageName,
                            @QueryParam( Constants.CHECK_CACHE_ONLY ) final Boolean cacheOnly, @Context final UriInfo uriInfo,
                            @Context final HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Retrieve NPM package tarball header metadata content under the given artifact store (type/name), packageName and versionTarball (/version or /-/tarball)." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ),
            @ApiResponse( code = 200, message = "Header metadata for tarball content" ), } )
    @HEAD
    @Path( "/{packageName}/{versionTarball: (.*)}" )
    public Response doHead( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type,
                            final @ApiParam( required = true ) @PathParam( "name" ) String name,
                            final @PathParam( "packageName" ) String packageName,
                            final @PathParam( "versionTarball" ) String versionTarball,
                            @QueryParam( Constants.CHECK_CACHE_ONLY ) final Boolean cacheOnly, @Context final UriInfo uriInfo,
                            @Context final HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve NPM package metadata content under the given artifact store (type/name) and packageName." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Metadata content is not available" ),
            @ApiResponse( code = 200, response = String.class, message = "Rendered content listing" ),
            @ApiResponse( code = 200, response = StreamingOutput.class, message = "Content stream" ), } )
    @GET
    @Path( "/{packageName}" )
    public Response doGet( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                           @PathParam( "type" ) String type,
                           final @ApiParam( required = true ) @PathParam( "name" ) String name,
                           final @PathParam( "packageName" ) String packageName, @Context final UriInfo uriInfo,
                           @Context final HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Retrieve NPM package tarball content under the given artifact store (type/name), packageName and versionTarball (/version or /-/tarball)." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ),
            @ApiResponse( code = 200, response = StreamingOutput.class, message = "Content stream" ), } )
    @GET
    @Path( "/{packageName}/{versionTarball: (.*)}" )
    public Response doGet( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                           @PathParam( "type" ) String type,
                           final @ApiParam( required = true ) @PathParam( "name" ) String name,
                           final @PathParam( "packageName" ) String packageName,
                           final @PathParam( "versionTarball" ) String versionTarball, @Context final UriInfo uriInfo,
                           @Context final HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve root listing under the given artifact store (type/name)." )
    @ApiResponses( { @ApiResponse( code = 200, response = String.class, message = "Rendered root content listing" ),
            @ApiResponse( code = 200, response = StreamingOutput.class, message = "Content stream" ), } )
    @GET
    @Path( "/" )
    public Response doGet( final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                           @PathParam( "type" ) String type,
                           final @ApiParam( required = true ) @PathParam( "name" ) String name,
                           @Context final UriInfo uriInfo, @Context final HttpServletRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

}
