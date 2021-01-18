package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A cartesianCoordinate represents the exact position in a three-dimensional space where a photo was taken
 */

@PatternInstance(
        patternName = "Value Object",
        participants = {"ValueObject"},
        participantClasses = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
)

public class CartesianCoordinate extends AbstractCoordinate{

    private final double xCoordinate;
    private final double yCoordinate;
    private final double zCoordinate;

    //map to share coordinates
    private static ConcurrentHashMap<Integer, CartesianCoordinate> cartesianCoordinateMap = new ConcurrentHashMap<>();

    /**
     * Creates a cartesianCoordinate instance using the arguments xCoordinate, yCoordinate and zCoordinate
     * @methodtype constructor
     * @param xCoordinate x component of cartesianCoordinate
     * @param yCoordinate y component of cartesianCoordinate
     * @param zCoordinate z component of cartesianCoordinate
     */

    private CartesianCoordinate(double xCoordinate, double yCoordinate, double zCoordinate){
        assertValidDouble(xCoordinate);
        assertValidDouble(yCoordinate);
        assertValidDouble(zCoordinate);

        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;

        assertClassInvariant();
    }


    /**
     *  Gets or Creates Coordinate with given parameter
     *  Coordinate is immutable and shared
     * @methodtype helper
     * @param xCoordinate double
     * @param yCoordinate double
     * @param zCoordinate double
     * @return cartesian coordinate
     */
    public static CartesianCoordinate getOrCreateCartesianCoordinate(double xCoordinate, double yCoordinate, double zCoordinate){
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(xCoordinate, yCoordinate, zCoordinate);
        int coordinateHash = cartesianCoordinate.hashCode();
        synchronized (cartesianCoordinateMap) {
            if (cartesianCoordinateMap.get(coordinateHash) != null) {
                cartesianCoordinateMap.put(coordinateHash, cartesianCoordinate);
            }
            return cartesianCoordinate;
        }
    }


    /**
     * Gets or creates cartesian coordinate whose x, y and z components are initialised with default parameter 0, 0, 0
     * @methodtype helper
     */

    public static CartesianCoordinate getOrCreateCartesianCoordinate(){
        return CartesianCoordinate.getOrCreateCartesianCoordinate(0,0,0);
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
        assertClassInvariant();
        if (this == coordinate) {
            return true;
        }
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        double tolerance = 0.00001;
        boolean x_equals = Math.abs(cartesianCoordinate.getxCoordinate() - this.xCoordinate) <= tolerance;
        boolean y_equals = Math.abs(cartesianCoordinate.getyCoordinate() - this.yCoordinate) <= tolerance;
        boolean z_equals = Math.abs(cartesianCoordinate.getzCoordinate() - this.zCoordinate) <= tolerance;
        assertClassInvariant();
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
        assertNotNegative(distance);
        if(distance < 0.0){
            throw new ArithmeticException("Error in cartesian distance calculation");
        }
        assertClassInvariant();
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
            SphericCoordinate isOrigin = SphericCoordinate.getOrCreateSphericCoordinate(0.0,0.0,0.0);
            return isOrigin;
        } else {
            double radius = Math.sqrt(Math.pow(this.xCoordinate, 2) + Math.pow(this.yCoordinate, 2) +
                    Math.pow(this.zCoordinate, 2));
            double theta = Math.acos(this.zCoordinate / radius);
            double phi = Math.atan(this.yCoordinate / this.xCoordinate);
            SphericCoordinate sphericCoordinate = SphericCoordinate.getOrCreateSphericCoordinate(phi, theta, radius);
            assertClassInvariant();
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
