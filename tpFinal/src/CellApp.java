import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/*
 Metodos:
 	iniciarEstacionamiento(string patente)
 	finalizarEstacionamiento()
 	cargarSaldo(float x)
 	consultarSaldo()
 	setState(string stateName)
 */

public class CellApp {

	private int cellNumber;
	private SEM server;
	private UserAsistance state = new DeactivatedUserAssistance();

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(int cell_number, SEM server) {
		cellNumber  = cell_number;
		this.server = server;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = LocalTime.now();
		horaActual = horaActual.minusSeconds(horaActual.getSecond());
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = horaActual.plusHours((long) horasEnSaldo);
			output = "Hora actual: " + horaActual.format(dateFrmt) + ", Hora máxima: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, licensePlate, horaActual));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = LocalTime.now();
		horaActual = horaActual.minusSeconds(horaActual.getSecond());
		Parking parking = server.getParkingManager().getParking(activeLisencePlate);
		float duration = ((parking.getStart().getMinute() > LocalTime.now().getMinute())) ?
						 LocalTime.now().getHour() - parking.getStart().getHour() :
						 LocalTime.now().getHour() - parking.getStart().getHour() - 1;
		server.getParkingManager().finishParking(activeLisencePlate);
		return "Horario de inicio: " + parking.getStart().format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
		activeLisencePlate = null;
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
	
	public void on_gps_update(String updateType) {
		state.handle(updateType, this);
	}
	
}
