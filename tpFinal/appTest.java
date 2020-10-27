import static org.mockito.Mockito.*;
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
	
	@setUp
	public void setUp() {
		app1.cargarSaldo(130);
		app2.cargarSaldo(1000);
		app3.cargarSaldo(10);
	};
	
	@test
	public void iniciarEstacionamiento(string p) {
		string resultado1 = app1.iniciarEstacionamiento();
		string resultado2 = app2.iniciarEstacionamiento();
		string resultado3 = app3.iniciarEstacionamiento();
		assertEquals(resultado1, "Horario de inicio: 16:15, horario máximo: 19:15");
		assertEquals(resultado2, "Horario de inicio: 16:15, horario máximo: 20:00");
		assertEquals(resultado3, "Saldo insuficiente. Estacionamiento no permitido.");
	}
	
	public void finalizarEstacionamiento() {
		string resultado1 = app1.finalizarEstacionamiento();
		assertEquals(resultado1, "Horario de inicio: 16:15, horario de finalizacion: 18:45, duracion: 2 horas, costo: 80");
	};

	public void cargarSaldo() {
		float saldoPrevio = app.consultarSaldo();
		app3.cargarSaldo(100);
		assertEquals(app3.consultarSaldo(), saldoPrevio + 100);
	};
	
	public void consultarSaldo() {
		assertEquals(app1.consultarSaldo, 130);
		assertEquals(app2.consultarSaldo, 1000);
		assertEquals(app3.consultarSaldo, 10);
	}
	
	public void setSate() {
		//no se si cubrir setState con un test
	};
}
