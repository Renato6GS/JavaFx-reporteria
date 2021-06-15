package controlDePagos;

import javafx.beans.property.SimpleStringProperty;

public class ModeloDatos {
	
	// Atributos
	private String No;
	private String valor;
	private String Fecha;
	private String Interes;
	private String pendiente;
	
	public ModeloDatos() {
		
	}
	
	public ModeloDatos(String noDeCuota, String valor, String fechaDePago, String interes, String pendiente) {
		this.No = noDeCuota;
		this.valor     = valor;
		this.Fecha = fechaDePago;
		this.Interes   = interes;
		this.pendiente = pendiente;
	}
	
	// Métodos
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getInteres() {
		return Interes;
	}
	public void setInteres(String interes) {
		Interes = interes;
	}
	public String getPendiente() {
		return pendiente;
	}
	public void setPendiente(String pendiente) {
		this.pendiente = pendiente;
	}

	public String getNo() {
		return No;
	}

	public void setNo(String no) {
		No = no;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	
}
