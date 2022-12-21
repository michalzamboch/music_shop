package additional;

import dataLayer.CustomerDAO;
import dataLayer.EmployeeDAO;

public class DBwork {
	public static void test() {
		//print();
	}

	public static void print() {
		CustomerDAO cuDao = new CustomerDAO();
		EmployeeDAO emDao = new EmployeeDAO();

		cuDao.getAll().forEach(System.out::println);
		emDao.getAll().forEach(System.out::println);

		cuDao.close();
		emDao.close();
	}

	public static void genPeople(int count) {
		var customers = Generate.getRandCustomers(count);
		customers.forEach(System.out::println);
		System.out.println(customers.size());
		
		var employees = Generate.getRandEmployees(count);
		employees.forEach(System.out::println);
		System.out.println(employees.size());
	}
	
	public static void insertCustomers(int count) {
		CustomerDAO cuDao = new CustomerDAO();
		var customers = Generate.getRandCustomers(count);
		
		for(var customer : customers) {
			cuDao.insertRow(customer);
		}
		
		cuDao.close();
	}
	
	public static void insertEmployees(int count) {
		EmployeeDAO emDao = new EmployeeDAO();
		var employees = Generate.getRandEmployees(count);
		
		for(var employee : employees) {
			emDao.insertRow(employee);
		}
		
		emDao.close();
	}
}
