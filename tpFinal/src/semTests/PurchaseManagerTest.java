import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

public class PurchaseManagerTest {
	private SEM server = mock(SEM.class);
	private PurchaseManager sut = new PurchaseManager(server);
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
		
	@Test
	public void generateParkingPurchase() {
		Parking parking = mock(Parking.class);
		sut.generateParkingPurchase(zone, 5, "AA555BB");
		assertEquals(sut.getPurchases().size(), 1);
		verify(server).registerParking(parking);
	}
	
	@Test 
	public void generateBalanceRecharge() {
		sut.generateBalanceRecharge(zone, (Integer) 1133665588, (float) 100);
		assertEquals(sut.getPurchases().size(), 1);
	}
	
	@Test
	public void generateNextControlNumber(){
		sut.generateNextControlNumber();
		assertEquals(sut.getControlNumber(), 1);
	}
	
}
