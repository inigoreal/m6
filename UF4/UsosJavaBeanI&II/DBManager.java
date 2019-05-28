package control;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;


/*
 */
public class DBManager {
	private String file="test.DB";
	private ODB odb;
	public DBManager() {
		try {
			this.odb = ODBFactory.open(this.file);
		}catch(Exception e){
			System.out.println("Error al abrir el fichero: "+this.file+" comprueba que no este siendo usado.");
			System.exit(0);
		}

	}
	public void insertarElemento(Departamento elemento){
		this.odb.store(elemento);
		System.out.print("Deptno: "+elemento.getDeptno()+" Nombre: "+elemento.getDnombre()+" Localidad: "+elemento.getLoc());
	}
	public void obtenerDepartamento() {
		Objects<Departamento> objectPedido = this.odb.getObjects(Departamento.class);
		while(objectPedido.hasNext()){
			Departamento elemento = objectPedido.next();
			System.out.print("Deptno: "+elemento.getDeptno()+" Nombre: "+elemento.getDnombre()+" Localidad: "+elemento.getLoc()+"\n");
			//return ped;	
		}
		//return null;
		
	}
	public void cerrarDB(){
		this.odb.close();
	}
}
