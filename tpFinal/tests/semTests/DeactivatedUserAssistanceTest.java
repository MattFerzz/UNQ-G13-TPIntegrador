package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import sem.CellPhoneApp;
import sem.DeactivatedUserAssistance;

public class DeactivatedUserAssistanceTest {
	private DeactivatedUserAssistance sut = new DeactivatedUserAssistance();
	private CellPhoneApp app = mock(CellPhoneApp.class);
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	@Test
	public void testHandle() {
		System.setOut(new PrintStream(outContent));
		sut.handle("Caminando", app, "AA55BB");
		assertEquals(outContent.toString(), "La asistencia al Usuario esta desactivada");
	}
}
