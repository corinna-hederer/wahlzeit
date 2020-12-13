package org.wahlzeit.model;

import java.util.Objects;

/**
 * A cartesianCoordinate represents the exact position in a three-dimensional space where a photo was taken
 */

public class CartesianCoordinate extends AbstractCoordinate{

    private double xCoordinate;
    private double yCoordinate;
    private double zCoordinate;

    /**
     * Creates a cartesianCoordinate instance using the arguments xCoordinate, yCoordinate and zCoordinate
     * @methodtype constructor
     * @param xCoordinate x component of cartesianCoordinate
     * @param yCoordinate y component of cartesianCoordinate
     * @param zCoordinate z component of cartesianCoordinate
     */

    public CartesianCoordinate(double xCoordinate, double yCoordinate, double zCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
        assertClassInvariant();
    }


    /**
     * Default CartesianCoordinate, whose x, y and z components are initialised with 0.0
     * @methodtype constructor
     */

    public CartesianCoordinate(){
        this.xCoordinate = 0.0;
        this.yCoordinate = 0.0;
        this.zCoordinate = 0.0;
    }


    /**
     * Getter methods for x, y, and z component
     * @methodtype get
     */

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public double getzCoordinate() {
        return zCoordinate;
    }


    /**
     * Setter methods for x, y, and z component
     * @methodtype set
     */

    public double setxCoordinate(double xCoordinate) {
        assertValidDouble(xCoordinate);
        return this.xCoordinate = xCoordinate;
    }

    public double setyCoordinate(double yCoordinate) {
        assertValidDouble(yCoordinate);
        return this.yCoordinate = yCoordinate;
    }

    public double setzCoordinate(double zCoordinate) {
        assertValidDouble(zCoordinate);
        return this.zCoordinate = zCoordinate;
    }


    /**
     * Implements methods in interface Coordinate
     */

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate, zCoordinate);
    }


    /**
     * Compares equality of two coordinates by using a maximal derivation of 0.00001
     * @methodtype boolean query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        this.assertNotNull(coordinate);
        if (this == coordinate) {
            return true;
        }
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        double tolerance = 0.00001;
        boolean x_equals = Math.abs(cartesianCoordinate.getxCoordinate() - this.xCoordinate) <= tolerance;
        boolean y_equals = Math.abs(cartesianCoordinate.getyCoordinate() - this.yCoordinate) <= tolerance;
        boolean z_equals = Math.abs(cartesianCoordinate.getzCoordinate() - this.zCoordinate) <= tolerance;
        return x_equals && y_equals && z_equals;
    }

    /**
     * Interpret coordinate as cartesian coordinate
     * @methodtype conversion
     * @return cartesian coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariant();
        assertNotNull(this);
        return this;
    }

    /**
     * Interpret coordinate as cartesian and get distance between two of them
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        assertClassInvariant();
        assertNotNull(this);
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
        CartesianCoordinate otherCartesianCoordinate = coordinate.asCartesianCoordinate();
        double distance = Math.sqrt(Math.pow((otherCartesianCoordinate.getxCoordinate() - cartesianCoordinate.getxCoordinate()), 2) +
                Math.pow((otherCartesianCoordinate.getyCoordinate() - cartesianCoordinate.getyCoordinate()), 2) +
                Math.pow((otherCartesianCoordinate.getzCoordinate() - cartesianCoordinate.getzCoordinate()), 2) );

        return distance;
    }


    /**
     * Transforms cartesian coordinate into spheric coordinate
     * @methodtype command method
     * @return sphericCoordinate spheric coordinate object with phi, theta and radius component
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariant();
        assertNotNull(this);
        if(xCoordinate == 0.0 && yCoordinate == 0.0 && zCoordinate == 0.0){
            SphericCoordinate isOrigin = new SphericCoordinate(0.0,0.0,0.0);
            return isOrigin;
        } else {
            double radius = Math.sqrt(Math.pow(this.xCoordinate, 2) + Math.pow(this.yCoordinate, 2) +
                    Math.pow(this.zCoordinate, 2));
            double theta = Math.acos(this.zCoordinate / radius);
            double phi = Math.atan(this.yCoordinate / this.xCoordinate);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(phi, theta, radius);
            return sphericCoordinate;
        }
    }


    @Override
    protected void assertClassInvariant() {
        assertValidDouble(this.xCoordinate);
        assertValidDouble(this.yCoordinate);
        assertValidDouble(this.zCoordinate);
    }
}

