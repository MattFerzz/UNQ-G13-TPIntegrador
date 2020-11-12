package sem;

import java.time.LocalDateTime;

public class LocalParking extends Parking {
	private Purchase purchase;

	public LocalParking(String licensePlate, LocalDateTime start, Purchase purchase, LocalDateTime finishTime) {
		super(licensePlate, start);
		this.purchase = purchase;
		this.setFinishTime(finishTime);
	}

	public boolean isValid() {
		return LocalDateTime.now().isAfter(getFinishTime());
	}

	public Purchase getPurchase() {
		return purchase;
	}

	@Override
	public void finish() {
		// Do nothing

	}

}
