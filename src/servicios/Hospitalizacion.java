// src/servicio/Hospitalizacion.java
package servicio;

import registro.RegistroAtencion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hospitalizacion extends ServicioVeterinario {

    private final RegistroAtencion registro;
    private final int diasEstancia;
    private final String tipoHabitacion;
    private final double costoDiario;
    private final List<String> notasEvolucion = new ArrayList<>();

    /**
     * @param registro        Registro de atención existente
     * @param diasEstancia    Número de días de hospitalización (>=1)
     * @param tipoHabitacion  Ej. "General", "Privada"
     * @param costoDiario     Tarifa diaria de la habitación
     */
    public Hospitalizacion(RegistroAtencion registro,
                           int diasEstancia,
                           String tipoHabitacion,
                           double costoDiario) {
        super(registro.getFechaIngreso(),
              registro.getMotivo(),
              registro.getMascota(),
              registro.getAsistente());
        if (diasEstancia < 1) {
            throw new IllegalArgumentException("La estancia debe ser al menos 1 día.");
        }
        this.registro = registro;
        this.diasEstancia = diasEstancia;
        this.tipoHabitacion = tipoHabitacion;
        this.costoDiario = costoDiario;
    }

    /**
     * Permite agregar observaciones diarias durante la hospitalización.
     */
    public void agregarNota(String nota) {
        if (nota != null && !nota.isBlank()) {
            notasEvolucion.add(nota);
        }
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Hospitalizando a " + getMascota().getNombre()
            + " por " + diasEstancia + " días en habitación " + tipoHabitacion + ".");
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        return diasEstancia * costoDiario;
    }

    /**
     * Extrae toda la información de la hospitalización.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("fechaIngreso", registro.getFechaIngreso());
        info.put("mascota", registro.getMascota().getRegistro());
        info.put("diasEstancia", diasEstancia);
        info.put("tipoHabitacion", tipoHabitacion);
        info.put("costoDiario", costoDiario);
        info.put("costoTotal", calcularCosto());
        info.put("notasEvolucion", List.copyOf(notasEvolucion));
        return info;
    }
}
