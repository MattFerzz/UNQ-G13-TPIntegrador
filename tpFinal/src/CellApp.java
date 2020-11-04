import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.Clock;

public class CellApp {

	private int cellNumber;
	private SEM server;
	private UserAssistance state = new DeactivatedUserAssistance();

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	private Clock clock;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(int cell_number, SEM server, Clock clock) {
		cellNumber  = cell_number;
		this.server = server;
		this.clock = clock;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = LocalTime.now(Clock.fixed(clock.instant(), ZoneId.systemDefault()));
		horaActual = horaActual.minusSeconds(horaActual.getSecond());
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = (horasEnSaldo >= 24 || horaActual.plusHours((long) horasEnSaldo).isAfter(LocalTime.of(20,00))) ?
					               LocalTime.of(20,00):
                                   horaActual.plusHours((long) horasEnSaldo);
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario m�ximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, licensePlate, horaActual));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = LocalTime.now(Clock.fixed(clock.instant(), ZoneId.systemDefault()));
		horaActual = horaActual.minusSeconds(horaActual.getSecond());
		horaActual = horaActual.minusNanos(horaActual.getNano());
		Parking parking = server.getParkingManager().getParking(activeLisencePlate);
		LocalTime pStart = parking.getStart();
		float duration = (pStart.getMinute() > horaActual.getMinute()) ?
				         horaActual.getHour() - pStart.getHour() - 1:
					     horaActual.getHour() - pStart.getHour();
		server.getParkingManager().finishParking(activeLisencePlate);
		activeLisencePlate = null;
		return "Horario de inicio: " + pStart.format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
	}
	
	public void loadBalance(float x) {
		balance = balance + x;
	}
	
	public float checkBalance() {
		return balance;
	}
	
	public void setState(UserAssistance s) {
		state = s;
	}
	
	public UserAssistance getState() {
		return state;
	}
	
	public void on_gps_update(String updateType) {
		state.handle(updateType, this);
	}
	
}
