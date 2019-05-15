package es.edu.ull.metaheuristicas.sa;
import java.util.ArrayList;

public class Destino {

    @SuppressWarnings("rawtypes")
	private static ArrayList destinos = new ArrayList<Ubicacion>();


    @SuppressWarnings("unchecked")
	public static void agregarUbicacion(Ubicacion ubicacion) {
    	destinos.add(ubicacion);
    }
    
    public static Ubicacion obtenerUbicacion(int posicion){
        return (Ubicacion)destinos.get(posicion);
    }
    
    public static int numeroDestinos(){
        return destinos.size();
    }
    
}