package reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import conexiones.conexionPostgresBD;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteClientesPorDpi {	
	
	/**
	 * Revisa los DPIs ingresados en cada registro para ver si existe o no.
	 * @param dpi Evalua si existe o no dentro de la base de datos.
	 * @return
	 */
	public boolean existeDpi(String dpi) {
		
		// Instanciamos un objeto para conectarnos a la base de datos:
		conexionPostgresBD conexion = new conexionPostgresBD();

		// Establecemos conexión:
		Connection CONEXION = conexion.connect();

		// Armamos la consulta
		String SQL = "SELECT dpi FROM cliente WHERE dpi = ?";

		// Creamos la sentencia:
		try {
			PreparedStatement sentencia = CONEXION.prepareStatement(SQL);
			sentencia.setString(1, dpi);
			ResultSet res = sentencia.executeQuery();

			// Si existe, se ejecuta el siguiente while:
			while (res.next()) {
				// Cerramos la conexión y anulamos las variables.
				CONEXION.close();
				conexion = null;
				CONEXION = null;
				return true;
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

		return false;
	}
	
	/**
	 * Consulta a todos las personas que no se hayan eliminado y los muestra en un reporte.
	 */
	public void crearReportePorDpiCliente(List<String> pDpi) {
		//variables de conexion
        conexionPostgresBD conn = new conexionPostgresBD();
        try {
            //obtenemos el archivo jrxml
            String jrxmlFile = System.getProperty("user.dir") + "\\reporte_de_clientes_por_dpi.jrxml";
            System.out.println(jrxmlFile);
            // parametros, los utilizaremos mas adelante
            Map<String, Object> arg = new HashMap<String, Object>();
            arg.put("P_DPI", pDpi);
            // conexion a BD
            Connection conexion = conn.connect();
            // compilamos el archivo jrxml
            JasperReport jasperReport2 = JasperCompileManager.compileReport(jrxmlFile);
            //llamamos a jasperprint y le pasamos como parametro el jasper ya compilado, los parametros, conexion a BD
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, arg, conexion);
            //vreamos el viewer jasper para visualizar el reporte
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte de clientes por DPI");
            jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jv.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
	}
}
