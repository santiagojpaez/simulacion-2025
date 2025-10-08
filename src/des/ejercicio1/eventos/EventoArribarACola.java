package ejercicio1.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.LibreriaDeRutinas;
import des.ListaDeEventos;
import ejercicio1.componentespropios.LibreriaDeRutinasEjercicio1;
import ejercicio1.estadodelsistema.EstadoEjercicio1;
import ejercicio1.estadodelsistema.Solicitud;

public class EventoArribarACola extends Evento {

	public EventoArribarACola(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinas libreria) {
				
		EstadoEjercicio1 modeloActual = (EstadoEjercicio1) modelo;
		LibreriaDeRutinasEjercicio1 libreriaActual = (LibreriaDeRutinasEjercicio1) libreria;
		
		//Agendar el próximo arribo de solicitud.
		EventoArribarACola nuevoEvento = new EventoArribarACola(libreriaActual.tiempoEntreArribosSolicitudes());	
		eventos.agregar(nuevoEvento);
		
		//Procesar este arribo, para lo cual es necesario generar la solicitud que acaba de arribar.
		Solicitud solicitudParaAgregar = new Solicitud();
		
		if(modeloActual.estaServidorOcupado()) {
			modeloActual.encolarSolicitud(solicitudParaAgregar);
		}
		else {
			modeloActual.atenderSolicitud(solicitudParaAgregar);
			double duracionDelProcesamiento = libreriaActual.tiempoDeProcesamiento();
			EventoTerminaProcesamiento nuevoEventoAdicional = new EventoTerminaProcesamiento(duracionDelProcesamiento);
			eventos.agregar(nuevoEventoAdicional);
		}
	}

}
