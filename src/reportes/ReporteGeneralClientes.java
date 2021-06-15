package reportes;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import conexiones.conexionPostgresBD;

public class ReporteGeneralClientes {
	/**
	 * Consulta a todos las personas que no se hayan eliminado y los muestra en un reporte.
	 */
	public void crearReporteGeneralClientes() {
		//variables de conexion
        conexionPostgresBD conn = new conexionPostgresBD();
        try {
            //obtenemos el archivo jrxml
            String jrxmlFile = System.getProperty("user.dir") + "\\reporte_general_clientes.jrxml";
            System.out.println(jrxmlFile);
            // parametros, los utilizaremos mas adelante
            Map<String, Object> arg = new HashMap<String, Object>();
            // arg.put("PARAMETRO1", "502");
            // conexion a BD
            Connection conexion = conn.connect();
            // compilamos el archivo jrxml
            JasperReport jasperReport2 = JasperCompileManager.compileReport(jrxmlFile);
            //llamamos a jasperprint y le pasamos como parametro el jasper ya compilado, los parametros, conexion a BD
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, arg, conexion);
            //vreamos el viewer jasper para visualizar el reporte
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte General de Clientes");
            jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jv.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
	}
}
