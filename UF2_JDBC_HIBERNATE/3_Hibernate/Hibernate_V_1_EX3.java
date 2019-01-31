import primero.*;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
/*
 * Hibernate_V_1_EX3
 * Visualiza los nombres de los empleados junto con el departamento al que
pertenecen. (Ayuda: consulta en el manual de HQL cómo realizar un JOIN.
Observarás que es muy parecido a SQL)
 * 
 * 
 */

public class Hibernate_V_1_EX3 {
	public static void main(String[] args){
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q = session.createQuery("from Emple as emp inner join emp.depart as dep");
		List <?> lista =q.list();
		for(int i=0; i<lista.size(); i++) {
			Object[] row = (Object[]) lista.get(i);
			Emple emp = (Emple)row[0];
			Depart dep = (Depart)row[1];
			System.out.printf("Apellido: %s, Departamento: %s%n",emp.getApellido(),dep.getDeptNo());
		}
		session.close();
		System.exit(0);
	}
}

