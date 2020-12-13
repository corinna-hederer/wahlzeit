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
        this.assertClassInvariant();
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
        assertValidDouble(phi);
        this.phi = phi;
    }

    public void setTheta(double theta) {
        assertValidDouble(theta);
        this.theta = theta;
    }

    public void setRadius(double radius) {
        assertValidDouble(radius);
        this.radius = radius;
    }


    /**
     * Implements methods in interface Coordinate
     */

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius);
    }

    /**
     * Transforms spheric coordinate into cartesian coordinate
     * @methodtype command method
     * @return cartesianCoordinate cartesian coordinate object with x, y, and z component
     */

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariant();
        assertNotNull(this);
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
        assertClassInvariant();
        assertNotNull(this);
        return this;
    }

    /**
     * Get central angle between two spheric coordinates
     * @methodtype get
     * @return centralAngle great circle distance
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) throws ArithmeticException{
        assertClassInvariant();
        assertNotNull(this);
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(this.phi)) *
                Math.sin(Math.toRadians(sphericCoordinate.getPhi())) +
                Math.cos(Math.toRadians(this.phi)) *
                Math.cos(Math.toRadians(sphericCoordinate.getPhi())) *
                Math.cos(Math.toRadians(sphericCoordinate.getTheta() - this.theta))));
        return centralAngle;
    }


    @Override
    protected void assertClassInvariant() {
        assertValidDouble(this.phi);
        assertValidDouble(this.theta);
        assertValidDouble(this.radius);
    }

}
