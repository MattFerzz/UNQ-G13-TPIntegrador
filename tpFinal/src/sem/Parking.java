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

	public abstract void setOutput(String o);

	
	public String getLicensePlate() {
		return licensePlate;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public LocalDateTime getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(LocalDateTime finish) {
		this.finishTime = finish;
	}	
}
