import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

public class PurchaseTest {
	private MeasuredParkingZone zone = mock(MeasuredParkingZone.class);
	private LocalDateTime date = LocalDateTime.now();
	private Purchase sut = new Purchase(3, zone, date);

	@Test
	
	public void getId() {
		long id = sut.getId();
		assertEquals(id, 3);
	}
	
	@Test
	public void getZone() {
		MeasuredParkingZone z = sut.getZone();
		assertEquals(z, zone);
	}
	
	@Test
	public void getDate() {
		LocalDateTime d = sut.getDate();
		assertEquals(d, date);
	}
}
