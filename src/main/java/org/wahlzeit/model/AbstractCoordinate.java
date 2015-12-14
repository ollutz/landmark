package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

public abstract class AbstractCoordinate implements Coordinate {
	
	protected static final double EARTHRADIUS = 6371;
	
	
	@Pattern (name = "Template", participants = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"})
	/**
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate mycoordinate) {
		//pre-condition
		assertNotNull(mycoordinate);
		
		if (Double.doubleToLongBits(this.getLatitude()) != Double.doubleToLongBits(mycoordinate.getLatitude()))
			return false;
		if (Double.doubleToLongBits(this.getLongitude()) != Double.doubleToLongBits(mycoordinate.getLongitude()))
			return false;
		if (Double.doubleToLongBits(this.getRadius()) != Double.doubleToLongBits(mycoordinate.getRadius()))
			return false;
		return true;
	}
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate coordinate2) {
		//pre-condition
		assertNotNull(coordinate2);
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(coordinate2);
		
		double phi1 = Math.toRadians(this.getLatitude());
		double phi2 = Math.toRadians(coordinate2.getLatitude());
		double deltaPhi = Math.toRadians(getLatitudinalDistance(coordinate2));
		double deltaLambda = Math.toRadians(getLongitudinalDistance(coordinate2));		
		
		double tmp = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double angle = 2*Math.asin(Math.sqrt(tmp));
		
		double distance = Math.abs(getRadius()*angle);
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(coordinate2);
		
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
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(coordinate2);
		
		double tmp = Math.abs(this.getLatitude() - coordinate2.getLatitude());
		if (tmp > 90) {
			tmp = (180 - tmp);
		}
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(coordinate2);
		
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
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(coordinate2);
		
		double tmp = Math.abs(this.getLongitude() - coordinate2.getLongitude());
		if (tmp > 180) {
			tmp = (360 - tmp);
		} 
		
		//valid class-invariants
		assertClassInvariants(this);
		assertClassInvariants(coordinate2);
		
		//post-condition
		assert(tmp >= 0 && tmp <=180);
		
		return tmp;
	}
	
	/**
	 * @methodtype assertion
	 */
	private static void assertNotNull(Coordinate myCoordinate) throws NullPointerException {
		if (myCoordinate == null) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private static void assertValidLatitude(double mylatitude) throws IllegalArgumentException {
		assertValidValue(mylatitude);
		if (mylatitude <= -90 || mylatitude > 90 || Double.isNaN(mylatitude)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private static void assertValidLongitude(double mylongitude) throws IllegalArgumentException {
		assertValidValue(mylongitude);
		if (mylongitude <= -180 || mylongitude > 180) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private static void assertValidRadius(double myradius) throws IllegalArgumentException {
		assertValidValue(myradius);
		if (myradius < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	private static void assertValidValue(double myvalue) throws IllegalArgumentException {
		if (Double.isNaN(myvalue)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */
	protected static void assertClassInvariants(Coordinate mycoordinate) throws IllegalStateException  {
		try {
			assertNotNull(mycoordinate);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException();
		}
		try {
			assertValidLatitude(mycoordinate.getLatitude());
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException();
		}
		try {
			assertValidLongitude(mycoordinate.getLongitude());
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException();
		}
		try {
			assertValidRadius(mycoordinate.getRadius());
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException();
		}
	}
}
