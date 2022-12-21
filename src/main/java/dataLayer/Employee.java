package dataLayer;

import java.io.Serializable;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Employee implements Person, Identifiable, Serializable, Observable {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String position;
	private int wage;

	public Employee(int id, String firstName, String lastName, String email, String username, String password,
			String position, int wage) {
		this(firstName, lastName, email, username, password, position, wage);
		this.id = id;
	}

	public Employee(Employee em) {
		this(em.id, em.firstName, em.lastName, em.email, em.username, em.password, em.position, em.wage);
	}

	public Employee(int id, Employee em) {
		this(id, em.firstName, em.lastName, em.email, em.username, em.password, em.position, em.wage);
	}

	public Employee(String firstName, String lastName, String email,
			String username, String password, String position, int wage) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.position = position;
		this.wage = wage;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return Integer.toString(id) + ". " + this.position + "(" + this.wage + " Kc) " + this.username + ": "
				+ this.firstName + " " + this.lastName;
	}

	@Override
	public String toStringAll() {
		return Integer.toString(id) + ". " + this.position + "(" + this.wage + " Kc) " + this.username + ": "
				+ this.firstName + " " + this.lastName + " " + this.email + " heslo: " + this.password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub

	}

}
