package estacionamiento;

public class DeactivatedUserAssistance extends UserAssistance{
	
	public void handle(String updateType, CellApp app, String licensePlate) {
		System.out.print("La asistencia al Usuario esta desactivada");
	}
	
}

