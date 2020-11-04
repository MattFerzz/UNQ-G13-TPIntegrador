package estacionamiento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

public class BalanceRechargeTest {
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private BalanceRecharge sut = new BalanceRecharge(5, zone, LocalDateTime.now(), 1155662233, 50);

	@Test
	public void getPhoneNumber() {
		Integer phone = sut.getPhoneNumber();
		assertEquals(phone, 1155662233);	
	}
	
	@Test 
	public void getMount() {
		Float mount =sut.getMount();
		assertEquals(mount, 50);
	}
}

