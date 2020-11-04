package estacionamiento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

public class AppParkingTest {

	private AppParking sut = new AppParking(1155336622, "Activada", "AA55BB", LocalDateTime.now());
	
	@Test
	public void isValid() {
		assertTrue(sut.isValid());
	}
	
	@Test
	public void getNumberPhone() {
		 Integer num = sut.getNumberPhone();
		 assertEquals(num, 1155336622);
	}
	
	@Test
	public void getOutput() {
		String output = sut.getOutput();
		assertEquals(output, "Activada");
	}
	
	@Test
	public void setOutPut() {
		sut.setOutput("Desactivada");
		String output = sut.getOutput();
		assertEquals(output, "Desactivada");
	}

	@Test
	public void finish() {
		sut.finish();
		assertFalse(sut.isValid());
	}
	

	@Test
	public void getLicensePlate() {
		String lp = sut.getLicensePlate();
		assertEquals(lp, "AA55BB");
	}
	
	@Test
	public void getStart() {
        LocalDateTime date = sut.getStart();
        assertEquals(date, LocalDateTime.of(2020, 11, 4, 13, 45)); 
	}
	
	@Test 
	public void getFinish() {
        LocalDateTime date = sut.getFinish();
        assertEquals(date, LocalDateTime.of(2020,11,4,16,45));
	}
	
	@Test
	public void setFinish() {
		sut.setFinish(LocalDateTime.of(2020,11,4,22,45)); 
		LocalDateTime date = sut.getFinish();
		assertEquals(date,LocalDateTime.of(2020,11,4,22,45));
	}

}
