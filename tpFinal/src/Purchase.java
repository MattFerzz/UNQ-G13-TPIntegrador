package estacionamiento;
import java.time.LocalDateTime;

public class Purchase {

	private long id;
	private MeasuredParkingZone zone; 
	private LocalDateTime date;
	
	public Purchase(long id, MeasuredParkingZone zone, LocalDateTime date) {
		this.id = id;
		this.zone = zone;
		this.date = date;
	}
	
	public long getId() {
		return id;
	}

	public MeasuredParkingZone getZone() {
		return zone;
	}

	public LocalDateTime getDate() {
		return date;
	}

}

