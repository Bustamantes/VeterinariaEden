// src/servicio/ServicioVeterinario.java
package servicio;

import modelo.Mascota;
import modelo.Empleado;
import java.time.LocalDate;

public abstract class ServicioVeterinario {
    public enum Estado { RECIBIDO, EN_PROCESO, TERMINADO }

    protected LocalDate fecha;
    protected String motivo;
    protected Mascota mascota;
    protected Empleado asistente;
    protected Estado estado;

    public ServicioVeterinario(LocalDate fecha, String motivo, Mascota mascota, Empleado asistente) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.mascota = mascota;
        this.asistente = asistente;
        this.estado = Estado.RECIBIDO;
    }

    // Plantilla de ejecución del servicio
    public final void realizarServicio() {
        iniciar();
        aplicarServicio();  // hook sobrescribible
        finalizar();
    }

    // Método común: inicializa el estado del servicio
    protected void iniciar() {
        System.out.println("Iniciando servicio: " + this.getClass().getSimpleName());
        this.estado = Estado.EN_PROCESO;
    }

    // Hook obligatorio para cada subclase
    protected abstract void aplicarServicio();

    // Método común: marca finalización
    protected void finalizar() {
        System.out.println("Finalizando servicio.");
        this.estado = Estado.TERMINADO;
    }

    // Hook para cálculo de costo, sobreescribible
    public double calcularCosto() {
        return 10.0; // valor base genérico (puede sobrescribirse)
    }

    // Getters
    public LocalDate getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
    public Mascota getMascota() { return mascota; }
    public Empleado getAsistente() { return asistente; }
    public Estado getEstado() { return estado; }
}
