package modelo;

public class Empleado extends Persona {

    private TipoEmpleado tipo;

    public Empleado(String identificacion, String nombre, String apellido, TipoEmpleado tipo) {
        super(identificacion, nombre, apellido);
        this.tipo = tipo;
    }

    public TipoEmpleado getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleado tipo) {
        this.tipo = tipo;
    }

    @Override
    public String identificar() {
        return super.identificar() + " - Tipo: " + tipo;
    }

    @Override
    public String toString() {
        return "[" + tipo + "] " + getNombre() + " " + getApellido() +
               " (ID: " + getIdentificacion() + ")";
    }
}
