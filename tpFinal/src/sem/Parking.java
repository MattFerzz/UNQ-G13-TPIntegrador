package sem;

import java.time.LocalDateTime;

public abstract class Parking {
	private String licensePlate; 
	private LocalDateTime startTime;
	private LocalDateTime finishTime;
	
	public Parking(String licensePlate, LocalDateTime start) {
		this.licensePlate = licensePlate;
		this.startTime = start;
	}

	public abstract boolean isValid();
	
	public abstract void finish();

	
	public String getLicensePlate() {
		return licensePlate;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public LocalDateTime getFinishTime() {
		return finishTime;
	}
	
	protected void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

}
