package semTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import sem.InspectorApp;
import sem.MeasuredParkingZone;
import sem.Parking;
import sem.ParkingManager;
import sem.ParkingMonitor;
import sem.ParkingZoneManager;
import sem.PurchaseManager;
import sem.CellPhoneAppManager;
import sem.SEM;

public class SEMTest {

	ParkingZoneManager parkingZoneManager   = mock(ParkingZoneManager.class);
	ParkingManager parkingManager           = mock(ParkingManager.class);
	PurchaseManager purchaseManager         = mock(PurchaseManager.class);
	ParkingMonitor parkingMonitor           = mock(ParkingMonitor.class);
	CellPhoneAppManager cellPhoneAppManager = mock(CellPhoneAppManager.class);
	
	SEM server = new SEM(parkingZoneManager, purchaseManager, parkingManager, parkingMonitor, cellPhoneAppManager);

	@Test
	public void testCloseAllParkings() {
		server.closeAllParkings();
		verify(parkingManager).closeAllParkings();
	}

	@Test
	public void testNotify() {
		Parking parking = mock(Parking.class);
		server.notify(parking);
		verify(parkingMonitor).notify(parking);
	}

	@Test
	public void testGetParkingZones() {
		server.getParkingZones();
		verify(parkingZoneManager).getParkingZones();
	}

	@Test
	public void testGetParking() {
		server.getParking("abc123");
		verify(parkingManager).getParking("abc123");
	}

	@Test
	public void testFinishParking() {
		server.finishParking("abc123", "output");
		verify(parkingManager).finishParking("abc123", "output");
	}

	@Test
	public void testGenerateParkingPurchase() {
		MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
		server.generateParkingPurchase(zone, 5, "abc123");
		verify(purchaseManager).generateParkingPurchase(zone, 5, "abc123");
	}

	@Test
	public void testRegisterParking() {
		Parking p = mock(Parking.class);
		server.registerParking(p);
		verify(parkingManager).registerParking(p);
	}

	@Test
	public void testHasValidParking() {
		server.hasValidParking("AA403BG");
		verify(parkingManager).hasValidParking("AA403BG");
	}

	@Test
	public void testGenerateParkingTicketFor() {
		MeasuredParkingZone parkingZone = mock(MeasuredParkingZone.class);
		InspectorApp inspectorApp = mock(InspectorApp.class);
		server.generateParkingTicketFor("AA403BG", parkingZone, inspectorApp);
		verify(parkingManager).generateParkingTicketFor("AA403BG", parkingZone, inspectorApp);
	}
	
	@Test
	public void testLoadBalance() {
		server.loadBalance(123456, 48F);
		verify(cellPhoneAppManager).loadBalance(123456, 48F);
	}
}