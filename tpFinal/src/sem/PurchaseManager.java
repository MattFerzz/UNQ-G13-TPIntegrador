package sem;

import java.time.LocalDateTime;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;


public class PurchaseManager {

	private SEM server; 
	private long controlNumber = 0;
	private MutableList<Purchase> purchases;

	public PurchaseManager(SEM server){
		this.server = server;
		this.purchases = Lists.mutable.empty();
	}

	public void generateParkingPurchase( MeasuredParkingZone zone, Integer hours, String licensePlate){
		ParkingPurchase purchase = new ParkingPurchase(generateNextControlNumber(), zone, LocalDateTime.now(), hours);
		purchases.add(purchase);
		
		LocalParking parking = new LocalParking(licensePlate, LocalDateTime.now(), purchase, LocalDateTime.now().plusHours((long)hours));
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
	
	public MutableList<Purchase> getPurchases(){
		return purchases;
	}
	
	public long getControlNumber() {
		return controlNumber;
	}
}

