package org.wahlzeit.model;

import java.util.Objects;

/**
 * A coordinate represents the exact position in a three-dimensional space where a photo was taken
 */

public class Coordinate {

    private double xCoordinate;
    private double yCoordinate;
    private double zCoordinate;

    /**
     * Creates a coordinate instance using the arguments xCoordinate, yCoordinate and zCoordinate
     * @methodtype constructor
     * @param xCoordinate x component of coordinate
     * @param yCoordinate y component of coordinate
     * @param zCoordinate z component of coordinate
     */

    public Coordinate(double xCoordinate, double yCoordinate, double zCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
    }


    /**
     * Default Coordinate, whose x, y and z components are initialised with 0.0
     * @methodtype constructor
     */

    public Coordinate(){
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
     * @param c other coordinate to which distance should be calculated
     * @return distance between the two coordinates
     */

    public double getDistance(Coordinate c){
        double distance = Math.sqrt(Math.pow((c.getxCoordinate() - this.xCoordinate), 2) +
                Math.pow((c.getyCoordinate() - this.yCoordinate), 2) +
                Math.pow((c.getzCoordinate() - this.zCoordinate), 2) );
        return distance;
    }


     /**
     * compares two coordinates in order to determine if they are equal
     * @methodtype boolean-query
     * @param c other coordinate with which comparison is made
     * @return true, if coordinates are equal; false if they are not
     */

    public boolean isEqual(Coordinate c) {
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
        if (!(o instanceof Coordinate)) {
            return false;
        }

        Coordinate c = (Coordinate) o;
        return isEqual(c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate, zCoordinate);
    }
}


