/*5_Ficheros binarios.pdf
 * Ex2
 *Escribe un programa que inserte datos en “FicherosDatos.dat”. Los datos los
tomará de dos arrays definidos en el propio programa. Uno contendrá los
nombres de una serie de personas y el otro sus edades. Se irá recorriendo
los arrays e iremos escribiendo en el fichero el nombre (mediante el método
writeUTF(String str) y la edad (writeInt (int v)). NOTA: si queremos añadir
bytes al final del fichero (FicheroDatos.dat) se puede usar el siguiente
constructor: FileOutputStream fileout = new FileOutputStream (fichero, true)
 */
import java.io.* ;
public class uf1_T4_ex2{
	public static void main (String [] args) throws IOException{
		File fichero = new File ("FicheroDatos.dat");
		FileOutputStream fileout = new FileOutputStream (fichero);
		FileInputStream filein = new FileInputStream(fichero);
		DataOutputStream filedos = new DataOutputStream(fileout);
		String[] persones= {"Persona1","Persona2","Persona3","Persona4","Persona5","Persona6","Persona7","Persona8"};
		Integer[] edades = {2,20,3,2,55,44,32,44};
		for (int i=1; i<persones.length; i++){
			filedos.writeUTF(persones[i]);
			filedos.writeInt(edades[i]);
		}
		fileout.close();
		filedos.close();
	}
}
