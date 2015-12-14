package org.wahlzeit.model;

import org.wahlzeit.utils.Pattern;

@Pattern (name = "Template", participants = {"AbstractCoordinate", "CartesianCoordinate", "SphericCoordinate"})

public class CartesianCoordinate extends AbstractCoordinate {
	/**
	 * 
	 */
	private double x;
	private double y;
	private double z;
	private static final double EARTHRADIUS = 6371;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		x = EARTHRADIUS;
		y = 0;
		z = 0;
		
		//valid class-invariants
		assertClassInvariants(this);
	}
	
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double myx, double myy, double myz) {
		//pre-conditions
		assertValidValue(myx);
		assertValidValue(myy);
		assertValidValue(myz);
		x = myx;
		y = myy;
		z = myz;
		
		//valid class-invariants
		assertClassInvariants(this);
	}
	
	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}
		
	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * @methodtype get
	 */
	@Override
	public double getLatitude() {
		double r = Math.sqrt(x*x + y*y+ z*z);
		double lat = Math.toDegrees(Math.asin(z/r));
		return lat;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getLongitude() {
		double lon = Math.toDegrees(Math.atan2(y, x));
		return lon;
	}

	/**
	 * @methodtype get
	 */
	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return EARTHRADIUS;
	}

	/**
	 * @methodtype assertion
	 */
	private void assertValidValue(double value) throws IllegalArgumentException {
		if (Double.isNaN(value)) {
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
		CartesianCoordinate other = (CartesianCoordinate) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
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
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
