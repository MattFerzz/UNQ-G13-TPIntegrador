package sem;

public class ActivatedUserAssistance extends UserAssistance {
	
	public void handle(String updateType, CellPhoneApp app, String licensePlate) {
		if (updateType == "Caminando"){
			app.startParking(licensePlate);
			System.out.print("Estacionamiento Iniciado");
		}
	
		if (updateType == "En Auto") {
			app.endParking();
			System.out.print("Estacionamiento Finalizado");
		}
	}
}
