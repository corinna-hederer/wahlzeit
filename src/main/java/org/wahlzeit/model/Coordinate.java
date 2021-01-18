package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
		patternName = "Bridge",
		participants = {"Abstraction"},
		participantClasses = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"}
)
public interface Coordinate{

	/**
	 * Formats cartesian coordinate as CartesianCoordinate
	 */
	public CartesianCoordinate asCartesianCoordinate();

	/**
	 * Compute distance between two cartesian coordinates
	 */
	public double getCartesianDistance(Coordinate coordinate);

	/**
	 * Formats spheric coordinate as SphericCoordinate
	 */
	public SphericCoordinate asSphericCoordinate();

	/**
	 * Compute central angle of two spheric coordinates
	 */
	public double getCentralAngle(Coordinate coordinate);

	/**
	 * Compares equality of two coordinates
	 */
	public boolean isEqual(Coordinate coordinate);

}
