package sem;
import java.util.ArrayList;

public class SEM {

	private ParkingZoneManager  zones;
	private PurchaseManager     purchases;
	private ParkingManager      parkings;
	private ParkingMonitor      observer;
	
	public SEM(ParkingZoneManager z, PurchaseManager pur, ParkingManager prk, ParkingMonitor obs) {
		zones = z;
		purchases = pur;
		parkings = prk;
		observer = obs;
	}

	public void closeAllParkings() {
		parkings.closeAllParkings();
	}
	
	public void Notify() {
		observer.Notify();
	}
	
	public Parking getParking(String aLicensePlate) {
		return parkings.getParking(aLicensePlate);
	}
	
	public void generateParkingPurchase( MeasuredParkingZone zone, Integer hours, String licensePlate) {
		purchases.generateParkingPurchase(zone, hours, licensePlate);
	}
	
	
	public void registerParking(Parking p) {
		parkings.registerParking(p);
	}
}