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
     * Transforms cartesian coordinate into spheric coordinate
     * @methodtype command method
     * @return sphericCoordinate spheric coordinate object with phi, theta and radius component
     */
    @Override
     public SphericCoordinate asSphericCoordinate() {
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
