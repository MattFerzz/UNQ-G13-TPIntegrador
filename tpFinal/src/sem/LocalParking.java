package sem;

import java.time.LocalDateTime;
import java.time.Clock;
import java.time.ZoneId;

public class LocalParking extends Parking {
	private Purchase purchase; 
	public LocalParking (String licensePlate, LocalDateTime start, Purchase purchase, LocalDateTime finish) {
		super(licensePlate, start);
		this.purchase = purchase;
		setFinishTime(finish);
	}
	
	public void finish(){}
	
	public void setOutput(String o){}
	
	public boolean isValid() {
		return LocalDateTime.now().isAfter(getFinishTime());
	}

}
