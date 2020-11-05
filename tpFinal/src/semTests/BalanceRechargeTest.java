package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sem.BalanceRecharge;
import sem.MeasuredParkingZone;

public class BalanceRechargeTest {
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private BalanceRecharge sut = new BalanceRecharge(5, zone, LocalDateTime.now(), 1155662233, 50);

	@Test
	public void testGetPhoneNumber() {
		Integer phone = sut.getPhoneNumber();
		assertEquals((long)phone, (long)1155662233);	
	}
	
	@Test 
	public void testGetMount() {
		Float mount =sut.getMount();
		assertEquals(mount, 50, 0);
	}
}

