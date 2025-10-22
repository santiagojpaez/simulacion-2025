package seguimiento3.eventos;

import des.Evento;
import seguimiento3.componentesPropios.ContadoresEstadisticosSeguimiento3;
import seguimiento3.estadoDelSistema.EstadoSeguimiento3;

public class EventoFinalizarIncorporacion  extends Evento {
    public EventoFinalizarIncorporacion(double tiempoQueFaltaParaQueOcurra) {
        super(tiempoQueFaltaParaQueOcurra);
    }

    @Override
    public void rutinaDeEvento(des.EstadoDelSistema modelo, des.ContadoresEstadisticos contadores, des.ListaDeEventos eventos, des.LibreriaDeRutinas libreria) {
        ContadoresEstadisticosSeguimiento3 cont = (ContadoresEstadisticosSeguimiento3) contadores;
        EstadoSeguimiento3 estado = (EstadoSeguimiento3) modelo;
        Integer longitudCola = estado.obtenerTamanioColaSeguimiento();
        cont.actualizarLongitudDeColaActual(longitudCola, this.getTiempoDeOcurrencia());
    }
}
