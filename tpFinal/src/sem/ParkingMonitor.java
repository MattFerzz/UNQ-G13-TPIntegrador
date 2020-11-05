package sem;
import java.util.ArrayList;

public class ParkingMonitor {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void Attach(Observer observer) {
		observers.add(observer);
	}
	
	public void Detach(Observer observer) {
		observers.remove(observer);
	}

	public void Notify() {
		for(Observer o : observers) {
			o.Update();
		}
	}
	
	public ArrayList<Observer> getObservers() {
		return observers;
	}
}
