package semTests;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
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
	void testGetReportingInspectorApp() {
		assertEquals(mockInspectorApp, parkingTicket.getReportingInspectorApp());
	}

	@Test
	void testGetParkingZone() {
		assertEquals(mockParkingZone, parkingTicket.getParkingZone());
	}

	@Test
	void testGetTimestamp() {
		LocalDateTime currentDateTime = LocalDateTime.of(2020, 11, 4, 16, 45);
		try (MockedStatic<LocalDateTime> date = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			date.when(LocalDateTime::now).thenReturn(currentDateTime);

			ParkingTicket anotherTicket = new ParkingTicket("AA506BG", mockParkingZone, mockInspectorApp);
			assertEquals(anotherTicket.getTimestamp(), LocalDateTime.of(2020, 11, 4, 16, 45));
		}
	}

}
