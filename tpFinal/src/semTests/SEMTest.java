package semTests;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import sem.MeasuredParkingZone;
import sem.Parking;
import sem.ParkingManager;
import sem.ParkingMonitor;
import sem.ParkingZoneManager;
import sem.Purchase;
import sem.PurchaseManager;
import sem.SEM;

public class SEMTest {
	
	ParkingZoneManager prkZnManager = mock(ParkingZoneManager.class);
	ParkingManager     prkManager   = mock(ParkingManager.class);
	PurchaseManager    prchManager  = mock(PurchaseManager.class);
	ParkingMonitor     prkMonitor   = mock(ParkingMonitor.class);
	
	SEM server = new SEM(prkZnManager, prchManager, prkManager, prkMonitor);
	
	@Test
	public void testCloseAllParkings() {
		server.closeAllParkings();
		verify(prkManager).closeAllParkings();
	}
	
	@Test
	public void testNotify() {
		Parking parking = mock(Parking.class);
		server.notify(parking);
		verify(prkMonitor).notify(parking);
	}
	
	@Test
	public void testGetParking() {
		server.getParking("abc123");
		verify(prkManager).getParking("abc123");
	}
	
	@Test
	public void testFinishParking() {
		server.finishParking("abc123", "output");
		verify(prkManager).finishParking("abc123", "output");
	}
	
	@Test
	public void testGenerateParkingPurchase() {
		MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
		server.generateParkingPurchase(zone, 5, "abc123");
		verify(prchManager).generateParkingPurchase(zone, 5, "abc123");
	}
	
	@Test
	public void testRegisterParking() {
		Parking p = mock(Parking.class);
		server.registerParking(p);
		verify(prkManager).registerParking(p);
	}
}