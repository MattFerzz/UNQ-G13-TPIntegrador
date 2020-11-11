package semTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import sem.CellApp;
import sem.DeactivatedUserAssistance;
import sem.Parking;
import sem.ParkingManager;
import sem.SEM;

public class appTest {

	SEM                       server  = mock(SEM.class);
	Parking                   parking = mock(Parking.class);
	ParkingManager            pkm     = mock(ParkingManager.class);
	DeactivatedUserAssistance state   = mock(DeactivatedUserAssistance.class);
	
	CellApp app1 = new CellApp(123456, server, "abc123", state);
	CellApp app2 = new CellApp(234567, server, "bcd234", state);
	CellApp app3 = new CellApp(345678, server, "cde345", state);
	CellApp app4 = new CellApp(456789, server, "def456", state);
	
	@BeforeEach
	protected void setUp() {
		app1.loadBalance(130);
		app2.loadBalance(1000);
		app3.loadBalance(10);
		app4.loadBalance(160);
	};
	
	@Test
	public void testGetActiveLisencePlate() {
			String lisencePlate = app1.getActiveLisencePlate();
			assertNull(lisencePlate);
			app1.startParking("abc123");
			lisencePlate = app1.getActiveLisencePlate();
			assertEquals(lisencePlate, "abc123");
	}
	
	@Test
	public void testStartParking() {
		LocalTime horaActual = LocalTime.of(16, 15, 00);
		try (MockedStatic<LocalTime> date = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
			date.when(LocalTime::now).thenReturn(horaActual);
			String parking1 = app1.startParking("abc123");
			String parking2 = app2.startParking("bcd234");
			String parking3 = app3.startParking("cde345");
			String parking4 = app4.startParking("def456");
			assertEquals(parking1, "Horario de inicio: 16:15:00, horario máximo: 19:15:00");
			assertEquals(parking2, "Horario de inicio: 16:15:00, horario máximo: 20:00:00");
			assertEquals(parking3, "Saldo insuficiente. Estacionamiento no permitido.");
			assertEquals(parking4, "Horario de inicio: 16:15:00, horario máximo: 20:00:00");
		}
	}
	
	@Test
	public void testEndParking() {
		LocalDateTime startTime1 = LocalDateTime.of(2020, 11, 3, 16, 15, 00);
		LocalDateTime startTime2 = LocalDateTime.of(2020, 11, 3, 16, 46, 00);
		LocalTime horaActual = LocalTime.of(18, 45, 00);
		try (MockedStatic<LocalTime> date = Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)) {
			when(server.getParking(null)).thenReturn(parking);
			when(parking.getStartTime()).thenReturn(startTime1, startTime2);
			date.when(LocalTime::now).thenReturn(horaActual);
			String parking1 = app1.endParking();
			String parking2 = app2.endParking();
			assertEquals(parking1, "Horario de inicio: 16:15:00, horario de finalizacion: 18:45:00, duracion: 2.0 horas, costo: 80.0");
			assertEquals(parking2, "Horario de inicio: 16:46:00, horario de finalizacion: 18:45:00, duracion: 1.0 horas, costo: 40.0");
		}
	};

	@Test
	public void testCalcHoraMaxima() {
		LocalTime horaMaxima1 = app1.calcHoraMaxima(3, LocalTime.of(15, 45, 00));
		LocalTime horaMaxima2 = app2.calcHoraMaxima(6, LocalTime.of(15, 45, 00));
		LocalTime horaMaxima3 = app3.calcHoraMaxima(15, LocalTime.of(15, 45, 00));
		System.out.print(horaMaxima3);
		assertEquals(horaMaxima1, LocalTime.of(18, 45, 00));
		assertEquals(horaMaxima2, LocalTime.of(20, 00, 00));
		assertEquals(horaMaxima3, LocalTime.of(20, 00, 00));
	}
	
	@Test
	public void testCalcHoraActual() {
		
	}
	
	@Test
	public void testCalcDuration() {
		float duration1 = app1.calcDuration(LocalTime.of(16, 45), LocalTime.of(19, 00));
		float duration2 = app2.calcDuration(LocalTime.of(16, 45), LocalTime.of(19, 55));
		assertEquals(duration1, 2, 0);
		assertEquals(duration2, 3, 0);
	}
	
	@Test
	public void testLoadBalance() {
		float saldoPrevio = app3.checkBalance();
		app3.loadBalance(100);
		assertEquals(app3.checkBalance(), saldoPrevio + 100, 0);
	};
	
	@Test
	public void testCheckBalance() {
		assertEquals(app1.checkBalance(), 130, 0);
		assertEquals(app2.checkBalance(), 1000, 0);
		assertEquals(app3.checkBalance(), 10, 0);
	}
	
	@Test
	public void testSetSate() {
		DeactivatedUserAssistance state2 = mock(DeactivatedUserAssistance.class);
		app1.setState(state2);
		assertEquals(app1.getState(), state2);
	};
	
	@Test
	public void testGetState() {
		assertEquals(app1.getState(), state);
	}
	
	@Test
	public void testOn_gps_update() {
		app1.onGpsUpdate("caminando");
		verify(app1.getState()).handle("caminando", app1, "abc123");;
	}
}
