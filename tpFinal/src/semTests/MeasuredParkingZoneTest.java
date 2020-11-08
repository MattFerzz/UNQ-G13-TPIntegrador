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
import sem.ParkingCardStore;

class MeasuredParkingZoneTest {
	private Coordinate mockParkingStoreLocation;
	private Coordinate mockCoordinate;
	private ParkingCardStore parkingStore;
	private Pair<Coordinate, Coordinate> boundaries;
	private MeasuredParkingZone parkingZone;
	private LocalParking mockParking;
	private LocalParking anotherMockParking;
	private String description;

	@BeforeEach
	void setUp() {
		mockParkingStoreLocation = Mockito.mock(Coordinate.class);
		mockCoordinate = Mockito.mock(Coordinate.class);
		boundaries = Tuples.pair(mockCoordinate, mockCoordinate);
		description = "Zona de estacionamiento barrio Kolynos";
		parkingStore = Mockito.mock(ParkingCardStore.class);

		parkingZone = new MeasuredParkingZone(description, boundaries);
	}

	@Test
	void testRegisterAndGetParkingStore() {
		Mockito.when(parkingStore.getLocation()).thenReturn(mockParkingStoreLocation);

		parkingZone.registerParkingCardStore(mockParkingStoreLocation);
		assertEquals(parkingStore.getLocation(), parkingZone.getParkingCardStores().getFirst().getLocation());
	}

	@Test
	void testRegisterAndGetParking() {
		parkingZone.registerParking(mockParking);
		assertEquals(mockParking, parkingZone.getParkings().getFirst());
	}
	@Test
	void testGetActiveParkings() {
		Mockito.when(mockParking.isValid()).thenReturn(true);
		Mockito.when(anotherMockParking.isValid()).thenReturn(false);

		parkingZone.registerParking(mockParking);
		parkingZone.registerParking(anotherMockParking);

		assertEquals(1, parkingZone.getActiveParkings().size());
		assertEquals(mockParking, parkingZone.getParkings().getFirst());
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
