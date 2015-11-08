package org.wahlzeit.model;

public class Location {
	
	/**
	 * 
	 */
	private String name;
	public Coordinate coordinate;
	
	/**
	 * @methodtype constructor
	 */
	public Location() {
		coordinate = null;
		name = null;
	}
	
	/**
	 * @methodtype constructor
	 */
	public Location(String myname) {
		name = myname;
		coordinate = null;
	}
	
	/**
	 * @methodtype constructor
	 */
	public Location(String myname, Coordinate mycoordinate) {
		name = myname;
		coordinate = mycoordinate;
	}
	
	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @methodtype set
	 */
	public void setName(String myname) {
		name = myname;
	}	
}
