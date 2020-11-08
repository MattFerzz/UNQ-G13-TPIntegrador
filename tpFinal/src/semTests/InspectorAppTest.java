package semTests;

import static org.junit.Assert.assertTrue;
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
		Mockito.when(mockSystem.getParkingManager().hasValidParking("AA506BG")).thenReturn(true);
		
		assertTrue(inspectorApp.hasValidParkingCard("AA506BG"));
	}

	@Test
	void testGenerateParkingTicketFor() {
		//FIX;
	}

}
