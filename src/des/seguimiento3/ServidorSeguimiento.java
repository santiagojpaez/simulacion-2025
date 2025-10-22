package seguimiento3;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ServidorSeguimiento {
	Optional<SolicitudSeguimiento> solicitudEnProcesamiento; /* Solicitud que está siendo retenida en el servidor. */
	private Double tiempoTotalLibre = 0.00;
	private Double tiempoUltimaAtencion = 0.00;
	private Integer cantidadClientesAtendidos = 0;
	private Double duracionDeLaAtencion = 0.00 ;

	public ServidorSeguimiento() {
		super();
		solicitudEnProcesamiento = Optional.empty();
	}

	public boolean estaOcupado() {
		return solicitudEnProcesamiento.isPresent();
	}

	public void atenderSolicitud(SolicitudSeguimiento solicitud) {
		this.solicitudEnProcesamiento = Optional.of(solicitud);
	}

	public SolicitudSeguimiento finalizarAtencionSolicitud(){
		SolicitudSeguimiento solicitudQueSale = solicitudEnProcesamiento.orElseThrow(() -> new NoSuchElementException("No se estaba procesando una solicitud"));
		this.solicitudEnProcesamiento = Optional.empty();
		return solicitudQueSale;
	}

}
