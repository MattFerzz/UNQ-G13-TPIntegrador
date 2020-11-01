import java.time.LocalTime;

/*
 Metodos:
 	iniciarEstacionamiento(string patente)
 	finalizarEstacionamiento()
 	cargarSaldo(float x)
 	consultarSaldo()
 	setState(string stateName)
 */

public class CellApp {

	private float balance = 0;
	private LocalTime activeParking;
	private int cellNumber;
	
	public CellApp(int cell_number) {
		cellNumber = cell_number;
	}
	
	public String StartParking(String licensePlate) {
		String ret;
		if (balance >= 40) {
			double horasEnSaldo = Math.floor(balance/40);
			LocalTime horaMaxima = LocalTime.now().plusHours((long) horasEnSaldo);
			ret = "Hora actual: " + LocalTime.now() + ", Hora máxima: " + horaMaxima;
			activeParking = LocalTime.now();
		} else {
			ret = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return ret;
	}
	
	public String EndParking() {
		float duracion = ((activeParking.getMinute() > LocalTime.now().getMinute())) ?
						 LocalTime.now().getHour() - activeParking.getHour() :
						 LocalTime.now().getHour() - activeParking.getHour() - 1;
		return "Horario de inicio: " + activeParking + ", horario de finalizacion: " + LocalTime.now() + ", duracion: " + duracion + " horas, costo: " + duracion*40;
	}
	
	public void loadBalance(float x) {
		balance = balance + x;
	}
	
	public float checkBalance() {
		return balance;
	}
	
}
