
import java.beans.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PanelEmpleado implements Serializable, PropertyChangeListener  {

	public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
	private String[] listaDeCargos = {"Junior", "SemiSenior", "Analista", "CEO"};
	private int limiteVariacionSueldo;
	private Empleado empleado;


	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("cargo")){
			String[] cargoAnterior = this.listaDeCargos;
			boolean match = false;
			for (int i=0; i<cargoAnterior.length;i++) {
				if (cargoAnterior[i].equals(evt.getNewValue())) {
					match = true;
				}
			}
			System.out.println("Cargo Anterior: "+evt.getOldValue());
			System.out.println("Cargo Intento: "+evt.getNewValue());
			
			if (!match) {
				System.out.println("Cargo Final: "+evt.getOldValue());
				throw new IllegalArgumentException("Cargo no esta en la lista");
			}else {
				System.out.println("Cargo Final: "+evt.getNewValue());
			}


		}else {
			int salarioAnterior = this.empleado.getSueldo();
			int newValueSalario = Integer.parseInt(evt.getNewValue().toString());
			int prc2 = salarioAnterior/this.limiteVariacionSueldo;
			if (newValueSalario-salarioAnterior<=prc2) {
				System.out.println("Sueldo establecido, variacion del salario, menor a: "+this.limiteVariacionSueldo+"%");
			}
			else {
				throw new IllegalArgumentException("Sueldo no establecido, variacion del salario, mayor a: "+this.limiteVariacionSueldo+"%");
			}
		}
	}

	public PanelEmpleado () {
		this.limiteVariacionSueldo = 10;

	}

	public PanelEmpleado (int valor) {

		this.limiteVariacionSueldo = valor;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public int getLimiteVariacionSueldo() {
		return limiteVariacionSueldo;
	}

	public void setLimiteVariacionSueldo(int limiteVariacionSueldo) {
		this.limiteVariacionSueldo = limiteVariacionSueldo;
	}

	public String[] getListaDeCargos() {
		return listaDeCargos;
	}

	public void setListaDeCargos(String[] listaDeCargos) {
		this.listaDeCargos = listaDeCargos;
	}
	public void setListaDeCargos(int indice, String valor) { 
		this.listaDeCargos[indice] = valor;
	}
	public String getCategorias(int indice) {
		return this.listaDeCargos[indice];
	}
}
