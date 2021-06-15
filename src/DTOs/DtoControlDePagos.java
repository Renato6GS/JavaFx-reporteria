package DTOs;

import java.sql.Array;
import java.util.List;

public class DtoControlDePagos {

	// Atributos:
	private String id_pago;
	private String dpi_cliente;
	private String codigo_propiedad;
	private List<String> fecha_de_pago;
	private List<String> no_cuota_pagada;
	private List<String> interes;
	private List<String> total_pagado;
	
	
	
	// Métodos getters y setters:
	public String getId_pago() {
		return id_pago;
	}
	public void setId_pago(String id_pago) {
		this.id_pago = id_pago;
	}
	public String getDpi_cliente() {
		return dpi_cliente;
	}
	public void setDpi_cliente(String dpi_cliente) {
		this.dpi_cliente = dpi_cliente;
	}
	public String getCodigo_propiedad() {
		return codigo_propiedad;
	}
	public void setCodigo_propiedad(String codigo_propiedad) {
		this.codigo_propiedad = codigo_propiedad;
	}
	public List<String> getFecha_de_pago() {
		return fecha_de_pago;
	}
	public void setFecha_de_pago(List<String> fecha_de_pago) {
		this.fecha_de_pago = fecha_de_pago;
	}
	public List<String> getNo_cuota_pagada() {
		return no_cuota_pagada;
	}
	public void setNo_cuota_pagada(List<String> no_cuota_pagada) {
		this.no_cuota_pagada = no_cuota_pagada;
	}
	public List<String> getInteres() {
		return interes;
	}
	public void setInteres(List<String> interes) {
		this.interes = interes;
	}
	public List<String> getTotal_pagado() {
		return total_pagado;
	}
	public void setTotal_pagado(List<String> total_pagado) {
		this.total_pagado = total_pagado;
	}
	
	
	
}
