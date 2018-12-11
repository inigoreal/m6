/*6_Ficheros de acceso aleatorio.pdf
 * Los 4 ejercicios juntos[CONSULTA(1) | INSERCION(2) | MODIFICACION(3) | BORRADO(4)] 
 * CONSULTA
 * INSERCION
 * MODIFICACION
 * BORRADO
 */
import java.io.*;
import java.util.ArrayList;
public class uf1_t6_ex1 {
	public static void main(String[] args) throws IOException {
		ArrayList<Empleado> infoEmpleados = new ArrayList<Empleado>();
		infoEmpleados = ReadFile();
		options(infoEmpleados);
	}
	public static String reader(String text) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(text);
		String resposta = reader.readLine();
		return resposta;
	}
	public static String reader(String[] text) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0;i<text.length;i++) {
			System.out.println(text[i]);
		}
		String resposta = reader.readLine();
		return resposta;
	}
	public static ArrayList<Empleado> ReadFile() throws IOException {
		ArrayList<Empleado> infoEmpleados = new ArrayList<Empleado>();
		File fichero = new File ("AleatorioEmpleado.dat");
		RandomAccessFile file = new RandomAccessFile (fichero, "r");
		int id, dep ,posicion;
		Double salario;
		char apellido[]= new char[10], aux;
		posicion =0;
		for ( ; ; ){
			file.seek (posicion); // Nos posicionamos en posicion
			id = file.readInt(); // Obtengo identificar de Empleado
			for ( int i =0; i<apellido.length; i++) {
				aux = file.readChar(); // Voy leyendo carácter a carácter el apellido y lo guardo
				apellido[i]=aux; // en el array apellido
			}
			String apellidos = new String (apellido);
			dep = file.readInt(); //Lectura de departamento y salario
			salario = file.readDouble();
			if (id >0) {
			
			Empleado empleado = new Empleado(id,dep,salario,apellidos.trim());
			infoEmpleados.add(empleado);
			}
			posicion = posicion + 36;
			if (file.getFilePointer() ==file.length()) break; // Si he recorrido todo el fichero, salgo
		}
		file.close();
		return infoEmpleados;
	}
	public static int buscarEmpleado(ArrayList<Empleado> infoEmpleados,int idR) throws IOException {
		for(int i=0; i<infoEmpleados.size();i++) {
			int idO = infoEmpleados.get(i).getId();
			if (idO==idR) {
				return i;
			}
		}
		return -2;
	}
	public static void options(ArrayList<Empleado> infoEmpleados) throws IOException {
		infoEmpleados = ReadFile();
		String[] options = {"CONSULTA     (1):","INSERCION    (2):","MODIFICACION (3):","BORRADO      (4):"};
		switch(Integer.parseInt(reader(options))) {
		case 1:
			CONSULTA(infoEmpleados);
			break;
		case 2:
			INSERCION(infoEmpleados);
			break;
		case 3:
			MODIFICACION(infoEmpleados);
			break;
		case 4:
			BORRADO(infoEmpleados);
			break;
		}
	}
	public static void BORRADO(ArrayList<Empleado> infoEmpleados) throws IOException {
		Integer persona = buscarEmpleado(infoEmpleados,Integer.parseInt(reader("ID Empleado:")));
		if (persona!=-2) {
			File fichero = new File ("AleatorioEmpleado.dat");
			RandomAccessFile file = new RandomAccessFile (fichero , "rw");
			StringBuffer buffer = null; 
			int posicion = persona * 36;
			buffer = new StringBuffer (persona.toString());
			buffer.setLength(10);
			file.seek(posicion);
			file.writeInt(-1); 					
			file.writeChars (buffer.toString());	
			file.writeInt(0);			
			file.writeDouble(0);
			file.close();	
			System.out.println("Empleado borrado correctamente.");
			options(infoEmpleados);
		}
		else {
			System.out.println("No existe el Empleado");
			options(infoEmpleados);
		}
	}
	public static void MODIFICACION(ArrayList<Empleado> infoEmpleados) throws IOException {
		int persona = buscarEmpleado(infoEmpleados,Integer.parseInt(reader("ID Empleado:")));
		if (persona!=-2) {
			double salarioAntiguo = infoEmpleados.get(persona).getSalario();			
			double salarioNuevo = Double.parseDouble(reader("Salario a sumar:"));
			salarioNuevo = salarioNuevo + salarioAntiguo;
			System.out.println("ID: "+infoEmpleados.get(persona).getId()+", Apellido: "+infoEmpleados.get(persona).getApellido()+", Departamento: "+infoEmpleados.get(persona).getDep()+", Nuevo Salario:"+salarioNuevo+", Salario Antiguo: "+salarioAntiguo);
			File fichero = new File ("AleatorioEmpleado.dat");
			RandomAccessFile file = new RandomAccessFile (fichero , "rw");		
			int posicion = (persona * 36 + 28);
			file.seek(posicion);
			file.writeDouble(salarioNuevo);
			file.close();	
			System.out.println("Salario modificado correctamente.");
			options(infoEmpleados);
		}
		else {
			System.out.println("No existe el Empleado");
			options(infoEmpleados);
		}

	}
	
	public static void CONSULTA(ArrayList<Empleado> infoEmpleados) throws IOException {
		int idR = Integer.parseInt(reader("ID Empleado:"));
		int persona = buscarEmpleado(infoEmpleados,idR);
		if (persona!=-2) {
			System.out.println("ID: "+infoEmpleados.get(persona).getId()+", Apellido: "+infoEmpleados.get(persona).getApellido()+", Departamento: "+infoEmpleados.get(persona).getDep()+", Salario: "+infoEmpleados.get(persona).getSalario());
			options(infoEmpleados);
		}
		else {
			System.out.println("No existe el Empleado");
			options(infoEmpleados);
		}
	}

	public static void INSERCION(ArrayList<Empleado> infoEmpleados) throws IOException {
		System.out.println("insertar valores:");
		int idR = Integer.parseInt(reader("ID Empleado:"));
		int persona = buscarEmpleado(infoEmpleados,idR);
		
		if (persona!=-2) {
			System.out.println("Ya existe el Empleado");
			options(infoEmpleados);
		}
		else {
			String apellidoR = reader("Apellido:");
			int depR = Integer.parseInt(reader("Departamento:"));
			Double salarioR =  Double.parseDouble(reader("Salario:"));

			File fichero = new File ("AleatorioEmpleado.dat");
			RandomAccessFile file = new RandomAccessFile (fichero , "rw");
			StringBuffer buffer = null; 
			file.writeInt (idR);
			buffer = new StringBuffer (apellidoR);
			buffer.setLength(10); // Fijo en 10 caracteres la longitud del apellido
			file.writeChars (buffer.toString());
			file.writeInt(depR);
			file.writeDouble (salarioR);

			file.close(); // No olvidarse de cerrar el fichero
			System.out.println("Empleado introducido");
			
			options(infoEmpleados);
		}
	}


}
