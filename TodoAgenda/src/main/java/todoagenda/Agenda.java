/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todoagenda;

/**
 *
 * @author administrador
 */



import contenidos.ElementoAgenda;
import contenidos.ContactoAgenda;
import contenidos.Tarea;
import contenidos.Evento;
import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda {
    private ArrayList<ElementoAgenda> elementos;
    private ContactoAgenda contactoAgenda;

 
    public Agenda() {
        this.elementos = new ArrayList<>();
        this.contactoAgenda = new ContactoAgenda();
    }

    public void agregarElemento(ElementoAgenda elemento) {
        elementos.add(elemento);
    }

    public boolean eliminarElemento(String titulo) {
        return elementos.removeIf(elemento -> elemento.getTitulo().equalsIgnoreCase(titulo));
    }

    public ElementoAgenda buscarElementoPorTitulo(String titulo) {
        for (ElementoAgenda elemento : elementos) {
            if (elemento.getTitulo().equalsIgnoreCase(titulo)) {
                return elemento;
            }
        }
        return null;
    }

    public void mostrarElementos() {
        if (elementos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            for (ElementoAgenda elemento : elementos) {
                System.out.println(elemento);
            }
        }
    }

    public void mostrarElementosTipo(String tipo) {
        for (ElementoAgenda elemento : elementos) {
            if ((tipo.equalsIgnoreCase("Evento") && elemento instanceof Evento) ||
                (tipo.equalsIgnoreCase("Tarea") && elemento instanceof Tarea)) {
                System.out.println(elemento);
            }
        }
    }

    public void mostrarElementosFecha(LocalDate fecha) {
        for (ElementoAgenda elemento : elementos) {
            if (elemento instanceof Evento && ((Evento) elemento).getFecha().equals(fecha)) {
                System.out.println(elemento);
            } else if (elemento instanceof Tarea) {
                Tarea tarea = (Tarea) elemento;
                if ((tarea.getFechaVencimiento() != null && tarea.getFechaVencimiento().equals(fecha)) ||
                    (tarea.getFechaRecordatorio() != null && tarea.getFechaRecordatorio().equals(fecha))) {
                    System.out.println(elemento);
                }
            }
        }
    }

    public ContactoAgenda getContactoAgenda() {
        return contactoAgenda;
    }

}
