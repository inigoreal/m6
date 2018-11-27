/*3_Flujos o Streams. Tipos.pdf
 * Ex4
 * Crea el siguiente array de String e inserta en el fichero las cadenas una a una
usando el método write (String str)
 */
import java.io.*;
public class uf1_T2_ex4{
	public static void main (String [] args) throws IOException {
		File fichero = new File("FicheroTexto.txt");
		FileWriter fic = new FileWriter (fichero);
		String prov[] = {"Albacete", "Avila", "Badajoz", "Caceres", "Huelva", "Jaen","Madrid", "Segovia", "Soria", "Toledo", "Valladolid", "Zamora"};
		
		for (int i=0;i<prov.length;i++){
			fic.write(prov[i]);
		}
		fic.close (); // cerramos fichero
	}
}

