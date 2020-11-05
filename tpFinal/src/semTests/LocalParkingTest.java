package semTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sem.LocalParking;
import sem.Purchase;

public class LocalParkingTest {
	private Clock clock = mock(Clock.class);
	private Purchase purchase = mock(Purchase.class);
	private LocalParking sut = new LocalParking("AA55BB", LocalDateTime.of(2020, 11, 4, 13, 45), purchase, LocalDateTime.of(2020,11,4,16,45), clock);

	@Test
	public void testIsValid() {
		Instant fixedClock1 = Instant.parse("2020-11-04T22:45:00Z");
        Instant fixedClock2 = Instant.parse("2020-11-04T18:45:00Z");
        when(clock.instant()).thenReturn(fixedClock1, fixedClock2);
        assertTrue(sut.isValid());
        assertFalse(sut.isValid());
	}
	

	@Test
	public void testGetLicensePlate() {
		String lp = sut.getLicensePlate();
		assertEquals(lp, "AA55BB");
	}
	
	@Test
	public void testGetStart() {
        LocalDateTime date = sut.getStart();
        assertEquals(date, LocalDateTime.of(2020, 11, 4, 13, 45)); 
	}
	
	@Test 
	public void testGetFinish() {
        LocalDateTime date = sut.getFinish();
        assertEquals(date, LocalDateTime.of(2020,11,4,16,45));
	}
	
	@Test
	public void testSetFinish() {
		sut.setFinish(LocalDateTime.of(2020,11,4,22,45)); 
		LocalDateTime date = sut.getFinish();
		assertEquals(date,LocalDateTime.of(2020,11,4,22,45));
	}
}
