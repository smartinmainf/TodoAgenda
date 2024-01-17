/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenidos;

/**
 *
 * @author administrador
 */
import java.util.LinkedList;
import java.util.Scanner;

public class ContactoAgenda {
    private LinkedList<Contacto> contactos;

    // Constructor
    public ContactoAgenda() {
        this.contactos = new LinkedList<>();
    }

    // Método para administrar contactos
    public void administrarContactos() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("¿Qué desea hacer con los contactos?");
            System.out.println("(A)ñadir, (M)mostrar, (E)eliminar, (S)alir");
            String opcion = sc.next();

            switch (opcion) {
                case "A":
                    añadirContactoInteractivo(sc);
                    break;
                case "M":
                    mostrarContactos();
                    break;
                case "E":
                    eliminarContactoInteractivo(sc);
                    break;
                case "S":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    // Métodos auxiliares para añadir, mostrar y eliminar contactos
    private void añadirContactoInteractivo(Scanner sc) {
        System.out.println("Introduce el nombre del contacto:");
        sc.nextLine();  // Limpiar buffer
        String nombre = sc.nextLine();

        System.out.println("Introduce el teléfono del contacto:");
        String telefono = sc.nextLine();

        System.out.println("Introduce el email del contacto:");
        String email = sc.nextLine();

        Contacto nuevoContacto = new Contacto(nombre, telefono, email);
        contactos.add(nuevoContacto);
        System.out.println("Contacto añadido correctamente.");
    }

    private void mostrarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
        } else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }

    private void eliminarContactoInteractivo(Scanner sc) {
        System.out.println("Introduce el nombre del contacto a eliminar:");
        sc.nextLine();  // Limpiar buffer
        String nombre = sc.nextLine();

        boolean eliminado = contactos.removeIf(contacto -> contacto.getNombre().equalsIgnoreCase(nombre));
        if (eliminado) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("El contacto no se ha encontrado.");
        }
    }
}
