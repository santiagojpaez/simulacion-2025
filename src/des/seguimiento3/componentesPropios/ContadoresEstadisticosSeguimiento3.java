package seguimiento3.componentesPropios;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.ContadoresEstadisticos;

/***************************************/

public  class ContadoresEstadisticosSeguimiento3 extends ContadoresEstadisticos {
	private Integer beneficioTotal = 0;

	private Double acumuladorClientesEnCola = 0.0;
	private Double ultimoTiempoCambioLongitudDeCola = 0.0;
	public Double acumuladorTiempo = 0.00;
	private Integer previaLongitudCola = 0;

	public Integer cantidadDeClientes = 0;
	private Double acumuladorTiemposDeClientes = 0.00;

	private Double tiempoLibreEmpleada = 0.00;
	// Tiempo de turno en MINUTOS
	public Double tiempoDeTurno = 0.00;

	@Override
	public void inicializar() {
	}

	public void sumarBeneficio(Integer beneficio){
		beneficioTotal += beneficio;
	}
	public Integer obtenerBeneficioTotal(){
		return beneficioTotal;
	}

	public void actualizarLongitudDeColaActual(Integer longCola, Double tiempo){
		Double tiempoEnCantidadPrevia = tiempo - ultimoTiempoCambioLongitudDeCola;
		acumuladorTiempo+= tiempoEnCantidadPrevia;
		double peso = tiempoEnCantidadPrevia * previaLongitudCola;
		acumuladorClientesEnCola += peso;
		ultimoTiempoCambioLongitudDeCola = tiempo;
		previaLongitudCola = longCola;
	}
	public Double obtenerPromedioLongitudCola(){
		Double tiempoEnCantidadPrevia = tiempoDeTurno - ultimoTiempoCambioLongitudDeCola;
		acumuladorTiempo+= tiempoEnCantidadPrevia;
		double peso = tiempoEnCantidadPrevia * previaLongitudCola;
		acumuladorClientesEnCola+=peso;
		ultimoTiempoCambioLongitudDeCola = tiempoDeTurno;
		return acumuladorClientesEnCola / acumuladorTiempo;
	}

	public void agregarTiempoCliente(Double tiempo){
		acumuladorTiemposDeClientes+=tiempo;
		cantidadDeClientes++;
	}
	public Double obtenerPromedioTiempoClientesEnCola(){
		return acumuladorTiemposDeClientes / cantidadDeClientes.doubleValue();
	}

	public void setearTiempoDeTurno(Double tiempo){
		tiempoDeTurno = tiempo;
	}

	public Double obtenerTasaDeAtencionPorHora(){
		return cantidadDeClientes.doubleValue() / (tiempoDeTurno/60);
	}

	public void agregarTiempoLibreEmpleada(Double tiempo){
		tiempoLibreEmpleada += tiempo;
	}

	public Double obtenerPorcentajeDeTiempoLibreEmpleada(){
		return tiempoLibreEmpleada / tiempoDeTurno;
	}

}
