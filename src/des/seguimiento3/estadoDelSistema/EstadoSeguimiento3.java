package seguimiento3.estadoDelSistema;

import des.EstadoDelSistema;

public class EstadoSeguimiento3 extends EstadoDelSistema {
    private ColaDeSolicitudesSeguimiento cola;
    private ServidorSeguimiento servidor;

    @Override
    public void inicializar() {
        cola = new ColaDeSolicitudesSeguimiento();
        servidor = new ServidorSeguimiento();
    }

    public boolean estaServidorOcupado() {
        return servidor.estaOcupado();
    }

    public boolean haySolicitudesEnEspera() {
        return cola.getCantSolicitudesEsperando() > 0;
    }

    public SolicitudSeguimiento obtenerSolicitudPrioritaria() {
        return cola.solicitudPrioritaria();
    }

    public Integer obtenerTamanioColaSeguimiento(){
        return cola.getCantSolicitudesEsperando();
    }

    public void atenderSolicitud(SolicitudSeguimiento solicitudAProcesar) {
        servidor.atenderSolicitud(solicitudAProcesar);
    }

    public SolicitudSeguimiento finalizarAtencionSolicitud() {
        return servidor.finalizarAtencionSolicitud();
    }

    public void encolarSolicitud(SolicitudSeguimiento solicitudParaAgregar) {
        cola.encolarSolicitud(solicitudParaAgregar);
    }

    public void setTiempoUltimaAtencion(Double tiempo){
        servidor.setTiempoUltimaAtencion(tiempo);
    }

    public Double getTiempoUltimaAtencion(){
        return servidor.getTiempoUltimaAtencion();
    }
}
