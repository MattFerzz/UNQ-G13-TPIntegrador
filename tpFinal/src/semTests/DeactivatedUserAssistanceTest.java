package semTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import sem.CellApp;
import sem.DeactivatedUserAssistance;

public class DeactivatedUserAssistanceTest {
	private DeactivatedUserAssistance sut = new DeactivatedUserAssistance();
	private CellApp app = mock(CellApp.class);
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	@Test
	public void testHandle() {
		System.setOut(new PrintStream(outContent));
		sut.handle("Caminando", app, "AA55BB");
		assertEquals(outContent.toString(), "La asistencia al Usuario esta desactivada");
	}
}
