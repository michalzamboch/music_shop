package dataLayer;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;

public class PurchachesDAO extends DbGeneric implements DAO<Purchaches> {
	private static final String INSERT_SQL = "INSERT INTO Prodej (datum_cas, typ_prodeje, id_zakaznik, id_zamestnanec, id_platba, id_zbozi, pocet) VALUES (?,?,?,?,?,?,?)";
	private static final String SELECT_SQL = "SELECT id_prodej, datum_cas, typ_prodeje, id_zakaznik, id_zamestnanec, id_platba, id_zbozi, pocet FROM Prodej";
	private static final String SELECT_SQL_WHERE = "SELECT datum_cas, typ_prodeje, id_zakaznik, id_zamestnanec, id_platba, id_zbozi, pocet FROM Prodej WHERE id_prodej = ?";
	private static final String UPDATE_SQL = "UPDATE Prodej set datum_cas = ?, typ_prodeje = ?, id_zakaznik = ?, id_zamestnanec = ?, id_platba = ?, id_zbozi = ?, pocet = ? WHERE id_prodej = ?";
	private static final String DELETE_SQL = "DELETE FROM Prodej WHERE id_prodej = ?";
	private static final String CREATE_SQL = "CREATE TABLE Prodej("
			+ " id_prodej INT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),"
			+ " datum_cas TIMESTAMP NOT NULL,"
			+ " typ_prodeje VARCHAR(100),"
			+ " id_zakaznik INT,"
			+ " id_zamestnanec INT,"
			+ " id_platba INT,"
			+ " id_zbozi INT,"
			+ " pocet INT,"
			+ " PRIMARY KEY(id_prodej),"
			+ " CONSTRAINT id_zakaznik_FK FOREIGN KEY (id_zakaznik) REFERENCES Zakaznik(id_zakaznik),"
			+ " CONSTRAINT id_zamestnanec_FK FOREIGN KEY (id_zamestnanec) REFERENCES Zamestnanec(id_zamestnanec),"
			+ " CONSTRAINT id_platba_FK FOREIGN KEY (id_platba) REFERENCES Platba(id_platba),"
			+ " CONSTRAINT id_zbozi_FK FOREIGN KEY (id_zbozi) REFERENCES Zbozi(id_zbozi)" + ")";
	private static final String DROP_SQL = "ALTER TABLE Prodej DROP CONSTRAINT id_zakaznik_FK"
			+ " ALTER TABLE Prodej DROP CONSTRAINT id_zamestnanec_FK"
			+ " ALTER TABLE Prodej DROP CONSTRAINT id_platba_FK" + " ALTER TABLE Prodej DROP CONSTRAINT id_zbozi_FK"
			+ " DROP TABLE Prodej";

	/*
	 * id_prodej, datum_cas, typ_prodeje, id_zakaznik, id_zamestnanec, id_platba
	 */

	public PurchachesDAO() {
		super(CREATE_SQL, DELETE_SQL, DROP_SQL);
	}

	@Override
	public Purchaches selectBy(int id) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_SQL_WHERE)) {
			/*
			 * id, timestamp, purchachesType, id_zakaznik, id_zamestnanec, id_platba
			 */

			ps.setInt(1, id);
			ps.execute();

			ResultSet resultSet = ps.getResultSet();
			resultSet.next();

			Purchaches result = new Purchaches(id, resultSet.getTimestamp(1), resultSet.getString(2),
					resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
					resultSet.getInt(7));

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Collection<Purchaches> getAll() {
		checkConnection();
		Collection<Purchaches> result = new Vector<>();

		try (Statement stm = conn.createStatement()) {
			/*
			 * id, timestamp, purchachesType, id_zakaznik, id_zamestnanec, id_platba
			 */

			stm.execute(SELECT_SQL);
			ResultSet resultSet = stm.getResultSet();

			while (resultSet.next()) {
				Purchaches temp = new Purchaches(resultSet.getInt(1), resultSet.getTimestamp(2), resultSet.getString(3),
						resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7),
						resultSet.getInt(8));

				result.add(temp);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Purchaches insertRow(Purchaches obj) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			// datum_cas, typ_prodeje, id_zakaznik, id_zamestnanec, id_platba
			ps.setTimestamp(1, obj.getTimestamp());
			ps.setString(2, obj.getPurchachesType());
			ps.setInt(3, obj.getId_zakaznik());
			ps.setInt(4, obj.getId_zamestnanec());
			ps.setInt(5, obj.getId_platba());
			ps.setInt(6, obj.getId_zbozi());
			ps.setInt(7, obj.getCount());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			return new Purchaches(id, obj);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateRow(Purchaches obj) {
		checkConnection();
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

			/*
			 * UPDATE Prodej set datum_cas = ?, typ_prodeje = ?, id_zakaznik = ?,
			 * id_zamestnanec = ?, id_platba = ? WHERE id_prodej = ?
			 */
			ps.setTimestamp(1, obj.getTimestamp());
			ps.setString(2, obj.getPurchachesType());
			ps.setInt(3, obj.getId_zakaznik());
			ps.setInt(4, obj.getId_zamestnanec());
			ps.setInt(5, obj.getId_platba());
			ps.setInt(6, obj.getId_zbozi());
			ps.setInt(7, obj.getCount());
			ps.setInt(8, obj.getId());

			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
