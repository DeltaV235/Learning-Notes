package com.deltav.entity;

import com.deltav.exception.OutOfVehicleException;

import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class Station {
    private String name;
    private int remainingBike;
    private int remainingTruck;
    private final Map<String, List<Path>> stationPathMap;

    private final List<Bike> usingBikeList;

    public Station(String name,
                   int remainingBike,
                   int remainingTruck,
                   Map<String, List<Path>> stationPathMap,
                   List<Bike> usingBikeList) {
        this.name = name;
        this.remainingBike = remainingBike;
        this.remainingTruck = remainingTruck;
        this.stationPathMap = stationPathMap;
        this.usingBikeList = usingBikeList;
    }

    public void releaseBike(Bike bike) {
        usingBikeList.remove(bike);
        remainingBike++;
    }

    public void releaseTruck() {
        remainingTruck++;
    }

    public Bike rentBike(int speed, Station destinationStation, int distanceToDestination) throws OutOfVehicleException {
        if (remainingBike <= 0) {
            throw new OutOfVehicleException("No remaining bike in station " + this.name);
        }
        remainingBike--;
        return new Bike(speed, destinationStation, distanceToDestination);
    }

    public void rentTruck() throws OutOfVehicleException {
        if (remainingTruck <= 0) {
            throw new OutOfVehicleException("No remaining truck in station " + this.name);
        }
        remainingTruck--;
    }

    public String getName() {
        return name;
    }

    public int getRemainingBike() {
        return remainingBike;
    }

    public int getRemainingTruck() {
        return remainingTruck;
    }

    public Map<String, List<Path>> getStationPathMap() {
        return stationPathMap;
    }

}
