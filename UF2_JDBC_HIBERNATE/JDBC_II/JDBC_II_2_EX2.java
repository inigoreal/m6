/*JDBC_II_2_EX2
 * 
 * Realiza un programa que cree una vista (de nombre “totales”) que contenga
por cada departamento el número de departamento, el nombre, el número
de empleados que tiene y el salario medio.
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
public class JDBC_II_2_EX2 {
	public static void main (String[] args) throws IOException{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection
					("jdbc:mysql://192.168.56.10/ejemplo","austria","123");
			String sql = String.format("CREATE or replace VIEW totales AS select depart.dept_no, dnombre, count(emp_no), avg(salario) from emple,depart where depart.dept_no=emple.dept_no;");
			Statement sentencia = conexion.createStatement();
			sentencia.close();
			conexion.close();
		}
		catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}