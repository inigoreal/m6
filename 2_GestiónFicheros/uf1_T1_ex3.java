/*2_Gestión de ficheros.pdf 
 * Ex3
 * Realiza un programa Java que utilice el método listFiles() para mostrar la lista
de ficheros de un directorio que se pasará al programa desde los argumentos
del main
 */
import java.io.*;
public class uf1_T1_ex3{
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Directori seleccionat");
		String directoriRead=reader.readLine();
		File directory = new File(directoriRead);
		File[] llistaDoc = directory.listFiles();
			
		for (int i = 0; i < llistaDoc.length; i++) {			
			System.out.println(llistaDoc[i].getName());
		}
	}
}

