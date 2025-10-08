package ejercicio1.estadodelsistema;

import des.EstadoDelSistema;

public class EstadoEjercicio1 extends EstadoDelSistema {
    private ColaDeSolicitudes cola;
    private Servidor servidor;

    @Override
    public void inicializar() {
        cola = new ColaDeSolicitudes();
        servidor = new Servidor(false);
    }

    public boolean estaServidorOcupado() {
        return servidor.getEstaOcupado();
    }

    public boolean haySolicitudesEnEspera() {
        return cola.getCantSolicitudesEsperando() > 0;
    }

    public Solicitud obtenerSolicitudPrioritaria() {
        return cola.solicitudPrioritaria();
    }

    public void atenderSolicitud(Solicitud solicitudAProcesar) {
        servidor.pasarAOcupado(solicitudAProcesar);
    }

    public void actualizarServidorDisponible() {
        servidor.setEstaOcupado(false);
    }

    public void encolarSolicitud(Solicitud solicitudParaAgregar) {
        cola.encolarSolicitud(solicitudParaAgregar);
    }
}
