package sem;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class ParkingMonitor {
	
	private MutableList<Observer> observers;
	
	public ParkingMonitor() {
		this.observers = Lists.mutable.empty();
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	public void notify(Parking aParking) {
		for(Observer observer : observers) {
			observer.update(aParking);
		}
	}
	
	public MutableList<Observer> getObservers() {
		return observers;
	}
}
