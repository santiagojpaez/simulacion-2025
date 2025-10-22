package des;

import seguimiento3.componentesPropios.ContadoresEstadisticosSeguimiento3;
import seguimiento3.componentesPropios.GeneradorDeReportesSeguimiento3;
import seguimiento3.componentesPropios.LibreriaDeRutinasSeguimiento3;
import seguimiento3.estadoDelSistema.EstadoSeguimiento3;
import seguimiento3.eventos.EventoArribarACola;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgramaPrincipal {
	private static EstadoDelSistema modelo;
	private static ContadoresEstadisticos contadores;
	private static GeneradorDeReportesSeguimiento3 reporte;
	private static LibreriaDeRutinas libreria;
	private static ListaDeEventos eventos;

	public static void main(String[] args) {
		int cantidadDeCorridas = 1000; // Definir la cantidad de corridas
		List<Map<String, Double>> resultadosTotales = new ArrayList<>();

		for (int i = 0; i < cantidadDeCorridas; i++) {
			System.out.println("------------------------------------------------------");
			System.out.println("*** INICIO DE LA CORRIDA " + (i + 1) + " ***");
			System.out.println("------------------------------------------------------");

			// Crear componentes y ejecutar simulación
			crearComponentesDependientes();
			ejecutarSimulacion();

			// Generar reporte y almacenar resultados
			Map<String, Double> resultados = reporte.run((ContadoresEstadisticosSeguimiento3) contadores);
			resultadosTotales.add(resultados);
		}

		// Escribir resultados en un archivo CSV
		escribirResultadosEnCSV(resultadosTotales);
	}

	private static void crearComponentesDependientes() {
		modelo = new EstadoSeguimiento3();
		contadores = new ContadoresEstadisticosSeguimiento3();
		reporte = new GeneradorDeReportesSeguimiento3();
		libreria = new LibreriaDeRutinasSeguimiento3();
		Evento primerEvento = new EventoArribarACola(((LibreriaDeRutinasSeguimiento3) libreria).tiempoEntreArribosSolicitudes());
		eventos = new ListaDeEventos(primerEvento);
	}

	private static void ejecutarSimulacion() {
		RutinaDeInicializacion inicializacion = new RutinaDeInicializacion();
		RutinaDeTiempo manejoDeTiempo = new RutinaDeTiempo();
		RelojDeSimulacion reloj = new RelojDeSimulacion();

		inicializacion.run(reloj, modelo, contadores, eventos, libreria);

		do {
			Evento eventoPorEjecutar = manejoDeTiempo.run(eventos, reloj);
			eventoPorEjecutar.rutinaDeEvento(modelo, contadores, eventos, libreria);
		} while (!terminoLaSimulacion(reloj, contadores));

		((ContadoresEstadisticosSeguimiento3) contadores).setearTiempoDeTurno(480.0);
	}

	private static boolean terminoLaSimulacion(RelojDeSimulacion reloj, ContadoresEstadisticos contadores) {
		return reloj.getValor() >= 480;
	}

	private static void escribirResultadosEnCSV(List<Map<String, Double>> resultadosTotales) {
		try (FileWriter writer = new FileWriter("resultados_simulacion.csv")) {
			writer.write("Corrida,Beneficio,PromedioTiempoEnCola,TasaDeAtencion,PorcentajeTiempoLibre,UtilizacionEmpleada\n");
			double sumaBeneficio = 0.0;
			double sumaPromedioTiempoEnCola = 0.0;
			double sumaTasaDeAtencion = 0.0;
			double sumaPorcentajeTiempoLibre = 0.0;
			double sumaUtilizacionEmpleada = 0.0;

			for (int i = 0; i < resultadosTotales.size(); i++) {
				Map<String, Double> resultados = resultadosTotales.get(i);
				writer.write(String.format("%d,%.4f,%.4f,%.4f,%.2f,%.2f\n",
						i + 1,
						resultados.get("Beneficio"),
						resultados.get("PromedioTiempoEnCola"),
						resultados.get("TasaDeAtencion"),
						resultados.get("PorcentajeTiempoLibre"),
						resultados.get("UtilizacionEmpleada")));
				sumaBeneficio += resultados.get("Beneficio");
				sumaPromedioTiempoEnCola += resultados.get("PromedioTiempoEnCola");
				sumaTasaDeAtencion += resultados.get("TasaDeAtencion");
				sumaPorcentajeTiempoLibre += resultados.get("PorcentajeTiempoLibre");
				sumaUtilizacionEmpleada += resultados.get("UtilizacionEmpleada");
			}

			int totalCorridas = resultadosTotales.size();
			double promedioBeneficio = sumaBeneficio / totalCorridas;
			double promedioTiempoEnCola = sumaPromedioTiempoEnCola / totalCorridas;
			double promedioTasaDeAtencion = sumaTasaDeAtencion / totalCorridas;
			double promedioPorcentajeTiempoLibre = sumaPorcentajeTiempoLibre / totalCorridas;
			double promedioUtilizacionEmpleada = sumaUtilizacionEmpleada / totalCorridas;

			// Imprimir los promedios en consola
			System.out.println("Promedios de las corridas:");
			System.out.printf("Promedio Beneficios totales %.4f\n",promedioBeneficio);
			System.out.printf("Promedio Tiempo en Cola: %.4f\n", promedioTiempoEnCola);
			System.out.printf("Promedio Tasa de Atención: %.4f\n", promedioTasaDeAtencion);
			System.out.printf("Promedio Porcentaje Tiempo Libre: %.2f\n", promedioPorcentajeTiempoLibre);
			System.out.printf("Promedio Utilización Empleada: %.2f\n", promedioUtilizacionEmpleada);

			System.out.println("Resultados guardados en 'resultados_simulacion.csv'.");
		} catch (IOException e) {
			System.err.println("Error al escribir el archivo CSV: " + e.getMessage());
		}
	}
}