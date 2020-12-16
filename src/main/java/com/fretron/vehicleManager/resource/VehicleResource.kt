package com.fretron.vehicleManager.resource

import com.fretron.vehicleManager.service.VehicleService
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject
import javax.inject.Named
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/vehicles/v1")
class VehicleResource @Inject constructor(
    @Named("objectMapper") private val objectMapper: ObjectMapper,
    @Named("vehicleServiceImpl") private val vehicleServiceImpl: VehicleService
) {

    @POST
    @Path("/vehicle")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun addVehicle(request: String): String {
        return Response.ok().toString()
    }

    @GET
    @Path("/vehicle")
    @Produces(MediaType.APPLICATION_JSON)
    fun getVehicle(@QueryParam("id") id: String): String {
        return Response.ok().toString()
    }

    @GET
    @Path("/vehicles")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllVehicles() : String {
        println("getAllVehicles :: ok")
        return Response.ok().toString()
    }

    @PUT
    @Path("/vehicle")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateVehicle(@QueryParam("id") id: String, request: String): String {
        return Response.ok().toString()
    }

    @DELETE
    @Path("/vehicle")
    @Produces(MediaType.APPLICATION_JSON)
    fun removeVehicle(@QueryParam("id") id: String): String {
        return Response.ok().toString()
    }

}