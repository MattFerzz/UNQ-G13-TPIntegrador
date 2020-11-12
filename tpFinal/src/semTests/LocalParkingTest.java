package semTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import sem.LocalParking;
import sem.Purchase;

public class LocalParkingTest {
	private Purchase purchase = mock(Purchase.class);
	private LocalParking sut = new LocalParking("AA55BB", LocalDateTime.of(2020, 11, 4, 13, 45), purchase, LocalDateTime.of(2020,11,4,16,45));

	@Test
	public void testIsValid() {
		LocalDateTime dateTime1 = LocalDateTime.of(2020, 11, 4, 19, 45, 00);
		LocalDateTime dateTime2 = LocalDateTime.of(2020, 11, 4, 15, 45, 00);
		try (MockedStatic<LocalDateTime> fixedDateTime = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			fixedDateTime.when(LocalDateTime::now).thenReturn(dateTime1, dateTime2);
			assertTrue(sut.isValid());
			assertFalse(sut.isValid());
		}
	}
	
	

	@Test
	public void testGetLicensePlate() {
		String licensePlate = sut.getLicensePlate();
		assertEquals(licensePlate, "AA55BB");
	}
	
	@Test
	public void testGetStartTime() {
        LocalDateTime dateTime = sut.getStartTime();
        assertEquals(dateTime, LocalDateTime.of(2020, 11, 4, 13, 45)); 
	}
	
	@Test 
	public void testGetFinishTime() {
        LocalDateTime dateTime = sut.getFinishTime();
        assertEquals(dateTime, LocalDateTime.of(2020,11,4,16,45));
	}
	
	@Test
	public void testGetPurchase() {
		assertEquals(purchase, sut.getPurchase());
	}
	
	@Test
	public void testFinish() {
		assertEquals(LocalDateTime.of(2020,11,4,16,45), sut.getFinishTime());
		sut.finish();
		assertEquals(LocalDateTime.of(2020,11,4,16,45), sut.getFinishTime());
	}
}
