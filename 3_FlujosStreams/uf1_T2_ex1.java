/*3_Flujos o Streams. Tipos.pdf
 * Ex1
 * Modifica el código anterior para que el programa vaya leyendo caracteres de
20 en 20.
 */
import java.io.*;
public class uf1_T2_ex1{
	public static void main ( String [] args) throws IOException {
		File fichero = new File ("uf1_T2_ex1.java"); // declaración fichero
		FileReader flu = new FileReader (fichero); // creamos flujo de entrada hacia el fichero
		char[] buf = new char[20];
		
		
		int i;
		while ((flu.read(buf))!=-1){ //Vamos leyendo carácter a carácter
			System.out.print(buf); //hacemos cast a char del entero leído
		}
			flu.close();
	}
}
