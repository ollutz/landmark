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

public class CoordinateTest {
	
	private Coordinate location;
	private Coordinate location2;
	private static final double delta = 0.0001; 
	private static final double delta2 = 1; 

	@Before
	public void initPhotoFilter() {
		location = new Coordinate();
		location2 = new Coordinate(45, 90);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(location);	
		assertNotNull(location2);
		
		assertEquals(0, location.getLatitude(), delta);
		assertEquals(0, location.getLongitude(), delta);
		assertEquals(45, location2.getLatitude(), delta);
		assertEquals(90, location2.getLongitude(), delta);
		
		boolean thrown = false;
		try {
			location = new Coordinate(0, Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(Double.NaN, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(0, -181);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(0, 181);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(91, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(-91, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(91, -181);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new Coordinate(91, Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testAttributes() {
		location.setLatitude(45);
		assertEquals(45, location.getLatitude(), delta);
		location.setLongitude(90);
		assertEquals(90, location.getLongitude(), delta);
		location.setCoordinate(new Coordinate(0,0));
		assertEquals(0, location.getLatitude(), delta);
		assertEquals(0, location.getLongitude(), delta);
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
			location.setCoordinate(new Coordinate(0, Double.NaN));
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
			location.setCoordinate(new Coordinate(Double.NaN, 0));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(Double.NaN, Double.NaN));
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
			location.setLatitude(-91);
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
			location.setLongitude(-181);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	 
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(91, 0));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(-91, 0));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	 
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(0, 181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(0, -181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	 
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(91, 181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(-91, 181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(91, -181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location.setCoordinate(new Coordinate(-91, -181));
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testgetLatitudinalDistance() {
		location.setCoordinate(new Coordinate(0,0));
		location2.setCoordinate(new Coordinate(45,90));
		assertEquals(45, location.getLatitudinalDistance(location2), delta);
		assertEquals(45, location2.getLatitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(45,90));
		assertEquals(0, location.getLatitudinalDistance(location2), delta);
		assertEquals(0, location2.getLatitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(-90,-180));
		location2.setCoordinate(new Coordinate(89,179));
		assertEquals(1, location.getLatitudinalDistance(location2), delta);
		assertEquals(1, location2.getLatitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(-45,-90));
		location2.setCoordinate(new Coordinate(50,100));
		assertEquals(85, location.getLatitudinalDistance(location2), delta);
		assertEquals(85, location2.getLatitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(-90,-180));
		location2.setCoordinate(new Coordinate(90,180));
		assertEquals(0, location.getLatitudinalDistance(location2), delta);
		assertEquals(0, location2.getLatitudinalDistance(location), delta);
		
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
		location.setCoordinate(new Coordinate(0,0));
		location2.setCoordinate(new Coordinate(45,90));
		assertEquals(90, location.getLongitudinalDistance(location2), delta);
		assertEquals(90, location2.getLongitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(45,90));
		assertEquals(0, location.getLongitudinalDistance(location2), delta);
		assertEquals(0, location2.getLongitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(-90,-180));
		location2.setCoordinate(new Coordinate(89,179));
		assertEquals(1, location.getLongitudinalDistance(location2), delta);
		assertEquals(1, location2.getLongitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(-45,-90));
		location2.setCoordinate(new Coordinate(50,100));
		assertEquals(170, location.getLongitudinalDistance(location2), delta);
		assertEquals(170, location2.getLongitudinalDistance(location), delta);
		
		location.setCoordinate(new Coordinate(-90,-180));
		location2.setCoordinate(new Coordinate(90,180));
		assertEquals(0, location.getLongitudinalDistance(location2), delta);
		assertEquals(0, location2.getLongitudinalDistance(location), delta);
		
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
		location.setCoordinate(new Coordinate(52.517, 13.40)); //Berlin
		location2.setCoordinate(new Coordinate(35.70, 139.767)); //Tokio
		Coordinate location3 = new Coordinate(40.712778, -74.005833); //New York
		
		assertEquals(8918, location.getDistance(location2), delta2);
		assertEquals(8918, location2.getDistance(location), delta2);
				
		assertEquals(6385, location.getDistance(location3), delta2);
		assertEquals(6385, location3.getDistance(location), delta2);
		
		assertEquals(10844, location2.getDistance(location3), delta2);
		assertEquals(10844, location3.getDistance(location2), delta2);
		
		boolean thrown = false;
		try {
			location.getDistance(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}
