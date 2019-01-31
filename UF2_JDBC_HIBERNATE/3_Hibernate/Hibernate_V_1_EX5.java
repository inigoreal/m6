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
		Query q = session.createQuery("select avg(salario), count(apellido) from Emple emp group by emp.apellido");
		//Query q = session.createQuery("from Emple as emp inner join emp.depart as dep group by dep.dept_no");
		//select cat.color, sum(cat.weight), count(cat) from Cat cat group by cat.color
		List <Emple> lista =q.list();
		Iterator <Emple> iter = lista.iterator();
		while (iter.hasNext()){
			Emple emp = iter.next();
			System.out.printf("Salario medio: %s%n",emp.getApellido());
		}
		session.close();
		System.exit(0);
	}
}

