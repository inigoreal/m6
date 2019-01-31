import primero.*;
import java.util.Set;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/*
 * HibernateIV_1 ex2
 * Obtén los datos del departamento 10 y el APELLIDO y SALARIO de sus
empleados.
 * 
 * 
 */
public class HibernateIV_1_EX2 {
	public static void main(String[] args){
		//En primer lugar se obtiene la sesión creada por el Singleton.
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//Abrimos sesión e iniciamos una transacción
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		//Creamos un nuevo objeto Depart y damos valor a sus atributos
		Depart dep = (Depart) session.get (Depart.class, 10);
		if (dep==null) {
		System.out.println ("No existe el departamento");
		
		}
		else {
			Set<Emple> listaemp = dep.getEmples();
			Iterator<Emple> it = listaemp.iterator();
			while(it.hasNext()) {
				Emple emp = it.next();
				System.out.printf("Apellido: %s Salario: %s%n", emp.getApellido(),emp.getSalario());				
			}
		session.save(dep);
		}
		//Guardamos en la base de datos y comprometemos la información
		
		tx.commit();
		session.close();
		System.exit(0);
	}
}
