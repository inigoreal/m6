
import java.io.*;
public class r3 {
    public static Partida recuperarEstado(String[] p,String[] nom, String[] ftx, int[] gnom, int x) throws IOException, ClassNotFoundException {
        String directory = reader("Fitxer a llegir: ");
        Partida partida = null;
        File fichero = new File (directory);
        try {
            FileInputStream filein = new FileInputStream (fichero);
            ObjectInputStream dataIS = new ObjectInputStream (filein) ;
            partida = (Partida) dataIS.readObject ();
            dataIS.close();
            filein.close();
            return partida;
        }catch (Exception e) {
            System.out.print("Fitxer no trobat");
        }
        return null;
    }
    public static void guardarEstado(String[] p, String[] nom, String[] ftx, int[] gnom, int x) throws IOException {
        String directory = reader("on vols guardar el fitxer?");
        File fichero = new File (directory);
        FileOutputStream fileout = new FileOutputStream (fichero);
        ObjectOutputStream dataOS = new ObjectOutputStream (fileout);
        Partida partida = new Partida (p, x, gnom, nom, ftx);
        dataOS.writeObject (partida);
        dataOS.close();
        fileout.close();
    }
    public static void introduirValors(String[] nom, String[] ftx) throws IOException {
        String resposta = reader("Asignar valors propis?[si|no]");
        if (resposta.toLowerCase().equals("si")){
            nom[0]=reader("Nom jugador 1: ");
            nom[1]=reader("Nom jugador 2: ");
            //nom iguals
            while (nom[0].equals(nom[1])){
                nom[1]=reader("Error. Nom jugador 2: ");
            }
            ftx[0]=reader("Fitxa Jugador: "+nom[0]);
            //mida de la fitxa
            while (ftx[0].length()==0 || ftx[0].length()>=2){
                ftx[0]=reader("Error. Fitxa Jugador: "+nom[0]);
            }
            ftx[1]=reader("Fitxa Jugador: "+nom[1]);
            //mida de la fitxa i fitxa igual
            while (ftx[1].length()==0 || ftx[1].length()>=2 || ftx[0].equals(ftx[1])){
                ftx[1]=reader("Error. Fitxa Jugador: "+nom[1]);
            }
        }
    }
    public static void results(int[] gnom,String type,String[] nom) {
        System.out.println(gnom[0]+":"+gnom[1]);
        if (type.equals("final")) {
            if (gnom[0]>gnom[1]){
                System.out.println("Guanya: "+nom[0]);
            }
            if (gnom[0]<gnom[1]){
                System.out.println("Guanya: "+nom[1]);
            }
            if (gnom[0]==gnom[1]){
                System.out.println("Empat");
            }
        }
    }
    public static String reader(String text) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(text);
        String resposta = reader.readLine();
        return resposta;
    }
    public static void guanyaJugadorCheck(String[] p,String[] nom,int[] gnom,int x) throws IOException {
        
        if ((p[1]==p[2] && p[2]==p[3] && p[1]!=" ") 
                || (p[4]==p[5] && p[5]==p[6] && p[4]!=" ") 
                || (p[7]==p[8] && p[8]==p[9] && p[7]!=" ") 
                || (p[1]==p[4] && p[4]==p[7] && p[1]!=" ") 
                || (p[2]==p[5] && p[5]==p[8] && p[2]!=" ") 
                || (p[3]==p[6] && p[6]==p[9] && p[3]!=" ") 
                || (p[7]==p[5] && p[5]==p[3] && p[7]!=" ") 
                || (p[9]==p[5] && p[5]==p[1] && p[9]!=" ")){
            System.out.println("Guanya: "+nom[x]);
            gnom[x]++;
            String resposta = reader("Vols Tornar a jugar? [si|no]");
            if (resposta.toLowerCase().equals("no")){
                results(gnom,"final",nom);
                return;
            }
            else{
                netejaArray(p);
                results(gnom,"normal",nom);
                imprimirPlantilla(p);
            }
        }
        else if(p[1]!=" " && p[2]!=" " && p[3]!=" " && p[4]!=" " && p[5]!=" " && p[6]!=" " && p[7]!=" " && p[8]!=" " && p[9]!=" ") {
            System.out.println("Empat: ");
            String resposta = reader("Vols Tornar a jugar? [si|no]");
            if (resposta.toLowerCase().equals("no")){
                results(gnom,"final",nom);
                return;
            }
            else{
                netejaArray(p);
                results(gnom,"normal",nom);
                imprimirPlantilla(p);
            }
            
        }
    }
    public static int changePlayer(int x) {
        if (x==0) {return 1;}
        else {return 0;}
    }
    public static void netejaPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void imprimirPlantilla(String[] p) {
        System.out.println(" _________________");
        System.out.println("|     |     |     |");
        System.out.println("|  "+p[1]+"  |  "+p[2]+"  |  "+p[3]+"  |");
        System.out.println("|____1|____2|____3|");
        System.out.println("|     |     |     |");
        System.out.println("|  "+p[4]+"  |  "+p[5]+"  |  "+p[6]+"  |");
        System.out.println("|____4|____5|____6|");
        System.out.println("|     |     |     |");
        System.out.println("|  "+p[7]+"  |  "+p[8]+"  |  "+p[9]+"  |");
        System.out.println("|____7|____8|____9|");
    }
    public static void netejaArray(String[] p) {
        for(int i=1;i<=9;i++){
            p[i]=" ";
        }
    }
    public static int turn(String[] nom, int x) throws IOException  {
        String resposta = reader("Torn de "+nom[x]+": ");
        while(true){
            try{
                int a=Integer.parseInt(resposta);
                return a;
            }
            catch(Exception e){
                resposta = reader("Torn de "+nom[x]+": ");
            }
        }

    }
    public static void regles() {
        System.out.println("Cada jugador introdueix la posició del 1 al 9.");
        System.out.println("L'objectiu és fer una linea de 3, (horitzontal, diagonal o vertical).");
        System.out.println("Per reiniciar la partida: 0");
        System.out.println("Per acabar la partida: 10");
        System.out.println("Per veure els resultats: 11");
        System.out.println("Per guardar l'estat de la partida: 12");
        System.out.println("Per recuperar l'estat de la partida: 13");
    }
    public static int options(int a, String[] p, String[] nom,int[] gnom,int x,String[] ftx) throws IOException, ClassNotFoundException {
        switch (a){
        case 0:
            //reset joc
            netejaArray(p);
            x = changePlayer(x);
            System.out.println("Partida reiniciada.");
            a = turn(nom,x);
            return a;
        case 11:
            //puntuació
            System.out.println(gnom[0]+":"+gnom[1]);
            a = turn(nom,x);
            return a;
        case 10:
            //final del joc
            System.out.println("Gracias per jugar");
            results(gnom,"final",nom);
            break;
        case 12:
            //estat Partida
            guardarEstado(p,nom,ftx,gnom,x);
            a = turn(nom,x);
            break;
        
        default:
            a = turn(nom,x);
            break;
        }
        return a;
    }
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        String p[]=new String [11];
        String nom[]={"Jugador 1","Jugador 2"};
        String ftx[]= {"X","O"};
        int gnom[]= {0,0};
        int x=0;
        introduirValors(nom,ftx);
        regles();
        netejaArray(p);
        imprimirPlantilla(p);
        //rondes
        while(true) {
            int a = turn(nom,x);
            //Comproba que no estigui usada
            while (a>10 || a<1 || p[a].equals(ftx[1]) || p[a].equals(ftx[0]) || a==0 || a==11 || a==12 || a==10 || a==13){
                if (a<10 || a>1) {
					if (a==0 || a==11 || a==12 || a==10 ){
                        a = options(a,p,nom,gnom,x,ftx);
                    }
                    if (a==13){
						Partida partida = recuperarEstado(p,nom,ftx,gnom,x);
						if(partida!=null){
							
							for(int i=1;i<=9;i++) {
								p[i] = partida.getP()[i];
							}
							ftx= partida.getFtx();	
							gnom = partida.getGnom();
							nom = partida.getNom();
							x = partida.getX();
							imprimirPlantilla(p);
							a = turn(nom,x);
						}
						else{
							System.out.print("[Error] ");
						}
					}
                    else {
                        System.out.print("[Error] ");
                        a = turn(nom,x);
                    }

                }

            }
            netejaPantalla();
            p[a]=ftx[x];
            imprimirPlantilla(p);           
            guanyaJugadorCheck(p,nom,gnom,x);
            x = changePlayer(x);
        }
    }
}
