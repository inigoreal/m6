/*2_Gestión de ficheros.pdf 
 * Ex6
 * Realiza un programa que elimine el directorio creado en el punto anterior.
Para ello habrás de eliminar todos los archivos que se encuentren dentro del
directorio.
 */
import java.io.*;
public class uf1_T1_ex6{
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Directori seleccionat a borrar");
		String directoriRead=reader.readLine();
		File directory = new File(directoriRead);
		File[] llistaDoc = directory.listFiles();
			
		for (int i = 0; i < llistaDoc.length; i++) {			
			llistaDoc[i].delete();
		}
		directory.delete();
	}
}
