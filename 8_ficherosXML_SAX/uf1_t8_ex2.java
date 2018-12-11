/*8_Acceso a ficheros XML con SAX.pdf
 *  Ex2
 * Crea un programa que devuelva el número de discos registrados en
discoteca.xml de un determinado autor que le pasamos por consola. Si el
autor carece de discos en el archivo, el programa devolverá un mensaje del
estilo: “El autor <xxxxxx> no aparece en el archivo.
 */
import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
public class uf1_t8_ex2 {
	public static void main (String [] args) throws FileNotFoundException, IOException, SAXException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Nombre del Autor");
		String resposta = reader.readLine();
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		GestionContenido2 gestor = new GestionContenido2(resposta);
		procesadorXML.setContentHandler(gestor);
		InputSource fileXML = new InputSource ("discoteca.xml");
		procesadorXML.parse(fileXML);
	}
}
class GestionContenido2 extends DefaultHandler {
	int totalDiscos=0;
	String autor;
	public GestionContenido2(String autor){
		super();
		this.autor=autor;
	}
	public void startDocument(){
		System.out.println("Comienzo del documento XML");
	}
	public void endDocument(){
		System.out.println("Final del documento XML");
		if (totalDiscos==0) {
			System.out.println("El autor "+ autor+" no aparece en el archivo.");	
		}else {
			System.out.println("Total: "+totalDiscos);
		}
	}
	public void startElement (String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\tPrincipio Elemento: %s %n", nombre);
	}
	public void endElement (String uri, String nombre, String nombreC){
		System.out.printf("\tFin Elemento: %s %n",nombre);

	}
	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String (ch, inicio, longitud);
		car = car.replaceAll("[\t\n]","");
		System.out.printf("\tCaracteres: %s %n", car);
		if (car.toLowerCase().equals(autor.toLowerCase())) {
			totalDiscos++;
		}
	}
}

