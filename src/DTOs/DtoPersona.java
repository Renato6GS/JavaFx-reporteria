package DTOs;

public class DtoPersona {
	
	// Atributos
	private String DPI;
	private String NIT;
	private String nombre;
	private String apellidos;
	private String fecha_de_nacimiento;
	private String sexo;
	private String edad;
	private String direccion;
	private int telefono;
	private String correo;
	
	// Métodos getters y setters:
	public String getDPI() {
		return DPI;
	}
	public void setDPI(String dPI) {
		DPI = dPI;
	}
	public String getNIT() {
		return NIT;
	}
	public void setNIT(String nIT) {
		NIT = nIT;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
