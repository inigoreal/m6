/*5_Ficheros binarios.pdf
 * Ex3
 *Ahora escribe un programa que permita visualizar los datos grabados
anteriormente en el fichero FicheroDatos.dat. Se deben obtener en el mismo
orden en el que se escribieron, es decir, primero obtenemos el nombre y
luego la edad.
 */
import java.io.* ;
public class uf1_T4_ex3{
	public static void main (String [] args) throws IOException{
		File fichero = new File ("FicheroDatos.dat");
		FileOutputStream fileout = new FileOutputStream (fichero);
		FileInputStream filein = new FileInputStream(fichero);
		DataOutputStream filedos = new DataOutputStream(fileout);
		DataInputStream filedis = new DataInputStream(filein);
		String[] persones= {"Persona1","Persona2","Persona3","Persona4","Persona5","Persona6","Persona7","Persona8"};
		Integer[] edades = {2,20,3,2,55,44,32,44};
		for (int i=1; i<persones.length; i++){
			filedos.writeUTF(persones[i]);
			filedos.writeInt(edades[i]);
		}
		fileout.close();
		filedos.close();
		while(filedis.available()>0) {
			System.out.println(filedis.readUTF());
			System.out.println(filedis.readInt());
		}
		filein.close();
		filedis.close();
	}
}
