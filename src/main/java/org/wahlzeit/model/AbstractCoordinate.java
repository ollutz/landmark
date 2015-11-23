package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	
	protected static final double EARTHRADIUS = 6371;
	
	/**
	 * @methodtype boolean query
	 */
	public boolean isEqual(Coordinate mycoordinate) {
		//pre-condition
		assertNotNull(mycoordinate);
		
		SphericCoordinate coordinate1 = asSphericCoordinate(this);
		SphericCoordinate coordinate2 = asSphericCoordinate(mycoordinate);
		
		if (Double.doubleToLongBits(coordinate1.getLatitude()) != Double.doubleToLongBits(coordinate2.getLatitude()))
			return false;
		if (Double.doubleToLongBits(coordinate1.getLongitude()) != Double.doubleToLongBits(coordinate2.getLongitude()))
			return false;
		return true;
	}
	
	/**
	 * @methodtype query
	 */
	public abstract double getDistance(Coordinate mycoordinate);
	
	/**
	 * @methodtype conversion
	 */
	protected static SphericCoordinate asSphericCoordinate(Coordinate mycoordinate) {
		//pre-condition
		assertNotNull(mycoordinate);
		if (mycoordinate instanceof SphericCoordinate) {
			SphericCoordinate ret = (SphericCoordinate) mycoordinate;
			
			//valid class-invariants
			assertClassInvariants(ret);
			
			return ret;
		} else if (mycoordinate instanceof CartesianCoordinate) {
			double x = ((CartesianCoordinate) mycoordinate).getX();
			double y = ((CartesianCoordinate) mycoordinate).getY();
			double z = ((CartesianCoordinate) mycoordinate).getZ();
			double r = Math.sqrt(x*x + y*y+ z*z);
			double lat = Math.toDegrees(Math.asin(z/r));
			double lon = Math.toDegrees(Math.atan2(y, x));
			SphericCoordinate ret = new SphericCoordinate(lat, lon);
			
			//valid class-invariants
			assertClassInvariants(ret);
			
			return ret;
		} else {
			throw new IllegalArgumentException("Unknown coordinate instance");
		}
	}
	
	/**
	 * @methodtype conversion
	 */
	protected static CartesianCoordinate asCartesianCoordinate(Coordinate mycoordinate) throws NullPointerException {
		//pre-condition
		assertNotNull(mycoordinate);
		
		if (mycoordinate instanceof CartesianCoordinate) {
			CartesianCoordinate ret = (CartesianCoordinate) mycoordinate;
			
			//valid class-invariants
			assertClassInvariants(asSphericCoordinate(ret));
			
			return ret;
		} else if (mycoordinate instanceof SphericCoordinate) {
			double phi = Math.toRadians(((SphericCoordinate) mycoordinate).getLatitude());
			double lambda = Math.toRadians(((SphericCoordinate) mycoordinate).getLongitude());
			double radius = ((SphericCoordinate) mycoordinate).getRadius();
			double xnew = radius * Math.cos(phi) * Math.cos(lambda);
			double ynew = radius * Math.cos(phi) * Math.sin(lambda);
			double znew = radius * Math.sin(phi);
			CartesianCoordinate ret = new CartesianCoordinate(xnew, ynew, znew);

			//valid class-invariants
			assertClassInvariants(asSphericCoordinate(ret));
			
			return ret;
		} else {
			throw new IllegalArgumentException("Unknown coordinate instance");
		}
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
	protected static void assertClassInvariants(SphericCoordinate mycoordinate) throws IllegalStateException  {
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
