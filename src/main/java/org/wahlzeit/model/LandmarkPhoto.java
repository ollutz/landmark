package org.wahlzeit.model;

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
	private Landmark landmark;

	/**
	 * @methodtype constructor
	 */
	public LandmarkPhoto(PhotoId myId, Landmark lm) {
		super(myId);
		landmark = lm;
	}
	
	/**
	 * @methodtype get
	 */
	public Landmark getLandmark() {
		return landmark;
	}
	
	/**
	 * @methodtype set
	 */
	public void setLandmark(Landmark lm) {
		landmark = lm;
	}
}
