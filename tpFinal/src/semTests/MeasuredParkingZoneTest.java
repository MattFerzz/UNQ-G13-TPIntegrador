package semTests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.Coordinate;
import sem.InspectorApp;
import sem.MeasuredParkingZone;
import sem.ParkingCardStore;
 
class MeasuredParkingZoneTest {
	private Coordinate mockParkingCardStoreLocation;
	private InspectorApp mockInspector;
	private MutableList<ParkingCardStore> parkingCardStores;
	private Coordinate mockCoordinate;
	private Pair<Coordinate, Coordinate> boundaries;
	private MeasuredParkingZone parkingZone;
	private String description;
	
	@BeforeEach
	void setUp() {
		mockParkingCardStoreLocation = Mockito.mock(Coordinate.class);
		mockInspector = Mockito.mock(InspectorApp.class);
		parkingCardStores = Lists.mutable.with(mockParkingCardStore);
		mockCoordinate = Mockito.mock(Coordinate.class);
		boundaries = Tuples.pair(mockCoordinate, mockCoordinate);
		description = "Zona de estacionamiento barrio Kolynos";
		
		parkingZone = new MeasuredParkingZone(description, boundaries);
	}
	@Test
	void testRegisterAndGetParkingCardStores() {
		parkingZone.registerParkingCardStore(mockParkingCardStoreLocation);
		assertEquals(parkingCardStores, parkingZone.getParkingCardStores().getFirst());
	}
	@Test
	void testRegisterAndGetParking() {
		parkingZone.registerParking(mockParking);
		assertEquals(parkingCardStores, parkingZone.getParkingCardStores().getFirst());
	}
	
	void testGetActiveParkings() {
		//TO-DO
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
