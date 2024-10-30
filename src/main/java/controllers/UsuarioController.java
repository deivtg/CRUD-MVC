/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.Usuario;
import services.UsuarioService;

/**
 *
 * @author deivt
 */
public class UsuarioController {
    private UsuarioService usuarioService;
    
    public UsuarioController(){
        usuarioService = new UsuarioService();
    }
    
    public boolean crearUsuario(Usuario usuario) throws Exception {
        try{
            return usuarioService.crearUsuario(usuario);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    public Usuario obtenerUsuarioPorId(int idUsuario) throws Exception {
        try{
            return usuarioService.obtenerUsuarioPorId(idUsuario);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    public boolean actualizarUsuario(Usuario usuario) throws Exception {
        try{
            return usuarioService.actualizarUsuario(usuario);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    public boolean eliminarUsuario(int idUsuario) throws Exception {
        try{
            return usuarioService.eliminarUsuario(idUsuario);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    public List<Usuario> obtenerTodosLosUsuarios() throws Exception {
        try{
            return usuarioService.obtenerTodosLosUsuarios();
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
