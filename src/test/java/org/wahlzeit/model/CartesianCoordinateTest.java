package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {
	private CartesianCoordinate location;
	private static final double DELTA = 0.0001; 
	private static final double DELTA2 = 1; 

	@Before
	public void initPhotoFilter() {
		location = new CartesianCoordinate();
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(location);
		
		assertEquals(6371, location.getX(), DELTA);
		assertEquals(0, location.getY(), DELTA);
		assertEquals(0, location.getZ(), DELTA);
		
		boolean thrown = false;
		try {
			location = new CartesianCoordinate(0, Double.NaN, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new CartesianCoordinate(Double.NaN, 0, 0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			location = new CartesianCoordinate(0, 0, Double.NaN);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test (expected = NullPointerException.class)
	public void testSetNullArgument() {
		location.setCoordinate(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testgetDistanceNullArgument() {
		location.getDistance(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testgetXDistanceNullArgument() {
		location.getXDistance(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testgetYDistanceNullArgument() {
		location.getYDistance(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testgetZDistanceNullArgument() {
		location.getZDistance(null);
	}
		
	@Test
	public void testgetDistance() {
		Coordinate Berlin = new SphericCoordinate(52.517, 13.40); //Berlin
		Coordinate Tokio = new SphericCoordinate(35.70, 139.767); //Tokio
		Coordinate NewYork = new SphericCoordinate(40.712778, -74.005833); //New York
		CartesianCoordinate carBerlin = AbstractCoordinate.asCartesianCoordinate((SphericCoordinate) Berlin);
		CartesianCoordinate carTokio = AbstractCoordinate.asCartesianCoordinate((SphericCoordinate) Tokio);
		CartesianCoordinate carNewYork = AbstractCoordinate.asCartesianCoordinate((SphericCoordinate) NewYork);
		
		assertEquals(8918, carBerlin.getDistance(carTokio), DELTA2);
		assertEquals(8918, carTokio.getDistance(carBerlin), DELTA2);
				
		assertEquals(6385, carNewYork.getDistance(carBerlin), DELTA2);
		assertEquals(6385, carBerlin.getDistance(carNewYork), DELTA2);
		
		assertEquals(10844, carTokio.getDistance(carNewYork), DELTA2);
		assertEquals(10844, carNewYork.getDistance(carTokio), DELTA2);
	}
}
