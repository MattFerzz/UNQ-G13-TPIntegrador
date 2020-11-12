package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sem.MeasuredParkingZone;
import sem.ParkingPurchase;


public class ParkingPurchaseTest {

	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private ParkingPurchase sut = new ParkingPurchase(3L, zone, LocalDateTime.now(), 5);
	
	@Test 
	public void testGetHours() {
		Integer hours = sut.getHours();
		assertEquals((long) hours,(long) 5);
	}

}
