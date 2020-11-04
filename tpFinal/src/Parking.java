package estacionamiento;
import java.time.LocalDateTime;

public abstract class Parking {
	private String licensePlate; 
	private LocalDateTime start;
	private LocalDateTime finish;
	
	public Parking(String licensePlate, LocalDateTime start) {
		this.licensePlate = licensePlate;
		this.start = start;
	}

	public abstract boolean isValid();

	
	public String getLicensePlate() {
		return licensePlate;
	}


	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getFinish() {
		return finish;
	}
	
	public void setFinish(LocalDateTime finish) {
		this.finish = finish;
	}

}
