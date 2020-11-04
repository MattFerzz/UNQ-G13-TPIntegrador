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
	
	public ParkingManager getParkingManager() {
		return parkings;
	}
	
	public PurchaseManager getPurchaseManager() {
		return purchases;
	}
	
	public void registerPurchase(Purchase p) {
		purchases.registerPurchase(p);
	}
	
	public void registerParking(Parking p) {
		parkings.registerParking(p);
	}
}