package daoJava;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import javaBeans.Producto;

public class DepartamentoImpl implements DepartamentoDao{
	
	private String file="“Departament.BD";
	private ODB odb;
	public DepartamentoImpl() {
		try {
			this.odb = ODBFactory.open(this.file);
		}catch(Exception e){
			System.out.println("Error al abrir el fichero: "+this.file+" comprueba que no este siendo usado.");
			System.exit(0);
		}
		
	}

	@Override
	public boolean InsertarDep(Departamento dep) {
		try {
		this.odb.store(dep);
		return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean EliminarDep(int deptno) {
		try {
		IQuery query = new CriteriaQuery(Departamento.class,
				Where.equal("deptno", deptno));
		Objects<Departamento> depart = odb.getObjects(query);				
		Departamento depart2 = (Departamento) depart.getFirst();
		this.odb.delete(depart2);
		return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean ModificarDep(int deptno, Departamento dep) {
		Departamento depEdit = ConsultarDep(deptno);
		if (depEdit!=null) {
			depEdit.setDeptno(dep.getDeptno());
			depEdit.setDnombre(dep.getDnombre());
			depEdit.setLoc(dep.getLoc());
		this.odb.store(depEdit);
		return true;
		}
		return false;
	}

	@Override
	public Departamento ConsultarDep(int deptno) {
		IQuery query = new CriteriaQuery(Departamento.class,
				Where.equal("deptno", deptno));
		Objects<Departamento> depart = odb.getObjects(query);	
		if(depart!=null && depart.size()>0) {
		Departamento depart2 = (Departamento) depart.getFirst();
		
		return depart2;	
		}
		else {
		System.out.println("No se ha encontrado el producto");
		return null;
		}

	}

}
