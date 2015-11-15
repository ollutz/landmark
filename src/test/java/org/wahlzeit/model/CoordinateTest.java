package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {
	
	private SphericCoordinate location;
	private SphericCoordinate location2;
	private CartesianCoordinate carlocation;
	private CartesianCoordinate carlocation2;
	private static final double DELTA = 0.0001; 
	private static final double DELTA2 = 1; 

	@Before
	public void initPhotoFilter() {
		location = new SphericCoordinate();
		location2 = new SphericCoordinate(45, 90);		
		carlocation = AbstractCoordinate.asCartesianCoordinate(location);
		carlocation2 = AbstractCoordinate.asCartesianCoordinate(location2);
	}
	
	@Test
	public void testisEqual() {
		location.isEqual(carlocation);
		carlocation.isEqual(location);
		location2.isEqual(carlocation2);
		carlocation2.isEqual(location2);
	}
	
	@Test
	public void testgetDistance() {

		assertEquals(0, location.getDistance(carlocation), DELTA);
		assertEquals(0, carlocation.getDistance(location), DELTA);
		assertEquals(0, location2.getDistance(carlocation2), DELTA);
		assertEquals(0, carlocation2.getDistance(location2), DELTA);
		
		Coordinate Berlin = new SphericCoordinate(52.517, 13.40); //Berlin
		Coordinate Tokio = new SphericCoordinate(35.70, 139.767); //Tokio
		Coordinate NewYork = new SphericCoordinate(40.712778, -74.005833); //New York
		CartesianCoordinate carBerlin = AbstractCoordinate.asCartesianCoordinate((SphericCoordinate) Berlin);
		CartesianCoordinate carTokio = AbstractCoordinate.asCartesianCoordinate((SphericCoordinate) Tokio);
		CartesianCoordinate carNewYork = AbstractCoordinate.asCartesianCoordinate((SphericCoordinate) NewYork);
		
		assertEquals(8918, Berlin.getDistance(Tokio), DELTA2);
		assertEquals(8918, Berlin.getDistance(carTokio), DELTA2);
		assertEquals(8918, Tokio.getDistance(carBerlin), DELTA2);
		assertEquals(8918, carBerlin.getDistance(Tokio), DELTA2);
		assertEquals(8918, carTokio.getDistance(Berlin), DELTA2);
		assertEquals(8918, carBerlin.getDistance(carTokio), DELTA2);
		assertEquals(8918, carTokio.getDistance(carBerlin), DELTA2);
				
		assertEquals(6385, Berlin.getDistance(NewYork), DELTA2);
		assertEquals(6385, Berlin.getDistance(carNewYork), DELTA2);
		assertEquals(6385, NewYork.getDistance(carBerlin), DELTA2);
		assertEquals(6385, carNewYork.getDistance(Berlin), DELTA2);
		assertEquals(6385, carBerlin.getDistance(NewYork), DELTA2);
		assertEquals(6385, carNewYork.getDistance(carBerlin), DELTA2);
		assertEquals(6385, carBerlin.getDistance(carNewYork), DELTA2);
		
		assertEquals(10844, Tokio.getDistance(NewYork), DELTA2);
		assertEquals(10844, Tokio.getDistance(carNewYork), DELTA2);
		assertEquals(10844, NewYork.getDistance(carTokio), DELTA2);
		assertEquals(10844, carTokio.getDistance(NewYork), DELTA2);
		assertEquals(10844, carNewYork.getDistance(Tokio), DELTA2);
		assertEquals(10844, carTokio.getDistance(carNewYork), DELTA2);
		assertEquals(10844, carNewYork.getDistance(carTokio), DELTA2);
	}	
}
