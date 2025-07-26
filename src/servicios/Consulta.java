package servicio;

import modelo.Mascota;
import modelo.Empleado;

import java.time.LocalDate;
import java.time.Period;

public class Consulta extends ServicioVeterinario {

    public Consulta(LocalDate fecha, String motivo, Mascota mascota, Empleado asistente) {
        super(fecha, motivo, mascota, asistente);
    }

    @Override
    protected void aplicarServicio() {
        System.out.println("Realizando consulta general para la mascota: " + mascota.getNombre());
        // Aquí podría ir lógica de revisión, diagnóstico, etc.
    }

    @Override
    public double calcularCosto() {
        double base = 25.0;
        int edad = Period.between(mascota.getFechaNacimiento(), fecha).getYears();
        if (edad > 10) {
            return base * 1.5; // recargo geriátrico
        }
        return base;
    }
}

