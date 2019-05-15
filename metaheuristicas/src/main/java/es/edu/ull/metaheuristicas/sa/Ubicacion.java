package es.edu.ull.metaheuristicas.sa;

public class Ubicacion {
    double x;
    double y;
    String nombre;
    int tipo;
        
    public Ubicacion(double x, double y, String nombre, int tipo){
        this.x = x;
        this.y = y;
        this.nombre=nombre;
        this.tipo=tipo;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }    
    
    public String getNombre() {
		return this.nombre;
	}

	public int getTipo() {
		return this.tipo;
	}

	public double distancia(Ubicacion city){
        double xDistancia = Math.abs(getX() - city.getX());
        double yDistancia = Math.abs(getY() - city.getY());
        double distancia = Math.sqrt( (xDistancia*xDistancia) + (yDistancia*yDistancia) );
        
        return distancia;
    }
    
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}
