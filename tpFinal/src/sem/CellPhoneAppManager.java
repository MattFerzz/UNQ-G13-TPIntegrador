package sem;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class CellPhoneAppManager {

	private MutableList<CellPhoneApp> apps;
	private SEM server;

	public CellPhoneAppManager(SEM server) {
		this.server = server;
		this.apps = Lists.mutable.empty();
	}

	public CellPhoneApp getCellPhoneApp(Integer aPhoneNumber) {
		return apps.detect(anApp -> anApp.getPhoneNumber().equals(aPhoneNumber));
	}

	public void loadBalance(Integer aPhoneNumber, Float anAmount) {
		getCellPhoneApp(aPhoneNumber).loadBalance(anAmount);
	}

	public void registerApp(Integer phoneNumber, String licensePlate) {
		CellPhoneApp app = new CellPhoneApp(phoneNumber, server, licensePlate, new DeactivatedUserAssistance());
		apps.add(app);
	}

	public void removeApp(CellPhoneApp app) {
		apps.remove(app);
	}

	public Integer appsSize() {
		return apps.size();
	}
}
