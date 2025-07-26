// src/servicio/Urgencia.java
package servicio;

import registro.RegistroAtencion;

import java.util.HashMap;
import java.util.Map;

public class Urgencia extends ServicioVeterinario {

    private final RegistroAtencion registro;
    private final String nivelGravedad;
    private final String descripcion;
    private final int tiempoAtencion;  // en minutos

    /**
     * @param registro        Registro de atención existente
     * @param nivelGravedad   "BAJA", "MEDIA" o "ALTA"
     * @param descripcion     Descripción de la intervención
     * @param tiempoAtencion  Minutos desde que se inició la atención
     */
    public Urgencia(RegistroAtencion registro,
                    String nivelGravedad,
                    String descripcion,
                    int tiempoAtencion) {
        super(registro.getFechaIngreso(),
              registro.getMotivo(),
              registro.getMascota(),
              registro.getAsistente());
        this.registro = registro;
        this.nivelGravedad = nivelGravedad.toUpperCase();
        this.descripcion = descripcion;
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Urgencia: nivel " + nivelGravedad
            + " para " + getMascota().getNombre()
            + ". Intervención: " + descripcion);
        // Validación de nivel de gravedad
        if (!nivelGravedad.matches("BAJA|MEDIA|ALTA")) {
            throw new IllegalArgumentException("Nivel de gravedad inválido: " + nivelGravedad);
        }
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        double base = 50.0;                    // tarifa base urgencia
        double recargoGravedad = switch (nivelGravedad) {
            case "BAJA"  -> 1.0;
            case "MEDIA" -> 1.5;
            case "ALTA"  -> 2.0;
            default      -> 1.0;
        };
        double costoTiempo = 1.0 * tiempoAtencion;  // $1 por minuto de atención
        return (base * recargoGravedad) + costoTiempo;
    }

    /**
     * Reúne datos para facturación o reportes.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("fecha", registro.getFechaIngreso());
        info.put("mascota", registro.getMascota().getRegistro());
        info.put("nivelGravedad", nivelGravedad);
        info.put("descripcion", descripcion);
        info.put("tiempoAtencion", tiempoAtencion);
        info.put("costo", calcularCosto());
        return info;
    }
}

