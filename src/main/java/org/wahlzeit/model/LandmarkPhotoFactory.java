package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class LandmarkPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(LandmarkPhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static LandmarkPhotoFactory instance = null;

	/**
	 *@methodtype constructor
	 */
	public LandmarkPhotoFactory() {
		// do nothing
	}

	/**
	 * Public singleton access method.
	 * 
	 * @methodtype get
	 */
	public static synchronized LandmarkPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic LandmarkPhotoFactory").toString());
			setInstance(new LandmarkPhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 * 
	 * @methodtype set
	 */
	public static synchronized void setInstance(LandmarkPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new LandmarkPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 * 
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId id) {
		return new LandmarkPhoto(id);
	}
	
	/**
	 * @methodtype factory
	 */
	public Photo createPhoto(PhotoId myId, double mylandmarkHeight, int myyearOfManufacture, String mystate, Coordinate mycoordinate, double myadmissionPrice)  {
		return new LandmarkPhoto(myId, mylandmarkHeight, myyearOfManufacture, mystate, mycoordinate, myadmissionPrice);
	}
}
