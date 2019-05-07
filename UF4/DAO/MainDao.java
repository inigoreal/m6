package daoJava;

public class MainDao {

	public static void main(String[] args) {
		
		
		DepartamentoDao depDAO = new DepartamentoImpl();
		/* 1-- Inserta un nuevo departamento */
		Departamento dep = new Departamento(1,"departamento1","valencia");
		System.out.println("Insertar: "+ depDAO.InsertarDep(dep));
		int idConsultar = 1;
		/* 2-- Consulta el nuevo departamento */
		Departamento Obtenido = depDAO.ConsultarDep(idConsultar);
		if (Obtenido!=null) {
			System.out.println("Consultar: "+ "Nombre: "+Obtenido.getDnombre()+ " ID: "+ Obtenido.getDeptno()+ " Loc: "+Obtenido.getLoc());
		}
		/* 3-- Modifica algunos valores del nuevo departamento*/
		Departamento depEditado = new Departamento(1,"departamento1Editado","valenciaEditado");
		
		System.out.println("Modificar: "+ depDAO.ModificarDep(idConsultar,depEditado));
		Obtenido = depDAO.ConsultarDep(idConsultar);
		if (Obtenido!=null) {
			System.out.println("Consultar: "+ "Nombre: "+Obtenido.getDnombre()+ " ID: "+ Obtenido.getDeptno()+ " Loc: "+Obtenido.getLoc());
		}
		/* 4-- Elimina el departamento creado */
		System.out.println("Eliminar: "+depDAO.EliminarDep(idConsultar));
		Obtenido = depDAO.ConsultarDep(idConsultar);
		if (Obtenido!=null) {
			System.out.println("Consultar: "+ "Nombre: "+Obtenido.getDnombre()+ " ID: "+ Obtenido.getDeptno()+ " Loc: "+Obtenido.getLoc());
		}
		
		

		
	}

}
