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
package org.commonjava.indy.ui.jaxrs.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.model.core.BatchDeleteRequest;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.ApplicationContent;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Api( value = "Maintenance", description = "Basic repository maintenance functions" )
@Path( "/api/admin/maint" )
public class MaintenanceResource
        implements IndyUIResources
{
    //    @Inject
    //    @RestClient
    //    MaintenanceServiceClient client;

    @ApiOperation(
            "[Deprecated] Rescan all content in the specified repository to re-initialize metadata, capture missing index keys, etc." )
    @ApiResponse( code = 200,
                  message = "Rescan was started successfully. (NOTE: There currently is no way to determine when rescanning is complete.)" )
    @Path( "/rescan/{type: (hosted|group|remote)}/{name}" )
    @GET
    @Deprecated
    public Response deprecatedRescan(
            @ApiParam( value = "The type of store / repository", allowableValues = "hosted,group,remote",
                       required = true ) final @PathParam( "type" ) String type,
            @ApiParam( "The name of the store / repository" ) @PathParam( "name" ) final String name )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Rescan all content in the specified repository to re-initialize metadata, capture missing index keys, etc." )
    @ApiResponse( code = 200,
                  message = "Rescan was started successfully. (NOTE: There currently is no way to determine when rescanning is complete.)" )
    @Path( "/rescan/{packageType}/{type: (hosted|group|remote)}/{name}" )
    @GET
    public Response rescan( @ApiParam( value = "The package type (eg. maven, npm, generic-http)", required = true )
                            @PathParam( "packageType" ) final String packageType,
                            @ApiParam( value = "The type of store / repository",
                                       allowableValues = "hosted,group,remote", required = true ) final @PathParam(
                                    "type" ) String type,
                            @ApiParam( "The name of the store / repository" ) @PathParam( "name" ) final String name )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            "Rescan all content in all repositories to re-initialize metadata, capture missing index keys, etc." )
    @ApiResponse( code = 200,
                  message = "Rescan was started successfully. (NOTE: There currently is no way to determine when rescanning is complete.)" )
    @Path( "/rescan/all" )
    @GET
    public Response rescanAll()
    {
        return Response.ok( "Not implemented" ).build();
    }

    /**
     * @deprecated use /content/all{path} instead
     * @param path
     * @return
     */
    @Deprecated
    @ApiOperation( "Delete the specified path globally (from any repository that contains it)." )
    @ApiResponse( code = 200, message = "Global deletion complete for path." )
    @Path( "/delete/all{path: (/.+)?}" )
    @DELETE
    public Response deleteAllViaGet( @ApiParam( "The path to delete globally" ) final @PathParam( "path" ) String path )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Delete the specified path globally (from any repository that contains it)." )
    @ApiResponse( code = 200, message = "Global deletion complete for path." )
    @Path( "/content/all{path: (/.+)?}" )
    @DELETE
    public Response deleteAll( @ApiParam( "The path to delete globally" ) final @PathParam( "path" ) String path )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Clean the specified Infinispan cache." )
    @ApiResponse( code = 200, message = "Clean complete." )
    @Path( "/infinispan/cache/{name}" )
    @DELETE
    public Response cleanInfinispanCache(
            @ApiParam( "The name of cache to clean" ) @PathParam( "name" ) final String name )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Export the specified Infinispan cache." )
    @ApiResponse( code = 200, message = "Export complete." )
    @Produces( "application/json" )
    @Path( "/infinispan/cache/{name}{key: (/.+)?}" )
    @GET
    public Response exportInfinispanCache(
            @ApiParam( "The name of cache to export" ) @PathParam( "name" ) final String name,
            @ApiParam( "The cache key" ) @PathParam( "key" ) final String key )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get groups affected by specified repo." )
    @ApiResponse( code = 200, message = "Complete." )
    @Produces( "application/json" )
    @Path( "/store/affected/{key}" )
    @GET
    public Response affectedBy( @ApiParam( "The store key" ) @PathParam( "key" ) final String key )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get tombstone stores that have no content and not in any group." )
    @ApiResponse( code = 200, message = "Complete." )
    @Produces( ApplicationContent.application_json )
    @Path( "/stores/tombstone/{packageType}/hosted" )
    @GET
    public Response getTombstoneStores(
            @ApiParam( "The packageType" ) @PathParam( "packageType" ) final String packageType )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Batch delete files under the given package store (type/name) and paths." )
    @ApiResponse( code = 200, message = "Batch delete operation finished." )
    @ApiImplicitParam( name = "body", paramType = "body",
                       value = "JSON object, specifying storeKey and paths, the option trackingID is not supported in this API.",
                       required = true, dataType = "org.commonjava.indy.model.core.BatchDeleteRequest" )
    @Path( "/content/batch/delete" )
    @POST
    public Response doDelete( final BatchDeleteRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Import artifact stores from a ZIP file." )
    @ApiResponses( { @ApiResponse( code = 201, message = "Import ZIP content" ) } )
    @Path( "/store/import" )
    @PUT
    public Response importStore( final @Context UriInfo uriInfo, final @Context HttpServletRequest request )
    {

        return Response.created( uriInfo.getRequestUri() ).build();
    }
}
