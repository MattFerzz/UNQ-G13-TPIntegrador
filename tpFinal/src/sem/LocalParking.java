package sem;

import java.time.LocalDateTime;
import java.time.Clock;
import java.time.ZoneId;

public class LocalParking extends Parking {
	private Purchase purchase; 
	public LocalParking (String licensePlate, LocalDateTime start, Purchase purchase, LocalDateTime finishTime) {
		super(licensePlate, start);
		this.purchase = purchase;
		this.setFinishTime(finishTime);
	}

	public boolean isValid() {
		return LocalDateTime.now().isAfter(getFinishTime());
	}

	@Override
	public void finish() {
	//Do nothing
		
	}

}
