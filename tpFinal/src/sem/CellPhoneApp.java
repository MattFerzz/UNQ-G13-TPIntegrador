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

	public CellPhoneApp(Integer phoneNumber, SEM server, String defaultLisencePlate, UserAssistance state) {
		this.phoneNumber = phoneNumber;
		this.server = server;
		this.defaultLicensePlate = defaultLisencePlate;
		this.state = state;
	}

	public String startParking(String licensePlate) {
		String output;
		LocalTime currentTime = currentTimeWithoutSeconds();
		if (balance >= 40) {
			Double availableHoursForCurrentBalance = Math.floor(balance / 40);
			LocalTime limitTime = latestPossibleTimeFor(availableHoursForCurrentBalance, currentTime);
			output = "Horario de inicio: " + currentTime.format(dateFormatter) + ", horario máximo: "
					+ limitTime.format(dateFormatter);
			activeLisencePlate = licensePlate;
			server.registerParking(
					new AppParking(phoneNumber, licensePlate, LocalDateTime.of(LocalDate.now(), currentTime)));
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
		String output = "Horario de inicio: " + parkingStartTime.format(dateFormatter) + ", horario de finalizacion: "
				+ currentTime.format(dateFormatter) + ", duracion: " + duration + " horas, costo: " + duration * 40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}

	public LocalTime latestPossibleTimeFor(double anHoursAmount, LocalTime aStartingTime) {
		LocalTime latestTime;
		if (anHoursAmount + aStartingTime.getHour() >= 24
				|| aStartingTime.plusHours((long) anHoursAmount).isAfter(LocalTime.of(20, 00))) {
			latestTime = LocalTime.of(20, 00);
		} else {
			latestTime = aStartingTime.plusHours((long) anHoursAmount);
		}
		return latestTime;
	}

	public LocalTime currentTimeWithoutSeconds() {
		LocalTime currentTime = LocalTime.now();
		currentTime = currentTime.minusSeconds(currentTime.getSecond());
		currentTime = currentTime.minusNanos(currentTime.getNano());
		return currentTime;
	}

	public float calculateDuration(LocalTime aStartingTime, LocalTime anEndingTime) {
		float duration;
		if (aStartingTime.getMinute() > anEndingTime.getMinute()) {
			duration = anEndingTime.getHour() - aStartingTime.getHour() - 1;
		} else {
			duration = anEndingTime.getHour() - aStartingTime.getHour();
		}
		return duration;
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

	public String getDefaultLicensePlate() {
		return defaultLicensePlate;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLicensePlate);
	}

}