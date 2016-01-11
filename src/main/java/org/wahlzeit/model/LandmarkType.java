package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LandmarkType {
	
	private String type; // historical, religious, artistic landmark etc.
	private String state;
	
	private LandmarkType superType = null;
	private Set<LandmarkType> subTypes = new HashSet<LandmarkType>();
	
	/**
	 * @methodtype constructor
	 */
	public LandmarkType(String s) {
		this.type = null;
		this.state = s;
	}
	
	/**
	 * @methodtype constructor
	 */
	public LandmarkType(LandmarkType superType, String type, String s) {
		this.superType = superType;
		this.type = type;
		this.state = s;
	}
	
	/**
	 * @methodtype constructor
	 */
	public LandmarkType(String type, String s) {
		this.type = type;
		this.state = s;
	}
	
	/**
	 * @methodtype factory
	 */
	public Landmark createInstance() {
		return new Landmark(this);
	}
	
	/**
	 * @methodtype set
	 */
	public void setSuperTyp(LandmarkType lt) {
		this.superType = lt;
	}
	
	/**
	 * @methodtype get
	 */
	public LandmarkType getSuperType() {
		return superType;
	}
	
	/**
	 * @methodtype get
	 */
	public Iterator<LandmarkType> getSubTypeIterator() {
		return subTypes.iterator();
	}
	
	/**
	 * @methodtype set
	 */
	public void addSubType(LandmarkType lt) {
		assert (lt != null) : "tried to set null sub-type";
		subTypes.add(lt);
	}
	
	/**
	 * @methodtype query
	 */
	public boolean hasInstance(Landmark landmark) {
		assert (landmark != null) : "asked about null object";
		if (landmark.getType() == this) {
			return true;
		}
		
		for (LandmarkType type : subTypes) {
			if (type.hasInstance(landmark)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @methodtype get
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @methodtype set
	 */
	public void setType(String t) {
		type = t;
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
	public void setState(String st) {
		state = st;
	}
	
	/**
	 * @methodtype conversion
	 */
	public String toString() {
		return type+state;
	}
}
