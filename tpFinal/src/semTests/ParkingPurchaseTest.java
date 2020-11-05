import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;


public class ParkingPurchaseTest {

	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private ParkingPurchase sut = new ParkingPurchase(3, zone, LocalDateTime.now(), 5);
	
	@Test 
	public void getHours() {
		Integer hours = sut.getHours();
		assertEquals((long) hours,(long) 5);
	}

}
