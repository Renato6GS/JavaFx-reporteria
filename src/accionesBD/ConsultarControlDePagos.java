package accionesBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DTOs.DtoControlDePagos;
import DTOs.DtoPersona;
import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ConsultarControlDePagos {
	
	/**
	 * Consulta la tabla clientes para actualizar sus datos.
	 * @param consultarDpi Parámetro para consultar la tabla.
	 * @return
	 */
	public List<DtoControlDePagos> obtenerDatos(String dpi, String codigo_propiedad) {
		
		boolean encontrado = false;
		List<DtoControlDePagos>  lista = new ArrayList<>();
		
		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();
				
		// Establecemos conexión:
		Connection CONEXION = conexion.connect();
				
		// Armamos la consulta
		String SQL = "SELECT * FROM control_de_pagos WHERE dpi_cliente = ? AND codigo_propiedad = ?";
			
		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, dpi);
			sentencia.setString(2, codigo_propiedad);
			ResultSet res = sentencia.executeQuery();
			
			// Si se llega ejecutar, entonces nos dará los datos del cliente:			
			while(res.next()) {			
				// Declaramos un dto para obtener los datos:				
				DtoControlDePagos miControl = new DtoControlDePagos();				
				encontrado = true;				
				// Obtenemos los datos:	
				miControl.setDpi_cliente(res.getString("dpi_cliente"));
				miControl.setCodigo_propiedad(res.getString("codigo_propiedad"));
				miControl.setFecha_de_pago(Arrays.asList(res.getString("fecha_de_pago").split("\\s*,\\s*")));
				miControl.setNo_cuota_pagada(Arrays.asList(res.getString("no_cuota_pagada").split("\\s*,\\s*")));
				miControl.setInteres(Arrays.asList(res.getString("interes").split("\\s*,\\s*")));
				miControl.setTotal_pagado(Arrays.asList(res.getString("total_pagado").split("\\s*,\\s*")));
				
				// Lo agregamos a la lista:				
				lista.add(miControl);				
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
	
	/**
	 * Consulta la tabla clientes para actualizar sus datos.
	 * @param consultarDpi Parámetro para consultar la tabla.
	 * @return
	 */
	public List<DtoControlDePagos> consultaTodosLosDatos() {
		
		boolean encontrado = false;
		List<DtoControlDePagos>  lista = new ArrayList<>();
		
		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();
				
		// Establecemos conexión:
		Connection CONEXION = conexion.connect();
				
		// Armamos la consulta
		String SQL = "SELECT * FROM control_de_pagos";
			
		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			ResultSet res = sentencia.executeQuery();
			
			// Si se llega ejecutar, entonces nos dará los datos del cliente:			
			while(res.next()) {			
				// Declaramos un dto para obtener los datos:				
				DtoControlDePagos miControl = new DtoControlDePagos();				
				encontrado = true;				
				// Obtenemos los datos:	
				miControl.setDpi_cliente(res.getString("dpi_cliente"));
				miControl.setCodigo_propiedad(res.getString("codigo_propiedad"));
				miControl.setFecha_de_pago(Arrays.asList(res.getString("fecha_de_pago").split("\\s*,\\s*")));
				miControl.setNo_cuota_pagada(Arrays.asList(res.getString("no_cuota_pagada").split("\\s*,\\s*")));
				miControl.setInteres(Arrays.asList(res.getString("interes").split("\\s*,\\s*")));
				miControl.setTotal_pagado(Arrays.asList(res.getString("total_pagado").split("\\s*,\\s*")));
				
				// Lo agregamos a la lista:				
				lista.add(miControl);				
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
