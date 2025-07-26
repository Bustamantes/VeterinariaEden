// src/servicio/Vacuna.java
package servicio;

import registro.RegistroAtencion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Vacuna extends ServicioVeterinario {

    private final RegistroAtencion registro;
    private final String tipoVacuna;
    private final double dosisMl;
    private final LocalDate proximaDosis;  // opcional, puede ser null

    /**
     * @param registro      Registro de atención existente
     * @param tipoVacuna    Nombre del tipo de vacuna (e.g., "Rabia")
     * @param dosisMl       Cantidad de dosis en mililitros
     * @param proximaDosis  Fecha sugerida para próxima dosis (null si no aplica)
     */
    public Vacuna(RegistroAtencion registro,
                  String tipoVacuna,
                  double dosisMl,
                  LocalDate proximaDosis) {
        super(registro.getFechaIngreso(),
              registro.getMotivo(),
              registro.getMascota(),
              registro.getAsistente());
        this.registro = registro;
        this.tipoVacuna = tipoVacuna;
        this.dosisMl = dosisMl;
        this.proximaDosis = proximaDosis;
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Aplicando vacuna " + tipoVacuna +
                           " (" + dosisMl + "ml) a " + getMascota().getNombre());
        // Podrías validar aquí contra el historial de la mascota
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        // Define precios base por tipo de vacuna
        Map<String, Double> precios = Map.of(
            "Rabia", 20.0,
            "Polivalente", 25.0,
            "Distemper", 18.0,
            "Parvovirus", 22.0
        );
        double precioBase = precios.getOrDefault(tipoVacuna, 15.0);
        // Si dosis es distinta de estándar (1ml), ajusta proporcionalmente
        return precioBase * (dosisMl / 1.0);
    }

    /**
     * Reúne datos para facturación o reportes.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("fecha", registro.getFechaIngreso());
        info.put("mascota", registro.getMascota().getRegistro());
        info.put("vacuna", tipoVacuna);
        info.put("dosisMl", dosisMl);
        info.put("proximaDosis", proximaDosis);
        info.put("costo", calcularCosto());
        return info;
    }
}

