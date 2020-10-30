import java.time.LocalTime;

/*
 Metodos:
 	iniciarEstacionamiento(string patente)
 	finalizarEstacionamiento()
 	cargarSaldo(float x)
 	consultarSaldo()
 	setState(string stateName)
 */

public class AplicacionCelular {

	private float saldo = 0;
	private LocalTime estacionamientoActivo;
	
	public String iniciarEstacionamiento(String patente) {
		String ret;
		if (saldo >= 40) {
			double horasEnSaldo = Math.floor(saldo/40);
			LocalTime horaMaxima = LocalTime.now().plusHours((long) horasEnSaldo);
			ret = "Hora actual: " + LocalTime.now() + ", Hora máxima: " + horaMaxima;
			estacionamientoActivo = LocalTime.now();
		} else {
			ret = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return ret;
	}
	
	public String finalizarEstacionamiento() {
		float duracion = ((estacionamientoActivo.getMinute() > LocalTime.now().getMinute())) ?
						 LocalTime.now().getHour() - estacionamientoActivo.getHour() :
						 LocalTime.now().getHour() - estacionamientoActivo.getHour() - 1;
		return "Horario de inicio: " + estacionamientoActivo + ", horario de finalizacion: " + LocalTime.now() + ", duracion: " + duracion + " horas, costo: " + duracion*40;
	}
	
	public void cargarSaldo(float x) {
		saldo = saldo + x;
	}
	
	public float consultarSaldo() {
		return saldo;
	}
}
