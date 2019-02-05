import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class BDOO_EX1_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String deporteSelected = reader.readLine();
		
		ODB odb = ODBFactory.open("EQUIPOS2.DB"); // Abrir BD
		Objects<Jugadores> objects = odb.getObjects(Jugadores.class); //recuperamos todos los objetos
		System.out.printf("%d Jugadores: %n", objects.size());
		int i = 1;

		while(objects.hasNext()){ // visualizar los objetos
			Jugadores jug = objects.next();
			if(jug.getDeporte().equals(deporteSelected)) {
				System.out.printf("%d: %s, %s, %s %s %s%n",
						i++, jug.getNombre(), jug.getDeporte(),
						jug.getCiudad(), jug.getEdad(),jug.getPais().getNombrePais());	
			}
			
			
		}
		odb.close(); // Cerrar BD
	}
}