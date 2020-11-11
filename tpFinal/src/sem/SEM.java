package sem;
<<<<<<< HEAD

public class SEM {

	private ParkingZoneManager  zoneManager;
	private PurchaseManager     purchaseManager;
	private ParkingManager      parkingManager;
	private ParkingMonitor      parkingMonitor;
	
	public SEM(ParkingZoneManager zoneManager, PurchaseManager purchaseManager, ParkingManager parkingManager, ParkingMonitor parkingMonitor) {
		this.zoneManager = zoneManager;
		this.purchaseManager = purchaseManager;
		this.parkingManager = parkingManager;
		this.parkingMonitor = parkingMonitor;
	}

	public void closeAllParkings() {
		parkingManager.closeAllParkings();
	}
	
	public void notify(Parking aParking) {
		parkingMonitor.Notify();
	}
	
	public Parking getParking(String aLicensePlate) {
		return parkingManager.getParking(aLicensePlate);
	}
	
	public void finishParking(String lisencePlate, String output) {
		parkingManager.finishParking(lisencePlate, output);
	}
	
	public void generateParkingPurchase( MeasuredParkingZone zone, Integer hours, String licensePlate) {
		purchaseManager.generateParkingPurchase(zone, hours, licensePlate);
	}
	
	public Boolean hasValidParking(String licensePlate) {
		return parkingManager.hasValidParking(licensePlate);
	}
	
	public void registerParking(Parking parking) {
		parkingManager.registerParking(parking);
	}

	public ParkingTicket generateParkingTicketFor(String aLicensePlate, MeasuredParkingZone parkingZone, InspectorApp inspectorApp) {
		return parkingManager.generateParkingTicketFor(aLicensePlate,  parkingZone, inspectorApp);
		
=======
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
>>>>>>> refs/heads/RamaDeLautaro
	}
}