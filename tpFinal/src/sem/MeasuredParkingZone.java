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
	

}