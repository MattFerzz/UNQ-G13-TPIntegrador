package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sem.MeasuredParkingZone;
import sem.Purchase;
public class PurchaseTest {
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private LocalDateTime dateTime = LocalDateTime.now();
	private Purchase sut = new Purchase(3L, zone, dateTime);

	@Test
	
	public void testGetId() {
		long id = sut.getId();
		assertEquals(id, 3);
	}
	
	@Test
	public void testGetParkingZone() {
		MeasuredParkingZone parkingZone = sut.getParkingZone();
		assertEquals(parkingZone, zone);
	}
	
	@Test
	public void testGetDate() {
		LocalDateTime date = sut.getDateTime();
		assertEquals(date, dateTime);
	}
}
