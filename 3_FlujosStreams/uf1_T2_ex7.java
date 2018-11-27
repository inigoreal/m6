/*3_Flujos o Streams. Tipos.pdf
 * Ex7
 * Repite el ejercicio anterior pero ahora utilizando la clase PrintWriter
 */
import java.io.*;
public class uf1_T2_ex7 {
	public static void main (String [] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Introdueix un fitxer ");
		String file = reader.readLine();

			PrintWriter fichero = new PrintWriter (new FileWriter (file,true));
			for (int i=0;i<10;i++){
				fichero.println(i);
			}
			fichero.close();
	}
}


