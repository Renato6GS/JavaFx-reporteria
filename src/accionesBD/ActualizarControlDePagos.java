package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import DTOs.DtoPropiedad;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ActualizarControlDePagos {

	private static DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * Actualiza los datos del cliente en su control de pagos.
	 * @param codigo_propiedad_act Nuevo código de propiedad a registrar.
	 * @param interes_act Actualizar el interés.
	 * @param codigo_propiedad_ant Parámetro para actualizar su nuevo código.
	 */
	public void actualizarControlDePagos(String codigo_propiedad_act, String interes_act,
			String codigo_propiedad_ant) {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		// Con cardinaility obtiene el último vector:
		String SQL = "UPDATE control_de_pagos " 
				+ " SET codigo_propiedad = ? "  
				+ "	WHERE codigo_propiedad = ?";
//		String SQL = "UPDATE control_de_pagos " 
//				+ " SET codigo_propiedad = ?, interes[cardinality(interes)] = ? "  
//				+ "	WHERE codigo_propiedad = ?";
		
		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);
			
			// Seteamos los datos:
			statement.setString(1, codigo_propiedad_act);
			statement.setString(2, codigo_propiedad_ant);
//			statement.setString(2, interes_act);
			
			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			// JOptionPane.showMessageDialog(null, "Datos actualizados correctamente",
			// "Éxtio",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Control de pago del cliente han sido actualizados.", ButtonType.OK);
			alert.setTitle("Éxito");
			alert.setHeaderText(null);
			alert.showAndWait();
			
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("No se pudo actualizar el control de pagos.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
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
			alert.setTitle("No se pudo actualizar el control de pagos.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Actualiza el DPI en la tabla de control de pagos.
	 * @param dpi_act Nuevo DPI.
	 * @param dpi_ant Antiguo DPI.
	 */
	public void actualizarControlDePagosDPI(String dpi_act, String dpi_ant) {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "UPDATE control_de_pagos " 
				+ " SET dpi_cliente = ? "  
				+ "	WHERE dpi_cliente = ?";
		
		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);
			
			// Seteamos los datos:
			statement.setString(1, dpi_act);
			statement.setString(2, dpi_ant);
			
			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			// JOptionPane.showMessageDialog(null, "Datos actualizados correctamente",
			// "Éxtio",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Control de pago del cliente han sido actualizados.", ButtonType.OK);
			alert.setTitle("Éxito");
			alert.setHeaderText(null);
			alert.showAndWait();
			
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("No se pudo actualizar el control de pagos.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
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
			alert.setTitle("No se pudo actualizar el control de pagos.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Actualiza el DPI en la tabla de control de pagos.
	 * @param dpi_act Nuevo DPI.
	 * @param dpi_ant Antiguo DPI.
	 */
	public boolean actualizarTrasladoPropiedad(String antiguo_propietario, String codigo_propiedad, String nuevo_propietario) {
		
		boolean exito = false;
		
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "UPDATE control_de_pagos " 
				+ " SET dpi_cliente = ? "  
				+ "	WHERE dpi_cliente = ? AND codigo_propiedad = ?";
		
		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);
			
			// Seteamos los datos:
			statement.setString(1, nuevo_propietario);
			statement.setString(2, antiguo_propietario);
			statement.setString(3, codigo_propiedad);
			
			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			// JOptionPane.showMessageDialog(null, "Datos actualizados correctamente",
			// "Éxtio",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Traslado realizado con éxito.", ButtonType.OK);
			alert.setTitle("Éxito");
			alert.setHeaderText(null);
			alert.showAndWait();
			exito = true;
			
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("No se pudo actualizar el control de pagos.");
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
			alert.setTitle("No se pudo actualizar el control de pagos.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
		return exito;
	}
	
	/**
	 * Actualiza un registro por motivo de mora.
	 * @param dpiCliente Parámetro para ingresar datos.
	 * @param codigoPropiedad Parámetro para ingresar datos.
	 * @param fechaDePago Día en que fue efectuado el pago de la mora.
	 * @param interes Interés que pago por motivo de mora más el interés base.
	 * @param total_pagado Suma la mora a lo que lleva pagando el cliente.
	 * @param moras Motivo para describir.
	 */
	public boolean actualizarPagosDeMora(String dpiCliente, String codigoPropiedad, 
			String fechaDePago, String interes, String total_pagado, String descripcion){
		
		boolean exito = false;
		
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		total_pagado = df.format(Double.parseDouble(total_pagado));
		
		String SQL = "UPDATE control_de_pagos " 
				+ " SET fecha_de_pago = fecha_de_pago || ARRAY['" + fechaDePago + "'], "
				+ " no_cuota_pagada = no_cuota_pagada || ARRAY['" + descripcion + "'], "
				+ " interes = interes || ARRAY['" + interes + "'], "
				+ " total_pagado = total_pagado || ARRAY['" + total_pagado + "'] "
				+ "	WHERE dpi_cliente = ? AND codigo_propiedad = ?";
		System.out.println(SQL);		
		
		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);
			
			// Seteamos los datos:
			statement.setString(1, dpiCliente);
			statement.setString(2, codigoPropiedad);
			
			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			exito = true;
			
		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex, "Error", JOptionPane.ERROR_MESSAGE); // muestra
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("No se pudo efectuar el pago a las moras.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
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
			alert.setTitle("No se pudo efectuar el pago a las moras.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
		return exito;
	}
	
	/**
	 * Pago para la primera cuota.
	 * @param dpiCliente Parámetro para localizar al cliente.
	 * @param codigoPropiedad Parámetro para localizar al cliente.
	 * @param fechaDePago Parámetro a ser actualizado.
	 * @param total_pagado Parámetro a ser actualizado.
	 * @return Retorna false si hubo algún fallo en el registro.
	 */
	public boolean actualizarPrimeraCuota(String dpiCliente, String codigoPropiedad, 
			String fechaDePago, String total_pagado) {
		boolean exito = false;
		
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		total_pagado = df.format(Double.parseDouble(total_pagado));
		
		String SQL = "UPDATE control_de_pagos"
				+ " SET fecha_de_pago = array_replace(fecha_de_pago, '', '" + fechaDePago + "'),"
				+ " total_pagado = array_replace(total_pagado, '0', '" + total_pagado + "')"
				+ " WHERE dpi_cliente = ? "
				+ " AND codigo_propiedad = ? "
				+ " AND no_cuota_pagada[1] = '1'";
		
		System.out.println(SQL);		
		
		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);
			
			// Seteamos los datos:
			statement.setString(1, dpiCliente);
			statement.setString(2, codigoPropiedad);
			
			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			exito = true;
			
		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex, "Error", JOptionPane.ERROR_MESSAGE); // muestra
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("No se pudo efectuar primer pago.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
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
			alert.setTitle("No se pudo efectuar el pago a las moras.");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
		return exito;
	}
}
