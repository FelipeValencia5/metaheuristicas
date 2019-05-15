package es.edu.ull.metaheuristicas.sa;
import java.util.ArrayList;
import java.util.Collections;

public class Recorrido{

    private ArrayList<Ubicacion> recorrido = new ArrayList<Ubicacion>();
    private double distancia = 0;
    
    // Construir recorrido en blanco
    public Recorrido(){
        for (int i = 0; i < Destino.numeroDestinos(); i++) {
        	recorrido.add(null);
        }
    }
    
    // Construir un recorrido a partir de otro
    @SuppressWarnings("unchecked")
	public Recorrido(ArrayList<Ubicacion> recorrido){
        this.recorrido = (ArrayList<Ubicacion>) recorrido.clone();
    }
    
    // Obtener el recorrido
    public ArrayList<Ubicacion> getRecorrido(){
        return recorrido;
    }

    public void generarRecorrido() {
        // Recorre todas las ubicaciones de destino y las anade a nuestro recorrido
        for (int recorridoIndex = 0; recorridoIndex < Destino.numeroDestinos(); recorridoIndex++) {
          setUbicacion(recorridoIndex, Destino.obtenerUbicacion(recorridoIndex));
        }
        // Reordenar aleatoriamente el recorrido
        Collections.shuffle(recorrido);
    }

    // Obtener ubicacion del tour
    public Ubicacion getUbicacion(int posicion) {
        return recorrido.get(posicion);
    }

    // Sets a city in a certain position within a tour
    public void setUbicacion(int posicion, Ubicacion ubicacion) {
    	recorrido.set(posicion, ubicacion);
        // Como el recorrido se altera reiniciamos la distancia entre ellos
    	distancia = 0;
    }
    
    // Obtiene la distancia total del recorrido
    public double getDistance(){
        if (distancia == 0) {
            double distanciaTour = 0;
            // Recorrer las ubicaciones del tour
            for (int i=0; i < tamanoRecorrido(); i++) {
                // Obtener la ciudad desde la que viajamos
                Ubicacion ubicacionDesde = getUbicacion(i);
                // Obtener la ciudad a la que viajamos
                Ubicacion ubicacionHacia;
                // Comprobamos que la ciudad siguiente no sea la final, de lo contrario
                // la establecemos la inicial como destino
                if(i+1 < tamanoRecorrido()){
                	ubicacionHacia = getUbicacion(i+1);
                }
                else{
                	ubicacionHacia = getUbicacion(0);
                }
                // Obtener la distancia entre las dos ciudades
                distanciaTour += ubicacionDesde.distancia(ubicacionHacia);
            }
            distancia = distanciaTour;
        }
        return distancia;
    }

    // Obtener el numero de ubicaciones del tour
    public int tamanoRecorrido() {
        return recorrido.size();
    }
    
    //Obtener coordenadas en string
    @Override
    public String toString() {
        String generarString = "|";
        for (int i = 0; i < tamanoRecorrido(); i++) {
        	generarString += getUbicacion(i)+"|";
        }
        return generarString;
    }
}