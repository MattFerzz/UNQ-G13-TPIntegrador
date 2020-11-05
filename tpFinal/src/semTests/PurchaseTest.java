package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sem.MeasuredParkingZone;
import sem.Purchase;
public class PurchaseTest {
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private LocalDateTime date = LocalDateTime.now();
	private Purchase sut = new Purchase(3, zone, date);

	@Test
	
	public void testGetId() {
		long id = sut.getId();
		assertEquals(id, 3);
	}
	
	@Test
	public void testGetZone() {
		MeasuredParkingZone z = sut.getZone();
		assertEquals(z, zone);
	}
	
	@Test
	public void testGetDate() {
		LocalDateTime d = sut.getDate();
		assertEquals(d, date);
	}
}
