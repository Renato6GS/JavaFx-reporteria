package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ActualizarRegistroTraslados {

	/**
	 * Registra el traslado en la base de datos a modo de historial.
	 * @param antiguo_propietario Dpi del antiguo dueño.
	 * @param codigo_propiedad Código de la propiedad trasladada.
	 * @param nuevo_propietario Dpi del nuevo dueño.
	 * @param fecha_traslado Fecha en que se efectuó el traslado.
	 */
	public void guardarTraslado(String antiguo_propietario, String codigo_propiedad, String nuevo_propietario, 
			String fecha_traslado) {
		
		// se crea una nueva conexion hacia la BD
		conexionPostgresBD conexion = new conexionPostgresBD();
		Connection CONEXION = conexion.connect();
		// SE ARMA LA INSTRUCCION SQL, LOS VALUES VAN ?
		String SQL = "INSERT INTO registro_traslados " + "VALUES(DEFAULT,?,?,?,?)";
		PreparedStatement statement;
		try {
			// Ingresamos la sentencia:
			statement = CONEXION.prepareStatement(SQL);

			// Seteamos los datos:
			statement.setString(1, antiguo_propietario);
			statement.setString(2, codigo_propiedad);
			statement.setString(3, nuevo_propietario);
			statement.setString(4, fecha_traslado);

			int filasInsertadas = statement.executeUpdate(); // SE EJECUTA EL INSERT
			System.out.println("Insertado: " + filasInsertadas);// se muestra las filas insertadas
			// JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito",
			// JOptionPane.INFORMATION_MESSAGE); // muestra mensaje al usaurio
			Alert alert = new Alert(AlertType.INFORMATION, "Se ha guardado el traslado en el historial.", ButtonType.OK);
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
