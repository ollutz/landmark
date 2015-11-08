/**
* CoordinateTest
*
* Version 1
*
* 25.10.15
*
* Oliver Lutz
*/

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SphericCoordinateTest {
	
	private SphericCoordinate location;
	private SphericCoordinate location2;
	private static final double DELTA = 0.0001; 
	private static final double DELTA2 = 1; 

	@Before
	public void initPhotoFilter() {
		location = new SphericCoordinate();
		location2 = new SphericCoordinate(45, 90);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(location);	
		assertNotNull(location2);
		
		assertEquals(0, location.getLatitude(), DELTA);
		assertEquals(0, location.getLongitude(), DELTA);
		assertEquals(45, location2.getLatitude(), DELTA);
		assertEquals(90, location2.getLongitude(), DELTA);
		
		boolean thrown = false;
		try {
			location = new SphericCoordinate(0, Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(Double.NaN, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(0, -180);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(0, 181);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(91, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(-90, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(91, -180);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new SphericCoordinate(91, Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testAttributes() {
		location.setLatitude(45);
		assertEquals(45, location.getLatitude(), DELTA);
		location.setLongitude(90);
		assertEquals(90, location.getLongitude(), DELTA);
		location.setCoordinate(new SphericCoordinate(0,0));
		assertEquals(0, location.getLatitude(), DELTA);
		assertEquals(0, location.getLongitude(), DELTA);
	}
	
	@Test
	public void testSetter() {
		boolean thrown = false;
		try {
			location.setLatitude(Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setLongitude(Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(0, Double.NaN));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(Double.NaN, 0));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(Double.NaN, Double.NaN));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setLatitude(91);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setLatitude(-90);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setLongitude(181);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setLongitude(-180);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	 
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(91, 0));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(-90, 0));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	 
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(0, 181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(0, -180));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	 
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(91, 181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(-90, 181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(91, -180));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new SphericCoordinate(-90, -180));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testgetLatitudinalDistance() {
		location.setCoordinate(new SphericCoordinate(0,0));
		location2.setCoordinate(new SphericCoordinate(45,90));
		assertEquals(45, location.getLatitudinalDistance(location2), DELTA);
		assertEquals(45, location2.getLatitudinalDistance(location), DELTA);
		
		location.setCoordinate(new SphericCoordinate(45,90));
		assertEquals(0, location.getLatitudinalDistance(location2), DELTA);
		assertEquals(0, location2.getLatitudinalDistance(location), DELTA);
		
		location.setCoordinate(new SphericCoordinate(-89,-179));
		location2.setCoordinate(new SphericCoordinate(90,180));
		assertEquals(1, location.getLatitudinalDistance(location2), DELTA);
		assertEquals(1, location2.getLatitudinalDistance(location), DELTA);
		
		location.setCoordinate(new SphericCoordinate(-45,-90));
		location2.setCoordinate(new SphericCoordinate(50,100));
		assertEquals(85, location.getLatitudinalDistance(location2), DELTA);
		assertEquals(85, location2.getLatitudinalDistance(location), DELTA);
		
		boolean thrown = false;
		try {
			location.getLatitudinalDistance(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testgetLongitudinalDistance() {
		location.setCoordinate(new SphericCoordinate(0,0));
		location2.setCoordinate(new SphericCoordinate(45,90));
		assertEquals(90, location.getLongitudinalDistance(location2), DELTA);
		assertEquals(90, location2.getLongitudinalDistance(location), DELTA);
		
		location.setCoordinate(new SphericCoordinate(45,90));
		assertEquals(0, location.getLongitudinalDistance(location2), DELTA);
		assertEquals(0, location2.getLongitudinalDistance(location), DELTA);
		
		location.setCoordinate(new SphericCoordinate(-89,-179));
		location2.setCoordinate(new SphericCoordinate(90,180));
		assertEquals(1, location.getLongitudinalDistance(location2), DELTA);
		assertEquals(1, location2.getLongitudinalDistance(location), DELTA);
		
		location.setCoordinate(new SphericCoordinate(-45,-90));
		location2.setCoordinate(new SphericCoordinate(50,100));
		assertEquals(170, location.getLongitudinalDistance(location2), DELTA);
		assertEquals(170, location2.getLongitudinalDistance(location), DELTA);
		
		boolean thrown = false;
		try {
			location.getLongitudinalDistance(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testgetDistance() {
		location.setCoordinate(new SphericCoordinate(52.517, 13.40)); //Berlin
		location2.setCoordinate(new SphericCoordinate(35.70, 139.767)); //Tokio
		Coordinate location3 = new SphericCoordinate(40.712778, -74.005833); //New York
		
		assertEquals(8918, location.getDistance(location2), DELTA2);
		assertEquals(8918, location2.getDistance(location), DELTA2);
				
		assertEquals(6385, location.getDistance(location3), DELTA2);
		assertEquals(6385, location3.getDistance(location), DELTA2);
		
		assertEquals(10844, location2.getDistance(location3), DELTA2);
		assertEquals(10844, location3.getDistance(location2), DELTA2);
		
		boolean thrown = false;
		try {
			location.getDistance(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}
