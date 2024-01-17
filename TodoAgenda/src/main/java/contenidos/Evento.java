/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenidos;

/**
 *
 * @author administrador
 */
import enumeraciones.Tipo;
import java.time.LocalDate;

public class Evento extends ElementoAgenda {
    private LocalDate fecha;

    
    public Evento(String titulo, String anotaciones, Tipo tipo, LocalDate fecha) {
        super(titulo, anotaciones, tipo);
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }



    @Override
    public String toString() {
        return "Evento{" +
               "titulo='" + titulo + '\'' +
               ", anotaciones='" + anotaciones + '\'' +
               ", tipo=" + tipo +
               ", fecha=" + fecha +
               '}';
    }
}
