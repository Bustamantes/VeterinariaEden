# Proyecto Veterinaria Edén

Este repositorio contiene la implementación en Java de un sistema de gestión para la Veterinaria Edén. A continuación encontrarás el plan de acción con las tareas ordenadas desde el inicio hasta la culminación del código.

---


## Descripción del Proyecto

La veterinaria Edén necesita un software que permita:
- Registrar responsables (dueños) y sus mascotas (perros, gatos, aves).  
- Gestionar servicios veterinarios (consulta, baño, vacuna, cirugía, urgencia, hospitalización).  
- Llevar un registro de atenciones y generar facturas.  
- Mostrar informes y estadísticas a través de un menú de consola.

---

## Estructura del Repositorio

```plaintext
VeterinariaEden/
├── README.md
├── .gitignore
├── src/
│   └── modelo/
│       ├── Persona.java
│       ├── Responsable.java
│       ├── Asistente.java
│       ├── Veterinario.java
│       ├── Mascota.java
│       ├── Perro.java
│       ├── Gato.java
│       └── Ave.java
│   └── servicio/
│       ├── ServicioVeterinario.java
│       ├── Consulta.java
│       ├── Bano.java
│       ├── Vacuna.java
│       ├── Cirugia.java
│       ├── Urgencia.java
│       └── Hospitalizacion.java
│   └── registro/
│       ├── RegistroAtencion.java
│       └── Factura.java
│   └── ui/
│       └── MenuPrincipal.java
└── docs/
    └── diagramas/
        └── diagrama_clases.png
````

---

## Plan de Acción

Sigue estas tareas en orden para avanzar de lo más básico a lo más complejo:

1. **Inicialización y configuración**

   * Crear repositorio en GitHub (`VeterinariaEden`).
   * Invitar colaboradores al repositorio.

2. **Modelo de dominio – Clases base**

   1. `Persona.java`

      * Definir atributos: `identificacion`, `nombre`, `apellido`.
      * Crear constructor, getters/setters y método `identificar()`.
   2. `Responsable.java`

      * Heredar de `Persona`.
      * Añadir `direccion`, `telefono`, `correo` y lista de `Mascota`.
      * Implementar `agregarMascota()` y override de `identificar()`.
   3. `Mascota.java` (abstracta)

      * Atributos: `registro`, `nombre`, `fechaNacimiento`, `raza`, `Responsable`.
      * Método abstracto `identificar()`.
   4. Subclases de `Mascota`:

      * `Perro.java` (patas, cola, pedigree)
      * `Gato.java` (patas, cola)
      * `Ave.java` (patas, alas)
      * Implementar constructores, getters/setters y `identificar()`.

3. **Relaciones y pruebas unitarias básicas**

   * Escribir un `main` o tests simples que:

     * Creen un `Responsable`.
     * Creen varias `Mascota` y las asocien.
     * Impriman resultados de `identificar()`.

4. **Módulo de servicios veterinarios**

   1. `ServicioVeterinario.java` (interfaz o clase abstracta)

      * Atributos comunes: fecha, motivo, mascota, asistente, estado.
      * Métodos: `iniciar()`, `finalizar()`, `calcularCosto()`.
   2. Subclases concretas:

      * `Consulta.java`, `Bano.java`, `Vacuna.java`, `Cirugia.java`, `Urgencia.java`, `Hospitalizacion.java`.
      * Implementar `calcularCosto()` según reglas de negocio.

5. **Registro de atenciones y facturación**

   1. `RegistroAtencion.java`

      * Guardar fecha de ingreso, servicio, estatus, asistente.
      * Métodos para actualizar estado y recuperar historial.
   2. `Factura.java`

      * Asociar a un `RegistroAtencion` o `ServicioVeterinario`.
      * Generar detalle y monto total.

6. **Menú de interfaz de usuario (consola)**

   * `MenuPrincipal.java` con opciones:

     1. Mostrar mascotas atendidas por tipo de servicio.
     2. Listar mascotas atendidas para un servicio X.
     3. Listar mascotas por propietario.
     4. Listar mascotas por tipo (perro, gato, ave).
     5. Mostrar distribución porcentual por tipo de servicio.
     6. Mostrar distribución porcentual por tipo de mascota.
   * Validar entradas del usuario y mostrar resultados en consola.

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

```
```
