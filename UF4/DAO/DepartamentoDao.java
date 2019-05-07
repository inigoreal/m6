package daoJava;

public interface DepartamentoDao {
		boolean InsertarDep (Departamento dep);
	
		boolean EliminarDep (int deptno);
		
		boolean ModificarDep (int deptno, Departamento dep);
		
		Departamento ConsultarDep (int deptno);
		
	}
