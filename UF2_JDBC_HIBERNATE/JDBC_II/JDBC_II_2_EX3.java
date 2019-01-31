/*JDBC_II_2_EX3
 * 
 * Crea un programa Java que inserte un empleado en la tabla emple, el
programa recibe del usuario los valores a insertar. Los argumentos que
recibe son: EMP_NO, APELLIDO, OFICIO, DIR, SALARIO, COMISION,
DEPT_NO. Antes de insertar se deben realizar una serie de comprobaciones:
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.sql.*;
public class JDBC_II_2_EX3 {
	public static void main (String[] args) throws IOException{
		ArrayList<Integer> departamentoList= new ArrayList<Integer>();
		ArrayList<Integer> empleadoList= new ArrayList<Integer>();
		ArrayList<Integer> directorList= new ArrayList<Integer>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
		System.out.println("Introduce EMP_NO:");
		int EMP_NO_INT = Integer.parseInt(reader.readLine());
		System.out.println("Introduce APELLIDO:");
		String APELLIDO = reader.readLine();
		System.out.println("Introduce OFICIO:");
		String OFICIO = reader.readLine();
		System.out.println("Introduce DIR:");
		int DIR_INT =  Integer.parseInt(reader.readLine());
		System.out.println("Introduce SALARIO:");
		int SALARIO =  Integer.parseInt(reader.readLine());
		System.out.println("Introduce COMISION:");
		int COMISION =  Integer.parseInt(reader.readLine());
		System.out.println("Introduce DEPT_NO:");
		int DEPT_NO_INT =  Integer.parseInt(reader.readLine());
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion=DriverManager.getConnection
					("jdbc:mysql://192.168.56.10/ejemplo","austria","123");
			Statement sentencia =conexion.createStatement();
			String sql = "SELECT DEPT_NO,EMP_NO,DIR from emple";
			ResultSet result = sentencia.executeQuery(sql);
	
			while (result.next()){
				departamentoList.add(result.getInt(1));
				empleadoList.add(result.getInt(2));
				directorList.add(result.getInt(3));
			}
			if (departamentoList.contains(DEPT_NO_INT)){
				if (!empleadoList.contains(EMP_NO_INT)){
					if(SALARIO>0){
						if(directorList.contains(DIR_INT)){
							if(APELLIDO!=null && OFICIO!=null){
								String sql2 = String.format("INSERT INTO emple VALUES (%s, '%s', '%s','%s','%s', %s, %s, %s)", EMP_NO_INT, APELLIDO, OFICIO, DIR_INT, "1990-01-01", SALARIO, COMISION, DEPT_NO_INT);				
								Statement sentencia2 = conexion.createStatement();
								sentencia2.executeUpdate(sql2);
							}else{
								System.out.print("El apellido o oficio no pueden ser  null");
							}
						}else{
							System.out.print("No existe el DIR");
						}
					}else{
						System.out.print("El salario ha de ser mayor que 0");
					}	
				}else{
					System.out.print("Ya existe el EMP_NO");
				}
			}else{
				System.out.print("No existe el DEPT_NO");
			}
			sentencia.close();
		}catch (ClassNotFoundException cn) {cn.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
	}
}