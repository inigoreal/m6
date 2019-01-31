import primero.*;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
/*
 * Hibernate_V_1_EX5
 * Muestra el salario medio y el número de empleados por departamento
 * 
 * 
 */

public class Hibernate_V_1_EX5 {
	public static void main(String[] args){
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q = session.createQuery("select avg(salario), count(apellido) from Emple emp group by emp.depart");
		List <?> lista =q.list();
		for(int i=0; i<lista.size(); i++) {
			Object[] row = (Object[]) lista.get(i);
			System.out.printf("Salario: %s, Empleados: %s%n",row[0],row[1]);
		}
		session.close();
		System.exit(0);
	}
}

