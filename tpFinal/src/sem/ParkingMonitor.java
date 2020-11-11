package sem;
import java.util.ArrayList;

public class ParkingMonitor {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
<<<<<<< HEAD
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	public void notify(Parking aParking) {
=======
	public void Attach(Observer observer) {
		observers.add(observer);
	}
	
	public void Detach(Observer observer) {
		observers.remove(observer);
	}

	public void Notify() {
>>>>>>> refs/heads/RamaDeLautaro
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
}
