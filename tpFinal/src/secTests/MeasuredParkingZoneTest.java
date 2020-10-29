package secTests;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;
import sec.Coordinate;
import sec.InspectorApp;
import sec.MeasuredParkingZone;
import sec.ParkingCardStore;
import org.junit.jupiter.api.BeforeEach;
 
class MeasuredParkingZoneTest {
	private ParkingCardStore mockParkingCardStore;
	private InspectorApp mockInspector;
	private MutableList<ParkingCardStore> parkingCardStores;
	private Coordinate mockCoordinate;
	private Pair<Coordinate, Coordinate> boundaries;
	private MeasuredParkingZone parkingZone;
	
	@BeforeEach
	void setUp() {
		mockParkingCardStore = Mockito.mock(ParkingCardStore.class);
		mockInspector = Mockito.mock(InspectorApp.class);
		parkingCardStores = Lists.mutable.with(mockParkingCardStore);
		mockCoordinate = Mockito.mock(Coordinate.class);
		boundaries = Tuples.pair(mockCoordinate, mockCoordinate);
		
		parkingZone = new MeasuredParkingZone(parkingCardStores, mockInspector, boundaries);
	}
 
	@Test
	void testGetParkingCardStores() {
		assertEquals(parkingCardStores, parkingZone.getParkingCardStores());
	}
 
	@Test
	void testGetDesignatedInspector() {
		assertEquals(mockInspector, parkingZone.getDesignatedInspector());
	}
 
	@Test
	void testGetBoundaries() {
		assertEquals(boundaries, parkingZone.getBoundaries());
	}
 
}
