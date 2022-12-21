package businessLayer;

import java.util.ArrayList;
import java.util.Collection;

import dataLayer.Customer;
import dataLayer.CustomerDAO;

public class CustomerLogic {

	public CustomerLogic() {

	}

	public Collection<Customer> loadAll() {
		CustomerDAO dao = new CustomerDAO();

		Collection<Customer> customers = dao.getAll();
		//customers.forEach(System.out::println);

		dao.close();

		return customers;
	}

	public boolean delete(int id) {

		CustomerDAO dao = new CustomerDAO();

		boolean done = dao.deleteRow(id);
		//dao.getAll().forEach(System.out::println);

		dao.close();
		return done;
	}

	public Collection<Customer> search(String root) {
		CustomerDAO dao = new CustomerDAO();

		Collection<Customer> tempCustomers = dao.getAll();
		ArrayList<Customer> listCustomers = new ArrayList<Customer>(tempCustomers);
		ArrayList<Customer> Customers = new ArrayList<Customer>();

		for (var customer : listCustomers) {
			if (customer.getFirstName().equals(root) || customer.getLastName().equals(root)
					|| customer.getUsername().equals(root)) {
				Customers.add(customer);
			}
		}

		dao.close();
		return Customers;

	}
}
