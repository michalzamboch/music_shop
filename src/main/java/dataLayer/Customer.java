package dataLayer;

import java.io.Serializable;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Customer implements Person, Identifiable, Serializable, Observable {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;

	public Customer(int id, String firstName, String lastName, String email, String username, String password) {
		this(firstName, lastName, email, username, password);
		this.id = id;
	}

	public Customer(int id, Customer customer) {
		this.id = id;
		this.firstName = customer.firstName;
		this.lastName = customer.lastName;
		this.email = customer.email;
		this.username = customer.username;
		this.password = customer.password;
	}

	public Customer(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
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
		return Integer.toString(id) + ". " + this.username + ": " + this.firstName + " " + this.lastName;
	}

	@Override
	public void addListener(InvalidationListener listener) {

	}

	@Override
	public void removeListener(InvalidationListener listener) {

	}

	@Override
	public String toStringAll() {
		return Integer.toString(id) + ". " + this.username + ": " + this.firstName + " " + this.lastName + " "
				+ this.email + " heslo: " + this.password;
	}
}
