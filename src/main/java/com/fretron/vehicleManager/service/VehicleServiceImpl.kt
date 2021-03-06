package com.fretron.vehicleManager.service

import com.fretron.vehicleManager.exceptions.FretronException
import com.fretron.vehicleManager.exceptions.ResourceNotFoundException
import com.fretron.vehicleManager.model.Vehicle
import com.fretron.vehicleManager.repository.VehicleRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class VehicleServiceImpl
@Inject constructor(@Named("vehicleRepositoryImpl") private val vehicleRepository: VehicleRepository) {

    @Throws(FretronException::class)
    fun createVehicle(vehicle: Vehicle): Vehicle {
        if (vehicle.registrationNumber == null || vehicle.chassisType == null) {
            throw FretronException("Either Registration or Chassis Number is not provided")
        }
        vehicle.uuid = UUID.randomUUID().toString()
        return vehicleRepository.createVehicle(vehicle)
    }

    @Throws(FretronException::class)
    fun getVehicle(id: String): Vehicle? {
        return vehicleRepository.getVehicle(id)
    }

    @Throws(FretronException::class)
    fun getAllVehicles(): List<Vehicle> = vehicleRepository.getAllVehicles()

    @Throws(FretronException::class)
    fun updateVehicle(id: String, vehicle: Vehicle): Vehicle {
        if (id.isEmpty()) throw FretronException("Invalid vehicle id")
        else if (vehicle.registrationNumber == null || vehicle.chassisType == null) {
            throw FretronException("Either Registration Or Chassis Number is not provided")
        }
        val vehicleFromDB = vehicleRepository.getVehicle(id)
        vehicleFromDB?.uuid ?: throw ResourceNotFoundException("Vehicle not found at id : $id")
        return vehicleRepository.updateVehicle(id, vehicle)
    }

    @Throws(FretronException::class)
    fun deleteVehicle(id: String): Vehicle {
        if (id.isEmpty()) throw FretronException("Invalid Vehicle Id : $id")
        return vehicleRepository.deleteVehicle(id)
    }
}