import java.beans.*;
import java.io.Serializable;

public class Empleado implements Serializable{
	
	private String nif;
	private String nombre;
	private String cargo;
	private int sueldo;
	
	private PropertyChangeSupport propertySupport;

	public Empleado() {
		super();
		this.cargo = "Junior";
		this.sueldo = 1000;
		propertySupport = new PropertyChangeSupport (this);
	}
	
	public Empleado(String nif, String nombre) {
		Empleado e = new Empleado();
		propertySupport = new PropertyChangeSupport (this);
		this.cargo = e.getCargo();
		this.sueldo = e.getSueldo();
		this.nif = nif;
		this.nombre = nombre;
	}
	
	public void addPropertyChangeListener (PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener (PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
	}

	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		try {
		propertySupport.firePropertyChange("cargo", this.cargo, cargo);
		this.cargo = cargo;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int getSueldo() {
		return sueldo;
	}
	public void setSueldo(int sueldo) {
		try {
			propertySupport.firePropertyChange("sueldo", this.sueldo, sueldo);
			this.sueldo = sueldo;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}

	public PropertyChangeSupport getPropertySupport() {
		return propertySupport;
	}

	public void setPropertySupport(PropertyChangeSupport propertySupport) {
		this.propertySupport = propertySupport;
	}
	
}
