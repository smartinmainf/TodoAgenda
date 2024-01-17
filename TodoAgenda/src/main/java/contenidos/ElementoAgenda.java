/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenidos;

import enumeraciones.Tipo;

/**
 *
 * @author administrador
 */
public abstract class ElementoAgenda {
    protected String titulo;
    protected String anotaciones;
    protected Tipo tipo;

    public ElementoAgenda(String titulo, String anotaciones, Tipo tipo) {
        this.titulo = titulo;
        this.anotaciones = anotaciones;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }




    @Override
    public String toString() {
        return "ElementoAgenda{" +
               "titulo='" + titulo + '\'' +
               ", anotaciones='" + anotaciones + '\'' +
               ", tipo=" + tipo +
               '}';
    }


}
