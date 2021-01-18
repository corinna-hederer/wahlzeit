package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;


@PatternInstance(
        patternName = "Bridge",
        participants = {"Implementor"},
        participantClasses = {"AbstractCoordinate", "CartesianCoordinate", "Coordinate", "SphericCoordinate"}
)

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
        assertClassInvariant();
        assertNotNull(this);
        return this.asCartesianCoordinate().hashCode();
    }


    /**
     * Compares equality of two coordinates by using a maximal derivation of 0.00001
     * @methodtype boolean query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        this.assertNotNull(coordinate);
        if (this == coordinate){
            return true;
        }
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        return cartesianCoordinate.isEqual(this);
    }


    /**
     * computes the direct cartesian distance of two coordinates
     * @methodtype calculation
     * @return distance between the two coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate){
        this.assertNotNull(coordinate);
        return coordinate.asCartesianCoordinate().getCartesianDistance(this);
    }

    /**
     * Get central angle between two spheric coordinates
     * @methodtype calculation
     * @return centralAngle great circle distance
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        this.assertNotNull(coordinate);
        return coordinate.asSphericCoordinate().getCentralAngle(this);
    }


    protected abstract void assertClassInvariant();

    /**
     * Precondition
     * Check if object is null
     * @param o Object
     * @methodtype condition
     */
    public void assertNotNull(Object o) {
        if(o == null){
            throw new NullPointerException("Object is null");
        }
    }

    /**
     * Precondition
     * Check if double values are valid
     * @param d double value
     * @methodtype condition
     */
    protected void assertValidDouble(double d) {
        if(!Double.isFinite(d)) {
            throw new IllegalArgumentException("Double argument is NaN or infinity");
        }
    }

    /**
     * Precondition
     * Test if double values are NaN
     * @param d double value
     * @methodtype condition
     */
    public void assertIsNaN(double d) {
        if(Double.isNaN(d)){
            throw new NumberFormatException("Double value is NaN");
        }
    }

    /**
     * Precondition
     * Test if double values are negative
     * @param d double value
     * @methodtype condition
     */
    public void assertNotNegative(double d) {
        assertValidDouble(d);
        if(d < 0.0){
            throw new IllegalArgumentException("Double value is negative");
        }
    }

    /**
     * Precondition
     * Test if double value is in defined range of values
     * @param d double value
     * @methodtype condition
     */
    public void assertInRange(double d, double min, double max){
        assertValidDouble(d);
        if(d < min || d > max){
            throw new IllegalArgumentException("Double value out of defined range");
        }
    }

}
