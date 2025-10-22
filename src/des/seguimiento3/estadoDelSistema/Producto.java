package seguimiento3.estadoDelSistema;

public enum Producto{
    BEBIDA_SALUDABLE("Bebida saludable",600,1200),
    PANADERIA("Panaderia",400,850);

    private final String nombre;
    private final Integer costoUnitario;
    private final Integer precioUnitario;

    private Producto(String nombre, Integer costoUnitario, Integer precioUnitario){
        this.nombre = nombre;
        this.costoUnitario = costoUnitario;
        this.precioUnitario = precioUnitario;
    }

    public Integer getBeneficioUnitario(){
        return this.precioUnitario - this.costoUnitario;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
}
