package com.mycompany.clinicaprivadadoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Paths;

public class ConexionSql {
    private static final String URL = "jdbc:sqlite:" + Paths.get("dbdoo.db").toAbsolutePath().toString();
    private static ConexionSql instance;
    private Connection connection;

    private ConexionSql() {
        // Inicializa la conexión al crear la instancia
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos establecida con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Falló la conexión a la base de datos", e);
        }
    }

    /**
     * Devuelve la instancia única de la conexión a la base de datos.
     */
    public static synchronized ConexionSql getInstance() {
        if (instance == null) {
            instance = new ConexionSql();
        } else {
            try {
                if (instance.connection == null || instance.connection.isClosed()) {
                    instance.connection = DriverManager.getConnection(URL);
                    System.out.println("Conexión reestablecida con éxito.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al verificar o restablecer la conexión.", e);
            }
        }
        return instance;
    }

    /**
     * Obtiene la conexión actual a la base de datos.
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("Conexión reestablecida con éxito.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la conexión a la base de datos.", e);
        }
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos.
     * Este método debe usarse solo al finalizar toda la aplicación.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Asegura que la conexión cerrada no sea reutilizada
                System.out.println("Conexión a la base de datos cerrada con éxito.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión a la base de datos.", e);
            }
        }
    }
}
