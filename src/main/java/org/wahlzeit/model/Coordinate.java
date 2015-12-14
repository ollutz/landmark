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
	
	/**
	 * @methodtype get
	 */
	public double getLatitude();
	
	/**
	 * @methodtype get
	 */
	public double getLongitude();
		
	/**
	 * @methodtype get
	 */
	public double getRadius();
}
