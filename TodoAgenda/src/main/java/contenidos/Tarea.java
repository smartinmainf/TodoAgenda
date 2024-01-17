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

public class Tarea extends ElementoAgenda {
    private LocalDate fechaVencimiento;
    private boolean esPrioritaria;
    private LocalDate fechaRecordatorio;

    public Tarea(String titulo, String anotaciones, Tipo tipo, LocalDate fechaVencimiento, boolean esPrioritaria, LocalDate fechaRecordatorio) {
        super(titulo, anotaciones, tipo);
        this.fechaVencimiento = fechaVencimiento;
        this.esPrioritaria = esPrioritaria;
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isEsPrioritaria() {
        return esPrioritaria;
    }

    public void setEsPrioritaria(boolean esPrioritaria) {
        this.esPrioritaria = esPrioritaria;
    }

    public LocalDate getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(LocalDate fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    
   
    @Override
    public String toString() {
        return "Tarea{" +
               "titulo='" + titulo + '\'' +
               ", anotaciones='" + anotaciones + '\'' +
               ", tipo=" + tipo +
               ", fechaVencimiento=" + fechaVencimiento +
               ", esPrioritaria=" + esPrioritaria +
               ", fechaRecordatorio=" + fechaRecordatorio +
               '}';
    }
}
