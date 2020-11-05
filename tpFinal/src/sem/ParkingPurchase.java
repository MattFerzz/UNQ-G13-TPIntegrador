package sem;

import java.time.LocalDateTime;

public class ParkingPurchase extends Purchase {

	private Integer hours;

	public ParkingPurchase(long id, MeasuredParkingZone zone, LocalDateTime date, Integer hours) {
		super(id, zone, date);
		this.hours = hours;	
	}
	
	public Integer getHours() {
		return hours;
	} 
	
	
	
}
