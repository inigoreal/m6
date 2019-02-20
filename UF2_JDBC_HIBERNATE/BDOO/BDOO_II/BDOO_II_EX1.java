import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/*BDOO_II_EX1
 * 
 * Realiza un programa que realice una búsqueda por nombre en la base de
datos “EQUIPOS.DB”. Pedirá un nombre al usuario y devolverá los datos del
jugador que responda a dicho nombre o un mensaje del estilo “no hay ningún
jugador que tenga ese nombre en la base de datos”
 * 
 */
public class BDOO_II_EX1 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Nombre Seleccionado");
		String nameSelected = reader.readLine();
		ODB odb = ODBFactory.open("EQUIPOS2.DB"); // Abrir BD
		int i = 1;
		IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("pais.nombrePais",nameSelected));
		query.orderByAsc ("nombre,edad");
		Objects<Jugadores> objects=odb.getObjects(query);
		if (objects.size()==0) {
			System.out.print("No existe");
		}
		else {
			System.out.printf("%d Jugadores: %n", objects.size());
		}
		while(objects.hasNext()){ // visualizar los objetos
			Jugadores jug = objects.next();
			System.out.printf("%d: %s, %s, %s %s %s%n",
				i++, jug.getNombre(), jug.getDeporte(),
				jug.getCiudad(), jug.getEdad(),jug.getPais().getNombrePais());	
		}
		odb.close(); // Cerrar BD
	}
}