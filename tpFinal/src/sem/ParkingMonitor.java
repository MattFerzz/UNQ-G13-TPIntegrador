package sem;
import java.util.ArrayList;

public class ParkingMonitor {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	public void notify(Parking aParking) {
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
}
