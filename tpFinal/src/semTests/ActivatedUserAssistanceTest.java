package semTests;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import sem.ActivatedUserAssistance;
import sem.CellPhoneApp;
public class ActivatedUserAssistanceTest {
	private ActivatedUserAssistance sut = new ActivatedUserAssistance();
	private CellPhoneApp app = mock(CellPhoneApp.class);
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	@Test
	public void testHandleStart() {
		System.setOut(new PrintStream(outContent));
		sut.handle("Caminando", app, "AA55BB");
		assertEquals(outContent.toString(), "Estacionamiento Iniciado");
	}
	
	@Test
	public void testHandleFinish() {
		System.setOut(new PrintStream(outContent));
		sut.handle("En Auto", app, "AA55BB");
		assertEquals(outContent.toString(), "Estacionamiento Finalizado");
	}
}
