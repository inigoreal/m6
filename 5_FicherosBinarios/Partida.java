
import java.io.Serializable;
public class Partida implements Serializable {
    private String[] p;
    private int x;
    private int[] gnom;
    private String[] nom;
    private String[] ftx;
    
    public Partida(String[] p, int x, int[] gnom, String[] nom, String[] ftx) {
        super();
        this.p = p;
        this.x = x;
        this.gnom = gnom;
        this.nom = nom;
        this.ftx = ftx;
    }
    public String[] getP() {
        return p;
    }
    public void setP(String[] p) {
        this.p = p;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int[] getGnom() {
        return gnom;
    }
    public void setGnom(int[] gnom) {
        this.gnom = gnom;
    }
    public String[] getNom() {
        return nom;
    }
    public void setNom(String[] nom) {
        this.nom = nom;
    }
    public String[] getFtx() {
        return ftx;
    }
    public void setFtx(String[] ftx) {
        this.ftx = ftx;
    }
    
}

