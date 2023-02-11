package com.deltav.entity;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class Bike extends VehicleAbstract {

    public Bike(int speed, Station destinationStation, int distanceToDestination) {
        super.speed = speed;
        super.setDestination(destinationStation, distanceToDestination);
    }

    @Override
    protected void postArrival() {
        super.destinationStation.releaseBike(this);
    }
}
