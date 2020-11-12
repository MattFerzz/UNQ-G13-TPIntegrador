package semTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import sem.AppParking;

public class AppParkingTest {

	private AppParking sut = new AppParking(1155336622, "AA55BB", LocalDateTime.of(2020, 11, 4, 13, 45));

	@Test
	public void testIsValid() {
		assertTrue(sut.isValid());
	}

	@Test
	public void testGetPhoneNumber() {
		Integer phoneNumber = sut.getPhoneNumber();
		assertEquals((long) phoneNumber, 1155336622L);
	}

	@Test
	public void testGetOutput() {
		sut.setOutput("Activada");
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
		String licensePlate = sut.getLicensePlate();
		assertEquals(licensePlate, "AA55BB");
	}

	@Test
	public void testGetStart() {
		LocalDateTime date = sut.getStartTime();
		System.out.println(date);
		System.out.println(LocalDateTime.of(2020, 11, 4, 13, 45));
		assertEquals(date, LocalDateTime.of(2020, 11, 4, 13, 45));
	}

	@Test
	public void testGetFinishTime() {
		LocalDateTime currentDateTime = LocalDateTime.of(2020, 11, 4, 16, 45);
		try (MockedStatic<LocalDateTime> date = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {
			date.when(LocalDateTime::now).thenReturn(currentDateTime);

			sut.finish();
			assertEquals(LocalDateTime.of(2020, 11, 4, 16, 45), sut.getFinishTime());
		}
	}

}