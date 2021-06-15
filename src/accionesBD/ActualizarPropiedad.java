package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DTOs.DtoPersona;
import DTOs.DtoPropiedad;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ActualizarPropiedad {
	// Métodos
	/**
	 * Verifica si el código de propiedad ya existe dentro de la base de datos para
	 * permitir registrarle una propiedad.
	 * 
	 * @return true: ya existe el Dpi. false: No existe Dpi.
	 */
	public boolean yaExisteCodigoPropiedad(DtoPropiedad miPropiedad, final String codigoPropiedadAnterior) {

		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();

		// Establecemos conexión:
		Connection CONEXION = conexion.connect();

		// Armamos la consulta
		String SQL = "SELECT codigo_propiedad FROM propiedad WHERE codigo_propiedad = ?";

		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, miPropiedad.getCodigo_propiedad());
			ResultSet res = sentencia.executeQuery();

			// Si ya existe, se ejecuta el siguiente while:
			while (res.next()) {
				// Si el código encontrado es el mismo del cliente, entonces no tiramos error:
				if(res.getString("codigo_propiedad").equals(codigoPropiedadAnterior)) {
					break;
				}
				// Cerramos la conexión y anulamos las variables.
				CONEXION.close();
				conexion = null;
				CONEXION = null;
				return true; // Ya existe el código de propiedad.
			}
			// Cerramos la conexión y anulamos las variables. No existe el cui en el BD.
			CONEXION.close();
			conexion = null;
			CONEXION = null;
		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, e.getMessage(), "Error de conexión",
			// JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.showAndWait();
		}

		// No existe el código dentro de la base de datos.
		return false;
	}
	
	/**
	 * Recibe todos los datos a guardar, no importa si son los mismos o no.
	 * 
	 * @param miPersona Contiene todos los datos que van a ser guardados nuevamente.
	 */
	public void actualizarDatos(DtoPropiedad miPropiedad, final String codigoPropiedadAnterior) {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "UPDATE propiedad " + " SET codigo_propiedad = ?, numero_de_finca = ?, "
				+ "numero_de_folio = ?," + " numero_de_libro = ?, area = ?, numero_de_catastro = ?," + 
				" numero_de_escritura = ?, fecha_de_escritura = ?, valor_de_la_propiedad = ?,"
				+ "	cantidad_de_cuotas = ?, porcentaje_de_interes = ? "
				+ "	WHERE codigo_propiedad = ?";

		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);

			// Seteamos los datos:
			statement.setString(1, miPropiedad.getCodigo_propiedad());
			statement.setString(2, miPropiedad.getNumero_de_finca());
			statement.setString(3, miPropiedad.getNumero_de_folio());
			statement.setString(4, miPropiedad.getNumero_de_libro());
			statement.setString(5, miPropiedad.getArea());
			statement.setString(6, miPropiedad.getNumero_de_catastro());
			statement.setString(7, miPropiedad.getNumero_de_escritura());
			statement.setString(8, miPropiedad.getFecha_de_escritura());
			statement.setString(9, miPropiedad.getValor_de_la_propiedad());
			statement.setString(10, miPropiedad.getCantidad_de_cuotas());
			statement.setString(11, miPropiedad.getPorcentaje_de_interes());
			statement.setString(12, codigoPropiedadAnterior);

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
	
	/**
	 * Actualiza el DPI en la tabla propiedad si es que el DPI cliente cambia en la "Actualización de clientes".
	 * @param nuevoDpi Nuevo DPI en la tabla propiedad. 
	 * @param antiguoDpi Viejo DPI en la tabal propiedad y sirve de parámetro en la búsqueda.
	 */
	public void actualizarDpiCliente(String nuevoDpi, String antiguoDpi) {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "UPDATE propiedad " + " SET dpi_cliente= ?	WHERE dpi_cliente = ?";

		PreparedStatement statement;
		try {
			statement = CONEXION.prepareStatement(SQL);

			// Seteamos los datos:
			statement.setString(1, nuevoDpi);
			statement.setString(2, antiguoDpi);

			int filasActualizadas = statement.executeUpdate(); // SE EJECUTA EL Update
			System.out.println("Actualizado: " + filasActualizadas);// se muestra las filas actualizadas
			// JOptionPane.showMessageDialog(null, "Datos actualizados correctamente",
			// "Éxtio",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "DPI actualizado en la tabla propiedades.", ButtonType.OK);
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
			// JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de conexión",
			// JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, ex.getMessage(), ButtonType.OK);
			alert.setTitle("Error en la actualización");
			alert.setHeaderText(null);
			alert.showAndWait();
			// Logger.getLogger(ingresoDatos.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
