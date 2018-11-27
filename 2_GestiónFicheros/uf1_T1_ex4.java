/*2_Gestión de ficheros.pdf 
 * Ex4
 * Añade al programa anterior las instrucciones necesarias para que envíe un
mensaje de error en caso de que el directorio pasado como argumento no
exista.
 */
import java.io.*;
public class uf1_T1_ex4{
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Directori seleccionat");
		String directoriRead=reader.readLine();
		try{
			File directory = new File(directoriRead);
			File[] llistaDoc = directory.listFiles();
				
			for (int i = 0; i < llistaDoc.length; i++) {			
				System.out.println(llistaDoc[i].getName());
			}
		}
		catch (Exception e){
			System.out.println("Error, no existe el directorio: "+ directoriRead);
		}
	}
}

