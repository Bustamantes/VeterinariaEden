// src/registro/RegistroAtencion.java
package registro;

import modelo.Mascota;
import modelo.Empleado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroAtencion {

    public enum Estado { RECIBIDO, EN_PROCESO, TERMINADO }

    private LocalDate fechaIngreso;
    private String motivo;
    private Mascota mascota;
    private Empleado asistente;
    private Estado estado;

    private List<Object> servicios; 

    public RegistroAtencion(LocalDate fechaIngreso,
                             String motivo,
                             Mascota mascota,
                             Empleado asistente) {
        this.fechaIngreso = fechaIngreso;
        this.motivo = motivo;
        this.mascota = mascota;
        this.asistente = asistente;
        this.estado = Estado.RECIBIDO;
        this.servicios = new ArrayList<>();
    }

    // Getters y setters básicos

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Empleado getAsistente() {
        return asistente;
    }

    public void setAsistente(Empleado asistente) {
        this.asistente = asistente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // Gestión de servicios (opcional)
    public void agregarServicio(Object servicio) {
        if (servicio != null) {
            servicios.add(servicio);
        }
    }

    public List<Object> getServicios() {
        return servicios;
    }

    @Override
    public String toString() {
        return "RegistroAtencion{" +
               "fechaIngreso=" + fechaIngreso +
               ", motivo='" + motivo + '\'' +
               ", mascota=" + mascota.getRegistro() +
               ", asistente=" + asistente.getIdentificacion() +
               ", estado=" + estado +
               ", servicios=" + servicios.size() +
               '}';
    }
}
