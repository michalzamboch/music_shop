package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

public class PaymentDAO extends DbGeneric implements DAO<Payment> {

	private static final String INSERT_SQL = "INSERT INTO Platba"
			+ " (castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti, overovaci_kod)"
			+ " VALUES (?,?,?,?,?,?)";
	private static final String SELECT_SQL = "SELECT id_platba, castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti, overovaci_kod FROM Platba";
	private static final String SELECT_SQL_WHERE = "SELECT castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti, overovaci_kod"
			+ " FROM Platba WHERE id_platba = ?";
	private static final String UPDATE_SQL = "UPDATE Platba"
			+ " set castka = ?, datum_cas = ?, zachovat_udaje = ?, cislo_karty = ?, datum_platnosti = ?, overovaci_kod = ?"
			+ " WHERE id_platba = ?";
	private static final String DELETE_SQL = "DELETE FROM Platba WHERE id_platba = ?";
	private static final String CREATE_SQL = "CREATE TABLE Platba("
			+ "	id_platba INT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),"
			+ " castka FLOAT NOT NULL," + "	datum_cas TIMESTAMP NOT NULL," + " zachovat_udaje BOOLEAN NOT NULL,"
			+ " cislo_karty INT," + " datum_platnosti TIMESTAMP," + " overovaci_kod INT," + " PRIMARY KEY(id_platba)"
			+ ")";
	private static final String DROP_SQL = "DROP TABLE Platba";

	public PaymentDAO() {
		super(CREATE_SQL, DELETE_SQL, DROP_SQL);
	}

	@Override
	public Payment selectBy(int id) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_SQL_WHERE)) {
			/*
			 * SELECT castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti,
			 * overovaci_kod FROM Platba WHERE id_platba = ?
			 */
			ps.setInt(1, id);
			ps.execute();

			ResultSet resultSet = ps.getResultSet();
			resultSet.next();

			Payment.CreditCard crCard = new Payment.CreditCard(resultSet.getInt(4), resultSet.getTimestamp(5),
					resultSet.getInt(6));
			Payment payment = new Payment(id, resultSet.getFloat(1), resultSet.getTimestamp(2), resultSet.getBoolean(3),
					crCard);

			return payment;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Collection<Payment> getAll() {
		checkConnection();
		Collection<Payment> result = new Vector<>();

		try (Statement stm = conn.createStatement()) {
			/*
			 * id_platba, castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti,
			 * overovaci_kod
			 */
			stm.execute(SELECT_SQL);
			ResultSet resultSet = stm.getResultSet();

			while (resultSet.next()) {
				Payment.CreditCard crCard = new Payment.CreditCard(resultSet.getInt(5), resultSet.getTimestamp(6),
						resultSet.getInt(7));

				Payment temp = new Payment(resultSet.getInt(1), resultSet.getFloat(2), resultSet.getTimestamp(3),
						resultSet.getBoolean(4), crCard);

				result.add(temp);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Payment insertRow(Payment obj) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			// castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti,
			// overovaci_kod
			ps.setFloat(1, obj.getAmount());
			ps.setTimestamp(2, obj.getTimestamp());
			ps.setBoolean(3, obj.getSaveInfo());
			ps.setInt(4, obj.getCreditCard().getCardNumber());
			ps.setTimestamp(5, obj.getCreditCard().getValidationDate());
			ps.setInt(6, obj.getCreditCard().getSecurityCode());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return new Payment(id, obj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateRow(Payment obj) {
		checkConnection();
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

			// castka, datum_cas, zachovat_udaje, cislo_karty, datum_platnosti,
			// overovaci_kod
			ps.setFloat(1, obj.getAmount());
			ps.setTimestamp(2, obj.getTimestamp());
			ps.setBoolean(3, obj.getSaveInfo());
			ps.setInt(4, obj.getCreditCard().getCardNumber());
			ps.setTimestamp(5, obj.getCreditCard().getValidationDate());
			ps.setInt(6, obj.getCreditCard().getSecurityCode());
			ps.setInt(7, obj.getId());

			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
