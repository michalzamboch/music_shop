package businessLayer;

import dataLayer.*;
import dataLayer.Record;

public class PurchachesLogic {

	public PurchachesLogic() {

	}

	public int sell(Customer customer, Employee employee, Record record) {

		int retVal = 0;

		customer.printAll();
		employee.printAll();
		record.printAll();

		Payment payment = Payment.paymentNow(record.getPrice());
		payment.printAll();

		PaymentDAO paDao = new PaymentDAO();
		payment = paDao.insertRow(payment);

		var purchaches = Purchaches.PurchachesNow(customer.getId(), employee.getId(), payment.getId(), record.getId(),
				1);
		// purchaches.printAll();

		var puDao = new PurchachesDAO();
		puDao.insertRow(purchaches);

		purchaches.setComposition("Nahravka" + " \'" + record.getArtist() + " " + record.getTitle() + "\' prodana:"
				+ " \'" + customer.getUsername() + "\' zamestnancem" + " \'" + employee.getUsername() + "\'");

		purchaches.printComposition();

		puDao.getAll().forEach(System.out::println);

		puDao.close();
		paDao.close();
		return retVal;
	}

	/*------------------------------------------------------*/

	public int buy(Customer customer, Record record) {

		int retVal = 0;
		EmployeeDAO emDao = new EmployeeDAO();
		Employee employee = emDao.selectBy(1);

		employee.printAll();
		customer.printAll();
		record.printAll();

		this.sell(customer, employee, record);

		emDao.close();
		return retVal;
	}
}
