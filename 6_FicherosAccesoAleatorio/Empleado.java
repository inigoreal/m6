public class Empleado {
	int id, dep;
	Double salario;
	String apellido;
	
	public Empleado(int id, int dep, Double salario, String apellido) {
		super();
		this.id = id;
		this.dep = dep;
		this.salario = salario;
		this.apellido = apellido;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDep() {
		return dep;
	}
	public void setDep(int dep) {
		this.dep = dep;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
