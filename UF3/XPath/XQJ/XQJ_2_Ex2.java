package exists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;
/* Problemas XQJ_2_Ex2
 * 2. A partir de los documentos productos.xml y zonas.xml, haz un programa que
reciba un número de zona por parámetro y genere un documento con nombre
zonaXX.xml donde XX es la zona solicitada. El documento debe contener los
productos de esta zona y las siguientes etiquetas: <cod_prod>,
<denominación>, <precio>, <nombre_zona>, <director> y <stock>. Donde el
stock se calcula restando el stock actual y el stock mínimo.
 * 
 */
public class XQJ_2_Ex2 {
	public static void main(String[] args) throws XQException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try{
			XQDataSource server = new ExistXQDataSource();
			server.setProperty ("serverName", "192.168.56.102");
			server.setProperty ("port","8080");
			server.setProperty ("user","admin");
			server.setProperty ("password","austria");
			XQConnection conn = server.getConnection();
			XQPreparedExpression consulta;
			XQResultSequence resultado;
			System.out.println("Zona:");
			String zona = reader.readLine();
			consulta = conn.prepareExpression(
					"let $pro := /productos/produc[cod_zona="+zona+"]"
					+ " let $namezona := /zonas/zona[cod_zona="+zona+"]/nombre"
					+ " let $dirzona := /zonas/zona[cod_zona="+zona+"]/director"
					+ " return"
					+ " <productos>{"
					+ " for $pro in $pro"
					+ " let $stockactual:=$pro/(stock_actual - stock_minimo)"
					+ " return"
					+ " <producto>"
					+ " <cod_prod>"
					+ " {data($pro/cod_prod)}"
					+ " </cod_prod>"
					+ " <denominacion>"
					+ " {data($pro/denominacion)}"
					+ " </denominacion>"
					+ " <precio>"
					+ " {data($pro/precio)}"
					+ " </precio>"
					+ " <nombre_zona>"
					+ " {data($namezona)}"
					+ " </nombre_zona>"
					+ " <director>"
					+ " {data($dirzona)}"
					+ " </director>"
					+ " <stock>"
					+ " {data($stockactual)}"
					+ " </stock>"
					+ " </producto>"
					+ " }"
					+ " </productos>");
			resultado = consulta.executeQuery();
			//	Ruta fixero
			String fitxer = "/home/cf17inigo.real/Escriptori/m6/";
			String fitxerName = "zona"+zona+".xml";
			BufferedWriter writer = new BufferedWriter(new FileWriter(fitxer+fitxerName));
			/* Cabecera XML */
			writer.write("<?xml version='1.0' encoding='UTF-8'?>");
			writer.newLine();
			/* Nodos XML */
			while (resultado.next()) {
			String cad = resultado.getItemAsString(null);
			writer.write(cad);
			writer.newLine();
			}
			conn.close();
			writer.close();
			System.out.print("Fixero creado correctamente en: "+fitxer+fitxerName);
		} catch (XQException ex) {System.out.println("Error al operar"+ex.getMessage());}
	}
}
