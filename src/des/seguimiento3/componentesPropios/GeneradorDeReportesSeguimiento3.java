package seguimiento3.componentesPropios;

import java.util.HashMap;
import java.util.Map;

public class GeneradorDeReportesSeguimiento3 {

	public Map<String, Double> run(ContadoresEstadisticosSeguimiento3 contadoresSeguimiento3) {
		Map<String, Double> resultados = new HashMap<>();


		double beneficioTotal = contadoresSeguimiento3.obtenerBeneficioTotal();
		resultados.put("Beneficio",beneficioTotal);
		// Promedio de Tiempo de Clientes en Cola (Wq)
		double promedioTiempoEnCola = contadoresSeguimiento3.cantidadDeClientes > 0
				? contadoresSeguimiento3.obtenerPromedioTiempoClientesEnCola()
				: 0.0;
		resultados.put("PromedioTiempoEnCola", promedioTiempoEnCola);

		// Tasa de Atención
		double tasaDeAtencion = contadoresSeguimiento3.tiempoDeTurno > 0.0
				? contadoresSeguimiento3.obtenerTasaDeAtencionPorHora()
				: 0.0;
		resultados.put("TasaDeAtencion", tasaDeAtencion);

		// Porcentaje de Tiempo Libre de la Empleada
		double porcentajeTiempoLibre = contadoresSeguimiento3.tiempoDeTurno > 0.0
				? contadoresSeguimiento3.obtenerPorcentajeDeTiempoLibreEmpleada() * 100.0
				: 0.0;
		resultados.put("PorcentajeTiempoLibre", porcentajeTiempoLibre);

		// Utilización de la Empleada
		double utilizacionEmpleada = 100.0 - porcentajeTiempoLibre;
		resultados.put("UtilizacionEmpleada", utilizacionEmpleada);

		return resultados;
	}
}