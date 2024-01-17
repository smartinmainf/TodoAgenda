/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author administrador
 */
import usuarios.Usuario;
import java.util.LinkedHashSet;
import java.util.List;

public class GestorUsuario {
    private LinkedHashSet<Usuario> usuarios;

    // Constructor
    public GestorUsuario(List<Usuario> usuariosIniciales) {
        this.usuarios = new LinkedHashSet<>(usuariosIniciales);
    }

    // nuevo
    public boolean registrarUsuario(String nombre, String contraseña) {
        Usuario nuevoUsuario = new Usuario(nombre, contraseña);
        return usuarios.add(nuevoUsuario); //falso si esta ya
    }

    // ver si existe
    public boolean usuarioExiste(String nombre, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContraseña().equals(contraseña)) {
                return true; // todo bn
            }
        }
        return false; // Usuario no encontrado o contraseña incorrecta
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GestorUsuarios{\n");
        usuarios.forEach(usuario -> sb.append(usuario.toString()).append("\n"));
        sb.append("}");
        return sb.toString();
    }
}
