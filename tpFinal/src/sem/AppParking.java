package sem;

import java.time.LocalDateTime;

public class AppParking extends Parking {
	private Integer phoneNumber;
	private String output;
	private boolean isFinished = false; 
	
	public AppParking(Integer phoneNumber, String licensePlate, LocalDateTime startTime) {
		super(licensePlate, startTime);
		this.phoneNumber = phoneNumber;
	}
	
	public boolean isValid() {
		return !isFinished;
	}

	public Integer getNumberPhone() {
		return phoneNumber;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	
	public void finish() {
		isFinished = true;
		this.setFinishTime(LocalDateTime.now());
	}
	
}

