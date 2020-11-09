package sem;

public class InspectorApp {
	private MeasuredParkingZone parkingZone;
	private String inspectorName;
	private SEM system;
	public InspectorApp(MeasuredParkingZone parkingZone, String inspectorName, SEM system) {
		this.parkingZone = parkingZone;
		this.inspectorName = inspectorName;
		this.system = system;
	}
	public MeasuredParkingZone getParkingZone() {
		return parkingZone;
	}

	public String getInspectorName() {
		return inspectorName;
	}
	
	public Boolean hasValidParkingCard(String aLicensePlate) {
		return system.hasValidParkingCard(aLicensePlate);
	}
	
	public void generateParkingTicketFor(String aLicensePlate) {
		system.generateParkingTicketFor(aLicensePlate, parkingZone, inspectorName);
	}
	
	
}
