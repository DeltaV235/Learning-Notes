package com.deltav.entity;

import com.deltav.constant.VehicleStatus;
import com.deltav.exception.IllegalVehicleStatusException;

/**
 * @author DeltaV235
 * @version 1.0
 */
public abstract class VehicleAbstract implements Vehicle {
    protected int speed;
    protected VehicleStatus vehicleStatus;
    protected int distanceToDestination;
    protected Station destinationStation;

    protected void setDestination(Station destinationStation, int distanceToDestination) {
        this.destinationStation = destinationStation;
        this.distanceToDestination = distanceToDestination;

        vehicleStatus = VehicleStatus.ON_THE_WAY;
    }

    @Override
    public void move() {
        if (VehicleStatus.ON_THE_WAY != vehicleStatus) {
            throw new IllegalVehicleStatusException("Vehicle docked on station, cannot move!");
        }
        distanceToDestination -= speed;
        if (distanceToDestination <= 0) {
            vehicleStatus = VehicleStatus.DOCKED;
            postArrival();
        }
    }

    protected abstract void postArrival();

    public int getSpeed() {
        return speed;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public int getDistanceToDestination() {
        return distanceToDestination;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }
}
