import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*JDBC_II_1_EX2
 * 
 * Genera un programa que devuelva la clave primaria de la tabla
departamentos y la clave ajena que apunta a la tabla departamentos. NOTA:
revisa que estan creadas las claves; tanto la primaria como la foranea.
 * 
 */
public class JDBC_II_1_EX2 {
	public static void main (String [] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection
					("jdbc:mysql://192.168.56.10/ejemplo","austria","123");
			DatabaseMetaData dbmd = conexion.getMetaData();

			ResultSet resul = dbmd.getPrimaryKeys("ejemplo",null,"depart");
            while (resul.next()) {
                System.out.println("Clave primaria: " + resul.getString("COLUMN_NAME"));
            }
            resul = dbmd.getExportedKeys("ejemplo",null,"depart");
            while (resul.next()) {
                System.out.println("Clave externa: " + resul.getString("FKCOLUMN_NAME"));
            }
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}
