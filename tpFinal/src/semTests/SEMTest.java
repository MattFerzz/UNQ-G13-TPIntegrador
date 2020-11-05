package semTests;
import org.junit.jupiter.api.Test;

import sem.ParkingMonitor;
import sem.SEM;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
		server.Notify();
		verify(prkMonitor).Notify();
	}
	
	@Test
	public void testGetParkingManager() {
		assertSame(server.getParkingManager(), prkManager);
	}
	
	@Test
	public void testGetPurchaseManager() {
		assertSame(server.getPurchaseManager(), prchManager);
	}
	
	@Test
	public void testRegisterPurchase() {
		Purchase p = mock(Purchase.class);
		server.registerPurchase(p);
		verify(prchManager).registerPurchase(p);
	}
	
	@Test
	public void testRegisterParking() {
		Parking p = mock(Parking.class);
		server.registerParking(p);
		verify(prkManager).registerParking(p);
	}
}
