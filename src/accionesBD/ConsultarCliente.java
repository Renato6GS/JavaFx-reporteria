package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import DTOs.DtoPersona;

public class ConsultarCliente {

	/**
	 * Consulta la tabla clientes para actualizar sus datos.
	 * @param consultarDpi Parámetro para consultar la tabla.
	 * @return
	 */
	public List<DtoPersona> obtenerDatos(String consultarDpi) {
		
		boolean encontrado = false;
		List<DtoPersona>  lista = new ArrayList<>();
		
		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();
				
		// Establecemos conexión:
		Connection CONEXION = conexion.connect();
				
		// Armamos la consulta
		String SQL = "SELECT * FROM cliente WHERE dpi = ?";
			
		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, consultarDpi);
			ResultSet res = sentencia.executeQuery();
			
			// Si se llega ejecutar, entonces nos dará los datos del cliente:			
			while(res.next()) {			
				// Declaramos un dto para obtener los datos:				
				DtoPersona miPersona = new DtoPersona();				
				encontrado = true;				
				// Obtenemos los datos:				
				miPersona.setNombre(res.getString("nombre"));
				miPersona.setApellidos(res.getString("apellidos"));
				miPersona.setDPI(res.getString("dpi"));
				miPersona.setNIT(res.getString("nit"));
				miPersona.setFecha_de_nacimiento(res.getString("fecha_de_nacimiento"));
				miPersona.setEdad(res.getString("edad"));
				miPersona.setSexo(res.getString("sexo"));
				miPersona.setDireccion(res.getString("direccion"));
				miPersona.setTelefono(res.getInt("telefono"));
				miPersona.setCorreo(res.getString("correo_electronico"));
				// Lo agregamos a la lista:				
				lista.add(miPersona);				
			}	
			
			// Cerramos la conexión
			CONEXION.close();			
			conexion = null;			
			CONEXION = null;			
		} catch(SQLException e) {		
			//JOptionPane.showMessageDialog(null, e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
           	alert.setTitle("Error");  
           	alert.setHeaderText(null);
           	alert.showAndWait(); 
		}	
		if(!encontrado) {
			String mensaje = "No hay coincidencias dentro de la base de datos.";
			// JOptionPane.showMessageDialog(null, mensaje, "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.ERROR, mensaje, ButtonType.OK);
           	alert.setTitle("Error");  
           	alert.setHeaderText(null);
           	alert.showAndWait(); 
		}	
		return lista.isEmpty() ? new ArrayList<>() : lista;
	}	
	
}
