package semTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sem.InspectorApp;
import sem.LocalParking;
import sem.MeasuredParkingZone;
import sem.Parking;
import sem.ParkingManager;
import sem.ParkingTicket;
import sem.SEM;

class ParkingManagerTest {
	private SEM mockSystem;
	private ParkingTicket mockTicket;
	private ParkingManager parkingManager;
	private Parking mockValidParking;
	private Parking mockNonValidParking;
	private Parking mockAnotherValidParking;
	private MeasuredParkingZone mockParkingZone;
	private InspectorApp mockInspectorApp;

	@BeforeEach
	void setUp() {
		mockSystem = Mockito.mock(SEM.class);
		mockTicket = Mockito.mock(ParkingTicket.class);
		parkingManager = new ParkingManager(mockSystem);
		mockParkingZone = Mockito.mock(MeasuredParkingZone.class);
		mockInspectorApp = Mockito.mock(InspectorApp.class);

		this.setUpParkings();
	}

	private void setUpParkings() {
		mockValidParking = Mockito.mock(Parking.class);
		mockNonValidParking = Mockito.mock(Parking.class);
		mockAnotherValidParking = Mockito.mock(Parking.class);
		
		Mockito.when(mockValidParking.isValid()).thenReturn(true);
		Mockito.when(mockNonValidParking.isValid()).thenReturn(false);
		Mockito.when(mockAnotherValidParking.isValid()).thenReturn(true);
	}
	
	@Test
	void testRegisterParking() {
		assertTrue(parkingManager.getParkings().isEmpty());
		parkingManager.registerParking(mockValidParking);
		assertEquals(mockValidParking,parkingManager.getParkings().getFirst());
	}

	@Test
	void testGetParkings() {
		parkingManager.registerParking(mockValidParking);
		parkingManager.registerParking(mockNonValidParking);
		
		assertTrue(parkingManager.getParkings().contains(mockValidParking));
		assertTrue(parkingManager.getParkings().contains(mockNonValidParking));
	}

	@Test
	void testGetValidParkings() {
		parkingManager.registerParking(mockValidParking);
		parkingManager.registerParking(mockNonValidParking);
		parkingManager.registerParking(mockAnotherValidParking);

		assertTrue(parkingManager.getValidParkings().contains(mockValidParking));
		assertTrue(parkingManager.getValidParkings().contains(mockAnotherValidParking));
		assertTrue(!parkingManager.getValidParkings().contains(mockNonValidParking));
	}

	@Test
	void testGetParking() {
		Mockito.when(mockValidParking.getLicensePlate()).thenReturn("AA506BG");
		parkingManager.registerParking(mockValidParking);
	
		assertEquals(mockValidParking ,parkingManager.getParking("AA506BG"));
	}
	
	@Test
	void testHasValidParking() {
		Mockito.when(mockValidParking.getLicensePlate()).thenReturn("AA506BG");
		parkingManager.registerParking(mockValidParking);
		
		assertTrue(parkingManager.hasValidParking("AA506BG"));
	}

	@Test
	void testFinishParking() {
		parkingManager.registerParking(mockValidParking);
		Mockito.when(mockValidParking.getLicensePlate()).thenReturn("AA506BG");
		parkingManager.finishParking("AA506BG", "Estacionamiento Terminado");
		verify(mockValidParking).finish();
	}

	@Test
	void testCloseAllParkings() {
		parkingManager.registerParking(mockValidParking);
		parkingManager.registerParking(mockAnotherValidParking);
		
		assertTrue(parkingManager.getValidParkings().contains(mockValidParking));
		assertTrue(parkingManager.getValidParkings().contains(mockAnotherValidParking));
		
		parkingManager.closeAllParkings();	
	}

	@Test
	void testGenerateParkingTicketFor() {
		assertTrue(parkingManager.getParkingTickets().isEmpty());
		parkingManager.generateParkingTicketFor("AA506BG", mockParkingZone, mockInspectorApp);
		assertEquals("AA506BG", parkingManager.getParkingTickets().getFirst().getInfringingLicensePlate());
	}

	@Test
	void testGetParkingTicketsFor() {
		parkingManager.generateParkingTicketFor("AA506BG", mockParkingZone, mockInspectorApp);
		assertEquals("AA506BG", parkingManager.getParkingTicketsFor("AA506BG").getFirst().getInfringingLicensePlate());
	}

	@Test
	void testRemoveParkingTicket() {
		assertTrue(parkingManager.getParkingTickets().isEmpty());
		parkingManager.generateParkingTicketFor("AA506BG", mockParkingZone, mockInspectorApp);
		assertTrue(!parkingManager.getParkingTickets().isEmpty());
		parkingManager.removeParkingTicket(parkingManager.getParkingTicketsFor("AA506BG").getFirst());
		assertTrue(parkingManager.getParkingTickets().isEmpty());
		
	}

}
