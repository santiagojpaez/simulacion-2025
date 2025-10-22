package seguimiento3.componentesPropios;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.ContadoresEstadisticos;

/***************************************/

public  class ContadoresEstadisticosSeguimiento3 extends ContadoresEstadisticos {
	private Integer beneficioTotal = 0;

	public Integer cantidadDeEventosDeCola = 0;
	private Integer acumuladorClientesEnCola = 0;

	public Integer cantidadDeClientes = 0;
	private Double acumuladorTiemposDeClientes = 0.00;

	private Double tiempoLibreEmpleada = 0.00;
	public Double tiempoDeTurno = 0.00;

	@Override
	public void inicializar() {
	}

	void sumarBeneficio(Integer beneficio){
		beneficioTotal += beneficio;
	}
	Integer obtenerBeneficioTotal(){
		return beneficioTotal;
	}

	void actualizarLongitudDeColaActual(Integer longCola){
		acumuladorClientesEnCola += longCola;
		cantidadDeEventosDeCola++;
	}
	Double obtenerPromedioLongitudCola(){
		return acumuladorClientesEnCola.doubleValue() / cantidadDeEventosDeCola.doubleValue();
	}

	void agregarTiempoCliente(Double tiempo){
		acumuladorTiemposDeClientes+=tiempo;
		cantidadDeClientes++;
	}
	Double obtenerPromedioTiempoClientesEnCola(){
		return acumuladorTiemposDeClientes / cantidadDeClientes.doubleValue();
	}

	void setearTiempoDeTurno(Double tiempo){
		tiempoDeTurno = tiempo;
	}

	Double obtenerTasaDeAtencion(){
		return cantidadDeClientes.doubleValue() / tiempoDeTurno;
	}

	void setearTiempoLibreEmpleada(Double tiempo){
		tiempoLibreEmpleada = tiempo;
	}

	Double obtenerPorcentajeDeTiempoLibreEmpleada(){
		return tiempoLibreEmpleada / tiempoDeTurno;
	}

}
