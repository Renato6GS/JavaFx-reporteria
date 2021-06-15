package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import DTOs.DtoPersona;

public class RegistrarCliente {
	// Atributos
	private DtoPersona miPersona = new DtoPersona();

	// Getters y setters.
	public DtoPersona getMiPersona() {
		return miPersona;
	}

	public void setMiPersona(DtoPersona miPersona) {
		this.miPersona = miPersona;
	}

	// Métodos
	/**
	 * Verifica si el CUI ingresado ya existe dentro de la base de datos para evitar
	 * duplicados.
	 * 
	 * @return true: ya existe el CUI. false: CUI nueva.
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
			sentencia.setString(1, miPersona.getDPI());
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
		String SQL = "INSERT INTO cliente " + "VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement;
		try {
			// Ingresamos la sentencia:
			statement = CONEXION.prepareStatement(SQL);

			// Seteamos los datos:
			statement.setString(1, miPersona.getNombre());
			statement.setString(2, miPersona.getApellidos());
			statement.setString(3, miPersona.getDPI());
			statement.setString(4, miPersona.getNIT());
			statement.setString(5, miPersona.getFecha_de_nacimiento());
			statement.setString(6, miPersona.getEdad());
			statement.setString(7, miPersona.getSexo());
			statement.setString(8, miPersona.getDireccion());
			statement.setInt(9, miPersona.getTelefono());
			statement.setString(10, miPersona.getCorreo());

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
