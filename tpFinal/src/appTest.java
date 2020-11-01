import org.junit.jupiter.api.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/*
 Metodos:
 	iniciarEstacionamiento(string patente)
 	finalizarEstacionamiento()
 	cargarSaldo(float x)
 	consultarSaldo()
 	setState(string stateName)
 */

public class appTest {

	CellApp app1 = new CellApp(123456);
	CellApp app2 = new CellApp(234567);
	CellApp app3 = new CellApp(345678);
	
	@Before
	protected void setUp() {
		app1.loadBalance(130);
		app2.loadBalance(1000);
		app3.loadBalance(10);
	};
	
	@Test
	public void iniciarEstacionamiento(String p) {
		String resultado1 = app1.StartParking("abc123");
		String resultado2 = app2.StartParking("bcd234");
		String resultado3 = app3.StartParking("cde345");
		assertEquals(resultado1, "Horario de inicio: 16:15, horario máximo: 19:15");
		assertEquals(resultado2, "Horario de inicio: 16:15, horario máximo: 20:00");
		assertEquals(resultado3, "Saldo insuficiente. Estacionamiento no permitido.");
	}
	
	@Test
	public void finalizarEstacionamiento() {
		String resultado1 = app1.EndParking();
		assertEquals(resultado1, "Horario de inicio: 16:15, horario de finalizacion: 18:45, duracion: 2 horas, costo: 80");
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
