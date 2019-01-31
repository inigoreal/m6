import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
/*JDBC_II_2_EX1
 * 
 * Realiza un programa en Java que suba el salario a los empleados de un
departamento. El programa recibirá el número de departamento y el
incremento.
 * 
 */
public class JDBC_II_2_EX1 {
	public static void main (String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Introduce DEPT_NO:");
		int DEPT_NO_INT = Integer.parseInt(reader.readLine());
		System.out.println("Introduce incremento:");
		int incremento = Integer.parseInt(reader.readLine());
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection
					("jdbc:mysql://192.168.56.10/ejemplo","austria","123");
			Statement sentencia =conexion.createStatement();
			
			String sql2 = "UPDATE emple SET salario = salario +"+incremento+" where DEPT_NO="+DEPT_NO_INT+";";
			sentencia.executeUpdate(sql2);

			sentencia.close();
			
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}