package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.DtoPersona;
import DTOs.DtoPropiedad;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ConsultarPropiedad {
	
	/**
	 * Consulta la tabla propiedad para actualizar sus datos.
	 * @param consultarPropiedad Parámetro para consultar la tabla.
	 * @return
	 */
	public List<DtoPropiedad> obtenerDatos(String consultarPropiedad) {
		
		boolean encontrado = false;
		List<DtoPropiedad>  lista = new ArrayList<>();
		
		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();
				
		// Establecemos conexión:
		Connection CONEXION = conexion.connect();
				
		// Armamos la consulta
		String SQL = "SELECT * FROM propiedad WHERE codigo_propiedad = ?";
			
		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, consultarPropiedad);
			ResultSet res = sentencia.executeQuery();
			
			// Si se llega ejecutar, entonces nos dará los datos del cliente:			
			while(res.next()) {			
				// Declaramos un dto para obtener los datos:				
				DtoPropiedad miPropiedad = new DtoPropiedad();				
				encontrado = true;				
				// Obtenemos los datos:				
				miPropiedad.setCodigo_propiedad(res.getString("codigo_propiedad"));
				miPropiedad.setNumero_de_finca(res.getString("numero_de_finca"));
				miPropiedad.setNumero_de_folio(res.getString("numero_de_folio"));
				miPropiedad.setNumero_de_libro(res.getString("numero_de_libro"));
				miPropiedad.setArea(res.getString("area"));
				miPropiedad.setNumero_de_catastro(res.getString("numero_de_catastro"));
				miPropiedad.setNumero_de_escritura(res.getString("numero_de_escritura"));
				miPropiedad.setFecha_de_escritura(res.getString("fecha_de_escritura"));
				miPropiedad.setValor_de_la_propiedad(res.getString("valor_de_la_propiedad"));
				miPropiedad.setCantidad_de_cuotas(res.getString("cantidad_de_cuotas"));
				miPropiedad.setPorcentaje_de_interes(res.getString("porcentaje_de_interes"));
				miPropiedad.setDpi_cliente(res.getString("dpi_cliente"));
				miPropiedad.setFecha_de_adquisicion(res.getString("fecha_de_adquisicion_cliente"));
				// Lo agregamos a la lista:				
				lista.add(miPropiedad);				
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
