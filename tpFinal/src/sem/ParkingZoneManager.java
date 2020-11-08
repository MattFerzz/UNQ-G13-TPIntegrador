package sem;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.*;
import org.eclipse.collections.api.tuple.Pair;
public class ParkingZoneManager{

	private MutableList<MeasuredParkingZone> parkingZones;
	private MutableList<InspectorApp> inspectorApps;
	private SEM system;
	
	public ParkingZoneManager(SEM system) {
		this.system = system;
		this.parkingZones = Lists.mutable.empty();
		this.inspectorApps = Lists.mutable.empty();
	}

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

	public InspectorApp getInspectorAppDeployedInParkingZone(MeasuredParkingZone aParkingZone) {
		return inspectorApps.detect(eachApp -> eachApp.getParkingZone() == aParkingZone);
	}
}
