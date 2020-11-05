package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import sem.MeasuredParkingZone;
import sem.Parking;
import sem.PurchaseManager;
import sem.SEM;
public class PurchaseManagerTest {
	private SEM server = mock(SEM.class);
	private PurchaseManager sut = new PurchaseManager(server);
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
		
	@Test
	public void testGenerateParkingPurchase() {
		Parking parking = mock(Parking.class);
		sut.generateParkingPurchase(zone, 5, "AA555BB");
		assertEquals(sut.getPurchases().size(), 1);
		verify(server).registerParking(parking);
	}
	
	@Test 
	public void testGenerateBalanceRecharge() {
		sut.generateBalanceRecharge(zone, (Integer) 1133665588, (float) 100);
		assertEquals(sut.getPurchases().size(), 1);
	}
	
	@Test
	public void testGenerateNextControlNumber(){
		sut.generateNextControlNumber();
		assertEquals(sut.getControlNumber(), 1);
	}
	
}
