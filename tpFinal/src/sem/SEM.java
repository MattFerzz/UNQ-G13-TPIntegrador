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
	
	public void finishParking(String lisencePlate, String output) {
		parkings.finishParking(lisencePlate, output);
	}
	
	public void generateParkingPurchase( MeasuredParkingZone zone, Integer hours, String licensePlate) {
		purchases.generateParkingPurchase(zone, hours, licensePlate);
	}
	
	public Boolean hasValidParkingCard(String licensePlate) {
		return parkings.hasValidParking(licensePlate);
	}
	
	public void registerParking(Parking p) {
		parkings.registerParking(p);
	}
}