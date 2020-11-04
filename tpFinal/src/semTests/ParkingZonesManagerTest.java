package semTests;

import static org.junit.jupiter.api.Assertions.*;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sec.InspectorApp;
import sec.MeasuredParkingZone;
import sec.ParkingZonesManager;

class ParkingZonesManagerTest {
	private MutableList<MeasuredParkingZone> parkingZones;
	private MutableList<InspectorApp> inspectorApps;
	private System system;
	@BeforeEach
	void setUp() throws Exception {
		parkingZones = Mockito.mock(MutableList<MeasuredParkingZone>.class)
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreation() {
	new ParkingZonesManager(MutableList<MeasuredParkingZone> parkingZones, System system, MutableList<InspectorApp> inspectorApps)
	}

}
