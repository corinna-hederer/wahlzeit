package org.wahlzeit.model;

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



    /**
     * Check if coordinate is not null
     * @param c Coordinate object
     * @methodtype condition
     */

    public void assertNotNull(Coordinate c) throws NullPointerException{
        assert c != null;
    }

    protected abstract void assertClassInvariant();


    /**
     * Check if double values are valid
     * @param d double value
     * @methodtype condition
     */

    protected void assertValidDouble(double d) {
        assert Double.isFinite(d);
    }

}
