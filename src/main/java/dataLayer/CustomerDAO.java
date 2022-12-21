package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

public class CustomerDAO extends DbGeneric implements DAO<Customer>, AutoCloseable {

	private static final String INSERT_SQL = "INSERT INTO Zakaznik(krestni_jmeno, prijmeni, email, username, password) values (?,?,?,?,?)";
	private static final String SELECT_SQL = "SELECT id_zakaznik, krestni_jmeno, prijmeni, email, username, password FROM Zakaznik";
	private static final String SELECT_SQL_WHERE = "SELECT krestni_jmeno, prijmeni, email, username, password FROM Zakaznik WHERE id_zakaznik = ?";
	private static final String UPDATE_SQL = "UPDATE Zakaznik set krestni_jmeno = ?, prijmeni = ?, email = ?, username = ?, password = ? WHERE id_zakaznik = ?";
	private static final String DELETE_SQL = "DELETE FROM Zakaznik WHERE id_zakaznik = ?";
	private static final String CREATE_SQL = "CREATE TABLE Zakaznik ("
			+ "   id_zakaznik INT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),"
			+ "   krestni_jmeno VARCHAR(50) NOT NULL," + "   prijmeni VARCHAR(50) NOT NULL,"
			+ "   email VARCHAR(250) NOT NULL," + "   username VARCHAR(50) NOT NULL,"
			+ "   password VARCHAR(50) NOT NULL," + "   PRIMARY KEY(id_zakaznik)" + ")";
	private static final String DROP_SQL = "DROP TABLE Zakaznik";

	public CustomerDAO() {
		super(CREATE_SQL, DELETE_SQL, DROP_SQL);
	}

	/*-------------------------------------------------------------------------------------*/

	@Override
	public Customer selectBy(int id) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_SQL_WHERE)) {
			// SELECT krestni_jmeno, prijmeni, email, username, password FROM Zakaznik WHERE
			// id_zakaznik = ?
			ps.setInt(1, id);
			ps.execute();

			ResultSet resultSet = ps.getResultSet();
			resultSet.next();

			var customer = new Customer(id, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5));
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Collection<Customer> getAll() {
		checkConnection();
		Collection<Customer> result = new Vector<>();

		try (Statement stm = conn.createStatement()) {
			stm.execute(SELECT_SQL);
			ResultSet resultSet = stm.getResultSet();

			// SELECT id_zakaznik, krestni_jmeno, prijmeni, email, username, password FROM
			// Zakaznik
			while (resultSet.next()) {
				Customer temp = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

				result.add(temp);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean userNameExists(String username) {
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

	public boolean userExists(String username) {
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
	public Customer insertRow(Customer obj) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			// INSERT INTO Zakaznik(krestni_jmeno, prijmeni, email, username, password)
			// values (?,?,?,?,?)
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return new Customer(id, obj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateRow(Customer obj) {
		checkConnection();
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

			// "UPDATE Zakaznik set krestni_jmeno = ?, prijmeni = ?, email = ?,
			// username = ?, password = ? WHERE id_zakaznik = ?";
			ps.setString(1, obj.getFirstName());
			ps.setString(2, obj.getLastName());
			ps.setString(3, obj.getEmail());
			ps.setString(4, obj.getUsername());
			ps.setString(5, obj.getPassword());
			ps.setInt(6, obj.getId());

			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
