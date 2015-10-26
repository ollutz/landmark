/**
* Coordinate
*
* Version 1
*
* 25.10.15
*
* Oliver Lutz
*/

package org.wahlzeit.model;

public class Coordinate {
	
	/**
	 * 
	 */
	private double latitude;
	private double longitude;
	private boolean distance;

	/**
	 * @methodtype constructor
	 */
	public Coordinate() {
		latitude = 0;
		longitude = 0;
		distance = false;
	}
	
	/**
	 * @methodtype constructor
	 */
	public Coordinate(double mylatitude, double mylongitude) throws IllegalArgumentException {
		if (mylatitude < -90 || mylatitude > 90 || Double.isNaN(mylatitude)) {
			throw new IllegalArgumentException();
		}
		if (mylongitude < -180 || mylongitude > 180 || Double.isNaN(mylongitude)) {
			throw new IllegalArgumentException();
		}
		latitude = mylatitude;
		longitude = mylongitude;
		distance = false;
	}
	
	/**
	 * @methodtype constructor
	 * 
	 * This constructor is only used to create a Coordinate in method getDistance()
	 * because latitude and longitude can be greater than 90/180!
	 */
	private Coordinate(double mylatitude, double mylongitude, boolean mydistance) throws IllegalArgumentException {
		if (mylatitude > 180 || Double.isNaN(mylatitude)) {
			throw new IllegalArgumentException();
		}
		if (mylongitude > 360 || Double.isNaN(mylongitude)) {
			throw new IllegalArgumentException();
		}
		if (mydistance == false) {
			throw new IllegalArgumentException();
		}
		latitude = mylatitude;
		longitude = mylongitude;
		distance = mydistance;
	}
	
	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * 
	 */
	public boolean isDistance() {
		return distance;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLongitude(double mylongitude) throws IllegalArgumentException {
		if (mylongitude < -180 || mylongitude > 180 || Double.isNaN(mylongitude)) {
			throw new IllegalArgumentException();
		}
		longitude = mylongitude;
	}
	
	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate mycoordinate) throws IllegalArgumentException, NullPointerException {
		if (mycoordinate == null) {
			throw new NullPointerException();
		}
		setLongitude(mycoordinate.getLongitude());
		setLatitude(mycoordinate.getLatitude());
	}
	
	/**
	 * @methodtype set
	 */
	public void setLatitude(double mylatitude) throws IllegalArgumentException {
		if (mylatitude < -90 || mylatitude > 90 || Double.isNaN(mylatitude)) {
			throw new IllegalArgumentException();
		}
		latitude = mylatitude;
	}
	
	/**
	 * 
	 */
	public Coordinate getDistance(Coordinate coordinate2) throws IllegalArgumentException, NullPointerException {
		return new Coordinate(getLatitudinalDistance(coordinate2), getLongitudinalDistance(coordinate2), true);
	}
	
	/**
	 * 
	 */
	public double getLatitudinalDistance(Coordinate coordinate2) throws IllegalArgumentException, NullPointerException {
		if (coordinate2 == null) {
			throw new NullPointerException();
		}
		if (distance == true || coordinate2.isDistance() == true) {
			throw new IllegalArgumentException();
		}
		return (Math.abs(latitude - coordinate2.getLatitude()));
	}
	
	/**
	 * 
	 */
	public double getLongitudinalDistance(Coordinate coordinate2) throws IllegalArgumentException, NullPointerException {
		if (coordinate2 == null) {
			throw new NullPointerException();
		}
		if (distance == true || coordinate2.isDistance() == true) {
			throw new IllegalArgumentException();
		}
		return (Math.abs(longitude - coordinate2.getLongitude()));
	}
}
