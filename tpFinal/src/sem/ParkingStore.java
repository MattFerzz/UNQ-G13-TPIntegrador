package sem;


public class ParkingStore {

	private Coordinate location;
	private MeasuredParkingZone parkingZone;
	private SEM system;
	
	public ParkingStore(Coordinate location, MeasuredParkingZone parkingZone, SEM system) {
		this.parkingZone = parkingZone;
		this.location = location;
		this.system = system;
	}
	
	public void sellParkingCard(String aLicensePlate, Integer hoursAmount) {
		system.generateParkingPurchase(parkingZone ,hoursAmount, aLicensePlate);
	}
	public Coordinate getLocation() {
		return location;
	}

	public MeasuredParkingZone getParkingZone() {
		return parkingZone;
	}
}