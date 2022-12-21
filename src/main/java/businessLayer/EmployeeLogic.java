package businessLayer;

import java.util.ArrayList;
import java.util.Collection;

import dataLayer.Employee;
import dataLayer.EmployeeDAO;

public class EmployeeLogic {

	public EmployeeLogic() {

	}

	public Collection<Employee> loadAll() {
		EmployeeDAO dao = new EmployeeDAO();

		Collection<Employee> employee = dao.getAll();
		//employee.forEach(System.out::println);

		dao.close();

		return employee;
	}

	public boolean delete(int id) {

		EmployeeDAO dao = new EmployeeDAO();

		boolean done = dao.deleteRow(id);
		//dao.getAll().forEach(System.out::println);

		dao.close();
		return done;
	}

	public Collection<Employee> search(String root) {
		EmployeeDAO dao = new EmployeeDAO();

		Collection<Employee> tempEmployees = dao.getAll();
		ArrayList<Employee> listEmployees = new ArrayList<Employee>(tempEmployees);
		ArrayList<Employee> Employees = new ArrayList<Employee>();

		for (var employee : listEmployees) {
			if (employee.getFirstName().equals(root) || employee.getLastName().equals(root)
					|| employee.getUsername().equals(root)) {
				Employees.add(employee);
			}
		}

		dao.close();
		return Employees;

	}
}
