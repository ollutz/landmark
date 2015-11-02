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
	private static final double EARTHRADIUS = 6371;

	/**
	 * @methodtype constructor
	 */
	public Coordinate() {
		latitude = 0;
		longitude = 0;
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
	}
	
	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}
		
	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
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
	 * 
	 */
	public double getDistance(Coordinate coordinate2) throws NullPointerException {
		if (coordinate2 == null) {
			throw new NullPointerException();
		}
		double phi1 = Math.toRadians(latitude);
		double phi2 = Math.toRadians(coordinate2.getLatitude());
		double deltaPhi = Math.toRadians(getLatitudinalDistance(coordinate2));
		double deltaLambda = Math.toRadians(getLongitudinalDistance(coordinate2));		

		double tmp = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double angle = 2*Math.asin(Math.sqrt(tmp));
		
		return Math.abs(EARTHRADIUS*angle);
	}
	
	/**
	 * 
	 */
	public double getLatitudinalDistance(Coordinate coordinate2) throws NullPointerException {
		if (coordinate2 == null) {
			throw new NullPointerException();
		}
		double tmp = Math.abs(latitude - coordinate2.getLatitude());
		if (tmp <= 90) {
			return tmp;
		} else {
			return (180 - tmp);
		}
	}
	
	/**
	 * 
	 */
	public double getLongitudinalDistance(Coordinate coordinate2) throws NullPointerException {
		if (coordinate2 == null) {
			throw new NullPointerException();
		}
		double tmp = Math.abs(longitude - coordinate2.getLongitude());
		if (tmp <= 180) {
			return tmp;
		} else {
			return (360 - tmp);
		}
	}
}
