package ejercicio1.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.LibreriaDeRutinas;
import des.ListaDeEventos;
import ejercicio1.componentespropios.ContadoresEstadisticosEjercicio1;
import ejercicio1.componentespropios.LibreriaDeRutinasEjercicio1;
import ejercicio1.estadodelsistema.EstadoEjercicio1;
import ejercicio1.estadodelsistema.Solicitud;

public class EventoTerminaProcesamiento extends Evento {

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinas libreria) {
		
		ContadoresEstadisticosEjercicio1 contadoresEjemplo = (ContadoresEstadisticosEjercicio1) contadores;

		
		EstadoEjercicio1 modeloActual = (EstadoEjercicio1) modelo;
		LibreriaDeRutinasEjercicio1 libreriaActual = (LibreriaDeRutinasEjercicio1) libreria;
		
		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitudPrioritaria();
			modeloActual.atenderSolicitud(solicitudAProcesar);
			double duracionDelProcesamiento = libreriaActual.tiempoDeProcesamiento();
			solicitudAProcesar.setTiempoDeProcesamiento(duracionDelProcesamiento);
			contadoresEjemplo.actualizarCantProcesadas(solicitudAProcesar)
			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(duracionDelProcesamiento);	
			eventos.agregar(nuevoEvento);	
		}
		else {
			modeloActual.actualizarServidorDisponible();
		}
	}

}
