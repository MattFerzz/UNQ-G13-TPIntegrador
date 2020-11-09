package sem;


public class ParkingCardStore {

	private Coordinate location;
	private MeasuredParkingZone parkingZone;
	private SEM system;
	
	public ParkingCardStore(Coordinate location, MeasuredParkingZone parkingZone) {
		this.parkingZone = parkingZone;
		this.location = location;
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