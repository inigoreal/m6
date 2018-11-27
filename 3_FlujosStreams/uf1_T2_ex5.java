/*3_Flujos o Streams. Tipos.pdf
 * Ex5
 * Escribe un programa en java que muestre por pantalla un fichero de texto
que le pasamos como argumento (o utilizando scanner) utilizando la clase
BufferedReader
 */
import java.io.*;
public class uf1_T2_ex5 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Introdueix un fitxer ");
		String file = reader.readLine();
		try {
			BufferedReader fichero = new BufferedReader ( new FileReader(file));
			String linea;
			while ((linea = fichero.readLine()) != null){
				System.out.println (linea);
			}
			fichero.close();
		}
		catch (FileNotFoundException fn) { System.out.println ("No se encuentra el fichero"); }
		catch (IOException io) { System.out.println ("Error de E/S"); }
	}
}
