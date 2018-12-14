/*Proyecto ficheros pdf4
4_ProyectoFicherosTexto.pdf*/
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class uf1_t3_ex1 {
	public static void main (String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		BufferedReader entrada1 = null;
		BufferedReader entrada2 = null;
		PrintWriter sortidaXML = null;
		PrintWriter sortidaLOG = null;
		String nomEtiquetas = "";
		String openFitxer="";
		String nomXml="";
		String separador="/";
		String separador2="/";
		int x=0,y=0,totalFitxers=1;
		ArrayList<String> documentsCSV = new ArrayList<String>();
		String linea,linea2;
		System.out.println("Introdueix [CSV | Directori] ");
		String mode = reader.readLine();
		while (true){
			if (!mode.endsWith(".csv")){
				File folder = new File(mode);
				File[] llistaDoc = folder.listFiles();
				try{
					for (int i = 0; i < llistaDoc.length; i++) {
						if (llistaDoc[i].isFile() && llistaDoc[i].getName().endsWith(".csv")) {
							documentsCSV.add(llistaDoc[i].getName());
						}

					}
					totalFitxers=documentsCSV.size();
					break;

				}
				catch(Exception e){
					System.out.println("Error, directori no valid.");
					System.out.println("F: ");
					mode = reader.readLine();

				}

				nomXml = mode;
			}else {
				break;
			}
		}
		sortidaLOG = new PrintWriter (new FileWriter ("doc.log",true));
		while(true) {
			try{
				entrada1 = new BufferedReader (new FileReader (nomEtiquetas));
				sortidaLOG.println("\nTot Correcte al fitxer: "+nomEtiquetas+". Data: "+Calendar.getInstance().getTime());
				break;
			}
			catch (Exception e){
				System.out.println("Fitxer etiquetas: ");
				nomEtiquetas = reader.readLine();
				sortidaLOG.println("\nError al obrir el fitxer: "+nomEtiquetas+". Data: "+Calendar.getInstance().getTime());
			}
		}
		long iniciTime =Calendar.getInstance().getTime().getTime();
		String[] parametres=null;
		String[] parametres2=null;
		ArrayList<String> paraules = new ArrayList<String>();


		while((linea2 = entrada1.readLine()) != null) {
			paraules.add(linea2);	
		}
		try{
			entrada1.close();
			sortidaLOG.println("\nTot Correcte al tancar el fitxer: "+nomEtiquetas+". Data: "+Calendar.getInstance().getTime());
		}catch (Exception e){
			sortidaLOG.println("\nError al tancar el fitxer: "+nomEtiquetas+". Data: "+Calendar.getInstance().getTime());
			sortidaLOG.close();
			System.exit(0);
		}
		for (int h=0;h<totalFitxers;h++){
			if (mode.endsWith(".csv")){
				openFitxer=mode;
			}else{
				openFitxer=mode+separador+documentsCSV.get(h);
			}
				try{
					entrada2 = new BufferedReader (new FileReader (openFitxer));
					sortidaLOG.println("\nTot Correcte al fitxer: "+openFitxer+". Data: "+Calendar.getInstance().getTime());
				}catch (Exception e){
					sortidaLOG.println("\nError al obrir el fitxer: "+openFitxer+". Data: "+Calendar.getInstance().getTime());
					sortidaLOG.close();
					System.exit(0);
				}try{
					sortidaXML = new PrintWriter (new FileWriter (openFitxer.replace(".csv", ".xml")));
					nomXml = openFitxer.getName();
					sortidaLOG.println("\nTot Correcte al fitxer: "+openFitxer.replace(".csv", ".xml")+". Data: "+Calendar.getInstance().getTime());
				}catch (Exception e){
					sortidaLOG.println("\nError al obrir el fitxer: "+openFitxer.replace(".csv", ".xml")+". Data: "+Calendar.getInstance().getTime());
					sortidaLOG.close();
					System.exit(0);
				}

			
			sortidaXML.println("<"+nomXml+">");
			while ((linea = entrada2.readLine())!=null){
				if (linea !=null){
					parametres2 = linea.split("\n");

					for(int i=0;i<parametres2.length;i++){
						parametres = parametres2[i].split(",");
					}
				}
				x++;y++;
				sortidaXML.print("\t<elem nr="+x+">\n");	
				for(int i=0;i<parametres.length;i++){
					if(paraules.size()<=i){
						sortidaXML.print("\t\t<Altre>");
						sortidaXML.print(parametres[i].replace("\"", ""));
						sortidaXML.print("</Altre>\n");
					}
					else{
						sortidaXML.print("\t\t<"+paraules.get(i)+">");
						sortidaXML.print(parametres[i].replace("\"", ""));
						sortidaXML.print("</"+paraules.get(i)+">\n");
					}
				}
				sortidaXML.print("\t</elem>\n");
			}
			sortidaXML.println("</"+nomXml+">");
			x=0;
			try{
				entrada2.close();
				sortidaLOG.println("\nTot Correcte al tancar el fitxer: "+openFitxer+". Data: "+Calendar.getInstance().getTime());
			}
			catch (Exception e){
				sortidaLOG.println("\nError al tancar el fitxer: "+openFitxer+". Data: "+Calendar.getInstance().getTime());
			}
			try{
				sortidaXML.close();
				sortidaLOG.println("\nTot Correcte al tancar el fitxer: "+openFitxer.replace(".csv", ".xml")+". Data: "+Calendar.getInstance().getTime());
			}
			catch (Exception e){
				sortidaLOG.println("\nError al tancar el fitxer: "+openFitxer.replace(".csv", ".xml")+". Data: "+Calendar.getInstance().getTime());
			}
		}
		System.out.println("Finalització del programa. Temps total: "+((Calendar.getInstance().getTime().getTime()-iniciTime))+"ms. Lineas Processades: "+y);
		sortidaLOG.println("\nFinalitzacio del programa. Temps total: "+((Calendar.getInstance().getTime().getTime()-iniciTime))+"ms. Lineas Processades: "+y+"\n\n");
		sortidaLOG.close();

	}
}
