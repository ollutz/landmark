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

public class SphericCoordinate implements Coordinate {
	
	/**
	 * 
	 */
	private double latitude;
	private double longitude;
	private double radius;
	private static final double EARTHRADIUS = 6371;
	private static final double DELTA = 10; 

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
	public void setCoordinate(SphericCoordinate mycoordinate) throws IllegalArgumentException, NullPointerException {
		assertNotNull(mycoordinate);
		setLongitude(mycoordinate.getLongitude());
		setLatitude(mycoordinate.getLatitude());
	}
	
	/**
	 * @methodtype query
	 */
	public double getDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		SphericCoordinate other;
		if ((other = asSphericCoordinate(coordinate2)) != null) {
			double phi1 = Math.toRadians(latitude);
			double phi2 = Math.toRadians(((SphericCoordinate) other).getLatitude());
			double deltaPhi = Math.toRadians(getLatitudinalDistance(other));
			double deltaLambda = Math.toRadians(getLongitudinalDistance(other));		
		
			double tmp = Math.sin(deltaPhi/2) * Math.sin(deltaPhi/2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
			double angle = 2*Math.asin(Math.sqrt(tmp));
				
			return Math.abs(EARTHRADIUS*angle);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype query
	 */
	public double getLatitudinalDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		SphericCoordinate other;
		if ((other = asSphericCoordinate(coordinate2)) != null) {
			double tmp = Math.abs(latitude - other.getLatitude());
			if (tmp <= 90) {
				return tmp;
			} else {
				return (180 - tmp);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype query
	 */
	public double getLongitudinalDistance(Coordinate coordinate2) throws NullPointerException {
		assertNotNull(coordinate2);
		SphericCoordinate other;
		if ((other = asSphericCoordinate(coordinate2)) != null) {
			double tmp = Math.abs(longitude - other.getLongitude());
			if (tmp <= 180) {
				return tmp;
			} else {
				return (360 - tmp);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @methodtype conversion
	 */
	public SphericCoordinate asSphericCoordinate(Coordinate mycoordinate) {
		assertNotNull(mycoordinate);
		if (mycoordinate instanceof SphericCoordinate) {
			return (SphericCoordinate) mycoordinate;
		} else if (mycoordinate instanceof CartesianCoordinate) {
			double x = ((CartesianCoordinate) mycoordinate).getX();
			double y = ((CartesianCoordinate) mycoordinate).getY();
			double z = ((CartesianCoordinate) mycoordinate).getZ();
			double r = Math.sqrt(x*x + y*y+ z*z);
			double lat = Math.toDegrees(Math.asin(z/r));
			double lon = Math.toDegrees(Math.atan2(y, x));
			SphericCoordinate ret = new SphericCoordinate(lat, lon);
			return ret;
		}
		return null;
	}
	
	/**
	 * @methodtype conversion
	 */
	public CartesianCoordinate asCartesianCoordinate() {
		double phi = Math.toRadians(getLatitude());
		double lambda = Math.toRadians(getLongitude());
		double xnew = EARTHRADIUS * Math.cos(phi) * Math.cos(lambda);
		double ynew = EARTHRADIUS * Math.cos(phi) * Math.sin(lambda);
		double znew = EARTHRADIUS * Math.sin(phi);
		CartesianCoordinate ret = new CartesianCoordinate(xnew, ynew, znew);
		return ret;
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
	public boolean isEqual(Coordinate mycoordinate) {
		if (this == mycoordinate)
			return true;
		if (mycoordinate == null)
			return false;
		SphericCoordinate other;
		if ((other = asSphericCoordinate(mycoordinate)) != null) {
			if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
				return false;
			if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
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
