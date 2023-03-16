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
package org.commonjava.indy.ui.jaxrs.nfc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.model.core.dto.NotFoundCacheDTO;
import org.commonjava.indy.model.core.dto.NotFoundCacheInfoDTO;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Api( description = "REST resource that manages the not-found cache", value = "Not-Found Cache" )
@Path( "/api/nfc" )
public class NfcResource
        implements IndyUIResources
{

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    @ApiOperation( "Clear all not-found cache entries" )
    @DELETE
    public Response clearAll()
    {
        return Response.ok( "Not implemented" ).build();
    }

    @Path( "/{type: (hosted|group|remote)}/{name}{path: (/.+)?}" )
    @ApiOperation(
            "[Deprecated] Clear all not-found cache entries for a particular store (or optionally, a subpath within a store)" )
    @DELETE
    @Deprecated
    public Response deprecatedClearStore(
            final @ApiParam( allowableValues = "hosted,group,remote", name = "type", required = true,
                             value = "The type of store" ) @PathParam( "type" ) String t,
            final @ApiParam( name = "name", required = true, value = "The name of the store" )
            @PathParam( "name" ) String name,
            final @ApiParam( name = "path", required = false, value = "The sub-path to clear" )
            @PathParam( "path" ) String p )
    {

        return Response.ok( "Not implemented" ).build();
    }

    @Path( "/{packageType}/{type: (hosted|group|remote)}/{name}{path: (/.+)?}" )
    @ApiOperation(
            "Clear all not-found cache entries for a particular store (or optionally, a subpath within a store)" )
    @DELETE
    public Response clearStore( final @ApiParam( name = "packageType", required = true,
                                                 value = "The type of package (eg. maven, npm, generic-http)" )
                                @PathParam( "packageType" ) String packageType,
                                final @ApiParam( allowableValues = "hosted,group,remote", name = "type",
                                                 required = true, value = "The type of store" )
                                @PathParam( "type" ) String t,
                                final @ApiParam( name = "name", required = true, value = "The name of the store" )
                                @PathParam( "name" ) String name,
                                final @ApiParam( name = "path", required = false, value = "The sub-path to clear" )
                                @PathParam( "path" ) String p )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @GET
    @ApiOperation( "Retrieve all not-found cache entries currently tracked" )
    @ApiResponses(
            { @ApiResponse( code = 200, response = NotFoundCacheDTO.class, message = "The full not-found cache" ) } )
    @Produces( ApplicationContent.application_json )
    public Response getAll(
            final @ApiParam( name = "pageIndex", value = "page index" ) @QueryParam( "pageIndex" ) Integer pageIndex,
            final @ApiParam( name = "pageSize", value = "page size" ) @QueryParam( "pageSize" ) Integer pageSize )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @GET
    @Path( "/info" )
    @ApiOperation( "Get not-found cache information, e.g., size, etc" )
    @ApiResponses( { @ApiResponse( code = 200, response = NotFoundCacheInfoDTO.class,
                                   message = "The info of not-found cache" ) } )
    @Produces( ApplicationContent.application_json )
    public Response getInfo()
    {
        return Response.ok( "Not implemented" ).build();
    }

    @Path( "/{type: (hosted|group|remote)}/{name}" )
    @ApiOperation( "[Deprecated] Retrieve all not-found cache entries currently tracked for a given store" )
    @ApiResponses( { @ApiResponse( code = 200, response = NotFoundCacheDTO.class,
                                   message = "The not-found cache for the specified artifact store" ) } )
    @GET
    @Produces( ApplicationContent.application_json )
    public Response deprecatedGetStore(
            final @ApiParam( allowableValues = "hosted,group,remote", name = "type", required = true,
                             value = "The type of store" ) @PathParam( "type" ) String t,
            final @ApiParam( name = "name", value = "The name of the store" ) @PathParam( "name" ) String name,
            final @ApiParam( name = "pageIndex", value = "page index" ) @QueryParam( "pageIndex" ) Integer pageIndex,
            final @ApiParam( name = "pageSize", value = "page size" ) @QueryParam( "pageSize" ) Integer pageSize )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @Path( "/{packageType}/{type: (hosted|group|remote)}/{name}/info" )
    @ApiOperation( "Get not-found cache information, e.g., size, etc" )
    @ApiResponses( { @ApiResponse( code = 200, response = NotFoundCacheInfoDTO.class,
                                   message = "The info of not-found cache" ) } )
    @GET
    @Produces( ApplicationContent.application_json )
    public Response getStoreInfo( final @ApiParam( name = "packageType", required = true,
                                                   value = "type of package (eg. maven, npm, generic-http)" )
                                  @PathParam( "packageType" ) String packageType,
                                  final @ApiParam( allowableValues = "hosted,group,remote", name = "type",
                                                   required = true, value = "type of store" )
                                  @PathParam( "type" ) String t,
                                  final @ApiParam( name = "name", value = "name of the store" )
                                  @PathParam( "name" ) String name )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @Path( "/{packageType}/{type: (hosted|group|remote)}/{name}" )
    @ApiOperation( "Retrieve all not-found cache entries currently tracked for a given store" )
    @ApiResponses( { @ApiResponse( code = 200, response = NotFoundCacheDTO.class,
                                   message = "The not-found cache for the specified artifact store" ) } )
    @GET
    @Produces( ApplicationContent.application_json )
    public Response getStore( final @ApiParam( name = "packageType", required = true,
                                               value = "type of package (eg. maven, npm, generic-http)" )
                              @PathParam( "packageType" ) String packageType,
                              final @ApiParam( allowableValues = "hosted,group,remote", name = "type", required = true,
                                               value = "type of store" ) @PathParam( "type" ) String t,
                              final @ApiParam( name = "name", value = "name of the store" )
                              @PathParam( "name" ) String name,
                              final @ApiParam( name = "pageIndex", value = "page index" )
                              @QueryParam( "pageIndex" ) Integer pageIndex,
                              final @ApiParam( name = "pageSize", value = "page size" )
                              @QueryParam( "pageSize" ) Integer pageSize )
    {
        return Response.ok( "Not implemented" ).build();
    }
}
