/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenidos;

/**
 *
 * @author administrador
 */
public class Contacto {
    private String nombre;
    private String telefono;
    private String email;

    // Constructor
    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    @Override
    public String toString() {
        return "Contacto{" +
               "nombre='" + nombre + '\'' +
               ", telefono='" + telefono + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
