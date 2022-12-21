package dataLayer;

import java.sql.*;
import java.util.*;

public class EmployeeDAO extends DbGeneric implements DAO<Employee> {

	private static final String INSERT_SQL = "INSERT INTO Zamestnanec (krestni_jmeno, prijmeni, email, username, password, pozice, plat) VALUES (?,?,?,?,?,?,?)";
	private static final String SELECT_SQL = "SELECT id_zamestnanec, krestni_jmeno, prijmeni, email, username, password, pozice, plat FROM Zamestnanec";
	private static final String SELECT_SQL_WHERE = "SELECT krestni_jmeno, prijmeni, email, username, password, pozice, plat FROM Zamestnanec WHERE id_zamestnanec = ?";
	private static final String UPDATE_SQL = "UPDATE Zamestnanec set krestni_jmeno = ?, prijmeni = ?, email = ?, username = ?, password = ?, pozice = ?, plat = ? WHERE id_zamestnanec = ?";
	private static final String DELETE_SQL = "DELETE FROM Zamestnanec WHERE id_zamestnanec = ?";
	private static final String CREATE_SQL = "CREATE TABLE Zamestnanec ("
			+ "   id_zamestnanec INT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),"
			+ "   krestni_jmeno VARCHAR(50) NOT NULL," + "   prijmeni VARCHAR(50) NOT NULL,"
			+ "   email VARCHAR(250) NOT NULL," + "   username VARCHAR(50) NOT NULL,"
			+ "   password VARCHAR(50) NOT NULL," + "   pozice VARCHAR(50) NOT NULL," + "   plat INT NOT NULL,"
			+ "   PRIMARY KEY(id_zamestnanec)" + ")";
	private static final String DROP_SQL = "DROP TABLE Zamestnanec";

	public EmployeeDAO() {
		super(CREATE_SQL, DELETE_SQL, DROP_SQL);
	}

	/*-------------------------------------------------------------------------------------*/

	@Override
	public Employee selectBy(int id) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_SQL_WHERE)) {
			// "SELECT krestni_jmeno, prijmeni, email, username, password, pozice, plat FROM
			// Zamestnanec WHERE id_zamestnanec = ?";
			ps.setInt(1, id);
			ps.execute();

			ResultSet resultSet = ps.getResultSet();
			resultSet.next();

			Employee em = new Employee(id, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));

			return em;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Employee selectBy(String column, String value) {
		return null;
	}

	public Employee selectBy(String column, int value) {
		return null;
	}

	@Override
	public Collection<Employee> getAll() {
		checkConnection();
		Collection<Employee> result = new Vector<>();

		try (Statement stm = conn.createStatement()) {
			stm.execute(SELECT_SQL);
			ResultSet resultSet = stm.getResultSet();

			// SELECT id_zamestnanec, krestni_jmeno, prijmeni, email, username, password,
			// pozice, plat FROM Zamestnanec
			while (resultSet.next()) {
				Employee em = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getInt(8));
				result.add(em);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean userNameExists(String username) {
		checkConnection();

		try (Statement stm = conn.createStatement()) {
			stm.execute(SELECT_SQL);
			ResultSet resultSet = stm.getResultSet();

			while (resultSet.next()) {
				if (resultSet.getString(5).equals(username)) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Employee insertRow(Employee obj) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			// INSERT INTO Zamestnanec(krestni_jmeno, prijmeni, email, username, password,
			// pozice, plat) values (?,?,?,?,?)
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());
			ps.setString(6, obj.getPosition());
			ps.setInt(7, obj.getWage());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return new Employee(id, obj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateRow(Employee obj) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
			// UPDATE set krestni_jmeno = ?, prijmeni = ?, email = ?, username = ?, password
			// = ?, pozice = ?, plat = ? WHERE id_zamestnanec = ?
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());
			ps.setString(6, obj.getPosition());
			ps.setInt(7, obj.getWage());
			ps.setInt(8, obj.getId());

			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
