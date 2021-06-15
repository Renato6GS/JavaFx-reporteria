package reportes;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import conexiones.conexionPostgresBD;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteTraslados {
	public void crearReporteTraslados() {
		// variables de conexion
		conexionPostgresBD conn = new conexionPostgresBD();
		try {
			// obtenemos el archivo jrxml
			String jrxmlFile = System.getProperty("user.dir") + "\\reporte_historial_de_traslados.jrxml";
			System.out.println(jrxmlFile);
			// parametros, los utilizaremos mas adelante
			Map<String, Object> arg = new HashMap<String, Object>();
//        arg.put("P_DPI", pagosPendientes);
			// conexion a BD
			Connection conexion = conn.connect();
			// compilamos el archivo jrxml
			JasperReport jasperReport2 = JasperCompileManager.compileReport(jrxmlFile);
			// llamamos a jasperprint y le pasamos como parametro el jasper ya compilado,
			// los parametros, conexion a BD
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, arg, conexion);
			// vreamos el viewer jasper para visualizar el reporte
			JasperViewer jv = new JasperViewer(jasperPrint, false);
			jv.setTitle("Reporte historial de traslados de propiedades");
			jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
			jv.setVisible(true);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}
