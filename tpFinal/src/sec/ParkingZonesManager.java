package sec;

import org.eclipse.collections.api.list.*;
public class ParkingZonesManager{
	private MutableList<MeasuredParkingZone> parkingZones;
	private System system;

	public void addMeasuredParkingZone(MeasuredParkingZone aParkingZone) {
		parkingZones.add(aParkingZone);
	}
	public void addInspectorAppForParkingZone(MeasuredParkingZone aParkingZone,String inspectorName,  ) {
		parkingZones.detectIfNone(eachParkingZone -> eachParkingZone.getID() == aParkingZoneID, Error)
	}
}
