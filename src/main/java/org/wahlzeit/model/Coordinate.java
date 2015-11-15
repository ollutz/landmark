package org.wahlzeit.model;

public interface Coordinate {
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate mycoordinate);
	
	/**
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate mycoordinate);
}
