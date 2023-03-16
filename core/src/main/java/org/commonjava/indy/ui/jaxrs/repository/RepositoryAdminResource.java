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
package org.commonjava.indy.ui.jaxrs.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.commonjava.atlas.maven.ident.util.JoinString;
import org.commonjava.indy.client.core.Indy;
import org.commonjava.indy.client.core.IndyClientException;
import org.commonjava.indy.client.core.module.IndyStoreQueryClientModule;
import org.commonjava.indy.client.core.module.IndyStoresClientModule;
import org.commonjava.indy.model.core.ArtifactStore;
import org.commonjava.indy.model.core.StoreKey;
import org.commonjava.indy.model.core.StoreType;
import org.commonjava.indy.model.core.dto.StoreListingDTO;
import org.commonjava.indy.ui.IndyUIException;
import org.commonjava.indy.ui.client.ServiceClient;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.jaxrs.ResponseHelper;
import org.commonjava.indy.ui.util.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api( tags = { "Store Administration" } )
@SwaggerDefinition( tags = { @Tag( name = "Store Administration",
                                   description = "Resource for accessing and managing artifact store definitions" ) } )
@Path( "/api/admin/stores/{packageType}/{type: (hosted|group|remote)}" )
@ApplicationScoped
public class RepositoryAdminResource
        implements IndyUIResources
{
    private final Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Inject
    @ServiceClient
    Indy client;

    @Inject
    ResponseHelper responseHelper;

    private ArtifactStore getStore( StoreKey key )
    {
        try
        {
            return client.module( IndyStoresClientModule.class ).load( key, key.getType().getStoreClass() );
        }
        catch ( IndyClientException e )
        {
            logger.error( "Can not get ArtifactStore for {} due to: {}", key, e.getMessage() );
            return null;
        }
    }

    @ApiOperation( "Check if a given store exists" )
    @ApiResponses( { @ApiResponse( code = 200, message = "The store exists" ),
            @ApiResponse( code = 404, message = "The store doesn't exist" ) } )
    @Path( "/{name}" )
    @HEAD
    public Response exists( final @PathParam( "packageType" ) String packageType,
                            final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type,
                            @ApiParam( required = true ) @PathParam( "name" ) final String name )
    {
        final StoreType st = StoreType.get( type );
        final StoreKey key = new StoreKey( packageType, st, name );
        org.commonjava.indy.model.core.ArtifactStore store = getStore( key );
        if ( store != null )
        {
            return Response.ok().build();
        }
        else
        {
            return Response.status( Response.Status.NOT_FOUND ).build();
        }
    }

    @ApiOperation( "Create a new store" )
    @ApiResponses( { @ApiResponse( code = 201, response = ArtifactStore.class, message = "The store was created" ),
            @ApiResponse( code = 409, message = "A store with the specified type and name already exists" ) } )
    @ApiImplicitParams( { @ApiImplicitParam( allowMultiple = false, paramType = "body", name = "body", required = true,
                                             dataType = "org.commonjava.indy.model.core.ArtifactStore",
                                             value = "The artifact store definition JSON" ) } )
    @POST
    @Consumes( ApplicationContent.application_json )
    @Produces( ApplicationContent.application_json )
    public Response create( final @PathParam( "packageType" ) String packageType,
                            final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type, final @Context UriInfo uriInfo,
                            final @Context HttpServletRequest request, final @Context SecurityContext securityContext )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Update an existing store" )
    @ApiResponses( { @ApiResponse( code = 200, message = "The store was updated" ), @ApiResponse( code = 400,
                                                                                                  message = "The store specified in the body JSON didn't match the URL parameters" ), } )
    @ApiImplicitParams( { @ApiImplicitParam( allowMultiple = false, paramType = "body", name = "body", required = true,
                                             dataType = "org.commonjava.indy.model.core.ArtifactStore",
                                             value = "The artifact store definition JSON" ) } )
    @Path( "/{name}" )
    @PUT
    @Consumes( ApplicationContent.application_json )
    public Response store( final @PathParam( "packageType" ) String packageType,
                           final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                           @PathParam( "type" ) String type,
                           final @ApiParam( required = true ) @PathParam( "name" ) String name,
                           final @Context HttpServletRequest request, final @Context SecurityContext securityContext )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve the definitions of all artifact stores of a given type on the system" )
    @ApiResponses(
            { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ), } )
    @GET
    @Produces( ApplicationContent.application_json )
    public Response getAll( final @ApiParam(
            "Filter only stores that support the package type (eg. maven, npm). NOTE: '_all' returns all." )
                            @PathParam( "packageType" ) String packageType,
                            final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type )
    {
        final StoreType st = StoreType.get( type );
        final Set<StoreType> types = Set.of( st );
        try
        {
            if ( !"_all".equals( packageType ) )
            {
                StoreListingDTO<ArtifactStore> listingDTO =
                        client.module( IndyStoreQueryClientModule.class ).getAllStores( packageType, types, false );
                return responseHelper.formatOkResponseWithJsonEntity( listingDTO );
            }
            else
            {
                StoreListingDTO<ArtifactStore> listingDTO =
                        client.module( IndyStoreQueryClientModule.class ).getAllByDefaultPkgTypes();
                return responseHelper.formatOkResponseWithJsonEntity( listingDTO );
            }
        }
        catch ( IndyClientException e )
        {
            logger.error( e.getMessage(), e );
            return responseHelper.formatResponse( e );
        }
    }

    @ApiOperation( "Retrieve the definition of a specific artifact store" )
    @ApiResponses( { @ApiResponse( code = 200, response = ArtifactStore.class, message = "The store definition" ),
            @ApiResponse( code = 404, message = "The store doesn't exist" ), } )
    @Path( "/{name}" )
    @GET
    @Produces( ApplicationContent.application_json )
    public Response get( final @PathParam( "packageType" ) String packageType,
                         final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                         @PathParam( "type" ) String type,
                         final @ApiParam( required = true ) @PathParam( "name" ) String name )
    {
        final StoreType st = StoreType.get( type );
        final StoreKey key = new StoreKey( packageType, st, name );
        ArtifactStore store = getStore( key );
        if ( store != null )
        {
            return Response.ok( store ).build();
        }
        else
        {
            return Response.status( Response.Status.NOT_FOUND ).build();
        }
    }

    @ApiOperation( "Delete an artifact store" )
    @ApiResponses( { @ApiResponse( code = 204, response = ArtifactStore.class,
                                   message = "The store was deleted (or didn't exist in the first place)" ), } )
    @Path( "/{name}" )
    @DELETE
    public Response delete( final @PathParam( "packageType" ) String packageType,
                            final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                            @PathParam( "type" ) String type,
                            final @ApiParam( required = true ) @PathParam( "name" ) String name,
                            final @QueryParam( "deleteContent" ) boolean deleteContent,
                            @Context final HttpServletRequest request, final @Context SecurityContext securityContext )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Retrieve the definition of a remote by specific url" )
    @ApiResponses( { @ApiResponse( code = 200, response = ArtifactStore.class, message = "The store definition" ),
            @ApiResponse( code = 404, message = "The remote repository doesn't exist" ), } )
    @Path( "/query/byUrl" )
    @GET
    public Response getRemoteByUrl( final @PathParam( "packageType" ) String packageType,
                                    final @ApiParam( allowableValues = "remote", required = true )
                                    @PathParam( "type" ) String type, final @QueryParam( "url" ) String url,
                                    @Context final HttpServletRequest request,
                                    final @Context SecurityContext securityContext )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Revalidation of Artifacts Stored on demand" )
    @ApiResponses( { @ApiResponse( code = 200, response = ArtifactStore.class,
                                   message = "Revalidation for Remote Repositories was successfull" ),
            @ApiResponse( code = 404, message = "Revalidation is not successfull" ), } )
    @Path( "/revalidate/all/" )
    @POST
    public Response revalidateArtifactStores( @PathParam( "packageType" ) String packageType,
                                              @PathParam( "type" ) String type )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Revalidation of Artifact Stored on demand based on package, type and name" )
    @ApiResponses( { @ApiResponse( code = 200, response = ArtifactStore.class,
                                   message = "Revalidation for Remote Repository was successfull" ),
            @ApiResponse( code = 404, message = "Revalidation is not successfull" ), } )
    @Path( "/{name}/revalidate" )
    @POST
    public Response revalidateArtifactStore( final @PathParam( "packageType" ) String packageType,
                                             final @ApiParam( allowableValues = "hosted,group,remote", required = true )
                                             @PathParam( "type" ) String type,
                                             final @ApiParam( required = true ) @PathParam( "name" ) String name )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Return All Invalidated Remote Repositories" )
    @ApiResponses( { @ApiResponse( code = 200, message = "Return All Invalidated Remote Repositories" ) } )
    @Path( "/invalid/all" )
    @GET
    public Response returnDisabledStores(
            final @ApiParam( required = true ) @PathParam( "packageType" ) String packageType,
            final @ApiParam( allowableValues = "remote", required = true ) @PathParam( "type" ) String type )
    {
        return Response.ok( "Not implemented" ).build();
    }
}
