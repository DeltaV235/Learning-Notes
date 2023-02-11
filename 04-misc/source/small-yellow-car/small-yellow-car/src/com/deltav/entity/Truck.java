package com.deltav.entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class Truck extends VehicleAbstract {
    private final List<Station> stationList;

    private String sourceStationName;
    private String destinationStationName;
    private String numberOfBike;

    public Truck(int speed, List<Station> stationList) {
        super.speed = speed;
        this.stationList = stationList;
    }

    @Override
    protected void postArrival() {
        super.destinationStation.releaseTruck();
    }

    private void calculateNextDestination() {
        int totalUnusedBike = 0;
        List<Integer> remainingBikeList = stationList.stream().map(Station::getRemainingBike).collect(Collectors.toList());
        // TODO implement calculate logic
    }

    public String getSourceStationName() {
        return sourceStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public String getNumberOfBike() {
        return numberOfBike;
    }
}
