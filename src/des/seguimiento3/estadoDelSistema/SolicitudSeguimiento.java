package seguimiento3.estadoDelSistema;

public class SolicitudSeguimiento{
    private Integer cantidadDeProductos;
    private Producto producto;
    private Double tiempoDeArribo;
    private Double tiempoEnKiosko;

    public SolicitudSeguimiento(Double tiempoDeArribo, Producto producto, Integer cantidadDeProductos) {
        this.cantidadDeProductos = cantidadDeProductos;
        this.producto = producto;
        this.tiempoDeArribo = tiempoDeArribo;
    }
    public int getCantidadDeProductos() {
        return cantidadDeProductos;
    }
    public Producto getProducto() {
        return producto;
    }
    public Double getTiempoDeArribo() {
        return tiempoDeArribo;
    }

    public void setTiempoEnKiosko(Double tiempoEnKiosko) {
            this.tiempoEnKiosko = tiempoEnKiosko;
    }
    public Double getTiempoEnKiosko(){
        return this.tiempoEnKiosko;
    }
}
