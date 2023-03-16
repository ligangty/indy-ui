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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.model.core.ArtifactStore;
import org.commonjava.indy.model.core.dto.SimpleBooleanResultDTO;
import org.commonjava.indy.model.core.dto.StoreListingDTO;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api( value = "Store Querying APIs", description = "Resource for querying artifact store definitions" )
@Path( "/api/admin/stores/query" )
@ApplicationScoped
public class RepositoryQueryResource
        implements IndyUIResources
{

    @ApiOperation( value = "Retrieve all repository definitions" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @Path( "/all" )
    @GET
    @Produces( APPLICATION_JSON )
    public Response getAll( @ApiParam( name = "packageType", value = "The package type of the repository.",
                                       example = "maven, npm, generic-http" ) @QueryParam( "packageType" )
                            final String packageType,
                            @ApiParam( name = "types", value = "The types of the repository. Split by comma",
                                       example = "\"remote, hosted\"" ) @QueryParam( "types" ) final String repoTypes,
                            @ApiParam( name = "enabled",
                                       value = "If the repositories retrieved are enabled, default is true if not specified",
                                       example = "true|false" ) @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve all remote repository definitions by specified package type" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/remotes/all" )
    @Produces( APPLICATION_JSON )
    public Response getAllRemoteRepositories(
            @ApiParam( name = "packageType", value = "The package type of the repository.",
                       example = "maven, npm, generic-http" ) @QueryParam( "packageType" ) final String packageType,
            @ApiParam( name = "enabled",
                       value = "If the repositories retrieved are enabled, default is true if not specified",
                       example = "true|false" ) @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve all hosted repository definitions by specified package type" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/hosteds/all" )
    @Produces( APPLICATION_JSON )
    public Response getAllHostedRepositories(
            @ApiParam( name = "packageType", value = "The package type of the repository.",
                       example = "maven, npm, generic-http" ) @QueryParam( "packageType" ) final String packageType,
            @ApiParam( name = "enabled",
                       value = "If the repositories retrieved are enabled, default is true if not specified",
                       example = "true|false" ) @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve all group definitions by specified package type" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/groups/all" )
    @Produces( APPLICATION_JSON )
    public Response getAllGroups( @ApiParam( name = "packageType", value = "The package type of the repository.",
                                             example = "maven, npm, generic-http" ) @QueryParam( "packageType" )
                                  final String packageType, @ApiParam( name = "enabled",
                                                                       value = "If the repositories retrieved are enabled, default is true if not specified",
                                                                       example = "true|false" ) @QueryParam( "enabled" )
                                  final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve all default package types" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/byDefaultPkgTypes" )
    @Produces( APPLICATION_JSON )
    public Response getAllByDefaultPackageTypes()
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve the first matched store with the given store name" )
    @ApiResponses( { @ApiResponse( code = 200, response = ArtifactStore.class, message = "The store definition" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/byName/{name}" )
    @Produces( APPLICATION_JSON )
    public Response getByName(
            @ApiParam( value = "Name of the repository", required = true ) @PathParam( "name" ) String name )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve the enabled groups whose constituents contains the specified store" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/groups/contains" )
    @Produces( APPLICATION_JSON )
    public Response getGroupsContaining(
            @ApiParam( value = "Key of the repository contained in the groups", required = true,
                       example = "maven:remote:central" ) @QueryParam( "storeKey" ) @Encoded final String storeKey,
            @ApiParam( value = "If the repositories retrieved are enabled, default is true if not specified",
                       example = "true" ) @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve the concrete stores which are constituents of the specified group" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The store definitions" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/concretes/inGroup" )
    @Produces( APPLICATION_JSON )
    public Response getOrderedConcreteStoresInGroup(
            @ApiParam( value = "Key of the group whom the repositories are contained in", required = true,
                       example = "maven:group:public" ) @QueryParam( "storeKey" ) @Encoded final String storeKey,
            @ApiParam( value = "If the repositories retrieved are enabled, default is true if not specified",
                       example = "true" ) @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve the stores which are constituents of the specified group" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class,
                                   message = "The stores definitions, include the master group itself" ),
            @ApiResponse( code = 404, message = "The stores are not found" ) } )
    @GET
    @Path( "/inGroup" )
    @Produces( APPLICATION_JSON )
    public Response getOrderedStoresInGroup(
            @ApiParam( value = "Key of the group whom the repositories are contained in", required = true,
                       example = "maven:group:public" ) @QueryParam( "storeKey" ) @Encoded final String storeKey,
            @ApiParam( value = "If the repositories retrieved are enabled, default is true if not specified",
                       example = "true" ) @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve the groups which are affected by the specified store keys" )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class, message = "The group definitions" ),
            @ApiResponse( code = 404, message = "The groups don't exist" ) } )
    @GET
    @Path( "/affectedBy" )
    @Produces( APPLICATION_JSON )
    public Response getGroupsAffectedBy(
            @ApiParam( value = "Store keys whom the groups are affected by, use \",\" to split", required = true,
                       example = "maven:remote:central,maven:hosted:local" ) @QueryParam( "keys" ) @Encoded
            final String keys )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Retrieve the remote repositories by package type and urls." )
    @ApiResponses( { @ApiResponse( code = 200, response = StoreListingDTO.class,
                                   message = "The remote repository definitions" ),
            @ApiResponse( code = 404, message = "The remote repositories don't exist" ) } )
    @GET
    @Path( "/remotes" )
    public Response getRemoteRepositoryByUrl( @QueryParam( "packageType" ) final String packageType,
                                              @QueryParam( "byUrl" ) final String url,
                                              @QueryParam( "enabled" ) final String enabled )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( value = "Check if there are no repository definitions." )
    @ApiResponse( code = 200, response = SimpleBooleanResultDTO.class,
                  message = "If there are repository definitions or not." )
    @GET
    @Path( "/isEmpty" )
    public Response getStoreEmpty()
    {
        return Response.ok( "Not implemented" ).build();
    }

}
