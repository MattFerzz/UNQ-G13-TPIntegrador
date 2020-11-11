package sem;
import java.time.LocalDateTime;

public class ParkingTicket {
	private String infringingLicensePlate;
	private MeasuredParkingZone parkingZone;
	private InspectorApp reportingInspectorApp;
	private LocalDateTime timestamp;
	
	public ParkingTicket(String infringingLicensePlate, MeasuredParkingZone parkingZone, InspectorApp reportingInspectorApp) {
		this.infringingLicensePlate = infringingLicensePlate;
		this.parkingZone = parkingZone;
		this.reportingInspectorApp = reportingInspectorApp;
		this.timestamp = LocalDateTime.now();
	}

	public String getInfringingLicensePlate() {
		return infringingLicensePlate;
	}
	
	public InspectorApp getReportingInspectorApp() {
		return reportingInspectorApp;
	}

	public MeasuredParkingZone getParkingZone() {
		return parkingZone;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}
