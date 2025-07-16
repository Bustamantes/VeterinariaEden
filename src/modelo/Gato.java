package modelo;

import java.time.LocalDate;

public class Gato extends Mascota {
    private int patas;
    private boolean cola;

    /**
    Estos son los valores que usaré:
     * @param registro        Número de registro único de la mascota
     * @param nombre          Nombre del gato
     * @param fechaNacimiento Fecha de nacimiento
     * @param raza            Raza del gato
     * @param responsable     Dueño responsable
     * @param patas           Número de patas (normalmente 4)
     * @param cola            Indica si tiene cola
     */

  
    public Gato(String registro,
                String nombre,
                LocalDate fechaNacimiento,
                String raza,
                Responsable responsable,
                int patas,
                boolean cola) {
        super(registro, nombre, fechaNacimiento, raza, responsable);
        this.patas = patas;
        this.cola = cola;
        // Asociar al responsable
        responsable.agregarMascota(this);
    }

    @Override
    public String identificar() {
        return String.format(
            "Gato: %s | Raza: %s | Nacido: %s | Patas: %d | Cola: %s | Responsable: %s",
            nombre,
            raza,
            fechaNacimiento,
            patas,
            cola ? "sí" : "no",
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
}

