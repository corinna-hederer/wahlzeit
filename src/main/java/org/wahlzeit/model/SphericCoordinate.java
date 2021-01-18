package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@PatternInstance(
        patternName = "Value Object",
        participants = {"ValueObject"},
        participantClasses = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
)

public class SphericCoordinate extends AbstractCoordinate{

    /**
     * @param phi latitude
     * @param theta longitude
     * @param radius radius or radial distance
     * @param ANGLE_MIN minimal double value of central angle in degrees
     * @param ANGLE_MAX maximal double value of central angle in degrees
     * @param sphericCoorinateMap concurrent hash map of spheric coordinates
     */
    private final double phi;
    private final double theta;
    private final double radius;

    private static double ANGLE_MIN = 0.0;
    private static double ANGLE_MAX = 360.0;

    //map to share coordinates
    private static ConcurrentHashMap<Integer, SphericCoordinate> sphericCoordinateMap = new ConcurrentHashMap<>();


    /**
     * Creates a SphericCoordinate instance using the arguments phi, theta and radius
     * @methodtype constructor
     * @param phi latitude
     * @param theta longitude
     * @param radius radius or radial distance
     */
    private SphericCoordinate(double phi, double theta, double radius) {
        assertValidDouble(phi);
        assertValidDouble(theta);
        assertValidDouble(radius);

        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertClassInvariant();
    }

    /**
     *  Gets or Creates Coordinate with given parameter
     *  Coordinate is immutable and shared
     * @methodtype helper
     * @param phi double
     * @param theta double
     * @param radius double
     * @return spheric coordinate
     */
    public static SphericCoordinate getOrCreateSphericCoordinate(double phi, double theta, double radius){
        SphericCoordinate sphericCoordinate = new SphericCoordinate(phi, theta, radius);
        int coordinateHash = sphericCoordinate.hashCode();
        synchronized (sphericCoordinateMap) {
            if (sphericCoordinateMap.get(coordinateHash) != null) {
                sphericCoordinateMap.put(coordinateHash, sphericCoordinate);
            }
            return sphericCoordinate;
        }
    }

    public static SphericCoordinate getOrCreateSphericCoordinate(){
        return SphericCoordinate.getOrCreateSphericCoordinate(0,0,0);
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
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getOrCreateCartesianCoordinate(x, y, z);
        assertClassInvariant();
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
    public double getCentralAngle(Coordinate coordinate){
        assertClassInvariant();
        assertNotNull(this);
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        double centralAngle = Math.toDegrees(Math.acos(Math.sin(Math.toRadians(this.phi)) *
                Math.sin(Math.toRadians(sphericCoordinate.getPhi())) +
                Math.cos(Math.toRadians(this.phi)) *
                Math.cos(Math.toRadians(sphericCoordinate.getPhi())) *
                Math.cos(Math.toRadians(sphericCoordinate.getTheta() - this.theta))));
        assertInRange(centralAngle, SphericCoordinate.ANGLE_MIN, SphericCoordinate.ANGLE_MAX);
        if(centralAngle < SphericCoordinate.ANGLE_MIN || centralAngle > SphericCoordinate.ANGLE_MAX){
            throw new ArithmeticException("Error in central angle calculation, angle out of range");
        }
        assertClassInvariant();
        return centralAngle;
    }


    @Override
    protected void assertClassInvariant() {
        assertValidDouble(this.phi);
        assertValidDouble(this.theta);
        assertValidDouble(this.radius);
    }

}
