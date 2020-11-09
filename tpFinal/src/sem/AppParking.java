package sem;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AppParking extends Parking {
	private Integer numberPhone;
	private String output;
	private boolean isFinished = false; 
	
	public AppParking(Integer numberPhone, String output, String licensePlate, LocalDateTime start) {
		super(licensePlate, start);
		this.numberPhone = numberPhone;
		this.output = output;
	}
	
	public boolean isValid() {
		return !isFinished;
	}

	public Integer getNumberPhone() {
		return numberPhone;
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

