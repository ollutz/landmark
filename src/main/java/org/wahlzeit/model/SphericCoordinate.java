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

public class SphericCoordinate extends AbstractCoordinate {
	
	/**
	 * 
	 */
	private double latitude;
	private double longitude;
	private double radius; 

	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate() {
		latitude = 0;
		longitude = 0;
		radius = EARTHRADIUS;
	}
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double mylatitude, double mylongitude) throws IllegalArgumentException {
		assertValidLatitude(mylatitude);
		assertValidLongitude(mylongitude);
		latitude = mylatitude;
		longitude = mylongitude;
		radius = EARTHRADIUS;
	}
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double mylatitude, double mylongitude, double myradius) throws IllegalArgumentException {
		assertValidLatitude(mylatitude);
		assertValidLongitude(mylongitude);
		latitude = mylatitude;
		longitude = mylongitude;
		radius = myradius;
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
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLatitude(double mylatitude) throws IllegalArgumentException {
		assertValidLatitude(mylatitude);
		latitude = mylatitude;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLongitude(double mylongitude) throws IllegalArgumentException {
		assertValidLongitude(mylongitude);
		longitude = mylongitude;
	}
	
	/**
	 * @methodtype set
	 */
	public void setRadius(double myradius) {
		radius = myradius;
	}
	
	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate mycoordinate) throws IllegalArgumentException, NullPointerException {
		assertNotNull(mycoordinate);
		
		SphericCoordinate newCoordinate = asSphericCoordinate(mycoordinate);
		
		setLongitude(newCoordinate.getLongitude());
		setLatitude(newCoordinate.getLatitude());
		setRadius(newCoordinate.getRadius());
	}
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		SphericCoordinate other = asSphericCoordinate(coordinate2);
		
		double phi1 = Math.toRadians(latitude);
		double phi2 = Math.toRadians(((SphericCoordinate) other).getLatitude());
		double deltaPhi = Math.toRadians(getLatitudinalDistance(other));
		double deltaLambda = Math.toRadians(getLongitudinalDistance(other));		
		
		double tmp = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double angle = 2*Math.asin(Math.sqrt(tmp));
			
		return Math.abs(radius*angle);
	}
	
	/**
	 * @methodtype query
	 */
	public double getLatitudinalDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		SphericCoordinate other = asSphericCoordinate(coordinate2);
		double tmp = Math.abs(latitude - other.getLatitude());
		if (tmp <= 90) {
			return tmp;
		} else {
			return (180 - tmp);
		}
	}
	
	/**
	 * @methodtype query
	 */
	public double getLongitudinalDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		SphericCoordinate other = asSphericCoordinate(coordinate2);
		double tmp = Math.abs(longitude - other.getLongitude());
		if (tmp <= 180) {
			return tmp;
		} else {
			return (360 - tmp);
		}
	}

	/**
	 * @methodtype assertion
	 */
	private void assertNotNull(Coordinate myCoordinate) throws NullPointerException {
		if (myCoordinate == null) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertValidLatitude(double mylatitude) throws IllegalArgumentException {
		if (mylatitude <= -90 || mylatitude > 90 || Double.isNaN(mylatitude)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertValidLongitude(double mylongitude) throws IllegalArgumentException {
		if (mylongitude <= -180 || mylongitude > 180 || Double.isNaN(mylongitude)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SphericCoordinate other = (SphericCoordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	
	/**
	 * @methodtype comparison
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
