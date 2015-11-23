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
	 * @methodtype set
	 */
	public void setLatitude(double mylatitude) throws IllegalArgumentException {
		//pre-condition
		assertValidLatitude(mylatitude);
		latitude = mylatitude;
		//post-condition
		assertValidLatitude(latitude);
	}
	
	/**
	 * @methodtype set
	 */
	public void setLongitude(double mylongitude) throws IllegalArgumentException {
		//pre-condition
		assertValidLongitude(mylongitude);
		longitude = mylongitude;
		//post-condition
		assertValidLongitude(longitude);
	}
	
	/**
	 * @methodtype set
	 */
	public void setRadius(double myradius) {
		//pre-condition
		assertValidRadius(myradius);
		radius = myradius;
		//post-condition
		assertValidRadius(radius);
	}
	
	

	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate mycoordinate) throws IllegalArgumentException, NullPointerException {
		//pre-condition
		assertNotNull(mycoordinate);
		
		SphericCoordinate newCoordinate = asSphericCoordinate(mycoordinate);
		
		//valid class-invariants
		assertClassInvariants(newCoordinate);
		
		setLatitude(newCoordinate.getLatitude());
		setLongitude(newCoordinate.getLongitude());
		setRadius(newCoordinate.getRadius());
		
		//post-conditions
		assertValidLatitude(latitude);
		assertValidLongitude(longitude);
		assertValidRadius(radius);
	}
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate coordinate2) {
		//pre-condition
		assertNotNull(coordinate2);
		
		SphericCoordinate other = asSphericCoordinate(coordinate2);
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(other);
		
		double phi1 = Math.toRadians(latitude);
		double phi2 = Math.toRadians(((SphericCoordinate) other).getLatitude());
		double deltaPhi = Math.toRadians(getLatitudinalDistance(other));
		double deltaLambda = Math.toRadians(getLongitudinalDistance(other));		
		
		double tmp = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double angle = 2*Math.asin(Math.sqrt(tmp));
		
		double distance = Math.abs(radius*angle);
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(other);
		
		//post-condition
		assert(distance > 0 && distance < 20016);
		
		return distance;
	}
	
	
	/**
	 * @methodtype query
	 */
	public double getLatitudinalDistance(Coordinate coordinate2) {
		//pre-condition
		assertNotNull(coordinate2);
		
		SphericCoordinate other = asSphericCoordinate(coordinate2);
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(other);
		
		double tmp = Math.abs(latitude - other.getLatitude());
		if (tmp > 90) {
			tmp = (180 - tmp);
		}
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(other);
		
		//post-condition
		assert(tmp >= 0 && tmp <= 90);
		
		return tmp;
	}
	
	/**
	 * @methodtype query
	 */
	public double getLongitudinalDistance(Coordinate coordinate2) {
		//pre-condition
		assertNotNull(coordinate2);
		
		SphericCoordinate other = asSphericCoordinate(coordinate2);
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(other);
		
		double tmp = Math.abs(longitude - other.getLongitude());
		if (tmp > 180) {
			tmp = (360 - tmp);
		} 
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(other);
		
		//post-condition
		assert(tmp >= 0 && tmp <=180);
		
		return tmp;
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
