package ejercicio1.componentespropios;

/* Subprograma que calcula las estimaciones de las medidas de performance 
 * (a partir de los Contadores Estadísticos). */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

/***************************************/

public  class GeneradorDeReportesEjercicio1 extends GeneradorDeReportes {


	@Override
	public void run(ContadoresEstadisticos contadores) {
		ContadoresEstadisticosEjercicio1  contadoresEjercicio1 = (ContadoresEstadisticosEjercicio1) contadores;
		double [] promedios = contadoresEjercicio1.obtenerPromediosPorClase();
		System.out.println("------------------------------------------------------");
		System.out.println("***REPORTE DE ESTADISTICAS");
		System.out.println("------------------------------------------------------");
		System.out.println("Cantidad de solicitudes procesadas: " + contadoresEjercicio1.getCantSolicitudesProcesadas());
		for (int i = 0; i < promedios.length; i++) {
			System.out.printf("Promedio tiempo de procesamiento para clase %d: %.4f unidades de tiempo.%n", i+1, promedios[i]);
		}
	}
}
