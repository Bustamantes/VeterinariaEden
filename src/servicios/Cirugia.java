
// src/servicio/Cirugia.java
package servicio;

import registro.RegistroAtencion;

import java.util.HashMap;
import java.util.Map;

public class Cirugia extends ServicioVeterinario {

    private final RegistroAtencion registro;
    private final String tipoCirugia;
    private final int duracionMin;        // en minutos
    private final String tipoAnestesia;   // "General" o "Local"

    /**
     * @param registro        Registro de atención existente
     * @param tipoCirugia     Descripción de la cirugía
     * @param duracionMin     Duración estimada en minutos
     * @param tipoAnestesia   Tipo de anestesia ("General"|"Local")
     */
    public Cirugia(RegistroAtencion registro,
                   String tipoCirugia,
                   int duracionMin,
                   String tipoAnestesia) {
        super(registro.getFechaIngreso(),
              registro.getMotivo(),
              registro.getMascota(),
              registro.getAsistente());
        this.registro = registro;
        this.tipoCirugia = tipoCirugia;
        this.duracionMin = duracionMin;
        this.tipoAnestesia = tipoAnestesia;
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Realizando cirugía (" + tipoCirugia + ") a "
            + getMascota().getNombre()
            + " con anestesia " + tipoAnestesia
            + " por " + duracionMin + " minutos.");
        // Aquí podrías validar el preset de anestesia o confirmar disponibilidad de quirófano
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        double base = 100.0;                  // costo base por cirugía
        double porMinuto = 2.0 * duracionMin; // costo de uso de quirófano
        double factorAnestesia = tipoAnestesia.equalsIgnoreCase("General") ? 1.5 : 1.0;
        double factorComplejidad = switch (tipoCirugia.toLowerCase()) {
            case "esterilización"   -> 1.0;
            case "tumor"            -> 1.8;
            case "fractura"         -> 1.5;
            default                 -> 1.2;
        };
        return (base * factorComplejidad + porMinuto) * factorAnestesia;
    }

    /**
     * Extrae datos para facturación o reportes.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("fecha", registro.getFechaIngreso());
        info.put("mascota", registro.getMascota().getRegistro());
        info.put("cirugia", tipoCirugia);
        info.put("duracionMin", duracionMin);
        info.put("anestesia", tipoAnestesia);
        info.put("costo", calcularCosto());
        return info;
    }
}
