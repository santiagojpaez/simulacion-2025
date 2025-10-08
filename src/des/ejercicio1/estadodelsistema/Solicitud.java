package ejercicio1.estadodelsistema;

/* Solicitud que puede ser procesada por el servidor. */

public class Solicitud {
	
	private int clase;

	private double tiempoDeProcesamiento;

	public Solicitud() {
		super();
		tiempoDeProcesamiento = -1;
		this.clase = (int) ((Math.random()*3) + 1);	
	}

	public int getClase() {
		return clase;
	}

	public void setTiempoDeProcesamiento(double tiempoDeProcesamiento) {
		this.tiempoDeProcesamiento = tiempoDeProcesamiento;
	}
	public double getTiempoDeProcesamiento() {
		return tiempoDeProcesamiento;
	}
	
}
