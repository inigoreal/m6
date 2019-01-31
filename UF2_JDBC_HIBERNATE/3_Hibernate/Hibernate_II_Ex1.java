import primero.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/*Hibernate_II_Ex1
 * 
 * Realiza un programa Java (puedes modificar el Main anterior) que añada una
nueva fila en la tabla emple. El nuevo empleado estará asignado al
departamento 10. Para asignarle el departamento será necesario crear un
objeto de tipo Depart y asignar como número de departamento el valor 10
con el método setDeptNo().
 */
public class Hibernate_II_Ex1 {
	public static void main(String[] args){
		//En primer lugar se obtiene la sesión creada por el Singleton.
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//Abrimos sesión e iniciamos una transacción
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("Inserto una fila en depart");
		//Creamos un nuevo objeto Depart y damos valor a sus atributos
		Depart dep = new Depart();
		Emple empl = new Emple();
		dep.setDeptNo((byte) 10);
		empl.setEmpNo(9992);
		empl.setDepart(dep);
		empl.setApellido("apellido1");
		empl.setOficio("oficio1");
		empl.setSalario(2222);
		empl.setDir(7902);
		empl.setComision(111);
		//Guardamos en la base de datos y comprometemos la información
		session.save(empl);
		tx.commit();
		session.close();
		System.exit(0);
	}
}
