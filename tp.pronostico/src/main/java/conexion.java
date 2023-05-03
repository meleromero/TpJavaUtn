
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class conexion {
	        public static void main(String[] args) {
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticosdeportivos", "root", "");
			
				System.out.println("Conexión exitosa");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("error al cargar el controlador");
				e.printStackTrace();
			}catch(SQLException e) {
				System.out.println("Error en la conexión");
				e.printStackTrace();
			}
	        }
	}

