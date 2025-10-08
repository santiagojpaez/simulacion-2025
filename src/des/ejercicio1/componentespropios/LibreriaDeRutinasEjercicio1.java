package ejercicio1.componentespropios;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */

import des.LibreriaDeRutinas;
import ejercicio1.estadodelsistema.Solicitud;

/***************************************/

public  class LibreriaDeRutinasEjercicio1 extends LibreriaDeRutinas {

    public double tiempoEntreArribosSolicitudes() {
        double randomNumber = Math.random();
        double media = 1.91;
        double lambda = 1 / media;
        return -Math.log(1 - randomNumber) / lambda;
    }

    public double tiempoDeProcesamiento() {
        return uniformeRandom(0.5,2.5);

    }

    private double uniformeRandom(double min, double max) {
        return min + (max - min) * Math.random();
    }
}
