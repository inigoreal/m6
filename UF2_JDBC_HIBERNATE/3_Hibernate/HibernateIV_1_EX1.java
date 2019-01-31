import primero.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * HibernateIV_1 ex1
 * Visualiza el apellido y el salario del empleado con número: 7369
 * 
 * 
 */
public class HibernateIV_1_EX1 {
	public static void main(String[] args){
		//En primer lugar se obtiene la sesión creada por el Singleton.
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//Abrimos sesión e iniciamos una transacción
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		//Creamos un nuevo objeto Depart y damos valor a sus atributos
		Emple empl = (Emple) session.get (Emple.class, 7369);
		if (empl==null) {
		System.out.println ("No existe el empleado");
		
		}
		else {
		System.out.printf("Nombre: %s Apellido: %s%n", empl.getApellido(),empl.getSalario());
		session.save(empl);
		}
		//Guardamos en la base de datos y comprometemos la información
		
		tx.commit();
		session.close();
		System.exit(0);
	}
}
