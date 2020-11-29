package org.wahlzeit.model;


/**
 * A location represents the coordinates of a photo
 */

public class Location {

    public CartesianCoordinate cartesianCoordinate;

    /**
     * Creates a Location instance using the argument cartesianCoordinate
     * @methodtype constructor
     */
    public Location(CartesianCoordinate cartesianCoordinate){
        this.cartesianCoordinate = cartesianCoordinate;
    }

}
