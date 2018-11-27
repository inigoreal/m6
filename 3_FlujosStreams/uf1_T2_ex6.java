/*3_Flujos o Streams. Tipos.pdf
 * Ex6
 * Escribe un programa que, utilizando la clase BufferedWriter, escriba 10 filas
de caracteres en un fichero de texto y después de escribir cada fila salta una
línea con el método newLine()
 */
import java.io.*;
public class uf1_T2_ex6 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Introdueix un fitxer ");
		String file = reader.readLine();

			BufferedWriter fichero = new BufferedWriter ( new FileWriter(file));
			for (int i=0;i<10;i++){
				fichero.write(i+"");
				fichero.newLine();
			}
			fichero.close();
	}
}


