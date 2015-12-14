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
	private final double latitude;
	private final double longitude;
	private final double radius; 

	/**
	 * @methodtype constructor
	 */
	private SphericCoordinate() {
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
	private SphericCoordinate(double mylatitude, double mylongitude) throws IllegalArgumentException {
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
	private SphericCoordinate(double mylatitude, double mylongitude, double myradius) throws IllegalArgumentException {
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
	 * @methodtype comparison
	 */
	public static SphericCoordinate getInstance(double mylatitude, double mylongitude, double myradius) {
		SphericCoordinate tmp = new SphericCoordinate(mylatitude, mylongitude, myradius);
		int hashCode = tmp.hashCode();
		Coordinate ret = coordinateInstances.get(hashCode);
		
		if (ret == null) {
			synchronized (coordinateInstances) {
				ret = coordinateInstances.get(hashCode);
				if (ret == null) {
					ret = tmp;
					coordinateInstances.put(hashCode, ret);
				}
			}
		}
		
		return (SphericCoordinate) ret;
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
}
