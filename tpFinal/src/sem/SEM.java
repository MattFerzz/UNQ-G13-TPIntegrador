package sem;

import org.eclipse.collections.api.list.MutableList;

public class SEM {

	private ParkingZoneManager  zoneManager;
	private PurchaseManager     purchaseManager;
	private ParkingManager      parkingManager;
	private ParkingMonitor      parkingMonitor;
	private CellPhoneAppManager cellPhoneAppManager;

	public SEM(ParkingZoneManager zoneManager, PurchaseManager purchaseManager, ParkingManager parkingManager,
			ParkingMonitor parkingMonitor, CellPhoneAppManager cellPhoneAppManager) {
		this.zoneManager = zoneManager;
		this.purchaseManager = purchaseManager;
		this.parkingManager = parkingManager;
		this.parkingMonitor = parkingMonitor;
		this.cellPhoneAppManager = cellPhoneAppManager;
	}

	public void closeAllParkings() {
		parkingManager.closeAllParkings();
	}

	public void notify(Parking aParking) {
		parkingMonitor.notify(aParking);
	}

	public MutableList<MeasuredParkingZone> getParkingZones() {
		return zoneManager.getParkingZones();
	}

	public Parking getParking(String aLicensePlate) {
		return parkingManager.getParking(aLicensePlate);
	}

	public void finishParking(String lisencePlate, String output) {
		parkingManager.finishParking(lisencePlate, output);
	}

	public void generateParkingPurchase(MeasuredParkingZone zone, Integer hours, String licensePlate) {
		purchaseManager.generateParkingPurchase(zone, hours, licensePlate);
	}

	public Boolean hasValidParking(String licensePlate) {
		return parkingManager.hasValidParking(licensePlate);
	}

	public void registerParking(Parking parking) {
		parkingManager.registerParking(parking);
	}

	public ParkingTicket generateParkingTicketFor(String aLicensePlate, MeasuredParkingZone parkingZone,
			InspectorApp inspectorApp) {
		return parkingManager.generateParkingTicketFor(aLicensePlate, parkingZone, inspectorApp);

	}
	
	public void loadBalance(Integer aPhoneNumber, Float anAmount) {
		cellPhoneAppManager.loadBalance(aPhoneNumber, anAmount);
	}
}