package com.ostn.triangulation.request;

public class TriangulationRequest {
    private Coordinates coordinates;
    private double distance;

    public TriangulationRequest(Coordinates coordinates, double distance) {
        this.coordinates = coordinates;
        this.distance = distance;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "[" + coordinates.getX() + ","  + coordinates.getY() + "]";
    }
}
