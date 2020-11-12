package semTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import sem.Coordinate;
import sem.InspectorApp;
import sem.LocalParking;
import sem.MeasuredParkingZone;
import sem.Parking;
import sem.ParkingStore;

class MeasuredParkingZoneTest {
	private Coordinate mockParkingStoreLocation;
	private Coordinate mockCoordinate;
	private ParkingStore parkingStore;
	private Pair<Coordinate, Coordinate> boundaries;
	private MeasuredParkingZone parkingZone;
	private String description;

	@BeforeEach
	void setUp() {
		mockParkingStoreLocation = Mockito.mock(Coordinate.class);
		mockCoordinate = Mockito.mock(Coordinate.class);
		boundaries = Tuples.pair(mockCoordinate, mockCoordinate);
		description = "Zona de estacionamiento barrio Kolynos";
		parkingStore = Mockito.mock(ParkingStore.class);

		parkingZone = new MeasuredParkingZone(description, boundaries);
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
