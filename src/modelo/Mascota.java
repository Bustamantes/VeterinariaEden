package modelo;

import java.time.LocalDate;

public abstract class Mascota {
    protected String registro;
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String raza;
    protected Responsable responsable;

    public Mascota(String registro,
                   String nombre,
                   LocalDate fechaNacimiento,
                   String raza,
                   Responsable responsable) {
        this.registro = registro;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.responsable = responsable;
    }

    public abstract String identificar();

    // Getters y setters | Necesito mejorar esta parte

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
