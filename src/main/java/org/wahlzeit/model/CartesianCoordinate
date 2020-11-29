package org.wahlzeit.model;

import java.util.Objects;

/**
 * A cartesianCoordinate represents the exact position in a three-dimensional space where a photo was taken
 */

public class CartesianCoordinate implements Coordinate{

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

    public double setxCoordinate() {
        return xCoordinate;
    }

    public double setyCoordinate() {
        return yCoordinate;
    }

    public double setzCoordinate() {
        return zCoordinate;
    }


    /**
     * computes the direct cartesian distance of two coordinates
     * @methodtype calculation
     * @param c other cartesianCoordinate to which distance should be calculated
     * @return distance between the two coordinates
     */

    public double getDistance(CartesianCoordinate c){
        double distance = Math.sqrt(Math.pow((c.getxCoordinate() - this.xCoordinate), 2) +
                Math.pow((c.getyCoordinate() - this.yCoordinate), 2) +
                Math.pow((c.getzCoordinate() - this.zCoordinate), 2) );
        return distance;
    }


    /**
     * compares two coordinates in order to determine if they are equal
     * @methodtype boolean-query
     * @param c other cartesianCoordinate with which comparison is made
     * @return true, if coordinates are equal; false if they are not
     */

    public boolean isEqual(CartesianCoordinate c) {
        Double x = c.getxCoordinate();
        Double y = c.getyCoordinate();
        Double z = c.getzCoordinate();
        return (x.compareTo(xCoordinate) == 0 && y.compareTo(yCoordinate) == 0 && z.compareTo(zCoordinate) == 0);
    }

    /**
     * Override of equals() to verify if given and current objects are the same
     * @methodtype comparison
     * @param o type Object
     * @return true if object equals current instance
     */

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if(o == null) return false;
        if (!(o instanceof CartesianCoordinate)) {
            return false;
        }

        CartesianCoordinate c = (CartesianCoordinate) o;
        return isEqual(c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate, zCoordinate);
    }

    /**
     * Implements methods in interface Coordinate
     */

    /**
     * Interpret coordinate as cartesian coordinate
     * @methodtype conversion
     * @return cartesian coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Interpret coordinate as cartesian and get distance between two of them
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return this.getDistance(coordinate.asCartesianCoordinate());
    }

    /**
     * Transforms cartesian coordinate into spheric coordinate
     * @methodtype command method
     * @return sphericCoordinate spheric coordinate object with phi, theta and radius component
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(this.xCoordinate, 2) + Math.pow(this.yCoordinate, 2) +
                Math.pow(this.zCoordinate, 2));
        double theta = Math.acos(this.zCoordinate / radius);
        double phi = Math.atan(this.yCoordinate / this.xCoordinate);
        SphericCoordinate sphericCoordinate = new SphericCoordinate(phi, theta, radius);
        return sphericCoordinate;
    }

    /**
     * Transform cartesian coordinate into spheric coordinate and get central angle between two of these
     * @methodtype get
     * @return centralAngle great circle distance
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return this.asSphericCoordinate().getCentralAngle(coordinate);
    }

    /**
     * Compares equality of two coordinates by using a maximal derivation of 0.00001
     * @methodtype boolean query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        double tolerance = 0.00001;
        boolean x_equals = Math.abs(cartesianCoordinate.getxCoordinate() - this.xCoordinate) <= tolerance;
        boolean y_equals = Math.abs(cartesianCoordinate.getyCoordinate() - this.yCoordinate) <= tolerance;
        boolean z_equals = Math.abs(cartesianCoordinate.getzCoordinate() - this.zCoordinate) <= tolerance;
        return x_equals && y_equals && z_equals;
    }
}

