/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todoagenda;

import java.util.ArrayList;
import usuarios.Usuario;

/**
 *
 * @author administrador
 */
public class BuilderUsuario {
    
    public static ArrayList<Usuario> crearUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        usuarios.add(new Usuario("alumno", "admin"));
        usuarios.add(new Usuario("alumno1", "admin1"));
        return usuarios;
    }
    
}


