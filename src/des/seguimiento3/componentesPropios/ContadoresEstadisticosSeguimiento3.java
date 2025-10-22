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

	public void sumarBeneficio(Integer beneficio){
		beneficioTotal += beneficio;
	}
	public Integer obtenerBeneficioTotal(){
		return beneficioTotal;
	}

	public void actualizarLongitudDeColaActual(Integer longCola){
		acumuladorClientesEnCola += longCola;
		cantidadDeEventosDeCola++;
	}
	public Double obtenerPromedioLongitudCola(){
		return acumuladorClientesEnCola.doubleValue() / cantidadDeEventosDeCola.doubleValue();
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

	public Double obtenerTasaDeAtencion(){
		return cantidadDeClientes.doubleValue() / tiempoDeTurno;
	}

	public void setearTiempoLibreEmpleada(Double tiempo){
		tiempoLibreEmpleada = tiempo;
	}

	public Double obtenerPorcentajeDeTiempoLibreEmpleada(){
		return tiempoLibreEmpleada / tiempoDeTurno;
	}

}
