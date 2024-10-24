/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.multitask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactosDAO {

    // Método para agregar un contacto
    public boolean agregarContacto(String nombre, String telefono, String correo) {
        String sql = "INSERT INTO contactos (nombre, telefono, correo_electronico) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.setString(3, correo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al agregar contacto: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todos los contactos
    public List<String[]> obtenerContactos() {
        List<String[]> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactos";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                contactos.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo_electronico")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener contactos: " + e.getMessage());
        }
        return contactos;
    }

    // Método para eliminar un contacto por ID
    public boolean eliminarContacto(int id) {
        String sql = "DELETE FROM contactos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar contacto: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener contactos por nombre
    public List<String[]> obtenerContactosPorNombre(String nombreBuscado) {
        List<String[]> contactosFiltrados = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE nombre LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombreBuscado + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contactosFiltrados.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener contactos por nombre: " + e.getMessage());
        }
        return contactosFiltrados;
    }
}
