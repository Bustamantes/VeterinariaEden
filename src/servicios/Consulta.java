// src/servicio/Consulta.java
package servicio;

import modelo.Mascota;
import modelo.Empleado;
import registro.RegistroAtencion;

import java.time.Period;
import java.util.HashMap;
import java.util.Map;

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
        // Lógica de consulta clínica
        System.out.println("Consulta: revisando síntomas de " + getMascota().getNombre());
        // ... podrías añadir validaciones, registros de signos vitales, etc.
        // Al terminar, vinculamos este servicio al registro:
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        double base = 25.0;
        int edad = Period.between(getMascota().getFechaNacimiento(), getFecha()).getYears();
        return (edad > 10) ? base * 1.5 : base;
    }

    /**
     * Reúne toda la información del registro y de la consulta
     * para presentar en UI, facturación o reportes.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("fechaIngreso", registro.getFechaIngreso());
        info.put("motivo", registro.getMotivo());
        info.put("mascotaRegistro", registro.getMascota().getRegistro());
        info.put("asistenteID", registro.getAsistente().getIdentificacion());
        info.put("síntomas", sintomas);
        info.put("diagnóstico", diagnostico);
        info.put("tratamiento", tratamiento);
        info.put("observaciones", observaciones);
        info.put("costo", calcularCosto());
        return info;
    }
}
