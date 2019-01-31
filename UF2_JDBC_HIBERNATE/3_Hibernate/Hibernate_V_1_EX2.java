import primero.*;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
/*
 * Hibernate_V_1_EX2
 * Visualiza los datos del señor “ARROYO”
 * 
 * 
 */

public class Hibernate_V_1_EX2 {
	public static void main(String[] args){
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q = session.createQuery("from Emple as e where e.apellido='ARROYO'");
		List <Emple> lista =q.list();
		Iterator <Emple> iter = lista.iterator();
		while (iter.hasNext()){
			Emple emp = (Emple) iter.next();
			System.out.printf("%s %s %s %s %s %s %s%n",emp.getEmpNo(),emp.getApellido(),emp.getOficio(),emp.getDir(),emp.getFechaAlt().toString(),emp.getSalario(),emp.getComision());
		}
		session.close();
		System.exit(0);
	}
}
