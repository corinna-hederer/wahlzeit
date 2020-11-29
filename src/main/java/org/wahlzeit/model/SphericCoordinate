package org.wahlzeit.model;

import java.util.Objects;

public class SphericCoordinate implements Coordinate{

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
     * Override of equals() to verify if given and current objects are the same
     * @methodtype comparison
     * @param o type Object
     * @return true if object equals current instance
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SphericCoordinate that = (SphericCoordinate) o;
        return Double.compare(that.phi, phi) == 0 &&
                Double.compare(that.theta, theta) == 0 &&
                Double.compare(that.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius);
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
     * Transform spheric coordinate into cartesian coordinate and get distance of two cartesian coordinates
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate);
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

    /**
     * Get central angle between two spheric coordinates
     * @methodtype get
     * @return centralAngle great circle distance
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(this.phi)) *
                Math.sin(Math.toRadians(sphericCoordinate.getPhi())) +
                Math.cos(Math.toRadians(this.phi)) *
                Math.cos(Math.toRadians(sphericCoordinate.getPhi())) *
                Math.cos(Math.toRadians(sphericCoordinate.getTheta() - this.theta))));
        return centralAngle;
    }

    /**
     * Compares equality of two coordinates by using a maximal derivation of 0.00001
     * @methodtype boolean query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double tolerance = 0.00001;
        boolean phi_equals = Math.abs(sphericCoordinate.getPhi()-this.phi) <= tolerance;
        boolean theta_equals = Math.abs(sphericCoordinate.getTheta()-this.theta) <= tolerance;
        boolean radius_equals = Math.abs(sphericCoordinate.getRadius()-this.radius) <= tolerance;
        return phi_equals && theta_equals && radius_equals;

    }
}
