package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTOs.DtoPropiedad;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class RegistrarControlDePagos {
	
	// Métodos	
	/**
	 * Guarda los datos dentro de la base de datos tras verificaciones.
	 */
	public void crearRegistro(String dpi, String codigo_propiedad, String interes) {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "INSERT INTO control_de_pagos " + 
					 "VALUES(DEFAULT,?,?, ARRAY[?], ARRAY[?], ARRAY[?], ARRAY[?])";
		PreparedStatement statement;
		try {
			// Ingresamos la sentencia:
			statement = CONEXION.prepareStatement(SQL);

			// Seteamos los datos:
			statement.setString(1, dpi);
			statement.setString(2, codigo_propiedad);
			statement.setString(3, "");
			statement.setString(4, "1");
			statement.setString(5, interes);
			statement.setString(6, "0");
			

			int filasInsertadas = statement.executeUpdate(); // SE EJECUTA EL INSERT
			System.out.println("Insertado: " + filasInsertadas);// se muestra las filas insertadas
			// JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Se ha creado un registro para llevar el control de pagos.", ButtonType.OK);
			alert.setTitle("Éxito");
			alert.setHeaderText(null);
			alert.showAndWait();

		} catch (SQLException ex) {
			// JOptionPane.showMessageDialog(null, "Error al guardar: " + ex, "Error",
			// JOptionPane.ERROR_MESSAGE); //muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.ERROR, "No se pudo crear un control de pagos para este cliente. " + ex.getMessage(), ButtonType.OK);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.showAndWait();
		}

		try {
			CONEXION.close();// SIEMPRE SE CIERRA LA CONEXION
			CONEXION = null; // SE COLOCA A NULL LA CONEXION PARA QUE EL GC TOME ESTE OBJETO Y LO ELIMINE DE
								// MEMORIA
			conexion = null;

		} catch (SQLException ex) {
			// JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de conexión",
			// JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
