package org.wahlzeit.model;

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
		assertClassInvariants(asSphericCoordinate(this));
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
		assertClassInvariants(asSphericCoordinate(this));
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
	 * @methodtype set
	 */
	public void setX(double myx) {
		//pre-condition
		assertValidValue(myx);
		x = myx;
	}
	
	/**
	 * @methodtype set
	 */
	public void setY(double myy) {
		//pre-condition
		assertValidValue(myy);
		y = myy;
	}
	
	/**
	 * @methodtype set
	 */
	public void setZ(double myz) {
		//pre-condition
		assertValidValue(myz);
		z = myz;
	}
	
	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate mycoordinate) throws NullPointerException {
		//pre-condition
		assertNotNull(mycoordinate);
		
		CartesianCoordinate newCoordinate = asCartesianCoordinate(mycoordinate);
		
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(newCoordinate));
		
		setX(newCoordinate.getX());
		setY(newCoordinate.getY());
		setZ(newCoordinate.getZ());
		
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		
		//post-conditions
		assertValidValue(x);
		assertValidValue(y);
		assertValidValue(z);
	}
	
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate coordinate2) throws NullPointerException {
		//pre-condition
		assertNotNull(coordinate2);
		
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
		
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
		
		double xdiff = getXDistance(other);
		double ydiff = getYDistance(other);
		double zdiff = getZDistance(other);
		double d = Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff);
		double omega = 2 * Math.asin((d/2)/EARTHRADIUS);
		double distance = omega * EARTHRADIUS;
		
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
		
		//post-condition
		assert(distance >= 0 && distance < 20016);
		
		return distance;
	}
	
	/**
	 * @methodtype query
	 */
	public double getXDistance(Coordinate coordinate2) throws NullPointerException {
		//pre-condition
		assertNotNull(coordinate2);
		
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
		
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
		
		double distance = Math.abs(x - other.getX());
		
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
		
		//post-condition
		assert(distance >= 0);
		
		return distance;
	}
	
	/**
	 * @methodtype query
	 */
	public double getYDistance(Coordinate coordinate2) throws NullPointerException {
		//pre-condition
		assertNotNull(coordinate2);
			
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
				
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
				
		double distance = Math.abs(y - other.getY());
			
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
				
		//post-condition
		assert(distance >= 0);
			
		return distance;
	}
	
	/**
	 * @methodtype query
	 */
	public double getZDistance(Coordinate coordinate2) throws NullPointerException {
		//pre-condition
		assertNotNull(coordinate2);
				
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
				
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
				
		double distance = Math.abs(z - other.getZ());
			
		//valid class-invariants
		assertClassInvariants(asSphericCoordinate(this));
		assertClassInvariants(asSphericCoordinate(coordinate2));
		assertClassInvariants(asSphericCoordinate(other));
				
		//post-condition
		assert(distance >= 0);
				
		return distance;
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
	private void assertValidValue(double value) throws IllegalArgumentException {
		if (Double.isNaN(value)) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @methodtype boolean query
	 */
	@Override
	public boolean isEqual(Coordinate mycoordinate) {
		if (this == mycoordinate)
			return true;
		if (mycoordinate == null)
			return false;
		CartesianCoordinate other;
		if ((other = asCartesianCoordinate(mycoordinate)) != null) {
			if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
				return false;
			if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
				return false;
			if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
				return false;
			return true;
		}
		return false;
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
