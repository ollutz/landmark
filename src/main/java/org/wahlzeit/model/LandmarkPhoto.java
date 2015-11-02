package org.wahlzeit.model;

import java.util.Calendar;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class LandmarkPhoto extends Photo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6761837684553577495L;
	
	/**
	 * 
	 */
	private double landmarkHeight;
	private int yearOfManufacture;
	private String state;
	private Coordinate landmarkLocation;
	private double admissionPrice;

	/**
	 * @methodtype constructor
	 */
	public LandmarkPhoto() {
		super();
		landmarkHeight = 0;
		yearOfManufacture = 0;
		state = "";
		landmarkLocation = null;
		admissionPrice = 0;
	}
	
	/**
	 * @methodtype constructor
	 */
	public LandmarkPhoto(PhotoId myId) {
		super(myId);
		landmarkHeight = 0;
		yearOfManufacture = 0;
		state = "";
		landmarkLocation = null;
		admissionPrice = 0;
	}
	
	/**
	 * @methodtype constructor
	 */
	public LandmarkPhoto(PhotoId myId, double mylandmarkHeight, int myyearOfManufacture, String mystate, Coordinate mylandmarkLocation, double myadmissionPrice) throws IllegalArgumentException {
		super(myId);
		if (myyearOfManufacture > Calendar.getInstance().get(Calendar.YEAR) || myadmissionPrice < 0) {
			throw new IllegalArgumentException();
		}		
		landmarkHeight = mylandmarkHeight;
		yearOfManufacture = myyearOfManufacture;
		state = mystate;
		landmarkLocation = mylandmarkLocation;
		admissionPrice = myadmissionPrice;
	}
	
	/**
	 * @methodtype constructor
	 */
	public LandmarkPhoto(PhotoId myId, double mylandmarkHeight, int myyearOfManufacture, String mystate, double latitude, double longitude, double myadmissionPrice) throws IllegalArgumentException {
		super(myId);
		if (myyearOfManufacture > Calendar.getInstance().get(Calendar.YEAR) || myadmissionPrice < 0) {
			throw new IllegalArgumentException();
		}
		landmarkHeight = mylandmarkHeight;
		yearOfManufacture = myyearOfManufacture;
		state = mystate;
		landmarkLocation = new Coordinate(latitude, longitude);
		admissionPrice = myadmissionPrice;
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
	public void setLandmarkHeight(double mylandmarkHeight) {
		landmarkHeight = mylandmarkHeight;
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
	public void setYearOfManufacture(int myyearOfManufacture) throws IllegalArgumentException {
		assertIsYearOfManufactureValid(myyearOfManufacture);
		yearOfManufacture = myyearOfManufacture;
	}
	
	/**
	 * @methodtype get
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @methodtype set
	 */
	public void setState(String mystate) {
		state = mystate;
	}
	
	/**
	 * @methodtype get
	 */
	public Coordinate getLandmarkLocation() {
		return landmarkLocation;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLocation(double latitude, double longitude) throws IllegalArgumentException {
		landmarkLocation = new Coordinate(latitude, longitude);
	}
	
	/**
	 * @methodtype set
	 */
	public void setLocation(Coordinate mylandmarkLocation) {
		landmarkLocation = mylandmarkLocation;
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
	public void setAdmissionPrice(double myadmissionPrice) throws IllegalArgumentException {
		assertIsAdmissionPriceValid(myadmissionPrice);
		admissionPrice = myadmissionPrice;
	}
	
	/**
	 * @methodtype assertion
	 */
	public void assertIsYearOfManufactureValid(int myyearOfManufacture) throws IllegalArgumentException {
		if (myyearOfManufacture > Calendar.getInstance().get(Calendar.YEAR)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @methodtype assertion
	 */	
	public void assertIsAdmissionPriceValid(double myadmissionPrice) throws IllegalArgumentException {
		if (myadmissionPrice < 0) {
			throw new IllegalArgumentException("Admission price should not be negative");
		}
	}
	
}
