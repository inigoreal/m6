/*2_Gestión de ficheros.pdf 
 * Ex2
 * Ahora haz los cambios necesarios para que el programa anterior muestre los
ficheros del directorio introducido desde línea de comandos al ejecutar el
programa
 */
import java.io.*;
public class uf1_T1_ex2{
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Directori seleccionat");
		String dir=reader.readLine();
		File f = new File(dir);
		String[] archivos = f.list();
		System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
		for (int i=0; i<archivos.length; i++){
			File f2 = new File(f, archivos[i]);
			System.out.printf("Nombre: %s, es fichero?: %b, es directorio?:%b %n", archivos[i],
			f2.isFile(), f2.isDirectory());
		}
	}
}
