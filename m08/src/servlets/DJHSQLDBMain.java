package servlets;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

public class DJHSQLDBMain {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {

			HsqlProperties hsqlProperties = new HsqlProperties();
			hsqlProperties.setProperty("server.database.0",
					"file:C:/home/juan/hsqldb-2.5.0/DatoJava/HSQLDB/miBaseDeDatos");
			hsqlProperties.setProperty("server.dbname.0", "mdb");

			Server server = new Server();
			server.setProperties(hsqlProperties);
			server.setTrace(false);
			System.out.println(server.getState() + " " + server.getStateDescriptor());
			server.start();

			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mdb", "SA", "");

			statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO usuario VALUES ('1', 'pedro', 'suarez', 'p.suarez', '12345');");
			statement.executeUpdate("INSERT INTO usuario VALUES ('2', 'pablo', 'martinez', 'p.martinez', '12345');");
			statement.executeUpdate("INSERT INTO usuario VALUES ('3', 'pepe', 'peres', 'p.perez', '12345');");

			resultSet = statement.executeQuery("SELECT * FROM usuario");

			while (resultSet.next()) {
				System.out.println("---Usuario---");
				System.out.println("ID: " + resultSet.getString(1));
				System.out.println("Nombre: " + resultSet.getString(2));
				System.out.println("Apellido: " + resultSet.getString(3));
			}

			// Opcional para detener el Servidor
			statement.executeQuery("SHUTDOWN COMPACT");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}