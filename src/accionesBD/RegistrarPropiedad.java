package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DTOs.DtoPropiedad;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class RegistrarPropiedad {
	// Atributos
	private DtoPropiedad miPropiedad = new DtoPropiedad();

	// Getters y setters.
	public DtoPropiedad getMiPropiedad() {
		return miPropiedad;
	}

	public void setMiPropiedad(DtoPropiedad miPropiedad) {
		this.miPropiedad = miPropiedad;
	}
	
	// Métodos
	/**
	 * Verifica si el Dpi ingresado ya existe dentro de la base de datos para permitir
	 * registrarle una propiedad.
	 * 
	 * @return true: ya existe el Dpi. false: No existe Dpi.
	 */
	public boolean yaExisteDPI() {

		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();

		// Establecemos conexión:
		Connection CONEXION = conexion.connect();

		// Armamos la consulta
		String SQL = "SELECT dpi FROM cliente WHERE dpi = ?";

		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, miPropiedad.getDpi_cliente());
			ResultSet res = sentencia.executeQuery();

			// Si ya existe, se ejecuta el siguiente while:
			while (res.next()) {
				// Cerramos la conexión y anulamos las variables.
				CONEXION.close();
				conexion = null;
				CONEXION = null;
				return true; // Ya existe el cui.
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

		// No existe el dpi dentro de la base de datos.
		return false;
	}
	
	// Métodos
	/**
	 * Verifica si el Dpi ingresado ya existe dentro de la base de datos para
	 * permitir registrarle una propiedad.
	 * 
	 * @return true: ya existe el Dpi. false: No existe Dpi.
	 */
	public boolean yaExisteCodigoPropiedad() {

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
	 * Guarda los datos dentro de la base de datos tras verificaciones.
	 */
	public void registrar() {
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "INSERT INTO propiedad " + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// Obtenemos la fecha actual:
		Date fechaActual = new Date();
		DateFormat formato_fecha = new SimpleDateFormat("dd/MM/yyyy");
		PreparedStatement statement;
		try {
			// Ingresamos la sentencia:
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
			statement.setString(12, miPropiedad.getDpi_cliente());
			statement.setString(13, formato_fecha.format(fechaActual));

			int filasInsertadas = statement.executeUpdate(); // SE EJECUTA EL INSERT
			System.out.println("Insertado: " + filasInsertadas);// se muestra las filas insertadas
			// JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Datos guardados correctamente", ButtonType.OK);
			alert.setTitle("Éxito");
			alert.setHeaderText(null);
			alert.showAndWait();

		} catch (SQLException ex) {
			// JOptionPane.showMessageDialog(null, "Error al guardar: " + ex, "Error",
			// JOptionPane.ERROR_MESSAGE); //muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.ERROR, "Error al guardar " + ex.getMessage(), ButtonType.OK);
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
