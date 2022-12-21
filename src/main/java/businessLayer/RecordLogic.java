package businessLayer;

import java.util.ArrayList;
import java.util.Collection;

import dataLayer.Customer;
import dataLayer.Employee;
import dataLayer.Purchaches;
import dataLayer.PurchachesDAO;
import dataLayer.Record;
import dataLayer.RecordDAO;

public class RecordLogic {

	public RecordLogic() {

	}

	public Collection<Record> loadAll() {
		RecordDAO dao = new RecordDAO();

		Collection<Record> records = dao.getAll();
		records.forEach(System.out::println);

		dao.close();

		return records;
	}

	public boolean delete(int id) {
		RecordDAO dao = new RecordDAO();

		boolean done = dao.deleteRow(id);
		dao.getAll().forEach(System.out::println);

		dao.close();
		return done;
	}

	public int write(String artist, String recordCompany, String recordName, String price, String marge, String count,
			String carrierType, String recordType, String decription, String genre, String releaseYear, String length) {

		int retVal = 0;

		var record = new Record(artist, recordCompany, recordName, Float.parseFloat(price), Integer.parseInt(marge),
				Integer.parseInt(count), carrierType, recordType, decription, genre, Integer.parseInt(releaseYear),
				Integer.parseInt(length));

		if (record == null) {
			return 1;
		}

		RecordDAO dao = new RecordDAO();
		dao.insertRow(record);
		dao.getAll().forEach(System.out::println);
		dao.close();

		// record.print();
		return retVal;
	}

	public Collection<Record> search(String root) {
		RecordDAO dao = new RecordDAO();

		Collection<Record> tempRecords = dao.getAll();
		ArrayList<Record> listRecords = new ArrayList<Record>(tempRecords);
		ArrayList<Record> Records = new ArrayList<Record>();

		for (var record : listRecords) {
			if (record.getArtist().equals(root) || record.getTitle().equals(root)) {
				Records.add(record);
			}
		}

		Records.forEach(System.out::println);

		dao.close();
		return Records;
	}

	public Collection<Record> loadMy(Customer customer) {

		customer.printAll();
		int id = customer.getId();
		var puDao = new PurchachesDAO();
		var reDao = new RecordDAO();

		Collection<Purchaches> allPurchaches = puDao.getAll();
		ArrayList<Record> Records = new ArrayList<Record>();

		for (var purchaches : allPurchaches) {
			if (purchaches.getId_zakaznik() == id) {
				var record = reDao.selectBy(purchaches.getId_zbozi());
				Records.add(record);
			}
		}

		Records.forEach(System.out::println);

		puDao.close();
		reDao.close();
		return Records;
	}

}
