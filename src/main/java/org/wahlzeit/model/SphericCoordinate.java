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

import org.wahlzeit.utils.Pattern;

@Pattern (name = "Template", participants = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"})

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
		
		//post-condition
		assertValidLatitude(latitude);
		assertValidLongitude(longitude);
		assertValidRadius(radius);
	}
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double mylatitude, double mylongitude) throws IllegalArgumentException {
		//pre-condition
		assertValidLatitude(mylatitude);
		assertValidLongitude(mylongitude);
		
		latitude = mylatitude;
		longitude = mylongitude;
		radius = EARTHRADIUS;
		
		//post-condition
		assertValidLatitude(latitude);
		assertValidLongitude(longitude);
		assertValidRadius(radius);
	}
	
	/**
	 * @methodtype constructor
	 */
	public SphericCoordinate(double mylatitude, double mylongitude, double myradius) throws IllegalArgumentException {
		//pre-conditions
		assertValidLatitude(mylatitude);
		assertValidLongitude(mylongitude);
		assertValidRadius(myradius);
		
		latitude = mylatitude;
		longitude = mylongitude;
		radius = myradius;
		
		//post-conditions
		assertValidLatitude(latitude);
		assertValidLongitude(longitude);
		assertValidRadius(radius);
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
	 * @methodtype assertion
	 */
	private void assertValidLatitude(double mylatitude) throws IllegalArgumentException {
		assertValidValue(mylatitude);
		if (mylatitude <= -90 || mylatitude > 90 || Double.isNaN(mylatitude)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertValidLongitude(double mylongitude) throws IllegalArgumentException {
		assertValidValue(mylongitude);
		if (mylongitude <= -180 || mylongitude > 180) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertValidRadius(double myradius) throws IllegalArgumentException {
		assertValidValue(myradius);
		if (myradius < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private void assertValidValue(double myvalue) throws IllegalArgumentException {
		if (Double.isNaN(myvalue)) {
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
