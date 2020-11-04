import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalTime;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class appTest {

	SEM                       server  = mock(SEM.class);
	Parking                   parking = mock(Parking.class);
	ParkingManager            pkm     = mock(ParkingManager.class);
	DeactivatedUserAssistance state   = mock(DeactivatedUserAssistance.class);
	Clock                     clock   = mock(Clock.class);
	
	CellApp app1 = new CellApp(123456, server, clock, "abc123", state);
	CellApp app2 = new CellApp(234567, server, clock, "bcd234", state);
	CellApp app3 = new CellApp(345678, server, clock, "cde345", state);
	CellApp app4 = new CellApp(456789, server, clock, "def456", state);
	
	@BeforeEach
	protected void setUp() {
		app1.loadBalance(130);
		app2.loadBalance(1000);
		app3.loadBalance(10);
		app4.loadBalance(160);
	};
	
	@Test
	public void getActiveLisencePlate() {
		String lisencePlate = app1.getActiveLisencePlate();
		assertNull(lisencePlate);
		Instant fixedClock = Instant.parse("2020-11-03T19:15:00Z");
		when(clock.instant()).thenReturn(fixedClock);
		app1.startParking("abc123");
		lisencePlate = app1.getActiveLisencePlate();
		assertEquals(lisencePlate, "abc123");
	}
	
	@Test
	public void startParking() {
		Instant fixedClock = Instant.parse("2020-11-03T19:15:00Z");
		when(clock.instant()).thenReturn(fixedClock);
		String parking1 = app1.startParking("abc123");
		String parking2 = app2.startParking("bcd234");
		String parking3 = app3.startParking("cde345");
		String parking4 = app4.startParking("def456");
		assertEquals(parking1, "Horario de inicio: 16:15:00, horario máximo: 19:15:00");
		assertEquals(parking2, "Horario de inicio: 16:15:00, horario máximo: 20:00:00");
		assertEquals(parking3, "Saldo insuficiente. Estacionamiento no permitido.");
		assertEquals(parking4, "Horario de inicio: 16:15:00, horario máximo: 20:00:00");
	}
	
	@Test
	public void endParking() {
		Instant fixedClock = Instant.parse("2020-11-03T21:45:00Z");
		when(server.getParkingManager()).thenReturn(pkm);
		when(pkm.getParking(null)).thenReturn(parking);
		when(parking.getStart()).thenReturn(LocalTime.of(16,15,00), LocalTime.of(16,46,00));
		when(clock.instant()).thenReturn(fixedClock);
		String parking1 = app1.endParking();
		String parking2 = app2.endParking();
		assertEquals(parking1, "Horario de inicio: 16:15:00, horario de finalizacion: 18:45:00, duracion: 2.0 horas, costo: 80.0");
		assertEquals(parking2, "Horario de inicio: 16:46:00, horario de finalizacion: 18:45:00, duracion: 1.0 horas, costo: 40.0");
	};

	@Test
	public void loadBalance() {
		float saldoPrevio = app3.checkBalance();
		app3.loadBalance(100);
		assertEquals(app3.checkBalance(), saldoPrevio + 100, 0);
	};
	
	@Test
	public void checkBalance() {
		assertEquals(app1.checkBalance(), 130, 0);
		assertEquals(app2.checkBalance(), 1000, 0);
		assertEquals(app3.checkBalance(), 10, 0);
	}
	
	@Test
	public void setSate() {
		DeactivatedUserAssistance state2 = mock(DeactivatedUserAssistance.class);
		app1.setState(state2);
		assertEquals(app1.getState(), state2);
	};
	
	@Test
	public void getState() {
		assertEquals(app1.getState(), state);
	}
	
	@Test
	public void on_gps_update() {
		app1.on_gps_update("caminando");
		verify(app1.getState()).handle("caminando", app1, "abc123");;
	}
}
