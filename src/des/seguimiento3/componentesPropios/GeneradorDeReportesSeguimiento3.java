package seguimiento3.componentesPropios;

/* Subprograma que calcula las estimaciones de las medidas de performance
 * (a partir de los Contadores Estadísticos). */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;

/***************************************/

public class GeneradorDeReportesSeguimiento3 extends GeneradorDeReportes {

	@Override
	public void run(ContadoresEstadisticos contadores) {
		// Se hace un casting a la subclase específica de contadores
		ContadoresEstadisticosSeguimiento3 contadoresSeguimiento3 = (ContadoresEstadisticosSeguimiento3) contadores;

		System.out.println("------------------------------------------------------");
		System.out.println("***REPORTE DE ESTADISTICAS***");
		System.out.println("------------------------------------------------------");

		// 1. Beneficio Total
		System.out.println("Beneficio Total: $" + contadoresSeguimiento3.obtenerBeneficioTotal());

		// 2. Promedio de longitud de cola
		// Se verifica que haya habido eventos de cola para evitar división por cero
		if (contadoresSeguimiento3.cantidadDeEventosDeCola > 0) {
			System.out.printf("Promedio de Longitud de Cola (Lq): %.4f clientes\n",
					contadoresSeguimiento3.obtenerPromedioLongitudCola());
		} else {
			System.out.println("Promedio de Longitud de Cola (Lq): 0.0000 clientes (No hubo eventos de cola)");
		}

		// 3. Promedio de tiempo de los clientes en cola (Wq)
		// Se verifica que haya habido clientes para evitar división por cero
		if (contadoresSeguimiento3.cantidadDeClientes > 0) {
			System.out.printf("Promedio de Tiempo de Clientes en Cola (Wq): %.4f unidades de tiempo\n",
					contadoresSeguimiento3.obtenerPromedioTiempoClientesEnCola());
		} else {
			System.out.println("Promedio de Tiempo de Clientes en Cola (Wq): 0.0000 unidades de tiempo (No hubo clientes atendidos)");
		}

		// 4. Tasa de Atención ($\lambda$)
		// Se verifica que el tiempo de turno sea positivo para evitar división por cero
		if (contadoresSeguimiento3.tiempoDeTurno > 0.0) {
			System.out.printf("Tasa de Atención : %.4f clientes/unidad de tiempo\n",
					contadoresSeguimiento3.obtenerTasaDeAtencion());
		} else {
			System.out.println("Tasa de Atención : N/A (Tiempo de Turno no establecido o es cero)");
		}

		// 5. Porcentaje de Tiempo Libre de la Empleada
		// Se verifica que el tiempo de turno sea positivo para evitar división por cero
		if (contadoresSeguimiento3.tiempoDeTurno > 0.0) {
			System.out.printf("Porcentaje de Tiempo Libre de la Empleada (1-U): %.2f%%\n",
					contadoresSeguimiento3.obtenerPorcentajeDeTiempoLibreEmpleada() * 100.0);
			System.out.printf("Utilización de la Empleada (U): %.2f%%\n",
					(1.0 - contadoresSeguimiento3.obtenerPorcentajeDeTiempoLibreEmpleada()) * 100.0);
		} else {
			System.out.println("Porcentaje de Tiempo Libre de la Empleada: N/A (Tiempo de Turno no establecido o es cero)");
			System.out.println("Utilización de la Empleada: N/A");
		}

		System.out.println("------------------------------------------------------");
	}
}