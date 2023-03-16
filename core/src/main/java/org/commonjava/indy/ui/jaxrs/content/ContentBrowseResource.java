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
import org.commonjava.indy.ui.util.ApplicationContent;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Api( value = "Indy Directory Content Browse", description = "Browse directory content in indy repository" )
@Path( "/api/browse/{packageType}/{type: (hosted|group|remote)}/{name}" )
//@ApplicationScoped
public class ContentBrowseResource
        implements IndyUIResources
{

    @ApiOperation( "Retrieve directory content under the given artifact store (type/name) and directory path." )
    @ApiResponses( { @ApiResponse( code = 200, response = String.class, message = "Rendered content listing" ),
            @ApiResponse( code = 404, message = "Content is not available" ) } )
    @HEAD
    @Path( "/{path (.*)}" )
    public Response headForDirectory( final @ApiParam( allowableValues = "maven,npm", required = true )
                                      @PathParam( "packageType" ) String packageType,
                                      final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                                      @PathParam( "type" ) String type,
                                      final @ApiParam( required = true ) @PathParam( "name" ) String name,
                                      final @PathParam( "path" ) String path )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve directory content under the given artifact store (type/name) and directory path." )
    @ApiResponses( { @ApiResponse( code = 404, message = "Content is not available" ),
            @ApiResponse( code = 200, response = String.class, message = "Rendered content listing" ) } )
    @GET
    @Path( "/{path: (.*)}" )
    @Produces( ApplicationContent.application_json )
    public Response browseDirectory( final @ApiParam( allowableValues = "maven,npm", required = true )
                                     @PathParam( "packageType" ) String packageType,
                                     final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                                     @PathParam( "type" ) String type,
                                     final @ApiParam( required = true ) @PathParam( "name" ) String name,
                                     final @PathParam( "path" ) String path, @Context final UriInfo uriInfo )
    {

        return processRequest( packageType, type, name, path, uriInfo );

    }

    @ApiOperation( "Retrieve root listing under the given artifact store (type/name)." )
    @ApiResponses( { @ApiResponse( code = 200, response = String.class, message = "Rendered root content listing" ) } )
    @GET
    @Path( "/" )
    @Produces( ApplicationContent.application_json )
    public Response browseRoot( final @ApiParam( allowableValues = "maven,npm", required = true )
                                @PathParam( "packageType" ) String packageType,
                                final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                                @PathParam( "type" ) String type,
                                final @ApiParam( required = true ) @PathParam( "name" ) String name,
                                @Context final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    private Response processRequest( final String packageType, final String type, final String name, final String path,
                                     final UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

}
