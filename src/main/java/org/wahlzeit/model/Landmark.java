package org.wahlzeit.model;

public class Landmark {
	
	protected LandmarkType landmarkType = null;
	
	protected static long ID = 0;
	
	private long id;
	
	private double landmarkHeight;
	private int yearOfManufacture;
	private Coordinate landmarkLocation;
	private double admissionPrice;
	
	/**
	 * @methodtype constructor
	 */
	public Landmark(LandmarkType lt) {
		landmarkType = lt;
		id = ID++;
	}
	
	/**
	 * @methodtype constructor
	 */
	public Landmark(LandmarkType lt, double lh, int yom, double mylatitude, double mylongitude, double myradius, double ap) {
		landmarkType = lt;
		landmarkHeight = lh;
		yearOfManufacture = yom;
		landmarkLocation = SphericCoordinate.getInstance(mylatitude, mylongitude, myradius);
		admissionPrice = ap;
		id = ID++;
	}
	
	/**
	 * @methodtype get
	 */
	public LandmarkType getType() {
		return landmarkType;
	}
	
	/**
	 * @methodtype set
	 */
	public void setType(LandmarkType lmt) {
		landmarkType = lmt;
	}
	
	/**
	 * @methodtype get
	 */
	public double getLandmarkHeight() {
		return landmarkHeight;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLandmarkHeight(double lh) {
		landmarkHeight = lh;
	}
	
	/**
	 * @methodtype get
	 */
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}
	
	/**
	 * @methodtype set
	 */
	public void setYearOfManufacture(int yom) {
		landmarkHeight = yom;
	}
	
	/**
	 * @methodtype get
	 */
	public Coordinate getLandmarkLoction() {
		return landmarkLocation;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLandmarkLoction(double mylatitude, double mylongitude, double myradius) {
		landmarkLocation = SphericCoordinate.getInstance(mylatitude, mylongitude, myradius);
	}
	
	/**
	 * @methodtype get
	 */
	public double getAdmissionPrice() {
		return admissionPrice;
	}
	
	/**
	 * @methodtype set
	 */
	public void setAdmissionPrice(double ap) {
		admissionPrice = ap;
	}
	
	/**
	 * @methodtype get
	 */
	public long getId() {
		return id;
	}
}
