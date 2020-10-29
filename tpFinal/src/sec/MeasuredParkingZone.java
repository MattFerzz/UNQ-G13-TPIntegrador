package sec;
 
import org.eclipse.collections.api.list.*;
import org.eclipse.collections.api.tuple.*;
 
public class MeasuredParkingZone {
 
	private MutableList<ParkingCardStore> parkingCardStores;
	private Integer id;
	private Pair<Coordinate, Coordinate> boundaries;
 
	public MeasuredParkingZone(MutableList<ParkingCardStore> parkingCardStores, Integer id,
			Pair<Coordinate, Coordinate> boundaries) {
		this.parkingCardStores = parkingCardStores;
		this.id = id;
		this.boundaries = boundaries;
	}
 
	public MutableList<ParkingCardStore> getParkingCardStores() {
		return parkingCardStores;
	}
 
	public Integer getID() {
		return id;
	}
 
	public Pair<Coordinate, Coordinate> getBoundaries() {
		return boundaries;
	}
 
}