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
	}
	
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double myx, double myy, double myz) {
		assertValidValue(myx);
		assertValidValue(myy);
		assertValidValue(myz);
		x = myx;
		y = myy;
		z = myz;
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
		assertValidValue(myx);
		x = myx;
	}
	
	/**
	 * @methodtype set
	 */
	public void setY(double myy) {
		assertValidValue(myy);
		y = myy;
	}
	
	/**
	 * @methodtype set
	 */
	public void setZ(double myz) {
		assertValidValue(myz);
		z = myz;
	}
	
	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate mycoordinate) throws NullPointerException {
		assertNotNull(mycoordinate);
		
		CartesianCoordinate newCoordinate = asCartesianCoordinate(mycoordinate);
		
		setX(newCoordinate.getX());
		setY(newCoordinate.getY());
		setZ(newCoordinate.getZ());
	}
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
		
		double xdiff = getXDistance(other);
		double ydiff = getYDistance(other);
		double zdiff = getZDistance(other);
		double d = Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff);
		double omega = 2 * Math.asin((d/2)/EARTHRADIUS);
		
		return omega * EARTHRADIUS;
	}
	
	/**
	 * @methodtype query
	 */
	public double getXDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
		
		return Math.abs(x - other.getX());
	}
	
	/**
	 * @methodtype query
	 */
	public double getYDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
		
		return Math.abs(y - other.getY());
	}
	
	/**
	 * @methodtype query
	 */
	public double getZDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		CartesianCoordinate other = asCartesianCoordinate(coordinate2);
		
		return Math.abs(z - other.getZ());	
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
