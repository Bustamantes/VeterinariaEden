// src/ui/MenuPrincipal.java
package ui;

import modelo.Mascota;
import modelo.Perro;
import modelo.Gato;
import modelo.Ave;
import registro.RegistroAtencion;
import servicio.ServicioVeterinario;
import servicio.Bano;
import servicio.Consulta;
import servicio.Vacuna;
import servicio.Cirugia;
import servicio.Urgencia;
import servicio.Hospitalizacion;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MenuPrincipal {

    private final List<RegistroAtencion> registros = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.cargarDatosDeEjemplo();  // opcional: carga registros de prueba
        menu.ejecutar();
    }

    private void ejecutar() {
        int opcion;
        do {
            System.out.println("\n--- Menú Veterinaria Edén ---");
            System.out.println("1. Detalle de mascotas atendidas hoy por tipo de servicio");
            System.out.println("2. Detalle de mascotas atendidas para un servicio específico");
            System.out.println("3. Listado de mascotas por propietario");
            System.out.println("4. Listado de mascotas por tipo");
            System.out.println("5. Distribución porcentual por tipo de servicio");
            System.out.println("6. Distribución porcentual por tipo de mascota");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> detallePorServicioHoy();
                case 2 -> detallePorServicioEspecifico();
                case 3 -> listarPorPropietario();
                case 4 -> listarPorTipoMascota();
                case 5 -> distribucionPorServicio();
                case 6 -> distribucionPorTipoMascota();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void detallePorServicioHoy() {
        LocalDate hoy = LocalDate.now();
        System.out.println("\n> Mascotas atendidas hoy (" + hoy + ") por tipo de servicio:");
        // Para cada servicio en cada registro de hoy, agrupa por tipo
        Map<String, List<String>> mapa = new HashMap<>();
        registros.stream()
            .filter(r -> r.getFechaIngreso().equals(hoy))
            .forEach(r -> r.getServicios().forEach(s -> {
                String tipo = s.getClass().getSimpleName();
                String nombreMascota = r.getMascota().getNombre();
                mapa.computeIfAbsent(tipo, k -> new ArrayList<>()).add(nombreMascota);
            }));
        mapa.forEach((serv, mascotas) -> {
            System.out.println(serv + ": " + String.join(", ", 
                new LinkedHashSet<>(mascotas))); // sin duplicados
        });
    }

    private void detallePorServicioEspecifico() {
        System.out.print("\n> Ingresa el nombre del servicio (e.g. Consulta, Bano): ");
        String servicioBuscado = scanner.nextLine().trim();
        System.out.println("Mascotas que recibieron " + servicioBuscado + ":");
        registros.forEach(r -> r.getServicios().forEach(s -> {
            if (s.getClass().getSimpleName().equalsIgnoreCase(servicioBuscado)) {
                System.out.printf("- %s (Registro: %s, Fecha: %s)%n",
                    r.getMascota().getNombre(),
                    r.getMascota().getRegistro(),
                    r.getFechaIngreso());
            }
        }));
    }

    private void listarPorPropietario() {
        System.out.println("\n> Listado de mascotas por propietario:");
        // Agrupa registros por responsable
        Map<String, Set<String>> mapa = new HashMap<>();
        registros.forEach(r -> {
            String resp = r.getMascota().getResponsable().identificar();
            String nombreMascota = r.getMascota().getNombre();
            mapa.computeIfAbsent(resp, k -> new HashSet<>()).add(nombreMascota);
        });
        mapa.forEach((resp, mascotas) -> {
            System.out.println(resp + ": " + String.join(", ", mascotas));
        });
    }

    private void listarPorTipoMascota() {
        System.out.println("\n> Listado de mascotas por tipo:");
        Map<String, Set<String>> mapa = new HashMap<>();
        registros.forEach(r -> {
            Mascota m = r.getMascota();
            String tipo = switch (m.getClass().getSimpleName()) {
                case "Perro" -> "Perro";
                case "Gato"  -> "Gato";
                case "Ave"   -> "Ave";
                default      -> "Otro";
            };
            mapa.computeIfAbsent(tipo, k -> new HashSet<>()).add(m.getNombre());
        });
        mapa.forEach((tipo, mascotas) -> {
            System.out.println(tipo + ": " + String.join(", ", mascotas));
        });
    }

    private void distribucionPorServicio() {
        System.out.println("\n> Distribución porcentual por tipo de servicio:");
        List<ServicioVeterinario> todos = registros.stream()
            .flatMap(r -> r.getServicios().stream())
            .collect(Collectors.toList());
        int total = todos.size();
        Map<String, Long> conteo = todos.stream()
            .collect(Collectors.groupingBy(
                s -> s.getClass().getSimpleName(),
                Collectors.counting()
            ));
        conteo.forEach((serv, cnt) -> {
            double pct = cnt * 100.0 / total;
            System.out.printf("%s: %.2f%% (%d)%n", serv, pct, cnt);
        });
    }

    private void distribucionPorTipoMascota() {
        System.out.println("\n> Distribución porcentual por tipo de mascota:");
        List<Mascota> todas = registros.stream()
            .map(RegistroAtencion::getMascota)
            .collect(Collectors.toList());
        int total = todas.size();
        Map<String, Long> conteo = todas.stream()
            .collect(Collectors.groupingBy(
                m -> m.getClass().getSimpleName(),
                Collectors.counting()
            ));
        conteo.forEach((tipo, cnt) -> {
            double pct = cnt * 100.0 / total;
            System.out.printf("%s: %.2f%% (%d)%n", tipo, pct, cnt);
        });
    }

    /**  
     * Carga algunos registros y servicios de ejemplo para probar el menú.
     */
    private void cargarDatosDeEjemplo() {
        // Ejemplo mínimo; en tu app real deberías cargar desde base de datos o UI
        Empleado asistente = new Empleado("A001", "Ana", "Pérez", TipoEmpleado.ASISTENTE);
        Perro perro = new Perro("R001", "Rex", LocalDate.now().minusYears(3), "Labrador", null, 4, true, false);
        Gato gato = new Gato("R002", "Michi", LocalDate.now().minusYears(5), "Siames", null, 4, true);
        Ave ave = new Ave("R003", "Tweety", LocalDate.now().minusYears(1), "Canario", null, 2, 2);

        // Asociar responsables (puedes crear responsables reales)
        // ...

        // Registro 1
        RegistroAtencion r1 = new RegistroAtencion(LocalDate.now(), "Chequeo anual", perro, asistente);
        new Consulta(r1, "Tos leve", "Resfriado", "Jarabe", "Descanso").realizarServicio();
        new Bano(r1, 30, Map.of("shampoo", 1)).realizarServicio();
        registros.add(r1);

        // Registro 2
        RegistroAtencion r2 = new RegistroAtencion(LocalDate.now(), "Vacunas de rutina", gato, asistente);
        new Vacuna(r2, "Rabia", 1.0, LocalDate.now().plusMonths(12)).realizarServicio();
        registros.add(r2);

        // Registro 3
        RegistroAtencion r3 = new RegistroAtencion(LocalDate.now(), "Avestrucción de parásitos", ave, asistente);
        new Urgencia(r3, "MEDIA", "Desparasitación rápida", 15).realizarServicio();
        registros.add(r3);
    }
}
