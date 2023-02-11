package com.deltav.entity;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class Path {
    private String sourceStationName;
    private String destinationStationName;
    private int distance;

    public Path(String sourceStationName, String destinationStationName, int distance) {
        this.sourceStationName = sourceStationName;
        this.destinationStationName = destinationStationName;
        this.distance = distance;
    }

    public String getSourceStationName() {
        return sourceStationName;
    }

    public void setSourceStationName(String sourceStationName) {
        this.sourceStationName = sourceStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
