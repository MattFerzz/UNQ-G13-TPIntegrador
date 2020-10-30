package sec;

import org.eclipse.collections.api.list.*;
import org.eclipse.collections.api.tuple.Pair;
public class ParkingZonesManager{
	private MutableList<MeasuredParkingZone> parkingZones;
	private MutableList<InspectorApp> inspectorApps;
	private System system;

	public void addMeasuredParkingZone(MeasuredParkingZone aParkingZone) {
		parkingZones.add(aParkingZone);
	}
	public MeasuredParkingZone getParkingZoneWithBoundaries(Pair<Coordinate, Coordinate> boundaries) {
		
		return parkingZones.detect(eachParkingZone -> eachParkingZone.getBoundaries() == boundaries || eachParkingZone.getBoundaries() == boundaries.swap() );
	}
	
	public void deployInspectorAppInParkingZone(MeasuredParkingZone aParkingZone,String inspectorName  ) {
		MeasuredParkingZone zoneToInspect;
		zoneToInspect = parkingZones.detect(eachParkingZone -> eachParkingZone == aParkingZone);
		inspectorApps.add(new InspectorApp(zoneToInspect, inspectorName, this.system));
	}

	public InspectorApp getAppDeployedInParkingZone(MeasuredParkingZone aParkingZone) {
		return inspectorApps.detect(eachApp -> eachApp.getParkingZone() == aParkingZone);
	}
}
