package org.wahlzeit.model;

import java.util.Objects;

public abstract class AbstractCoordinate implements Coordinate{

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
        return super.hashCode();  
    }


    /**
     * computes the direct cartesian distance of two coordinates
     * @methodtype calculation
     * @return distance between the two coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
        CartesianCoordinate otherCartesianCoordinate = coordinate.asCartesianCoordinate();
        double distance = Math.sqrt(Math.pow((otherCartesianCoordinate.getxCoordinate() - cartesianCoordinate.getxCoordinate()), 2) +
                Math.pow((otherCartesianCoordinate.getyCoordinate() - cartesianCoordinate.getyCoordinate()), 2) +
                Math.pow((otherCartesianCoordinate.getzCoordinate() - cartesianCoordinate.getzCoordinate()), 2) );
        return distance;
    }

    /**
     * Get central angle between two spheric coordinates
     * @methodtype calculation
     * @return centralAngle great circle distance
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = this.asSphericCoordinate();
        SphericCoordinate otherSphericCoordinate = coordinate.asSphericCoordinate();
        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(sphericCoordinate.getPhi())) *
                Math.sin(Math.toRadians(otherSphericCoordinate.getPhi())) +
                Math.cos(Math.toRadians(sphericCoordinate.getPhi())) *
                        Math.cos(Math.toRadians(otherSphericCoordinate.getPhi())) *
                        Math.cos(Math.toRadians(otherSphericCoordinate.getTheta() - sphericCoordinate.getTheta()))));
        return centralAngle;
    }


    /**
     * Compares equality of two coordinates by using a maximal derivation of 0.00001
     * @methodtype boolean query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();
        CartesianCoordinate otherCartesianCoordinate = coordinate.asCartesianCoordinate();

        double tolerance = 0.00001;
        boolean x_equals = Math.abs(otherCartesianCoordinate.getxCoordinate() - cartesianCoordinate.getxCoordinate()) <= tolerance;
        boolean y_equals = Math.abs(otherCartesianCoordinate.getyCoordinate() - cartesianCoordinate.getyCoordinate()) <= tolerance;
        boolean z_equals = Math.abs(otherCartesianCoordinate.getzCoordinate() - cartesianCoordinate.getzCoordinate()) <= tolerance;
        return x_equals && y_equals && z_equals;
    }
}
