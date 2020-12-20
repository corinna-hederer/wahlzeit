package org.wahlzeit.model;


/**
 * A location represents the coordinates of a photo
 */

public class Location {

    public Coordinate coordinate;

    /**
     * Creates a Location instance using the argument cartesianCoordinate
     * @methodtype constructor
     */
    public Location(Coordinate coordinate){
        if(coordinate == null){
            throw new IllegalArgumentException("Coordinate is null");
        }
        this.coordinate = coordinate;
    }

}
