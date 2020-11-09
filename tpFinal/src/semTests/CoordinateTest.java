package semTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.Coordinate;

class CoordinateTest {
	private Coordinate coord;
	@BeforeEach
	void setUp(){
		coord = new Coordinate(-34.708447, -58.282332);
	}


	@Test
	void testGetX() {
		assertEquals(-34.708447, coord.getX());
	}

	@Test
	void testGetY() {
		assertEquals(-58.282332, coord.getY());
	}

}
