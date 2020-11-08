package semTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sem.AppParking;

public class AppParkingTest {

	private AppParking sut = new AppParking(1155336622, "Activada", "AA55BB", LocalDateTime.of(2020, 11, 4, 13, 45));
	
	@Test
	public void testIsValid() {
		assertTrue(sut.isValid());
	}
	
	@Test
	public void testGetNumberPhone() {
		 Integer num = sut.getNumberPhone();
		 assertEquals((long)num,(long)1155336622);
	}
	
	@Test
	public void testGetOutput() {
		String output = sut.getOutput();
		assertEquals(output, "Activada");
	}
	
	@Test
	public void testSetOutPut() {
		sut.setOutput("Desactivada");
		String output = sut.getOutput();
		assertEquals(output, "Desactivada");
	}

	@Test
	public void testFinish() {
		sut.finish();
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
        System.out.println(date);
        System.out.println(LocalDateTime.of(2020, 11, 4, 13, 45));
        assertEquals(date, LocalDateTime.of(2020, 11, 4, 13, 45)); 
	}
	
	@Test 
	public void testGetFinish() {
		sut.setFinish(LocalDateTime.of(2020,11,4,16,45));
        LocalDateTime date = sut.getFinishTime();
        assertEquals(date, LocalDateTime.of(2020,11,4,16,45));
	}
	
	@Test
	public void testSetFinish() {
		sut.setFinish(LocalDateTime.of(2020,11,4,22,45)); 
		LocalDateTime date = sut.getFinishTime();
		assertEquals(date,LocalDateTime.of(2020,11,4,22,45));
	}

}
