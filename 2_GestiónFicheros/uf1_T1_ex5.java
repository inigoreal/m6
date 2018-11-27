/*2_Gestión de ficheros.pdf 
 * Ex5
 * Realiza un programa Java que muestre la siguiente información de un fichero
cualquiera: Nombre, ruta relativa, ruta absoluta, permisos y tamaño.
 * 
 */
import java.io.*;
public class uf1_T1_ex5{
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Directori seleccionat");
		String dir=reader.readLine();
		File f = new File(dir);
		System.out.println("Pesa: "+f.length()/ 1024 + "  kb");
		System.out.println("Nom: "+f.getName());	
		System.out.println("Pot escriure: "+f.canWrite());
		System.out.println("Pot llegir: "+f.canRead());
		System.out.println("Ruta relativa: "+f.getPath());
		System.out.println("Ruta absoluta: "+f.getAbsolutePath());
	}
}
