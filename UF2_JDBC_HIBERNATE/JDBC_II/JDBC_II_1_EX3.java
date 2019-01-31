import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/*JDBC_II_1_EX3
 * 
 * Busca informacion sobre la interfaz ResultSetMetaData y realiza un
programa utilizando dicha interfaz que obtenga el numero de columnas y el
tipo de columnas devueltos por la consulta SELECT * FROM
DEPARTAMENTOS.
 * 
 */
public class JDBC_II_1_EX3 {
	public static void main (String [] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection
					("jdbc:mysql://192.168.56.10/ejemplo","austria","123");
			ResultSet resul = conexion.createStatement().executeQuery("select * from depart;");
			ResultSetMetaData rsmd = resul.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			int columnas = rsmd.getColumnCount();
			System.out.println("Cantidad de columnas: "+ numberOfColumns);
			for (int i=1;i<=columnas;i++) {
				
				String tipo = rsmd.getColumnTypeName(i);
				
				System.out.printf("Tipo: %s",tipo+" ");
			}
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}
