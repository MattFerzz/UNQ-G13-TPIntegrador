<<<<<<< HEAD
package sem;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.*;
import org.eclipse.collections.api.tuple.*;

public class MeasuredParkingZone {

	private MutableList<ParkingStore> parkingStores;
	private String description;
	private Pair<Coordinate, Coordinate> boundaries;
	public MeasuredParkingZone( String description,Pair<Coordinate, Coordinate> boundaries) {
		this.description = description;
		this.boundaries = boundaries;
		this.parkingStores = Lists.mutable.empty();
	}

	public void registerParkingStore(Coordinate location) {
		parkingStores.add(new ParkingStore(location, this));
	}
	
	public MutableList<ParkingStore> getParkingStores() {
		return parkingStores;
	}

	public String getdesctiption() {
		return description;
	}

	public Pair<Coordinate, Coordinate> getBoundaries() {
		return boundaries;
	}
	

=======
package sem;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.*;
import org.eclipse.collections.api.tuple.*;

public class MeasuredParkingZone {

	private MutableList<ParkingCardStore> parkingCardStores;
	private String description;
	private Pair<Coordinate, Coordinate> boundaries;
	public MeasuredParkingZone( String description,Pair<Coordinate, Coordinate> boundaries) {
		this.description = description;
		this.boundaries = boundaries;
		this.parkingCardStores = Lists.mutable.empty();
	}

	public void registerParkingCardStore(Coordinate location) {
		parkingCardStores.add(new ParkingCardStore(location, this));
	}
	
	public MutableList<ParkingCardStore> getParkingCardStores() {
		return parkingCardStores;
	}

	public String getdesctiption() {
		return description;
	}

	public Pair<Coordinate, Coordinate> getBoundaries() {
		return boundaries;
	}
	
	public void registerParking(Parking parking) {
		parkings.add(parking);
	}
	
	public MutableList<Parking> getParkings(){
		return parkings;
	}
	
	public MutableList<Parking> getActiveParkings(){
		 return parkings.select(eachParking -> eachParking.isValid());
	}

>>>>>>> refs/heads/RamaDeLautaro
}