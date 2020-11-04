import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ActivatedUserAssistanceTest {
	private ActivatedUserAssistance sut = new ActivatedUserAssistance();
	private CellApp app = mock(CellApp.class);
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	@Test
	public void handleStart() {
		System.setOut(new PrintStream(outContent));
		sut.handle("Caminando", app, "AA55BB");
		assertEquals(outContent.toString(), "Estacionamiento Iniciado");
	}
	
	@Test
	public void handleFinish() {
		System.setOut(new PrintStream(outContent));
		sut.handle("En Auto", app, "AA55BB");
		assertEquals(outContent.toString(), "Estacionamiento Finalizado");
	}
}
