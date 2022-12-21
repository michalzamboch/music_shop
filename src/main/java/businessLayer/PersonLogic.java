package businessLayer;

import java.util.Vector;

import dataLayer.Customer;
import dataLayer.CustomerDAO;
import dataLayer.Employee;
import dataLayer.EmployeeDAO;
import dataLayer.Person;

public class PersonLogic {
	public static Person person;

	public PersonLogic() {

	}

	/*-------------------------------------------------------*/

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		PersonLogic.person = person;
	}

	/*-------------------------------------------------------*/

	public int login(String userName, String password) {
		int returnVal = 3;

		CustomerDAO cuDao = new CustomerDAO();
		EmployeeDAO emDao = new EmployeeDAO();

		Vector<Customer> allCustomers = (Vector<Customer>) cuDao.getAll();
		//allCustomers.forEach(System.out::println);
		Vector<Employee> allEmployees = (Vector<Employee>) emDao.getAll();
		//allEmployees.forEach(System.out::println);

		for (var person : allCustomers) {
			if (person.getUsername().equals(userName)) {
				if (person.getPassword().equals(password)) {
					PersonLogic.person = person;
					returnVal = 0;
				} else {
					returnVal = 4;
				}
			}
		}

		for (var person : allEmployees) {
			if (person.getUsername().equals(userName)) {
				if (person.getPassword().equals(password)) {
					PersonLogic.person = person;
					if (person.getPosition().equals("vedouci")) {
						returnVal = 2;
					} else {
						returnVal = 1;
					}
				} else {
					returnVal = 4;
				}
			}
		}

		cuDao.close();
		emDao.close();

		return returnVal;
	}

	/*-------------------------------------------------------*/

	public int register(String firstName, String lastName, String username, String password, String repeatedPassword,
			String email) {

		int returnVal = checkInfo(username, password, repeatedPassword);
		System.out.println(returnVal);
		if (returnVal != 0) {
			return returnVal;
		}

		CustomerDAO dao = new CustomerDAO();
		var customer = new Customer(firstName, lastName, email, username, password);

		customer.print();
		dao.insertRow(customer);
		dao.close();

		return 0;
	}

	private int checkInfo(String username, String password, String repeatedPassword) {
		int returnVal = 0;

		if (password.equals(repeatedPassword) == false) {
			System.out.println("Heslo se nerovna");
			returnVal = 1;
		}

		if (password.length() < 5) {
			System.out.println("Heslo kratke");
			returnVal = 2;
		}

		CustomerDAO daoCu = new CustomerDAO();
		EmployeeDAO daoEm = new EmployeeDAO();

		if (daoCu.userNameExists(username) == true && daoEm.userNameExists(username) == true) {
			System.out.println("Username uz existuje");
			returnVal = 3;
		}

		daoEm.close();
		daoCu.close();
		return returnVal;
	}

	/*-------------------------------------------------------*/

	public int registerEmployee(String firstName, String lastName, String username, String password,
			String repeatedPassword, String email, String pozice, int wage) {

		int returnVal = checkInfo(username, password, repeatedPassword);
		System.out.println(returnVal);
		if (returnVal != 0) {
			return returnVal;
		}

		EmployeeDAO dao = new EmployeeDAO();
		var employee = new Employee(firstName, lastName, email, username, password, pozice, wage);

		employee.printAll();
		dao.insertRow(employee);
		dao.close();

		return 0;
	}

}
