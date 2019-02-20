import java.sql.Date;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

class Depart { //Clase Jugadores
	private int deptNo;
	private String dnombre;
	private String loc;
	public String toString() {return dnombre;}
	public Depart() {}
	
	public Depart(int deptNo, String dnombre, String loc) {
		super();
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
	}


	public int getDeptNo() {
		return deptNo;
	}


	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}


	public String getDnombre() {
		return dnombre;
	}


	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}


	public String getLoc() {
		return loc;
	}


	public void setLoc(String loc) {
		this.loc = loc;
	}
}
class Emple { //Clase Jugadores
	private int empNo;
	private String apellido;
	private String oficio;
	private Emple dir;
	private java.sql.Date fechaAlt;
	private float comision;
	private float salario;
	private Depart dept;
	public String toString() {return apellido;}
	public Emple() {}
	public Emple(int empNo, String apellido, String oficio, Emple dir, Date fechaAlt, float salario, float comision, Depart dept) {
		super();
		this.empNo = empNo;
		this.apellido = apellido;
		this.oficio = oficio;
		this.dir = dir;
		this.fechaAlt = fechaAlt;
		this.comision = comision;
		this.salario = salario;
		this.dept = dept;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public Emple getDir() {
		return dir;
	}
	public void setDir(Emple dir) {
		this.dir = dir;
	}
	public java.sql.Date getFechaAlt() {
		return fechaAlt;
	}
	public void setFechaAlt(java.sql.Date fechaAlt) {
		this.fechaAlt = fechaAlt;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public Depart getDept() {
		return dept;
	}
	public void setDept(Depart dept) {
		this.dept = dept;
	}
	
	
	
}
public class Proyecto_Constructor {
	public static void main(String[] args) {
		Depart dep1 = new Depart(10,"VENTAS","bcn");
		Depart dep2 = new Depart(2,"VENTAS1","val");
		Depart dep3 = new Depart(3,"depa3","val");
		Date date1 = new Date(2/2/2000);
		Date date2 = new Date(2/2/2002);
		Emple emple1 = new Emple(1,"FERNANDEZ","oficio",null,date1,(float)222.22, (float)222.22,dep1);
		Emple emple2 = new Emple(2,"inigo","oficio2",emple1,date2,(float)222.22,(float)222.22,dep2);
		Emple emple3 = new Emple(3,"inigo1","oficio2",emple2,date2,(float)222.22,(float)222.22,dep3);
		Emple emple4 = new Emple(4,"inigo2","oficio2",emple3,date2,(float)222.22,(float)222.22,dep1);
		Emple emple5 = new Emple(5,"inigo3","oficio2",emple2,date2,(float)222.22,(float)222.22,dep2);
				//int empNo, String apellido, String oficio, Emple dir, Date fechaAlt, float comision, Depart dept

		ODB odb = ODBFactory.open("EMPRESA.DB"); // Abrir BD
		odb.store(emple2);
		odb.store(emple1);
		odb.store(emple3);
		odb.store(emple4);
		odb.store(emple5);

		odb.close(); // Cerrar BD
	}
}