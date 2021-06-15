package reportes;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import DTOs.DtoControlDePagos;
import DTOs.DtoPersona;
import DTOs.DtoPropiedad;
import accionesBD.ConsultarCliente;
import accionesBD.ConsultarControlDePagos;
import accionesBD.ConsultarPropiedad;
import conexiones.conexionPostgresBD;
import controlDePagos.ModeloDatos;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReportePagosPendientes {
	
	private String numeroDeCuotaGlobal;
	
    /**
     * Consulta la base de datos según el DPI y código de propiedad ingresados.
     * @param pDPI Dpi del cliente.
     * @param pCodigo Código de la propiedad.
     */
    public void obtenerDatos(String dpiPropietario, String pCodigo) {
    	    	
    	// Instanciamos un objeto para consultar a la base de datos:
		ConsultarControlDePagos consultar = new ConsultarControlDePagos();		
		
		// Instanciamos una lista de dtoControlDePagos:
		List<DtoControlDePagos> propietarioActual = new ArrayList<>();
		
		// Obtenemos los datos. Si está vacía, quiere decir que no encontró coincidencias con 
		// DPI o código ingresados.
		propietarioActual = consultar.obtenerDatos(dpiPropietario, pCodigo);
		if(propietarioActual.isEmpty()) {			
			return;
		};
		
		// Mostramos los datos
//		calcularPagosPendientes(propietarioActual);
	
		return;
    }
    
    
    /**
     * Consulta la base de datos según el DPI y código de propiedad ingresados.
     * @param pDPI Dpi del cliente.
     * @param pCodigo Código de la propiedad.
     */
    public void consultarDatos() {
    	
    	// Instanciamos un objeto para consultar a la base de datos:
    	ConsultarControlDePagos consultar = new ConsultarControlDePagos();		
    	
    	// Instanciamos una lista de dtoControlDePagos:
    	List<DtoControlDePagos> pagos = new ArrayList<>();
    	
    	// Obtenemos los datos. Si está vacía, quiere decir que no encontró coincidencias con 
    	// DPI o código ingresados.
    	pagos = consultar.consultaTodosLosDatos();
    	if(pagos.isEmpty()) {			
    		return;
    	};
    	
    	// Mostramos los datos
//    	calcularPagosPendientes(propietarioActual);
    	calcularPagosFaltantes(pagos);
    	
    	return;
    }
    
    public void calcularPagosFaltantes(List<DtoControlDePagos> pagos) {
		

		// Consultamos al cliente actual
//		ConsultarCliente consultarCliente = new ConsultarCliente();
//
//		// Instanciamos una lista de dtoPersona:
//		List<DtoPersona> cliente = new ArrayList<>();
//
//		// Obtenemos los datos.
//		cliente = consultarCliente.obtenerDatos(pagos.get(i).getDpi_cliente());
    	
    	// Aquí se guardaran todos los pagos de todos los clientes:
    	List<String> pagosPendientesLista = new ArrayList<>();
    	    	
    	// Calculamos los pagos pendientes. En caso de que lleve moras se le suma 
    	for(int i = 0; i < pagos.size(); i++) {

			// Consultamos propiedad:
			ConsultarPropiedad consultarPropiedad = new ConsultarPropiedad();

			// Instanciamos una lista de dtoPersona:
			List<DtoPropiedad> propiedad = new ArrayList<>();

			// Obtenemos los datos.
			propiedad = consultarPropiedad.obtenerDatos(pagos.get(i).getCodigo_propiedad());
			
			// Cálculamos mora:
			// La mora lo calculos con la fecha del último pago respecto al día actual:
			// Declaramos el iterador y recorremos el ArrayList
			List<Integer> mora = extraerNumeros(propiedad.get(0).getFecha_de_adquisicion());
			Iterator<Integer> miIterator = mora.iterator();
	
			// Contador que nos ayudará a obtener los días, meses y años.
			int contador = 0;
	
			// Obtenemos la fecha actual del sistema:
			Calendar hoy = Calendar.getInstance();
	
			// Recorremos el iterador:
			while (miIterator.hasNext()) {
				// Elemento que almacena la fecha del último pago:
				Integer elemento = miIterator.next();
	
				// Restamos fecha actual con la fecha del último pago:
				if (contador == 0) {
					hoy.add(Calendar.DATE, -elemento);
				} else if (contador == 1) {
					hoy.add(Calendar.MONTH, -elemento);
				} else if (contador == 2) {
					hoy.add(Calendar.YEAR, -elemento);
				}
				contador++;
			}
	
			// Obtenemos los meses y años por individuales con localDate
			LocalDate localDate = hoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			int meses = localDate.getMonthValue();
			int anio = localDate.getYear();
	
			// Si se debe más de un año, sumamos 12 cuotas
			meses = anio == 0 ? meses : meses + (12 * anio);
			meses = meses == -1 ? 0 : meses;
			System.out.println(meses);
	
			
			String pagosPendientes = String
					.valueOf(Integer.parseInt(propiedad.get(0).getCantidad_de_cuotas()) - Integer.parseInt(pagos.get(i)
							.getNo_cuota_pagada().get(pagos.get(i).getNo_cuota_pagada().size() - 1).replace("}", "")));
			
//			meses = meses >= 0 ? (meses = anio == 0 ? meses : meses + (12 * anio)) : meses*-1+1;
//			
//			// Este es el que hay que implementar en JXRML
//			meses = (meses = anio == 0 ? (meses) : meses + (12 * anio)) >= 0 ? meses : meses*-1+1;
			
			// Ya teniendo el número de cuotas pagadas, pasamos a calcular ahora sí las
			// moras:		
			meses = meses - Integer.parseInt(pagos.get(i)
					.getNo_cuota_pagada().get(pagos.get(i).getNo_cuota_pagada().size() - 1).replace("}", "")) + 1;
			String prueba = (meses >= 0 ? String.valueOf(meses)
					: String.valueOf(meses*-1+1));
			meses = meses >= 0 ? meses : 0;
			
			pagosPendientes = String.valueOf(Integer.parseInt(pagosPendientes) - Integer.parseInt(prueba));
				
			// Guardamos los datos dentro de la lista.
			pagosPendientesLista.add(pagosPendientes);
			System.out.println("HOLAAAAAAAAA " + pagosPendientesLista.get(i));
    	}    	
    	
//    	crearReportePagosPendientes(pagosPendientesLista);
	}
    
    /**
	 * Consulta a todos las personas con pagos pendientes.
	 */
	public void crearReportePagosPendientes() {
		//variables de conexion
        conexionPostgresBD conn = new conexionPostgresBD();
        try {
            //obtenemos el archivo jrxml
            String jrxmlFile = System.getProperty("user.dir") + "\\reporte_de_pagos_pendientes.jrxml";
            System.out.println(jrxmlFile);
            // parametros, los utilizaremos mas adelante
            Map<String, Object> arg = new HashMap<String, Object>();
//            arg.put("P_DPI", pagosPendientes);
            // conexion a BD
            Connection conexion = conn.connect();
            // compilamos el archivo jrxml
            JasperReport jasperReport2 = JasperCompileManager.compileReport(jrxmlFile);
            //llamamos a jasperprint y le pasamos como parametro el jasper ya compilado, los parametros, conexion a BD
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, arg, conexion);
            //vreamos el viewer jasper para visualizar el reporte
            JasperViewer jv = new JasperViewer(jasperPrint, false);
            jv.setTitle("Reporte de pagos pendientes");
            jv.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jv.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
	}
	
	/**
	 * Recorre un String y extrae cualquier número y los guarda en una lista.
	 * @param cadena Parámetro a recorrer.
	 * @return Una lista de todos los números encontrados en la cadena.
	 */
	List<Integer> extraerNumeros(String cadena) {
	      List<Integer> todosLosNumeros = new ArrayList<Integer>();
	      Matcher coincidencias = Pattern.compile("\\d+").matcher(cadena);
	      while (coincidencias.find()) { 
	        todosLosNumeros.add(Integer.parseInt(coincidencias.group()));
	      } 
	      return todosLosNumeros;
	 }	

}
