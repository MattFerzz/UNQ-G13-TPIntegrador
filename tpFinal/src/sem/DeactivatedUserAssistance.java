package sem;

public class DeactivatedUserAssistance extends UserAssistance {

	public void handle(String updateType, CellPhoneApp app, String licensePlate) {
		System.out.print("La asistencia al Usuario esta desactivada");
	}

}
