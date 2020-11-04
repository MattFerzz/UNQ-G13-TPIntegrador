public class ActivatedUserAssistance extends UserAssistance {
	
	public void handle(String updateType, CellApp app, String licensePlate) {
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
