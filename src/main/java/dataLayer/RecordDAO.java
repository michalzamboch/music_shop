package dataLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
/*
private int idRecord;
private int idArtist;
private int idRecordCompany;
private String title;
private int price;
private int shopMarge;
private String carrierType;
private String recordType;
private String decription;
private String genre;
private int realeaseYear;
private int reordLength;
*/
import java.util.Vector;

public class RecordDAO extends DbGeneric implements DAO<Record> {
	private static final String INSERT_SQL = "INSERT INTO Zbozi (umelec, vydavatel, nazev,"
			+ "cena, obchodni_marze, pocet, typ_nosice, typ_nahravky,"
			+ "popis, zanr, rok_vydani, delka_stopaze ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_SQL = "SELECT id_zbozi, umelec, vydavatel, nazev,"
			+ "cena, obchodni_marze, pocet, typ_nosice, typ_nahravky,"
			+ "popis, zanr, rok_vydani, delka_stopaze FROM Zbozi";
	private static final String SELECT_SQL_WHERE = "SELECT id_zbozi, umelec, vydavatel, nazev,"
			+ "cena, obchodni_marze, pocet, typ_nosice, typ_nahravky,"
			+ "popis, zanr, rok_vydani, delka_stopaze FROM Zbozi WHERE id_zbozi = ?";
	private static final String UPDATE_SQL = "UPDATE Zbozi umelec = ?, vydavatel = ?, nazev, = ?"
			+ "cena = ?, obchodni_marze = ?, pocet = ?, typ_nosice = ?, typ_nahravky = ?"
			+ "popis = ?, zanr = ?, rok_vydani = ?, delka_stopaze = ? WHERE id_zbozi = ?";
	private static final String DELETE_SQL = "DELETE FROM Zbozi WHERE id_zbozi = ?";
	private static final String CREATE_SQL = "CREATE TABLE Zbozi ("
			+ "    id_zbozi       INT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),"
			+ "    umelec      	  VARCHAR(100)," + "    vydavatel      VARCHAR(100),"
			+ "    nazev          VARCHAR(255) NOT NULL," + "    cena           FLOAT(2) NOT NULL,"
			+ "    obchodni_marze INT NOT NULL," + "    pocet 		  INT NOT NULL,"
			+ "    typ_nosice     VARCHAR(100) NOT NULL," + "    typ_nahravky   VARCHAR(50) NOT NULL,"
			+ "    popis          VARCHAR(512) NOT NULL," + "    zanr           VARCHAR(100),"
			+ "    rok_vydani     INT," + "    delka_stopaze  INT," + "    PRIMARY KEY (id_zbozi)" + ")";

	private static final String DROP_SQL = "DROP TABLE Zbozi";

	/*-------------------------------------------------------------------------------------*/

	public RecordDAO() {
		super(CREATE_SQL, DELETE_SQL, DROP_SQL);
	}

	@Override
	public Record selectBy(int id) {
		checkConnection();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_SQL_WHERE)) {

			ps.setInt(1, id);
			ps.execute();

			ResultSet resultSet = ps.getResultSet();
			resultSet.next();

			Record result = new Record(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getFloat(5), resultSet.getInt(6), resultSet.getInt(7),
					resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11),
					resultSet.getInt(12), resultSet.getInt(13));

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Collection<Record> getAll() {
		/*
		 * id_zbozi, umelec, vydavatel, nazev," +
		 * "cena, obchodni_marze, pocet, typ_nosice, typ_nahravky," + "popis, zanr,
		 * rok_vydani, delka_stopaze
		 */

		checkConnection();
		Collection<Record> result = new Vector<>();

		try (Statement stm = conn.createStatement()) {
			stm.execute(SELECT_SQL);
			ResultSet resultSet = stm.getResultSet();

			while (resultSet.next()) {
				Record temp = new Record(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getFloat(5), resultSet.getInt(6), resultSet.getInt(7),
						resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
						resultSet.getString(11), resultSet.getInt(12), resultSet.getInt(13));
				result.add(temp);
			}

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Record insertRow(Record obj) {
		/*
		 * umelec, vydavatel, nazev, cena, obchodni_marze, pocet, typ_nosice,
		 * typ_nahravky, popis, zanr, rok_vydani, delka_stopaze
		 */

		checkConnection();
		try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, obj.getArtist());
			ps.setString(2, obj.getRecordCompany());
			ps.setString(3, obj.getTitle());
			ps.setFloat(4, obj.getPrice());
			ps.setInt(5, obj.getShopMarge());
			ps.setInt(6, obj.getCount());
			ps.setString(7, obj.getCarrierType());
			ps.setString(8, obj.getRecordType());
			ps.setString(9, obj.getDecription());
			ps.setString(10, obj.getGenre());
			ps.setInt(11, obj.getRealeaseYear());
			ps.setInt(12, obj.getReordLength());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);

			Record result = new Record(id, obj);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateRow(Record obj) {
		System.out.println("Metoda updateRow neni inicializovana...");

		checkConnection();
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

			ps.setString(1, obj.getArtist());
			ps.setString(2, obj.getRecordCompany());
			ps.setString(3, obj.getTitle());
			ps.setFloat(4, obj.getPrice());
			ps.setInt(5, obj.getShopMarge());
			ps.setInt(6, obj.getCount());
			ps.setString(7, obj.getCarrierType());
			ps.setString(8, obj.getRecordType());
			ps.setString(9, obj.getDecription());
			ps.setString(10, obj.getGenre());
			ps.setInt(11, obj.getRealeaseYear());
			ps.setInt(12, obj.getReordLength());
			ps.setInt(13, obj.getId());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
