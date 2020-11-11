package semTests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import sem.InspectorApp;
import sem.MeasuredParkingZone;
import sem.ParkingTicket;

class ParkingTicketTest {

	private ParkingTicket parkingTicket;
	private MeasuredParkingZone mockParkingZone;
	private InspectorApp mockInspectorApp;

	@BeforeEach
	void setUp() {
		mockParkingZone = Mockito.mock(MeasuredParkingZone.class);
		mockInspectorApp = Mockito.mock(InspectorApp.class);
		parkingTicket = new ParkingTicket("AA506BG", mockParkingZone, mockInspectorApp);
	}


	@Test
	void testGetLicensePlate() {
		assertEquals("AA506BG", parkingTicket.getInfringingLicensePlate());
	}

	@Test
	void testGetParkingZone() {
		assertEquals(mockParkingZone, parkingTicket.getParkingZone());
	}

	@Test
	void testGetTimestamp() {
		fail("FIX TIME.NOW");
	}

}
