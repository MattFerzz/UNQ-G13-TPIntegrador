import java.util.ArrayList;

public class Server {

	private ArrayList<ZonaMedida> zones    = new ArrayList<ZonaMedida>();
	private ArrayList<Compra>     compras  = new ArrayList<Compra>();
	//public ArrayList<Parking>   parkings = new ArrayList<Parking>();
	
	private void closeAllParkings() {
		for(ZonaMedida z: zones) {
			z.classAllParkings();
		}
	}
	
	//registrarNuevaCompra
	
}