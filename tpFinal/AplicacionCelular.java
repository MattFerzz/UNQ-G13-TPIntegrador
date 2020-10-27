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
	
	public string iniciarEstacionamiento(string patente) {
		string ret;
		if (saldo >= 40) {
			float horasEnSaldo = Math.floor(saldo/40);
			LocalTime horaMaxima = LocalTime.now().addHours(horasEnSaldo);
			ret = "Hora actual: " + str(LocalTime.now()) + ", Hora máxima: " + horaMaxima;
			estacionamientoActivo = LocalTime.now();
		} else {
			ret = "Saldo insuficiente. Estacionamiento no permitido.";
		}
		return ret;
	}
	
	public string finalizarEstacionamiento() {
		float duracion = ((estacionamientoActivo.getMinute() > LocalTime.now().getMinute())) ?
						 LocalTime.now().getHour() - estacionamientoActivo.getHour :
						 LocalTime.now().getHour() - estacionamientoActivo.getHour - 1;
		return "Horario de inicio: " + str(estacionamientoActivo) + ", horario de finalizacion: " + str(LocalTime.now()) + ", duracion: " + duracion + " horas, costo: " + duracion*40;
	}
	
	public void cargarSaldo(float x) {
		saldo = saldo + x;
	}
	
	public float consultarSaldo() {
		return saldo;
	}
}
