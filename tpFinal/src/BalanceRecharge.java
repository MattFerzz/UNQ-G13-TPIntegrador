package estacionamiento;

import java.time.LocalDateTime;

public class BalanceRecharge extends Purchase {

	private Integer phoneNumber; 
	private float amount;
	
	public BalanceRecharge(long id, MeasuredParkingZone zone, LocalDateTime date, Integer phoneNumber, float amount) {
		super(id, zone, date);
		this.phoneNumber = phoneNumber;
		this.amount = amount;
	}
	
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	
	public float getMount() {
		return amount;
	} 
	
}
