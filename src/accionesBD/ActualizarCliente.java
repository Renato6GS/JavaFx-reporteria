package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DTOs.DtoPersona;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ActualizarCliente {

	// Métodos
	/**
	 * Verifica si el DPI ingresado ya existe dentro de la base de datos para evitar
	 * duplicados y que en caso de que exista, no sea el mismo DPI del cliente.
	 * 
	 * @return true: ya existe el DPI. false: DPI nueva.
	 */
	public boolean yaExisteDPI(DtoPersona miPersona, final String dpiAnterior) {

		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();

		// Establecemos conexión:
		Connection CONEXION = conexion.connect();

		// Armamos la consulta
		String SQL = "SELECT dpi FROM cliente WHERE dpi = ?";

		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, miPersona.getDPI());
			ResultSet res = sentencia.executeQuery();

			// Si ya existe, se ejecuta el siguiente while:
			while (res.next()) {
				// Si el DPI encontrado es el mismo del cliente, entonces no tiramos error:
				if(res.getString("dpi").equals(dpiAnterior)) {
					break;
				}
				// Cerramos la conexión y anulamos las variables.
				CONEXION.close();
				conexion = null;
				CONEXION = null;
				return true; // Ya existe el cui.
			}
			// Cerramos la conexión y anulamos las variables. No existe el dpi o es el mismo.
			CONEXION.close();
			conexion = null;
			CONEXION = null;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
           	alert.setTitle("Error en la actualización");  
           	alert.setHeaderText(null);
           	alert.showAndWait();  
		}

		// No existe el dpi dentro de la base de datos o es el mismo del cliente.
		return false;
	}

	/**
	 * Recibe todos los datos a guardar, no importa si son los mismos o no.
	 * 
	 * @param miPersona Contiene todos los datos que van a ser guardados nuevamente.
	 */
	public void actualizarDatos(DtoPersona miPersona, final String dpiAnterior) {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "UPDATE cliente " + "	SET nombre = ?, apellidos = ?, dpi = ?,"
				+ "	nit = ?, fecha_de_nacimiento = ?, edad = ?," + 
				"	sexo = ?, direccion = ?, telefono = ?,"
				+ "	correo_electronico = ?" + "	WHERE dpi = ?";

		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);

			// Seteamos los datos:
			statement.setString(1, miPersona.getNombre());
			statement.setString(2, miPersona.getApellidos());
			statement.setString(3, miPersona.getDPI());
			System.out.println(miPersona.getDPI());
			// NO HAY ACTU PORQUE EL DPI QUE ESTÁ TOMANDO ES EL DEL TEXT FIELD NUEVO
			statement.setString(4, miPersona.getNIT());
			statement.setString(5, miPersona.getFecha_de_nacimiento());
			statement.setString(6, miPersona.getEdad());
			statement.setString(7, miPersona.getSexo());
			statement.setString(8, miPersona.getDireccion());
			statement.setInt(9, miPersona.getTelefono());
			statement.setString(10, miPersona.getCorreo());
			statement.setString(11, dpiAnterior);

			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			// JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Éxtio",
					//JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Datos actualizados correctamente", ButtonType.OK);
           	alert.setTitle("Éxito");  
           	alert.setHeaderText(null);
           	alert.showAndWait();  
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex, "Error", JOptionPane.ERROR_MESSAGE); // muestra
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			CONEXION.close();// SIEMPRE SE CIERRA LA CONEXION
			CONEXION = null; // SE COLOCA A NULL LA CONEXION PARA QUE EL GC TOME ESTE OBJETO Y LO ELIMINE DE
								// MEMORIA
			conexion = null;
		} catch (SQLException ex) {
			//JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
           	alert.setTitle("Error en la actualización");  
           	alert.setHeaderText(null);
           	alert.showAndWait();  
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
