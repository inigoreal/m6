/*7_Acceso a ficheros XML con DOM.pdf
Vas a realizar exactamente lo mismo pero vas a utilizar un fichero de texto con los datos de los empleados. Es decir:

- Generar un archivo de texto que contendrá la información de cada empleado. 
- Cada línea del archivo se asociará al id, apellido, departamento y salario de cada empleado.
- Cada uno de estos campos irá separado por el carácter “:” (Ejemplo: 1:Fernandez:10:1000.45). 
- Llamarás al archivo “Empleados.txt” 
- Generar el programa Java que genere el archivo XML correspondiente a los datos contenidos en Empleados.txt 
 * 
 * 
 */
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
public class uf1_t7_ex1 {

	public static void main (String args[]) throws IOException {

		int id, dep;
		Double salario;
		char apellido[] = new char[10];
		ArrayList<String> infoEmpleados = new ArrayList<String>();
		BufferedReader file = new BufferedReader (new FileReader ("Empleados.txt"));
		String linea;
		while ((linea = file.readLine()) != null){
			infoEmpleados.add(linea);
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument (null,"Empleados", null);
			document.setXmlVersion("1.0");
			for (int i=0;i<infoEmpleados.size();i++) {
				String[] infoEmpleadosSplit = infoEmpleados.get(i).split(":");
				
				id = Integer.parseInt(infoEmpleadosSplit[0]);
				apellido = infoEmpleadosSplit[1].toCharArray();
				String apellidos = new String (apellido);
				dep = Integer.parseInt(infoEmpleadosSplit[2]);
				salario =Double.parseDouble(infoEmpleadosSplit[3]);
				if (id>0) {
					Element raiz = document.createElement ("empleado");
					document.getDocumentElement().appendChild(raiz);
					CrearElemento ("id", Integer.toString(id), raiz, document);
					CrearElemento ("apellido",apellidos.trim(), raiz, document);
					CrearElemento ("dep", Integer.toString(dep), raiz, document);
					CrearElemento ("salario", Double.toString(salario),raiz, document);
				}
			}
			Source source = new DOMSource (document);
			Result result = new StreamResult (new java.io.File ("Empleados.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform (source, result);
		} catch (Exception e ) { System.err.println ("Error: " + e);}
		file.close();
	}
	static void CrearElemento (String datoEmpleado, String valor, Element raiz, Document document) {
		Element elem = document.createElement (datoEmpleado);
		Text text = document.createTextNode(valor);
		raiz.appendChild (elem);
		elem.appendChild (text);
	}
}
