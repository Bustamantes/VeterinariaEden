package modelo;

import java.util.ArrayList;
import java.util.List;

public class Responsable extends Persona {
    private String direccion;
    private String telefono;
    private String correo;
    private List<Mascota> mascotas;

    public Responsable(String identificacion,
                       String nombre,
                       String apellido,
                       String direccion,
                       String telefono,
                       String correo) {
        super(identificacion, nombre, apellido);
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.mascotas = new ArrayList<>();
    }

    /**
     * Agrega una mascota al responsable.
     * Al añadirla, también fija este responsable en la mascota.
     */
    public void agregarMascota(Mascota mascota) {
        if (mascota != null && !mascotas.contains(mascota)) {
            mascotas.add(mascota);
            mascota.setResponsable(this);
        }
    }

    /**
     * Devuelve la lista de mascotas asignadas a este responsable.
     */
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    // Getters y setters de los atributos propios

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String identificar() {
        return super.identificar()
             + " | Dirección: " + direccion
             + " | Tel: " + telefono
             + " | Email: " + correo;
    }
}

