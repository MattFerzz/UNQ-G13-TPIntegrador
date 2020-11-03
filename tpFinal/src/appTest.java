import org.junit.jupiter.api.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/*
 Metodos:
 	iniciarEstacionamiento(string patente)
 	finalizarEstacionamiento()
 	cargarSaldo(float x)
 	consultarSaldo()
 	setState(string stateName)
 */

public class appTest {

	SEM server = mock(SEM.class);
	
	CellApp app1 = new CellApp(123456, server);
	CellApp app2 = new CellApp(234567, server);
	CellApp app3 = new CellApp(345678, server);
	
	@Before
	protected void setUp() {
		app1.loadBalance(130);
		app2.loadBalance(1000);
		app3.loadBalance(10);
	};
	
	@Test
	public void iniciarEstacionamiento(String p) {
		String parking1 = app1.startParking("abc123");
		String parking2 = app2.startParking("bcd234");
		String parking3 = app3.startParking("cde345");
		assertEquals(parking1, "Horario de inicio: 16:15:00, horario máximo: 19:15:00");
		assertEquals(parking2, "Horario de inicio: 16:15:00, horario máximo: 20:00:00");
		assertEquals(parking3, "Saldo insuficiente. Estacionamiento no permitido.");
	}
	
	@Test
	public void finalizarEstacionamiento() {
		String parking1 = app1.endParking();
		assertEquals(parking1, "Horario de inicio: 16:15:00, horario de finalizacion: 18:45:00, duracion: 2 horas, costo: 80");
	};

	@Test
	public void cargarSaldo() {
		float saldoPrevio = app3.checkBalance();
		app3.loadBalance(100);
		assertEquals(app3.checkBalance(), saldoPrevio + 100, 0);
	};
	
	@Test
	public void consultarSaldo() {
		assertEquals(app1.checkBalance(), 130, 0);
		assertEquals(app2.checkBalance(), 1000, 0);
		assertEquals(app3.checkBalance(), 10, 0);
	}
	
	@Test
	public void setSate() {
		//no se si cubrir setState con un test
	};
}
