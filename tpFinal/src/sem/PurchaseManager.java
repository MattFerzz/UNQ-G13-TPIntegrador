package sem;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class PurchaseManager {

	private SEM server; 
	private long controlNumber = 0;
	private ArrayList<Purchase> purchases = new ArrayList<Purchase>();

	public PurchaseManager(SEM server){
		this.server = server;
	}

	public void generateParkingPurchase( MeasuredParkingZone zone, Integer hours, String licensePlate){
		ParkingPurchase purchase = new ParkingPurchase(generateNextControlNumber(), zone, LocalDateTime.now(), hours);
		purchases.add(purchase);
		
		LocalParking parking = new LocalParking(licensePlate, LocalDateTime.now(), purchase, LocalDateTime.now().plusHours((long)hours), Clock.systemDefaultZone());
		server.registerParking(parking);	
	}

	public void generateBalanceRecharge(MeasuredParkingZone zone, Integer phoneNumber, Float amount) {
		BalanceRecharge purchase = new BalanceRecharge(generateNextControlNumber(), zone, LocalDateTime.now(), phoneNumber, amount);
		purchases.add(purchase);

	}
	
	public long generateNextControlNumber(){
		controlNumber = controlNumber + 1;
		return controlNumber;
	}
	
	public ArrayList<Purchase> getPurchases(){
		return purchases;
	}
	
	public long getControlNumber() {
		return controlNumber;
	}
}

