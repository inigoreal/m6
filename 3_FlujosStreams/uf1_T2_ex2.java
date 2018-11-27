/*3_Flujos o Streams. Tipos.pdf
 * Ex2
 * Modifica el código anterior para que se le puede pasar el nombre del fichero
al programa.
 */
import java.io.*;
public class uf1_T2_ex2{
	public static void main ( String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Directori seleccionat");
		String directoriRead=reader.readLine();
		File fichero = new File (directoriRead); // declaración fichero
		FileReader flu = new FileReader (fichero); // creamos flujo de entrada hacia el fichero
		char[] buf = new char[20];
		
		
		int i;
		while ((flu.read(buf))!=-1){ //Vamos leyendo carácter a carácter
			System.out.print(buf); //hacemos cast a char del entero leído
		}
			flu.close();
	}
}
