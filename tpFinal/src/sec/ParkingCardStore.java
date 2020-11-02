package sec;

public class ParkingCardStore {
	private Coordinate location;
	private MeasuredParkingZone parkingZone;
	
	public ParkingCardStore(Coordinate location, MeasuredParkingZone parkingZone) {
		this.parkingZone = parkingZone;
		this.location = location;
	}

}
