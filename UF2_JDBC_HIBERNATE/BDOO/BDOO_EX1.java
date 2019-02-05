import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
class Paises { //Clase Jugadores
	private String nombrePais;
	private int idPais;
	public Paises() {}
	public Paises(String nombrePais, int idPais) {
		this.nombrePais = nombrePais;
		this.idPais = idPais;
	}

	public void setNombrePais(String nombrePais) {this.nombrePais = nombrePais;}
	public String getNombrePais() {return nombrePais;}
	public void setidPais(int idPais) {this.idPais = idPais;}
	public int getidPais() {return idPais;}
	
	public String toString() {return nombrePais;}
}
class Jugadores { //Clase Jugadores
	private String nombre;
	private String deporte;
	private String ciudad;
	private int edad;
	private Paises pais;
	public Jugadores() {}
	public Jugadores(String nombre, String deporte,
			String ciudad, int edad,Paises pais) {
		this.nombre = nombre;
		this.deporte = deporte;
		this.ciudad = ciudad;
		this.edad = edad;
		this.pais = pais;
	}

	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getNombre() {return nombre;}
	public void setDeporte(String deporte) {this.deporte = deporte;}
	public String getDeporte() {return deporte;}
	public void setCiudad(String ciudad) {this.ciudad = ciudad;}
	public String getCiudad () {return ciudad;}
	public void setEdad(int edad) {this.edad = edad;}
	public int getEdad() {return edad;}
	public void setPais(Paises pais) {this.pais = pais;}
	public Paises getPais() {return pais;}
	
}
public class BDOO_EX1 {
	public static void main(String[] args) {
		Paises Spain = new Paises("Spain",222);
		Paises GER = new Paises("Germany",222);
		Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14,Spain); // Crear instancias para almacenar en BD
		Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15,GER);
		Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15,GER);
		Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14,Spain);
		ODB odb = ODBFactory.open("EQUIPOS2.DB"); // Abrir BD
		odb.store(Spain);
		odb.store(GER);
		odb.store(j1); // Almacenamos objetos
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		Objects<Jugadores> objects = odb.getObjects(Jugadores.class); //recuperamos todos los objetos
		System.out.printf("%d Jugadores: %n", objects.size());
		int i = 1;

		while(objects.hasNext()){ // visualizar los objetos
			Jugadores jug = objects.next();
			System.out.printf("%d: %s, %s, %s %s %s%n",
					i++, jug.getNombre(), jug.getDeporte(),
					jug.getCiudad(), jug.getEdad(),jug.getPais().getNombrePais());
			
		}
		odb.close(); // Cerrar BD
	}
}