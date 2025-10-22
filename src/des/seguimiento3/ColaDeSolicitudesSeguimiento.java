package seguimiento3;

import java.util.LinkedList;
import java.util.Queue;



public class ColaDeSolicitudesSeguimiento {
    private final Queue<SolicitudSeguimiento> colaSolicitud;


    public ColaDeSolicitudesSeguimiento(){
        super();
        colaSolicitud=new LinkedList<SolicitudSeguimiento>();
    }

    public void encolarSolicitud(SolicitudSeguimiento solicitudParaAgregar){
        colaSolicitud.add(solicitudParaAgregar);
    }
    public int getCantSolicitudesEsperando(){
        return colaSolicitud.size();
    }
    public SolicitudSeguimiento solicitudPrioritaria(){
        return colaSolicitud.remove();
    }

}
