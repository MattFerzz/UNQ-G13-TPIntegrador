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
		LocalDateTime horaActual1 = LocalDateTime.of(2020, 11, 4, 19, 45, 00);
		LocalDateTime horaActual2 = LocalDateTime.of(2020, 11, 4, 15, 45, 00);
		try (MockedStatic<LocalDateTime> date = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			date.when(LocalDateTime::now).thenReturn(horaActual1, horaActual2);
			assertTrue(sut.isValid());
			assertFalse(sut.isValid());
		}
	}
	
	

	@Test
	public void testGetLicensePlate() {
		String lp = sut.getLicensePlate();
		assertEquals(lp, "AA55BB");
	}
	
	@Test
	public void testGetStartTime() {
        LocalDateTime date = sut.getStartTime();
        assertEquals(date, LocalDateTime.of(2020, 11, 4, 13, 45)); 
	}
	
	@Test 
	public void testGetFinishTime() {
        LocalDateTime date = sut.getFinishTime();
        assertEquals(date, LocalDateTime.of(2020,11,4,16,45));
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
