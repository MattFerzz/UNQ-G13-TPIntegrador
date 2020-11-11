<<<<<<< HEAD
package sem;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class ParkingManager {
	private MutableList<Parking> parkings;
	private SEM server;
	private MutableList<ParkingTicket> parkingTickets;
	

	public ParkingManager(SEM server) {
		this.server = server;
		this. parkings = Lists.mutable.empty();
		this. parkingTickets = Lists.mutable.empty();
	}

	public MutableList<Parking> getValidParkings() {
		return parkings.select(eachParking -> eachParking.isValid());
	}
	
	public MutableList<Parking> getParkings() {
		return parkings;
	}

	public void closeAllParkings() {
		this.getValidParkings().each(eachValidParking -> eachValidParking.finish());
	}

	public Boolean hasValidParking(String aLicensePlate) {
		return this.getValidParkings().anySatisfy(eachparking -> eachparking.getLicensePlate() == aLicensePlate);
	}

	public Parking getParking(String aLicensePlate) {
		return this.getValidParkings().detect(eachparking -> eachparking.getLicensePlate() == aLicensePlate);
	}

	public void registerParking(Parking aParking) {
		parkings.add(aParking);
		server.notify(aParking);
	}

	public void finishParking(String aLicensePlate, String output) {
		AppParking parking = (AppParking) this.getParking(aLicensePlate);
		parking.setOutput(output);
		parking.finish();
	}

	public ParkingTicket generateParkingTicketFor(String aLicensePlate, MeasuredParkingZone aParkingZone, InspectorApp inspectorApp) {
		ParkingTicket parkingTicket = new ParkingTicket(aLicensePlate, aParkingZone, inspectorApp);
		parkingTickets.add(parkingTicket);
		return parkingTicket;
	}
	
	public MutableList<ParkingTicket> getParkingTickets(){
		return parkingTickets;
	}
	
	public MutableList<ParkingTicket> getParkingTicketsFor(String aLicensePlate) {
		return parkingTickets.select(eachTicket -> eachTicket.getInfringingLicensePlate() == aLicensePlate);
	}

	public void removeParkingTicket(ParkingTicket aParkingTicket) {
		parkingTickets.remove(aParkingTicket);
	}
}
=======
package sem;

import org.eclipse.collections.api.list.MutableList;

public class ParkingManager {
	private MutableList<Parking> parkings;
	private SEM server;
	
	public MutableList<Parking> getValidParkings() {
		return parkings.select(eachParking -> eachParking.isValid());
	}
	
	public void closeAllParkings() {
		this.getValidParkings().each(eachValidParking -> eachValidParking.finish() );
	}
	
	public Boolean hasValidParking(String aLicensePlate) {
		return this.getValidParkings().anySatisfy(eachparking -> eachparking.getLicensePlate() == aLicensePlate);
	}
	
	public Parking getParking(String aLicensePlate) {
		return this.getValidParkings().detect(eachparking -> eachparking.getLicensePlate() == aLicensePlate);
	}
	
	public void registerParking(Parking aParking) {
		parkings.add(aParking);
		server.notify();
	}

	public void finishParking(String lisencePlate, String output) {
		Parking parking = getParking(lisencePlate);
		parking.finish();
		parking.setOutput(output);
	}
	
}
>>>>>>> refs/heads/RamaDeLautaro
