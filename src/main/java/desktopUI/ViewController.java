package desktopUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import businessLayer.CustomerLogic;
import businessLayer.EmployeeLogic;
import businessLayer.PersonLogic;
import businessLayer.PurchachesLogic;
import businessLayer.RecordLogic;

import dataLayer.Customer;
import dataLayer.Employee;
import dataLayer.Person;
import dataLayer.Record;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewController {
	private Stage stage;
	private Parent root;
	private Scene scene;
	
	private Person user;

	/*-------------------------------------------------------------------*/

	@FXML
	public Label errorText;
	@FXML
	private TextField loginTxtFLog;
	@FXML
	private TextField passwordTxtFLog;

	/*-------------------------------------------------------------------*/

	@FXML
	private TextField firstNameTxtFRe;
	@FXML
	private TextField lastNameTxtFRe;
	@FXML
	private TextField usernameTxtFRe;
	@FXML
	private PasswordField passFRe;
	@FXML
	private PasswordField repeatPassFRe;
	@FXML
	private TextField emailTxtFRe;

	/*-------------------------------------------------------------------*/

	@FXML
	private TextField artistTxtF;
	@FXML
	private TextField recordCompanyTxtF;
	@FXML
	private TextField recordNameTxtF;
	@FXML
	private TextField priceTxtF;
	@FXML
	private TextField countTxtF;
	@FXML
	private TextField carrierTypeTxtF;
	@FXML
	private TextField recordTypeTxtF;
	@FXML
	private TextField genreTxtF;
	@FXML
	private TextField decriptionTxtF;
	@FXML
	private TextField releaseYearTxtF;
	@FXML
	private TextField lengthTxtF;

	/*-------------------------------------------------------------------*/

	@FXML
	private ListView<Employee> allEmployees;
	@FXML
	private TextField searchBarEmployees;
	@FXML
	private ListView<Customer> allCustomers;	
	@FXML
	private TextField searchBarCustomer;

	/*-------------------------------------------------------------------*/

	private Collection<Employee> Employees = null;
	private Collection<Customer> Customers = null;
	private Collection<Record> Records = null;

	@FXML
	private TextField searchBarRecord;
	@FXML
	private ListView<Record> listOfRecords;
	@FXML
	private ListView<Customer> listOfCustomers;

	/*-------------------------------------------------------------------*/

	@FXML
	private TextField firstNameEmTxtF;
	@FXML
	private TextField lastNameEmTxtF;
	@FXML
	private TextField usernameEmTxtF;
	@FXML
	private TextField passEmTxtF;
	@FXML
	private TextField repeatPassEmTxtF;
	@FXML
	private TextField emailEmTxtF;
	@FXML
	private TextField wageEmTxtF;

	/*-------------------------------------------------------------------*/
	
	@FXML
	private ListView<Record> myRecordsListView;
	
	/*-------------------------------------------------------------------*/

	public void login(ActionEvent event) {
		var personL = new PersonLogic();
		
		int where = personL.login(loginTxtFLog.getText(), passwordTxtFLog.getText());
		System.out.println(where);
		
		this.user = personL.getPerson();

		if (where > 2) {
			switchToErrorScene(event);
		}
		else {
			switch(where) {
			case 0: switchToUserScene(event);
				break;
			case 1: switchToEmployeeScene(event);
				break;
			case 2: switchToManagerScene(event);
				break;
			default: switchToErrorScene(event);
				break;
			}
		}
	}

	public void switchToScene(ActionEvent event, String fxmlFile, String title) {
		try {
			root = FXMLLoader.load(getClass().getResource(fxmlFile));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle(title);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void switchToUserScene(ActionEvent event) {
		switchToScene(event, "CustomerPage.fxml", "Evidence nahrávek - Uživatel");
	}

	public void switchToEmployeeScene(ActionEvent event) {
		switchToScene(event, "EmployeePage.fxml", "Evidence nahrávek - Zaměstnanec");
	}

	public void switchToManagerScene(ActionEvent event) {
		switchToScene(event, "ManagerPage.fxml", "Evidence nahrávek - Manager");
	}

	public void switchToErrorScene(ActionEvent event) {
		switchToScene(event, "ErrorMessage.fxml", "Evidence nahrávek");
	}


	public void register() {
		var personL = new PersonLogic();
		
		int returned = personL.register(
			firstNameTxtFRe.getText(),
			lastNameTxtFRe.getText(),
			usernameTxtFRe.getText(),
			passFRe.getText(),
			repeatPassFRe.getText(),
			emailTxtFRe.getText()
		);
	}

	/*-------------------------------------------------------------------*/

	public void writeRecord() {
		var recordL = new RecordLogic();

		recordL.write(
			artistTxtF.getText(),
			recordCompanyTxtF.getText(),
			recordNameTxtF.getText(),
			priceTxtF.getText(),
			"20",
			countTxtF.getText(),
			carrierTypeTxtF.getText(),
			recordTypeTxtF.getText(),
			decriptionTxtF.getText(),
			genreTxtF.getText(),
			releaseYearTxtF.getText(),
			lengthTxtF.getText()
		);

	}

	/*-------------------------------------------------------------------*/

	public void loadSellRecords() {
		var recordL = new RecordLogic();
		
		this.listOfRecords.getItems().clear();
		this.Records = recordL.loadAll();
		this.listOfRecords.getItems().addAll(Records);

		var customerL = new CustomerLogic();

		this.listOfCustomers.getItems().clear();
		this.Customers = customerL.loadAll();
		this.listOfCustomers.getItems().addAll(Customers);
	}

	public void searchRecord() {
		var recordL = new RecordLogic();
		
		this.listOfRecords.getItems().clear();
		this.Records = recordL.search(searchBarRecord.getText());
		this.listOfRecords.getItems().addAll(Records);
	}

	public void sellRecord() {
		int selectedRecord = listOfRecords.getSelectionModel().getSelectedIndex();
		int selectedCustomer = listOfCustomers.getSelectionModel().getSelectedIndex();

		ArrayList<Record> listRecords = new ArrayList<Record>(this.Records);
		ArrayList<Customer> listCustomers = new ArrayList<Customer>(this.Customers);

		PurchachesLogic purchachesL = new PurchachesLogic();
		var personL = new PersonLogic();
		Employee em = (Employee)personL.getPerson();
		purchachesL.sell
			(listCustomers.get(selectedCustomer), em, listRecords.get(selectedRecord));
	}

	public void buyRecord() {
		int selectedRecord = listOfRecords.getSelectionModel().getSelectedIndex();
		System.out.println("selectedRecord: " + selectedRecord);
		ArrayList<Record> listRecords = new ArrayList<Record>(this.Records);
		
		PurchachesLogic purchachesL = new PurchachesLogic();
		var personL = new PersonLogic();
		Customer cu = (Customer)personL.getPerson();
		purchachesL.buy(cu, listRecords.get(selectedRecord));
	}
	
	/*-------------------------------------------------------------------*/

	public void loadEmployees() {
		var emplyeeL = new EmployeeLogic();
		this.allEmployees.getItems().clear();
		this.Employees = emplyeeL.loadAll();
		this.allEmployees.getItems().addAll(Employees);
	}

	public void deleteEmployee() {
		var emplyeeL = new EmployeeLogic();
		
		int selectedEmployee = allEmployees.getSelectionModel().getSelectedIndex();
		ArrayList<Employee> listEmployees = new ArrayList<Employee>(this.Employees);

		emplyeeL.delete(listEmployees.get(selectedEmployee).getId());

		loadEmployees();
	}
	
	public void searchEmployee() {
		var emplyeeL = new EmployeeLogic();
		this.allEmployees.getItems().clear();
		this.Employees = emplyeeL.search(searchBarEmployees.getText());
		this.allEmployees.getItems().addAll(Employees);
	}

	public void registerEmployee() {
		var personL = new PersonLogic();
		
		int retVal = personL.registerEmployee(
			firstNameEmTxtF.getText(),
			lastNameEmTxtF.getText(),
			usernameEmTxtF.getText(),
			passEmTxtF.getText(),
			repeatPassEmTxtF.getText(),
			emailEmTxtF.getText(),
			"bezny_zamestnanec",
			Integer.parseInt(wageEmTxtF.getText())
		);
		
	}

	/*-------------------------------------------------------------------*/

	public void loadCustomers() {
		var customerL = new CustomerLogic();
		this.allCustomers.getItems().clear();
		this.Customers = customerL.loadAll();
		this.allCustomers.getItems().addAll(Customers);
	}

	public void deleteCustomer() {
		var customerL = new CustomerLogic();
		int selectedCustomer = allCustomers.getSelectionModel().getSelectedIndex();
		ArrayList<Customer> listCustomers = new ArrayList<Customer>(this.Customers);

		customerL.delete(listCustomers.get(selectedCustomer).getId());

		loadCustomers();
	}
	
	public void searchCustomer() {
		var customerL = new CustomerLogic();
		this.allCustomers.getItems().clear();
		this.Customers = customerL.search(searchBarCustomer.getText());
		this.allCustomers.getItems().addAll(Customers);
	}

	/*-------------------------------------------------------------------*/
	
	public void loadRecordsCu() {
		var recordL = new RecordLogic();
		
		this.listOfRecords.getItems().clear();
		this.Records = recordL.loadAll();
		this.listOfRecords.getItems().addAll(Records);
	}
	
	/*-------------------------------------------------------------------*/
	
	public void loadMyRecords() {
		var recordL = new RecordLogic();
		
		this.myRecordsListView.getItems().clear();
		var personL = new PersonLogic();
		Customer cu = (Customer)personL.getPerson();
		this.Records = recordL.loadMy(cu);
		this.myRecordsListView.getItems().addAll(Records);
	}
}