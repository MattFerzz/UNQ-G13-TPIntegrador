package sem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CellPhoneApp {

	private Integer phoneNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLicensePlate;

	private Float balance = 0F;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellPhoneApp(Integer cell_number, SEM server, String defaultLisencePlate, UserAssistance state) {
		phoneNumber  = cell_number;
		this.server = server;
		this.defaultLicensePlate = defaultLisencePlate;
		this.state = state;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime currentTime = currentTimeWithoutSeconds();
		if (balance >= 40) {
			Double availableHoursForCurrentBalance = Math.floor(balance/40);
			LocalTime limitTime = latestPossibleTimeFor(availableHoursForCurrentBalance, currentTime);
			output = "Horario de inicio: " + currentTime.format(dateFormatter) + ", horario máximo: " + limitTime.format(dateFormatter);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(phoneNumber, licensePlate, LocalDateTime.of(LocalDate.now(), currentTime)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime currentTime = currentTimeWithoutSeconds();
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime parkingStartTime = parking.getStartTime().toLocalTime();
		Float duration = calculateDuration(parkingStartTime, currentTime);
		String output = "Horario de inicio: " + parkingStartTime.format(dateFormatter) + ", horario de finalizacion: " + currentTime.format(dateFormatter) + ", duracion: " + duration + " horas, costo: " + duration*40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}
	
	public LocalTime latestPossibleTimeFor(double anHoursAmount, LocalTime aStartingTime) {
		LocalTime ret;
		if (anHoursAmount + aStartingTime.getHour() >= 24 || aStartingTime.plusHours((long) anHoursAmount).isAfter(LocalTime.of(20,00))) {
			ret = LocalTime.of(20,00);
		} else {
			ret = aStartingTime.plusHours((long) anHoursAmount);
		}
		return ret;
	}
	
	public LocalTime currentTimeWithoutSeconds() {
		LocalTime ret = LocalTime.now();
		ret = ret.minusSeconds(ret.getSecond());
		ret = ret.minusNanos(ret.getNano());
		return ret;
	}
	
	public float calculateDuration(LocalTime aStartingTime, LocalTime anEndingTime) {
		float ret;
		if(aStartingTime.getMinute() > anEndingTime.getMinute()) {
			ret = anEndingTime.getHour() - aStartingTime.getHour() - 1;
		} else {
			ret = anEndingTime.getHour() - aStartingTime.getHour();
		}
		return ret;
	}
	
	public void loadBalance(Float x) {
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLicensePlate);
	}
	
}