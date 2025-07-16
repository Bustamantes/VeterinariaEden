package modelo;

import java.time.LocalDate;

public class Perro extends Mascota {
    private int patas;
    private boolean cola;
    private boolean pedigree;

    /**
     * @param registro       Número de registro único de la mascota
     * @param nombre         Nombre del perro
     * @param fechaNacimiento Fecha de nacimiento
     * @param raza           Raza del perro
     * @param responsable    Dueño responsable
     * @param patas          Número de patas (normalmente 4)
     * @param cola           Indica si tiene cola
     * @param pedigree       Indica si tiene pedigree
     */
  
    public Perro(String registro,
                 String nombre,
                 LocalDate fechaNacimiento,
                 String raza,
                 Responsable responsable,
                 int patas,
                 boolean cola,
                 boolean pedigree) {
        super(registro, nombre, fechaNacimiento, raza, responsable);
        this.patas = patas;
        this.cola = cola;
        this.pedigree = pedigree;
        // Asociar al responsable
        responsable.agregarMascota(this);
    }

    @Override
    public String identificar() {
        return String.format(
            "Perro: %s | Raza: %s | Nacido: %s | Patas: %d | Cola: %s | Pedigree: %s | Responsable: %s",
            nombre,
            raza,
            fechaNacimiento,
            patas,
            cola ? "sí" : "no",
            pedigree ? "sí" : "no",
            responsable.identificar()
        );
    }

    // Getters y setters

    public int getPatas() {
        return patas;
    }

    public void setPatas(int patas) {
        this.patas = patas;
    }

    public boolean hasCola() {
        return cola;
    }

    public void setCola(boolean cola) {
        this.cola = cola;
    }

    public boolean hasPedigree() {
        return pedigree;
    }

    public void setPedigree(boolean pedigree) {
        this.pedigree = pedigree;
    }
}

