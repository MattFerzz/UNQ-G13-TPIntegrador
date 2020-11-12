package sem;

import java.time.LocalDateTime;

public class ParkingPurchase extends Purchase {

	private Integer hours;

	public ParkingPurchase(Long id, MeasuredParkingZone parkingZone, LocalDateTime date, Integer hours) {
		super(id, parkingZone, date);
		this.hours = hours;	
	}
	
	public Integer getHours() {
		return hours;
	} 
	
	
	
}
