package sem;

import java.time.LocalDateTime;

public class BalanceRecharge extends Purchase {

	private Integer phoneNumber; 
	private Float amount;
	
	public BalanceRecharge(long id, MeasuredParkingZone zone, LocalDateTime date, Integer phoneNumber, Float amount) {
		super(id, zone, date);
		this.phoneNumber = phoneNumber;
		this.amount = amount;
	}
	
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	
	public Float getAmount() {
		return amount;
	} 
	
}
