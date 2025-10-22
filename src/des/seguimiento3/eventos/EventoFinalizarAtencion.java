package seguimiento3.eventos;

import des.Evento;
import des.LibreriaDeRutinas;
import ejercicio1.estadodelsistema.Solicitud;
import seguimiento3.componentesPropios.ContadoresEstadisticosSeguimiento3;
import seguimiento3.componentesPropios.LibreriaDeRutinasSeguimiento3;
import seguimiento3.estadoDelSistema.EstadoSeguimiento3;
import seguimiento3.estadoDelSistema.Producto;
import seguimiento3.estadoDelSistema.SolicitudSeguimiento;

public class EventoFinalizarAtencion extends Evento {

    public EventoFinalizarAtencion(double tiempoQueFaltaParaQueOcurra) {
        super(tiempoQueFaltaParaQueOcurra);
    }

    @Override
    public void rutinaDeEvento(des.EstadoDelSistema modelo, des.ContadoresEstadisticos contadores, des.ListaDeEventos eventos, des.LibreriaDeRutinas libreria) {
        // Lógica para finalizar la atención

        // Variables actuales
        ContadoresEstadisticosSeguimiento3 contadoresActuales = (ContadoresEstadisticosSeguimiento3) contadores;
        EstadoSeguimiento3 modeloActual = (EstadoSeguimiento3) modelo;
        LibreriaDeRutinasSeguimiento3 libreriaActual = (LibreriaDeRutinasSeguimiento3) libreria;

        // Obtener el cliente en atencion actual
        SolicitudSeguimiento solicitudActual = modeloActual.finalizarAtencionSolicitud();

        // Se obtiene el tiempo en el kiosko del cliente
        Double tiempoArriboCliente = solicitudActual.getTiempoDeArribo();
        Double tiempoActual = this.getTiempoDeOcurrencia();
        solicitudActual.setTiempoEnKiosko(tiempoActual - tiempoArriboCliente);
        Producto prodActual = solicitudActual.getProducto();
        Integer cantidadProductosActual = solicitudActual.getCantidadDeProductos();
        Integer beneficio = prodActual.getBeneficioUnitario() * cantidadProductosActual;
        contadoresActuales.sumarBeneficio(beneficio);

        // Se actualiza el contador con el tiempo en el kiosko del cliente
        contadoresActuales.agregarTiempoCliente(solicitudActual.getTiempoEnKiosko());

        if(modeloActual.haySolicitudesEnEspera()){
            // La cola tiene gente

            // Genero el evento de finalizar remocion
            EventoFinalizarRemocion nuevoEventoFinalizarIncorporacion = new EventoFinalizarRemocion(0);
            eventos.agregar(nuevoEventoFinalizarIncorporacion);

            // Obtengo el cliente en el frente de la cola
            SolicitudSeguimiento sol = modeloActual.obtenerSolicitudPrioritaria();

            // Obtengo el tiempo de atencion en base al producto y la cantidad
            Producto prod = sol.getProducto();
            double tiempoDeAtencion = switch (prod) {
                case BEBIDA_SALUDABLE ->
                        libreriaActual.obtenerTiempoServicioBebida(sol.getCantidadDeProductos());
                case PANADERIA ->
                        libreriaActual.obtenerTiempoServicioPanadaria(sol.getCantidadDeProductos());
            };
            // Genero el evento siguiente de finalizar atencion
            EventoFinalizarAtencion nuevoEventoFinalizarAtencion = new EventoFinalizarAtencion(tiempoDeAtencion);
            eventos.agregar(nuevoEventoFinalizarAtencion);
            // Atiendo la solicitud actual (se setea en la empleada la solicitud actual)
            modeloActual.atenderSolicitud(sol);
        }else{
            // Seteo el tiempo de ultima atencion como el actual
            modeloActual.setTiempoUltimaAtencion(this.getTiempoDeOcurrencia());
        }
    }
}
