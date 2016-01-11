package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

public class LandmarkManager {
	
	protected static final LandmarkManager instance = new LandmarkManager();
	protected Map<String, LandmarkType> landmarkTypes = new HashMap<String, LandmarkType>();
	protected Map<Long, Landmark> landmarks = new HashMap<Long, Landmark>();
	
	public static final LandmarkManager getInstance() {
		return instance;
	}
	
	public Landmark createLandmark(String typeName) {
		LandmarkType lt = getLandmarkType(typeName);
		assert (lt != null) : "invalid LandmarkType name";
		Landmark result = lt.createInstance();
		landmarks.put(result.getId(), result);
		return result;
	}
	
	public LandmarkType getLandmarkType(String type) {
		return getLandmarkType(type, null);
	}
	
	public LandmarkType getLandmarkType(String type, String state) {
		LandmarkType ret = landmarkTypes.get(type+state);
		if (ret == null) {
			synchronized (landmarkTypes) {
				ret = landmarkTypes.get(type+state);
				if (ret == null) {
					ret = new LandmarkType(type, state);
					landmarkTypes.put(ret.toString(), ret);
				}
			}
		}
		return ret;
	}
	
}
