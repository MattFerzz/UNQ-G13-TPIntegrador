package semTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.Coordinate;
import sem.MeasuredParkingZone;
import sem.ParkingStore;
import sem.SEM;

class MeasuredParkingZoneTest {
	private Coordinate mockParkingStoreLocation;
	private Coordinate mockCoordinate;
	private ParkingStore parkingStore;
	private Pair<Coordinate, Coordinate> boundaries;
	private MeasuredParkingZone parkingZone;
	private String description;
	private SEM mockSystem;

	@BeforeEach
	void setUp() {
		mockParkingStoreLocation = Mockito.mock(Coordinate.class);
		mockCoordinate = Mockito.mock(Coordinate.class);
		boundaries = Tuples.pair(mockCoordinate, mockCoordinate);
		description = "Zona de estacionamiento barrio Kolynos";
		parkingStore = Mockito.mock(ParkingStore.class);
		mockSystem = Mockito.mock(SEM.class);

		parkingZone = new MeasuredParkingZone(description, boundaries, mockSystem);
	}

	@Test
	void testRegisterAndGetParkingStore() {
		Mockito.when(parkingStore.getLocation()).thenReturn(mockParkingStoreLocation);

		parkingZone.registerParkingStore(mockParkingStoreLocation);
		assertEquals(parkingStore.getLocation(), parkingZone.getParkingStores().getFirst().getLocation());
	}


	@Test
	void testGetDescription() {
		assertEquals(description, parkingZone.getdesctiption());
	}

	@Test
	void testGetBoundaries() {
		assertEquals(boundaries, parkingZone.getBoundaries());
	}

}
