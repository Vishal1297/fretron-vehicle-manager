package com.fretron.vehicleManager.resource

import com.fretron.vehicleManager.AppConstants
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.service.VehicleServiceImpl
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path(AppConstants.BASE_URL)
class VehicleResource @Inject constructor(
    private val objectMapper: ObjectMapper,
    private val vehicleServiceImpl: VehicleServiceImpl
) {

    @POST
    @Path(AppConstants.VEHICLE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun addVehicle(request: String): Response {
        val vehicle = vehicleServiceImpl.createVehicle(objectMapper.readValue(request, Vehicle::class.java))
        return if (vehicle.getRegistrationNumber() == null || vehicle.getChassisType() == null)
            Response.status(Response.Status.BAD_REQUEST).build()
        else
            Response.ok(vehicle.toString()).build()
    }

    @GET
    @Path(AppConstants.VEHICLE)
    @Produces(MediaType.APPLICATION_JSON)
    fun getVehicle(@QueryParam(AppConstants.UUID) uuid: String): Response {
        print("1 Resource")
        val vehicle = vehicleServiceImpl.getVehicle(uuid)
        println("Resource :: $vehicle")
        return if (vehicle.getRegistrationNumber() == null || vehicle.getChassisType() == null)
            Response.status(Response.Status.NOT_FOUND).build()
        else
            Response.ok(vehicle.toString()).build()
    }

    @GET
    @Path(AppConstants.VEHICLES)
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllVehicles(): Response {
        val vehicles = vehicleServiceImpl.getAllVehicles()
        return if (vehicles.isEmpty())
            Response.status(Response.Status.NOT_FOUND).build()
        else
            Response.ok(vehicles.toString()).build()
    }

    @PUT
    @Path(AppConstants.VEHICLES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateVehicle(@QueryParam(AppConstants.UUID) uuid: String, request: String): Response {
        val vehicle = vehicleServiceImpl.updateVehicle(uuid, objectMapper.readValue(request, Vehicle::class.java))
        return if (vehicle.getRegistrationNumber() == null || vehicle.getChassisType() == null)
            Response.status(Response.Status.NOT_MODIFIED).build()
        else
            Response.ok(vehicle.toString()).build()
    }

    @DELETE
    @Path(AppConstants.VEHICLE)
    @Produces(MediaType.APPLICATION_JSON)
    fun removeVehicle(@QueryParam(AppConstants.UUID) uuid: String): Response {
        val vehicle = vehicleServiceImpl.deleteVehicle(uuid)
        return if (vehicle.getRegistrationNumber() == null || vehicle.getChassisType() == null)
            Response.status(Response.Status.NOT_MODIFIED).build()
        else
            Response.ok(vehicle.toString()).build()
    }

}