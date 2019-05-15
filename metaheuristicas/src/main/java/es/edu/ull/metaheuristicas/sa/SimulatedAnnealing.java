package es.edu.ull.metaheuristicas.sa;

import es.edu.ull.metaheuristicas.utilidades.LeerFicherosExcel;

public class SimulatedAnnealing {

	public static double probabilidadAceptacion(int energia, int nuevaEnergia, double temperatura) {
		if (nuevaEnergia < energia) { // Si la nueva solucion es mejor, la aceptamos
			return 1.0;
		}
		// Si la nueva solucion es peor, calculamos una nueva probabilidad de aceptacion
		return Math.exp((energia - nuevaEnergia) / temperatura);
	}

	public static void simulatedAnnealing() {

		double temperatura = 1000; // Temperatura inicial
		double enfriamiento = 0.005; // Enfriamiento 
		
		// Inicializar solucion
		Recorrido recorridoInicial = new Recorrido();
		recorridoInicial.generarRecorrido();

		System.out.println("Solucion inicial de distancia: " + recorridoInicial.getDistance());
		System.out.println("Recorrido inicial: " + recorridoInicial);

		Recorrido recorridoMejorado = new Recorrido(recorridoInicial.getRecorrido());

		while (temperatura > 1) {
			// Copiar el recorrido inicial a un nuevo recorrido para mejorarlo
			Recorrido nuevaSolucion = new Recorrido(recorridoInicial.getRecorrido());

			// Obtener posiciones aleatoria del recorrido
			int posicion1 = (int) (nuevaSolucion.tamanoRecorrido() * Math.random());
			int posicion2 = (int) (nuevaSolucion.tamanoRecorrido() * Math.random());

			// Intercambiar las soluciones aletorias
			Ubicacion citySwap1 = nuevaSolucion.getUbicacion(posicion1);
			Ubicacion citySwap2 = nuevaSolucion.getUbicacion(posicion2);

			nuevaSolucion.setUbicacion(posicion2, citySwap1);
			nuevaSolucion.setUbicacion(posicion1, citySwap2);


			int currentEnergy = (int) recorridoInicial.getDistance();
			int neighbourEnergy = (int) nuevaSolucion.getDistance();


			if (probabilidadAceptacion(currentEnergy, neighbourEnergy, temperatura) > Math.random()) {
				recorridoInicial = new Recorrido(nuevaSolucion.getRecorrido());
			}

			if (recorridoInicial.getDistance() < recorridoMejorado.getDistance()) {
				recorridoMejorado = new Recorrido(recorridoInicial.getRecorrido());
			}


			temperatura *= 1-enfriamiento;
		}

		System.out.println("Solucion final de distancia: " + recorridoMejorado.getDistance());
		System.out.println("Recorrido del tour: " + recorridoMejorado);
	}



	public static void main(String[] args) {		
		LeerFicherosExcel LE= new LeerFicherosExcel();
		LE.leerArchivo();
		SimulatedAnnealing SA= new SimulatedAnnealing();
		SA.simulatedAnnealing();		
	}
}