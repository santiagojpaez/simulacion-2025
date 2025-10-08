package ejercicio1.componentespropios;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.ContadoresEstadisticos;
import ejercicio1.estadodelsistema.Solicitud;

/***************************************/

public  class ContadoresEstadisticosEjercicio1 extends ContadoresEstadisticos {
	private int cantSolicitudesProcesadas;
	private int[] cantPorClase;
	private int[] accPorClase;
	@Override
	public void inicializar() {
		cantSolicitudesProcesadas = 0;
		cantPorClase = new int[4];
		accPorClase = new int[4];
		// Set array values to 0
		for (int i = 0; i < 4; i++) {
			cantPorClase[i] = 0;
			accPorClase[i] = 0;
		}
	}

	public void actualizarCantProcesadas(Solicitud solicitud) {
		int claseIdx = solicitud.getClase() -1;
		cantPorClase[claseIdx]++;
		accPorClase[claseIdx] += solicitud.getTiempoDeProcesamiento();
		cantSolicitudesProcesadas++;
	}
	public int getCantSolicitudesProcesadas() {
		return cantSolicitudesProcesadas;
	}
	public double[] obtenerPromediosPorClase() {
		double[] promedios = new double[4];
		for (int i = 0; i < 4; i++) {
			if (cantPorClase[i] > 0) {
				promedios[i] = (double) accPorClase[i] / cantPorClase[i];
			} else {
				promedios[i] = 0.0;
			}
		}
		return promedios;
	}
}
