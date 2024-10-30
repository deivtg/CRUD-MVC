/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import oracle.jdbc.driver.OracleDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author deivt
 */
public class DbConnection {
    private static final String url = "jdbc:oracle:thin:@//DEIVTG:1521/XEPDB1";
    private static final String user = "APUSUARIO";
    private static final String password = "123";

    // Propiedades de tipo DbConnection y Connection que controlaran la instancia unica
    private static DbConnection instance;
    private Connection connection;

    // Contructor privado para controlar internamente cuando crear o no la instancia
    private DbConnection() throws SQLException, Exception {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
            throw e;
        } catch (Exception ex){
            throw ex;
        }
    }

    // Devuelve instancia, la crea en caso no este instanciada
    public static DbConnection getInstance() throws SQLException, Exception {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DbConnection();
        }
        return instance;
    }

    // Recupera la conexion
    public Connection getConnection() {
        return connection;
    }

    // Cierra la conexion
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
