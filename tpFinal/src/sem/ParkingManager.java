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

}
