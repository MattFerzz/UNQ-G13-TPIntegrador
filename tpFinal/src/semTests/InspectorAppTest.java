package semTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.InspectorApp;
import sem.MeasuredParkingZone;
import sem.SEM;

class InspectorAppTest {
	private MeasuredParkingZone mockParkingZone;
	private String inspectorName;
	private SEM mockSystem;
	private InspectorApp inspectorApp;
	@BeforeEach
	void setUp() throws Exception {
		mockParkingZone = Mockito.mock(MeasuredParkingZone.class);
		inspectorName = "Juan Perez";
		mockSystem = Mockito.mock(SEM.class);
		Mockito.when(mockSystem.getParkingManager().hasValidParking("AA506BG")).thenReturn(true);
		
		inspectorApp = new InspectorApp(mockParkingZone, inspectorName, mockSystem);
	}


	@Test
	void testGetParkingZone() {
		assertEquals(mockParkingZone, inspectorApp.getParkingZone());
	}

	@Test
	void testGetInspectorName() {
		assertEquals(inspectorName, inspectorApp.getInspectorName());
	}

	@Test
	void testHasValidParkingCard() {
		fail("Not yet implemented");
	}

	@Test
	void testGenerateParkingTicketFor() {
		fail("Not yet implemented");
	}

}
