package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbGeneric implements AutoCloseable {
	protected Connection conn;
	private DbConnection dialect;
	private String createStmt;
	private String dropStmt;
	private String deleteStmt;

	public DbGeneric(String createStmt, String deleteStmt, String dropStmt) {
		this.createStmt = createStmt;
		this.deleteStmt = deleteStmt;
		this.dropStmt = dropStmt;

		this.dialect = new DbConnection();

		try {
			Class.forName(dialect.getDriverClazzName());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			conn = dialect.getConnection("ShopTable");
			boolean constructed = constructTable();

			if (constructed == true) {
				System.out.println("constructed");
			} else {
				System.out.println("not constructed");
			}

		} catch (SQLException e) {
			dialect.handleExceptionForCreation(e);
		}
	}

	public boolean constructTable() {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(this.createStmt);

			return true;
		} catch (SQLException e) {
			if (e.getSQLState().equals("X0Y32")) {
				System.out.println("SQLState X0Y32 - already exists");
				return false;
			}

			e.printStackTrace();
			return false;
		}
	}

	public boolean dropTable() {
		return this.execute(dropStmt);
	}

	public boolean checkConnection() {
		return checkConnection(additional.Constants.printCheck);
	}

	public boolean checkConnection(boolean print) {
		if (conn == null) {
			throw new IllegalStateException("Connection is not opened");
		} else {
			if (print == true) {
				System.out.println("Connection running");
			}
			return true;
		}
	}

	public boolean execute(String query) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute(query);
			System.out.println("Executed: " + query);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	public boolean deleteRow(Identifiable obj) {
		return deleteRow(obj.getId());
	}

	public boolean deleteRow(int id) {
		checkConnection();
		try (PreparedStatement ps = conn.prepareStatement(deleteStmt)) {

			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void close() {
		checkConnection();

		try {
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
