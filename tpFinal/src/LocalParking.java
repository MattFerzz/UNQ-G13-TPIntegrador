import java.time.LocalDateTime;
import java.time.Clock;
import java.time.ZoneId;

public class LocalParking extends Parking {
	private Purchase purchase; 
	private Clock clock;
	public LocalParking (String licensePlate, LocalDateTime start, Purchase purchase, LocalDateTime finish, Clock clock) {
		super(licensePlate, start);
		this.purchase = purchase;
		setFinish(finish);
		this.clock = clock;
	}
	
	public boolean isValid() {
		return LocalDateTime.now(Clock.fixed(clock.instant(), ZoneId.systemDefault())).isAfter(getFinish());
	}

}
