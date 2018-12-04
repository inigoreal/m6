/*7_Acceso a ficheros XML con DOM.pdf
 * Crea un programa Java que lea el documento anterior y muestre toda la
información que contenga.
*/
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
public class uf1_t7_ex2 {
	public static void main (String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File ("musica.xml"));
			System.out.printf ("Elemento raíz : %s %n", document.getDocumentElement().getNodeName());
			NodeList albums = document.getElementsByTagName("album");
			System.out.printf ("Nodos empleado a recorrer: %d %n", albums.getLength());
			for (int i = 0; i < albums.getLength(); i++) {
				Node emple = albums.item(i);
				if (emple.getNodeType() == Node.ELEMENT_NODE){
					Element elemento = (Element) emple;
					System.out.printf("Autor = %s %n", elemento.getElementsByTagName("autor").item(0).getTextContent());
					System.out.printf(" * titulo = %s %n",
							elemento.getElementsByTagName("titulo").item(0).getTextContent());
					System.out.printf(" * formato = %s %n",
							elemento.getElementsByTagName("formato").item(0).getTextContent());
				}
			}
		}catch (Exception e) {e.printStackTrace();}
	}
}
