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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.commonjava.indy.ui.util.ApplicationContent;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import static org.commonjava.indy.ui.util.ApplicationContent.application_json;

@Api( value = "Promote administration resource to manage configurations for content promotion." )
@Path( "/api/admin/promotion" )
@Produces( { application_json } )
public class PromoteAdminResource
        implements IndyUIResources
{


    @ApiOperation( "Reload all rules for promotion validation" )
    @ApiResponse( code = 200, message = "", response = Response.class )
    @Path( "/validation/reload/rules" )
    @PUT
    @Consumes( application_json )
    public Response reloadRules( final @Context HttpServletRequest servletRequest,
                                 final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Reload all rule-sets for promotion validation" )
    @ApiResponse( code = 200, message = "", response = Response.class )
    @Path( "/validation/reload/rulesets" )
    @PUT
    @Consumes( application_json )
    public Response reloadRuleSets( final @Context HttpServletRequest servletRequest,
                                    final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Reload all rules & rule-sets for promotion validation" )
    @ApiResponse( code = 200, message = "", response = Response.class )
    @Path( "/validation/reload/all" )
    @PUT
    @Consumes( application_json )
    public Response reloadAll( final @Context HttpServletRequest servletRequest,
                               final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get all rules' names" )
    @ApiResponses( { @ApiResponse( code = 200, response = Response.class,
                                   message = "All promotion validation rules' definition" ),
            @ApiResponse( code = 404, message = "No rules are defined" ) } )
    @Path( "/validation/rules/all" )
    @GET
    @Consumes( application_json )
    public Response getAllRules( final @Context HttpServletRequest servletRequest,
                                 final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get promotion rule by rule name" )
    @ApiResponses( { @ApiResponse( code = 200, response = Response.class,
                                   message = "The promotion validation rule definition" ),
            @ApiResponse( code = 404, message = "The rule doesn't exist" ) } )
    @Path( "/validation/rules/named/{name}" )
    @GET
    @Consumes( application_json )
    public Response getRule( final @PathParam( "name" ) String ruleName,
                             final @Context HttpServletRequest servletRequest,
                             final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get promotion rule-set by store key" )
    @ApiResponses( { @ApiResponse( code = 200, response = Response.class,
                                   message = "The promotion validation rule-set definition" ),
            @ApiResponse( code = 404, message = "The rule-set doesn't exist" ) } )
    @Path( "/validation/rulesets/all" )
    @GET
    @Consumes( application_json )
    public Response getAllRuleSets( final @Context HttpServletRequest servletRequest,
                                    final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get promotion rule-set by store key" )
    @ApiResponses( { @ApiResponse( code = 200, response = Response.class,
                                   message = "The promotion validation rule-set definition" ),
            @ApiResponse( code = 404, message = "The rule-set doesn't exist" ) } )
    @Path( "/validation/rulesets/storekey/{storeKey}" )
    @GET
    @Consumes( application_json )
    public Response getRuleSetByStoreKey( final @PathParam( "storeKey" ) String storeKey,
                                          final @Context HttpServletRequest servletRequest,
                                          final @Context SecurityContext securityContext,
                                          final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation( "Get promotion rule-set by name" )
    @ApiResponses( { @ApiResponse( code = 200, response = Response.class,
                                   message = "The promotion validation rule-set definition" ),
            @ApiResponse( code = 404, message = "The rule-set doesn't exist" ) } )
    @Path( "/validation/rulesets/named/{name}" )
    @GET
    @Consumes( application_json )
    public Response getRuleSetByName( final @PathParam( "name" ) String name,
                                      final @Context HttpServletRequest servletRequest,
                                      final @Context SecurityContext securityContext, final @Context UriInfo uriInfo )
    {
        return Response.ok( "Not implemented" ).build();
    }


}