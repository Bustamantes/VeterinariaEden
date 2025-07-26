// src/servicio/Consulta.java
package servicio;

import modelo.Mascota;
import modelo.Empleado;
import registro.RegistroAtencion;

import java.time.Period;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Consulta extends ServicioVeterinario {

    private final RegistroAtencion registro;
    private final String sintomas;
    private final String diagnostico;
    private final String tratamiento;
    private final String observaciones;

    public Consulta(RegistroAtencion registro,
                    String sintomas,
                    String diagnostico,
                    String tratamiento,
                    String observaciones) {
        super(registro.getFechaIngreso(),
              registro.getMotivo(),
              registro.getMascota(),
              registro.getAsistente());
        this.registro = registro;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Consulta: revisando síntomas de " + getMascota().getNombre());
        // ... aquí podrías añadir lógica clínica adicional ...
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        double base = 25.0;
        int edad = Period.between(getMascota().getFechaNacimiento(), getFecha()).getYears();
        return (edad > 10) ? base * 1.5 : base;
    }

    /**
     * Reúne toda la información del registro y de la consulta,
     * incluyendo un resumen dinámico de servicios realizados.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new LinkedHashMap<>();
        // — Datos de registro y consulta —
        info.put("fechaIngreso", registro.getFechaIngreso());
        info.put("motivo", registro.getMotivo());
        info.put("mascotaRegistro", registro.getMascota().getRegistro());
        info.put("asistenteID", registro.getAsistente().getIdentificacion());
        info.put("sintomas", sintomas);
        info.put("diagnostico", diagnostico);
        info.put("tratamiento", tratamiento);
        info.put("observaciones", observaciones);
        info.put("costoConsulta", calcularCosto());

        // — Resumen dinámico de servicios realizados en esta visita —
        Map<String, Long> resumen = registro.getServicios().stream()
            .collect(Collectors.groupingBy(
                s -> s.getClass().getSimpleName(),   // e.g. "Bano", "Vacuna"
                Collectors.counting()
            ));

        // Aseguramos que todas las categorías existan, incluso con 0
        String[] tipos = {"Bano", "Vacuna", "Cirugia", "Urgencia", "Hospitalizacion"};
        for (String tipo : tipos) {
            info.put("n" + tipo, resumen.getOrDefault(tipo, 0L));
        }

        return info;
    }
}
