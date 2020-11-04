import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DeactivatedUserAssistanceTest {
	private DeactivatedUserAssistance sut = new DeactivatedUserAssistance();
	private CellApp app = mock(CellApp.class);
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	
	@Test
	public void handle() {
		System.setOut(new PrintStream(outContent));
		sut.handle("Caminando", app, "AA55BB");
		assertEquals(outContent.toString(), "La asistencia al Usuario esta desactivada");
	}
}
