package seguimiento3.eventos;

import des.*;
import seguimiento3.componentesPropios.ContadoresEstadisticosSeguimiento3;
import seguimiento3.componentesPropios.LibreriaDeRutinasSeguimiento3;
import seguimiento3.estadoDelSistema.EstadoSeguimiento3;
import seguimiento3.estadoDelSistema.Producto;
import seguimiento3.estadoDelSistema.SolicitudSeguimiento;

public class EventoArribarACola extends Evento {

    public EventoArribarACola(double tiempoQueFaltaParaQueOcurra) {
        super(tiempoQueFaltaParaQueOcurra);
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
                               LibreriaDeRutinas libreria) {

        EstadoSeguimiento3 modeloActual = (EstadoSeguimiento3) modelo;
        LibreriaDeRutinasSeguimiento3 libreriaActual = (LibreriaDeRutinasSeguimiento3) libreria;
        ContadoresEstadisticosSeguimiento3 contadoresActuales = (ContadoresEstadisticosSeguimiento3) contadores;
        //Agendar el próximo arribo de solicitud.
        double tiempoEntreArribos = libreriaActual.tiempoEntreArribosSolicitudes();
        EventoArribarACola nuevoEvento = new EventoArribarACola(tiempoEntreArribos);
        eventos.agregar(nuevoEvento);

        // Agregar cliente
        Producto nuevoProducto = libreriaActual.productoAleatorio();
        Integer cantidadDeProductos = switch (nuevoProducto) {
            case BEBIDA_SALUDABLE -> libreriaActual.cantidadDeUnidadesProductoBebida();
            case PANADERIA -> libreriaActual.cantidadDeUnidadesProductoPanaderia();
        };
        SolicitudSeguimiento solicitudParaAgregar = new SolicitudSeguimiento(this.getTiempoDeOcurrencia(), nuevoProducto, cantidadDeProductos);

        if (modeloActual.estaServidorOcupado()) {
            // Programar evento finalizar incorporacion
            EventoFinalizarIncorporacion nuevoEventoFinalizarIncorporacion = new EventoFinalizarIncorporacion(0);
            eventos.agregar(nuevoEventoFinalizarIncorporacion);

            // Encolar solicitud
            modeloActual.encolarSolicitud(solicitudParaAgregar);
        } else {
            // Agregar tiempo libre empleada
            double ultimoTiempoLibreEmpleada = this.getTiempoDeOcurrencia() - modeloActual.getTiempoUltimaAtencion();
            contadoresActuales.agregarTiempoLibreEmpleada(ultimoTiempoLibreEmpleada);

            // Calcular tiempo de atencion
            double tiempoDeAtencion = switch (solicitudParaAgregar.getProducto()) {
                case BEBIDA_SALUDABLE ->
                        libreriaActual.obtenerTiempoServicioBebida(solicitudParaAgregar.getCantidadDeProductos());
                case PANADERIA ->
                        libreriaActual.obtenerTiempoServicioPanadaria(solicitudParaAgregar.getCantidadDeProductos());
            };

            // Programar evento finalizar atencion
            EventoFinalizarAtencion nuevoEventoFinalizarAtencion = new EventoFinalizarAtencion(tiempoDeAtencion);
            eventos.agregar(nuevoEventoFinalizarAtencion);

            // Almacenar al cliente en la empleada
            modeloActual.atenderSolicitud(solicitudParaAgregar);
        }
    }

}
