package semTests;

import static org.junit.jupiter.api.Assertions.*;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.Coordinate;
import sem.InspectorApp;
import sem.MeasuredParkingZone;
import sem.ParkingZoneManager;
import sem.SEM;

class ParkingZoneManagerTest {
	private ParkingZoneManager manager;
	private InspectorApp inspectorApp;
	private MeasuredParkingZone parkingZone;
	private SEM system;
	private Pair<Coordinate, Coordinate> boundaries;
	private Coordinate coordinate;

	@BeforeEach
	void setUp() {
		system = Mockito.mock(SEM.class);
		parkingZone = Mockito.mock(MeasuredParkingZone.class);
		inspectorApp = Mockito.mock(InspectorApp.class);
		coordinate = Mockito.mock(Coordinate.class);
		boundaries = Tuples.pair(coordinate, coordinate);
		manager = new ParkingZoneManager(system);

	}

	@Test
	public void testAddAndGetParkingZone() {
		Mockito.when(parkingZone.getBoundaries()).thenReturn(boundaries);

		manager.addMeasuredParkingZone(parkingZone);
		assertEquals(parkingZone.getBoundaries(), manager.getParkingZoneWithBoundaries(boundaries).getBoundaries());
	}

	@Test
	public void testDeployInspectorInParkingZone() {
		manager.addMeasuredParkingZone(parkingZone);
		manager.deployInspectorAppInParkingZone(parkingZone, "Carlos");
		assertEquals("Carlos", manager.getInspectorAppDeployedInParkingZone(parkingZone).getInspectorName());
	}
}
