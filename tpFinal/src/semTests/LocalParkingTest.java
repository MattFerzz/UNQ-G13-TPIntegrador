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
	private LocalParking sut = new LocalParking("AA55BB", LocalDateTime.of(2020, 11, 4, 13, 45), purchase, LocalDateTime.of(2020,11,4,16,45));

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
        LocalDateTime date = sut.getStartTime();
        assertEquals(date, LocalDateTime.of(2020, 11, 4, 13, 45)); 
	}
	
	@Test 
	public void testGetFinish() {
        LocalDateTime date = sut.getFinishTime();
        assertEquals(date, LocalDateTime.of(2020,11,4,16,45));
	}
	
	@Test
	public void testFinish() {
		assertEquals(LocalDateTime.of(2020,11,4,16,45), sut.getFinishTime());
		sut.finish();
		assertEquals(LocalDateTime.of(2020,11,4,16,45), sut.getFinishTime());
	}
}
