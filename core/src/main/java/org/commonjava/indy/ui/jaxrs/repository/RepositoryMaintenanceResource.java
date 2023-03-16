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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.commonjava.indy.ui.jaxrs.IndyUIResources;
import org.jboss.resteasy.spi.HttpRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.commonjava.indy.ui.util.ApplicationContent.application_json;
import static org.commonjava.indy.ui.util.ApplicationContent.application_zip;

@Api( value = "Store Administration", description = "Resource for accessing and managing artifact store definitions" )
@Path( "/api/admin/stores/maint" )
@ApplicationScoped
public class RepositoryMaintenanceResource
        implements IndyUIResources
{

    @ApiOperation( "Retrieve a ZIP-compressed file containing all repository definitions." )
    @ApiResponses( { @ApiResponse( code = 200, response = File.class, message = "ZIP bundle" ),
            @ApiResponse( code = 500, message = "Repository files could not be found / accessed" ) } )
    @GET
    @Path( "/export" )
    @Produces( application_zip )
    public Response getRepoBundle()
    {
        return Response.ok( "Not implemented" ).build();
    }

    @ApiOperation(
            value = "Import a ZIP-compressed file containing repository definitions into the repository management database." )
    @ApiResponse( code = 200, message = "All repository definitions which are imported successfully." )
    @POST
    @Path( "/import" )
    @Consumes( application_zip )
    @Produces( application_json )
    public Response importRepoBundle( @Context final HttpRequest request )
    {
        return Response.ok( "Not implemented" ).build();
    }
}
