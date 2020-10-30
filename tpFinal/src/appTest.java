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

	AplicacionCelular app1 = new AplicacionCelular();
	AplicacionCelular app2 = new AplicacionCelular();
	AplicacionCelular app3 = new AplicacionCelular();
	
	@Before
	protected void setUp() {
		app1.cargarSaldo(130);
		app2.cargarSaldo(1000);
		app3.cargarSaldo(10);
	};
	
	@Test
	public void iniciarEstacionamiento(String p) {
		String resultado1 = app1.iniciarEstacionamiento("abc123");
		String resultado2 = app2.iniciarEstacionamiento("bcd234");
		String resultado3 = app3.iniciarEstacionamiento("cde345");
		assertEquals(resultado1, "Horario de inicio: 16:15, horario máximo: 19:15");
		assertEquals(resultado2, "Horario de inicio: 16:15, horario máximo: 20:00");
		assertEquals(resultado3, "Saldo insuficiente. Estacionamiento no permitido.");
	}
	
	@Test
	public void finalizarEstacionamiento() {
		String resultado1 = app1.finalizarEstacionamiento();
		assertEquals(resultado1, "Horario de inicio: 16:15, horario de finalizacion: 18:45, duracion: 2 horas, costo: 80");
	};

	@Test
	public void cargarSaldo() {
		float saldoPrevio = app3.consultarSaldo();
		app3.cargarSaldo(100);
		assertEquals(app3.consultarSaldo(), saldoPrevio + 100, 0);
	};
	
	@Test
	public void consultarSaldo() {
		assertEquals(app1.consultarSaldo(), 130, 0);
		assertEquals(app2.consultarSaldo(), 1000, 0);
		assertEquals(app3.consultarSaldo(), 10, 0);
	}
	
	@Test
	public void setSate() {
		//no se si cubrir setState con un test
	};
}
