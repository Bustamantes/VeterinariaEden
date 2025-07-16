
package modelo;

import java.time.LocalDate;

public class Ave extends Mascota {
    private int patas;
    private int alas;

    /**
     * @param registro        Número de registro único de la mascota
     * @param nombre          Nombre de la ave
     * @param fechaNacimiento Fecha de nacimiento
     * @param raza            Especie o raza del ave
     * @param responsable     Dueño responsable
     * @param patas           Número de patas (normalmente 2)
     * @param alas            Número de alas (normalmente 2)
     */
    public Ave(String registro,
               String nombre,
               LocalDate fechaNacimiento,
               String raza,
               Responsable responsable,
               int patas,
               int alas) {
        super(registro, nombre, fechaNacimiento, raza, responsable);
        this.patas = patas;
        this.alas = alas;
        // Asociar al responsable
        responsable.agregarMascota(this);
    }

    @Override
    public String identificar() {
        return String.format(
            "Ave: %s | Raza: %s | Nacido: %s | Patas: %d | Alas: %d | Responsable: %s",
            nombre,
            raza,
            fechaNacimiento,
            patas,
            alas,
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

    public int getAlas() {
        return alas;
    }

    public void setAlas(int alas) {
        this.alas = alas;
    }
}
