package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

    /**
     * @param phi latitude
     * @param theta longitude
     * @param radius radius or radial distance
     */
    private double phi;
    private double theta;
    private double radius;

    /**
     * Creates a SphericCoordinate instance using the arguments phi, theta and radius
     * @methodtype constructor
     * @param phi latitude
     * @param theta longitude
     * @param radius radius or radial distance
     */
    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }


    /**
     * Getter methods for phi, theta and radius
     * @methodtype get
     */

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Setter methods for phi, theta, radius
     * @methodtype set
     */

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    

    /**
     * Implements methods in interface Coordinate
     */

    /**
     * Transforms spheric coordinate into cartesian coordinate
     * @methodtype command method
     * @return cartesianCoordinate cartesian coordinate object with x, y, and z component
     */

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = this.radius * Math.sin(this.theta) * Math.cos(this.phi);
        double y = this.radius * Math.sin(this.theta) * Math.sin(this.phi);
        double z = this.radius * Math.cos(this.theta);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
        return cartesianCoordinate;
    }


    /**
     * Interpret coordinate as spheric coordinate
     * @methodtype conversion
     * @return spheric coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }
}
