import java.util.ArrayList;

public class SEM {

	private ParkingZoneManager  zones     = new ParkingZoneManager();
	private PurchaseManager     purchases = new PurchaseManager();
	private ParkingManager      parkings  = new ParkingManager();
	private ParkingMonitor      observer  = new ParkingMonitor();
	
	private void closeAllParkings() {
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