package com.ostn.triangulation.request;

public class Coordinates {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
