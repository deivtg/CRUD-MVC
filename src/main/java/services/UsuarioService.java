/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

/**
 *
 * @author deivt
 */
public class UsuarioService {
    public boolean crearUsuario(Usuario usuario) throws Exception {
        String sql = "INSERT INTO USUARIO (NOMBRE, APELLIDO, DIRECCION, PUESTO, DEPARTAMENTO, TELEFONO, CORREO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getPuesto());
            ps.setString(5, usuario.getDepartamento());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getCorreo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear usuario: " + e.getMessage());
        }
    }
    
    public Usuario obtenerUsuarioPorId(int idUsuario) throws Exception {
        String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDO, DIRECCION, PUESTO, DEPARTAMENTO, TELEFONO, CORREO FROM USUARIO WHERE ID_USUARIO = ?";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("DIRECCION"),
                        rs.getString("PUESTO"),
                        rs.getString("DEPARTAMENTO"),
                        rs.getString("TELEFONO"),
                        rs.getString("CORREO")
                );
            }
            
            return null;
        } catch (SQLException e) {
            throw new Exception("Error al obtener usuario: " + e.getMessage());
        }
    }
    
    public boolean actualizarUsuario(Usuario usuario) throws Exception {
        String sql = "UPDATE USUARIO SET NOMBRE = ?, APELLIDO = ?, DIRECCION = ?, TELEFONO = ?, CORREO = ?, DEPARTAMENTO = ?, PUESTO = ? WHERE ID_USUARIO = ?";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getDepartamento());
            ps.setString(7, usuario.getPuesto());
            ps.setInt(8, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar usuario: " + e.getMessage());
        }
    }
    
    public boolean eliminarUsuario(int idUsuario) throws Exception {
        String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = ?";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar usuario: " + e.getMessage());
        }
    }
    
    public List<Usuario> obtenerTodosLosUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT ID_USUARIO, NOMBRE, APELLIDO, DIRECCION, PUESTO, DEPARTAMENTO,TELEFONO, CORREO FROM USUARIO ORDER BY ID_USUARIO";
        try (Connection connection = DbConnection.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("DIRECCION"),
                        rs.getString("PUESTO"),
                        rs.getString("DEPARTAMENTO"),
                        rs.getString("TELEFONO"),
                        rs.getString("CORREO")
                );
                usuarios.add(usuario);
            }
            
            return usuarios;
        } catch (SQLException e) {
            throw new Exception("Error al obtener todos los usuarios: " + e.getMessage());
        }
    }
}
