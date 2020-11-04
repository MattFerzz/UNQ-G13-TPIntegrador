package sec;


public class ParkingCardStore {
	private Coordinate location;
	private MeasuredParkingZone parkingZone;
	private System system;
	
	public ParkingCardStore(Coordinate location, MeasuredParkingZone parkingZone) {
		this.parkingZone = parkingZone;
		this.location = location;
	}
	
	public void sellParkingCard(String aLicensePlate, Integer hoursAmount) {
		system.getPurchaseManager.generateParkingPurchase(parkingZone ,aLicensePlate, hoursAmount);
	}
}