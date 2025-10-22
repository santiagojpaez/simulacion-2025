package seguimiento3.componentesPropios;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.LibreriaDeRutinas;
import seguimiento3.estadoDelSistema.Producto;

/***************************************/

public  class LibreriaDeRutinasSeguimiento3 extends LibreriaDeRutinas {

    public double tiempoEntreArribosSolicitudes() {
        // Llegan clientes con una exponencial de media 4
        return randomExponential(4);
    }

    public Producto productoAleatorio(){
        double rnd = Math.random();
        if(rnd < 0.7){
            return Producto.BEBIDA_SALUDABLE;
        }
        return Producto.PANADERIA;
    }

    public Integer cantidadDeUnidadesProductoBebida(){
        double rnd = Math.random();
        if (rnd < 0.57) return 1;
        if (rnd < 0.9) return 2;
        return 3;
    }

    public Integer cantidadDeUnidadesProductoPanaderia(){
        double rnd = Math.random();
        return switch (rnd) {
            case double v when v < 0.27 -> 1;
            case double v when v < 0.52 -> 2;
            case double v when v < 0.87 -> 3;
            default -> 4;
        };
    }

    public Double obtenerTiempoServicioBebida(Integer cant){
        Double baseTime = randomExponential(2.4);
        Double multiplicador = switch (cant) {
            case 2 -> 1.1;
            case 3 -> 1.13;
            default -> 1.00;
        };
        return baseTime*multiplicador;
    }

    public Double obtenerTiempoServicioPanadaria(Integer cant){
        Double baseTime = randomExponential(3.5);
        Double multiplicador = switch (cant) {
            case 2 -> 1.12;
            case 3 -> 1.15;
            case 4 -> 1.20;
            default -> 1.00;
        };
        return baseTime*multiplicador;
    }

    private Double randomExponential(double media){
        double randomNumber = Math.random();
        double lambda = 1 / media;
        return -Math.log(1 - randomNumber) / lambda;
    }
}
