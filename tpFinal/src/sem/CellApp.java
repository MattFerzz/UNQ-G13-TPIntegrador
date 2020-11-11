package sem;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Clock;

public class CellApp {

	private Integer cellNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLisencePlate;

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	private Clock clock;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(Integer cell_number, SEM server, Clock clock, String defaultLisencePlate, UserAssistance state) {
		cellNumber  = cell_number;
		this.server = server;
		this.clock = clock;
		this.defaultLisencePlate = defaultLisencePlate;
		this.state = state;
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
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario máximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, licensePlate, LocalDateTime.of(LocalDate.now(Clock.fixed(clock.instant(), ZoneId.systemDefault())), horaActual)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = LocalTime.now(Clock.fixed(clock.instant(), ZoneId.systemDefault()));
		horaActual = horaActual.minusSeconds(horaActual.getSecond());
		horaActual = horaActual.minusNanos(horaActual.getNano());
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime pStart = parking.getStart();
		float duration = (pStart.getMinute() > horaActual.getMinute()) ?
				         horaActual.getHour() - pStart.getHour() - 1:
					     horaActual.getHour() - pStart.getHour();
		server.finishParking(activeLisencePlate);
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLisencePlate);
	}
	
}
package sem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CellApp {

	private Integer cellNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLisencePlate;

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(Integer cell_number, SEM server, String defaultLisencePlate, UserAssistance state) {
		cellNumber  = cell_number;
		this.server = server;
		this.defaultLisencePlate = defaultLisencePlate;
		this.state = state;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = calcHoraActual();
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = calcHoraMaxima(horasEnSaldo, horaActual);
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario máximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, output, licensePlate, LocalDateTime.of(LocalDate.now(), horaActual)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = calcHoraActual();
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime pStart = parking.getStartTime().toLocalTime();
		float duration = calcDuration(pStart, horaActual);
		String output = "Horario de inicio: " + pStart.format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}
	
	public LocalTime calcHoraMaxima(double horasEnSaldo, LocalTime horaActual) {
		LocalTime ret;
		if (horasEnSaldo + horaActual.getHour() >= 24 || horaActual.plusHours((long) horasEnSaldo).isAfter(LocalTime.of(20,00))) {
			ret = LocalTime.of(20,00);
		} else {
			ret = horaActual.plusHours((long) horasEnSaldo);
		}
		return ret;
	}
	
	public LocalTime calcHoraActual() {
		LocalTime ret = LocalTime.now();
		ret = ret.minusSeconds(ret.getSecond());
		ret = ret.minusNanos(ret.getNano());
		return ret;
	}
	
	public float calcDuration(LocalTime pStart, LocalTime horaActual) {
		float ret;
		if(pStart.getMinute() > horaActual.getMinute()) {
			ret = horaActual.getHour() - pStart.getHour() - 1;
		} else {
			ret = horaActual.getHour() - pStart.getHour();
		}
		return ret;
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLisencePlate);
	}
	
}
package sem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CellApp {

	private Integer cellNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLisencePlate;

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(Integer cell_number, SEM server, String defaultLisencePlate, UserAssistance state) {
		cellNumber  = cell_number;
		this.server = server;
		this.defaultLisencePlate = defaultLisencePlate;
		this.state = state;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = calcHoraActual();
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = calcHoraMaxima(horasEnSaldo, horaActual);
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario máximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, output, licensePlate, LocalDateTime.of(LocalDate.now(), horaActual)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = calcHoraActual();
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime pStart = parking.getStartTime().toLocalTime();
		float duration = calcDuration(pStart, horaActual);
		String output = "Horario de inicio: " + pStart.format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}
	
	public LocalTime calcHoraMaxima(double horasEnSaldo, LocalTime horaActual) {
		LocalTime ret;
		if (horasEnSaldo + horaActual.getHour() >= 24 || horaActual.plusHours((long) horasEnSaldo).isAfter(LocalTime.of(20,00))) {
			ret = LocalTime.of(20,00);
		} else {
			ret = horaActual.plusHours((long) horasEnSaldo);
		}
		return ret;
	}
	
	public LocalTime calcHoraActual() {
		LocalTime ret = LocalTime.now();
		ret = ret.minusSeconds(ret.getSecond());
		ret = ret.minusNanos(ret.getNano());
		return ret;
	}
	
	public float calcDuration(LocalTime pStart, LocalTime horaActual) {
		float ret;
		if(pStart.getMinute() > horaActual.getMinute()) {
			ret = horaActual.getHour() - pStart.getHour() - 1;
		} else {
			ret = horaActual.getHour() - pStart.getHour();
		}
		return ret;
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLisencePlate);
	}
	
}
package sem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CellApp {

	private Integer cellNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLisencePlate;

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(Integer cell_number, SEM server, String defaultLisencePlate, UserAssistance state) {
		cellNumber  = cell_number;
		this.server = server;
		this.defaultLisencePlate = defaultLisencePlate;
		this.state = state;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = calcHoraActual();
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = calcHoraMaxima(horasEnSaldo, horaActual);
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario máximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, output, licensePlate, LocalDateTime.of(LocalDate.now(), horaActual)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = calcHoraActual();
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime pStart = parking.getStartTime().toLocalTime();
		float duration = calcDuration(pStart, horaActual);
		String output = "Horario de inicio: " + pStart.format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}
	
	public LocalTime calcHoraMaxima(double horasEnSaldo, LocalTime horaActual) {
		LocalTime ret;
		if (horasEnSaldo + horaActual.getHour() >= 24 || horaActual.plusHours((long) horasEnSaldo).isAfter(LocalTime.of(20,00))) {
			ret = LocalTime.of(20,00);
		} else {
			ret = horaActual.plusHours((long) horasEnSaldo);
		}
		return ret;
	}
	
	public LocalTime calcHoraActual() {
		LocalTime ret = LocalTime.now();
		ret = ret.minusSeconds(ret.getSecond());
		ret = ret.minusNanos(ret.getNano());
		return ret;
	}
	
	public float calcDuration(LocalTime pStart, LocalTime horaActual) {
		float ret;
		if(pStart.getMinute() > horaActual.getMinute()) {
			ret = horaActual.getHour() - pStart.getHour() - 1;
		} else {
			ret = horaActual.getHour() - pStart.getHour();
		}
		return ret;
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLisencePlate);
	}
	
}
package sem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CellApp {

	private Integer cellNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLisencePlate;

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(Integer cell_number, SEM server, String defaultLisencePlate, UserAssistance state) {
		cellNumber  = cell_number;
		this.server = server;
		this.defaultLisencePlate = defaultLisencePlate;
		this.state = state;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = calcHoraActual();
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = calcHoraMaxima(horasEnSaldo, horaActual);
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario máximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, output, licensePlate, LocalDateTime.of(LocalDate.now(), horaActual)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = calcHoraActual();
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime pStart = parking.getStartTime().toLocalTime();
		float duration = calcDuration(pStart, horaActual);
		String output = "Horario de inicio: " + pStart.format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}
	
	public LocalTime calcHoraMaxima(double horasEnSaldo, LocalTime horaActual) {
		LocalTime ret;
		if (horasEnSaldo + horaActual.getHour() >= 24 || horaActual.plusHours((long) horasEnSaldo).isAfter(LocalTime.of(20,00))) {
			ret = LocalTime.of(20,00);
		} else {
			ret = horaActual.plusHours((long) horasEnSaldo);
		}
		return ret;
	}
	
	public LocalTime calcHoraActual() {
		LocalTime ret = LocalTime.now();
		ret = ret.minusSeconds(ret.getSecond());
		ret = ret.minusNanos(ret.getNano());
		return ret;
	}
	
	public float calcDuration(LocalTime pStart, LocalTime horaActual) {
		float ret;
		if(pStart.getMinute() > horaActual.getMinute()) {
			ret = horaActual.getHour() - pStart.getHour() - 1;
		} else {
			ret = horaActual.getHour() - pStart.getHour();
		}
		return ret;
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLisencePlate);
	}
	
}
package sem;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class CellApp {

	private Integer cellNumber;
	private SEM server;
	private UserAssistance state;
	private String defaultLisencePlate;

	private float balance = 0;
	private DateTimeFormatter dateFrmt = DateTimeFormatter.ISO_LOCAL_TIME;
	private String activeLisencePlate;
	
	public String getActiveLisencePlate() {
		return activeLisencePlate;
	}
	
	public CellApp(Integer cell_number, SEM server, String defaultLisencePlate, UserAssistance state) {
		cellNumber  = cell_number;
		this.server = server;
		this.defaultLisencePlate = defaultLisencePlate;
		this.state = state;
	}
	
	public String startParking(String licensePlate) {
		String output;
		LocalTime horaActual = calcHoraActual();
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = calcHoraMaxima(horasEnSaldo, horaActual);
			output = "Horario de inicio: " + horaActual.format(dateFrmt) + ", horario máximo: " + horaMaxima.format(dateFrmt);
			activeLisencePlate = licensePlate;
			server.registerParking(new AppParking(cellNumber, output, licensePlate, LocalDateTime.of(LocalDate.now(), horaActual)));
		} else {
			output = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return output;
	}
	
	public String endParking() {
		LocalTime horaActual = calcHoraActual();
		Parking parking = server.getParking(activeLisencePlate);
		LocalTime pStart = parking.getStartTime().toLocalTime();
		float duration = calcDuration(pStart, horaActual);
		String output = "Horario de inicio: " + pStart.format(dateFrmt) + ", horario de finalizacion: " + horaActual.format(dateFrmt) + ", duracion: " + duration + " horas, costo: " + duration*40;
		server.finishParking(activeLisencePlate, output);
		activeLisencePlate = null;
		return output;
	}
	
	public LocalTime calcHoraMaxima(double horasEnSaldo, LocalTime horaActual) {
		LocalTime ret;
		if (horasEnSaldo + horaActual.getHour() >= 24 || horaActual.plusHours((long) horasEnSaldo).isAfter(LocalTime.of(20,00))) {
			ret = LocalTime.of(20,00);
		} else {
			ret = horaActual.plusHours((long) horasEnSaldo);
		}
		return ret;
	}
	
	public LocalTime calcHoraActual() {
		LocalTime ret = LocalTime.now();
		ret = ret.minusSeconds(ret.getSecond());
		ret = ret.minusNanos(ret.getNano());
		return ret;
	}
	
	public float calcDuration(LocalTime pStart, LocalTime horaActual) {
		float ret;
		if(pStart.getMinute() > horaActual.getMinute()) {
			ret = horaActual.getHour() - pStart.getHour() - 1;
		} else {
			ret = horaActual.getHour() - pStart.getHour();
		}
		return ret;
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
	
	public void onGpsUpdate(String updateType) {
		state.handle(updateType, this, defaultLisencePlate);
	}
	
}
