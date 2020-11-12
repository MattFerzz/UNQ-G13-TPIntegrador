package sem;

import java.time.LocalDateTime;

public class Purchase {

	private Long id;
	private MeasuredParkingZone zone; 
	private LocalDateTime dateTime;
	
	public Purchase(Long id, MeasuredParkingZone parkingZone, LocalDateTime dateTime) {
		this.id = id;
		this.zone = parkingZone;
		this.dateTime = dateTime;
	}
	
	public long getId() {
		return id;
	}

	public MeasuredParkingZone getParkingZone() {
		return zone;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

}

