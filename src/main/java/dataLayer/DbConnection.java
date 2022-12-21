package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

public class DbConnection {

	public DbConnection() {
		System.setProperty("derby.language.sequence.preallocator", "SequencePreallocator");
		System.setProperty("derby.language.sequence.preallocator", "1");
	}

	public String getDriverClazzName() {
		return "org.apache.derby.jdbc.EmbeddedDriver";
	}

	public Connection getConnection(String dbName) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		System.out.println("connected " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());

		return DriverManager.getConnection("jdbc:derby:" + dbName + ";create=true");
	}

	public void handleExceptionForCreation(SQLException e) {
		// table already exist
		if (Objects.equals(e.getSQLState(), "X0Y32")) {
			return;
		}
		e.printStackTrace();
	}
}
