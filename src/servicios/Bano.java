// src/servicio/Bano.java
package servicio;

import registro.RegistroAtencion;
import modelo.Mascota;
import modelo.Empleado;

import java.util.HashMap;
import java.util.Map;

public class Bano extends ServicioVeterinario {

    private final RegistroAtencion registro;
    private final int duracionMin;  // en minutos
    private final Map<String, Integer> productos;  // nombre → cantidad usada

    /**
     * @param registro     Registro de atención existente
     * @param duracionMin  Duración estimada en minutos
     * @param productos    Mapa de productos (ej. "shampoo"→1, "toalla"→2)
     */
    public Bano(RegistroAtencion registro,
                int duracionMin,
                Map<String, Integer> productos) {
        super(registro.getFechaIngreso(),
              registro.getMotivo(),
              registro.getMascota(),
              registro.getAsistente());
        this.registro = registro;
        this.duracionMin = duracionMin;
        this.productos = new HashMap<>(productos);
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Iniciando baño de " + getMascota().getNombre()
            + " durante " + duracionMin + " minutos.");
        // Aquí podrías invocar Inventario.consumir(producto, cantidad) si existiera
        // Vinculamos este servicio al registro:
        registro.agregarServicio(this);
    }

    @Override
    public double calcularCosto() {
        // Regla de negocio: tarifa base + cargo por minuto + costo de productos
        double base = 15.0;                 // tarifa fija por baño
        double porMinuto = 0.3 * duracionMin;
        double costoProductos = productos.entrySet()
            .stream()
            .mapToDouble(e -> {
                String prod = e.getKey();
                int qty = e.getValue();
                // ejemplo de precios hardcodeados; idealmente vendrían de inventario
                double precioUnit = switch (prod.toLowerCase()) {
                    case "shampoo"     -> 5.0;
                    case "acondicionador" -> 4.0;
                    case "toalla"      -> 1.0;
                    default            -> 2.0;
                };
                return precioUnit * qty;
            })
            .sum();
        return base + porMinuto + costoProductos;
    }

    /**
     * Extrae datos para facturación o reporte.
     */
    public Map<String, Object> extraerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("fecha", registro.getFechaIngreso());
        info.put("mascota", registro.getMascota().getRegistro());
        info.put("duracionMin", duracionMin);
        info.put("productos", new HashMap<>(productos));
        info.put("costo", calcularCosto());
        return info;
    }
}

