package View;

import Model.Manufacturer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ManufacturerEditViewController {

    @FXML
    private TextField CityField;

    @FXML
    private TextField CountryField;

    @FXML
    private Label EmailLabel;

    @FXML
    private Button UpdateButton;


    @FXML
    private TextField EmailTextField;

    @FXML
    private Label NameLabel;

    @FXML
    private TextField NameTextField;

    @FXML
    private Label PhoneLabel;

    @FXML
    private TextField PhoneTextField;

    @FXML
    private Label PostCodeLabel;

    @FXML
    private TextField PostCodeTextField;

    @FXML
    private Button backButton;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Button displayButton;

    @FXML
    private Button saveButtton;

    private Region root;
    private ViewHandler viewHandler;

    private DatabaseConnector databaseConnector;




    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = databaseConnector;

    }
    public Region getRoot() {
        return root;
    }

    @FXML
    public void saveButtonPressed() {
        String name = NameTextField.getText();
        String phone = PhoneTextField.getText();
        String email = EmailTextField.getText();
        String city = CityField.getText();
        String country = CountryField.getText();
        int postalCode = Integer.parseInt(PostCodeTextField.getText());

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setPhone(phone);
        manufacturer.setEmail(email);
        manufacturer.setCity_name(city);
        manufacturer.setCountry_name(country);
        manufacturer.setPostal_code(postalCode);

        // Insert the manufacturer into the database
        databaseConnector.insertManufacturer(manufacturer);


        //    viewHandler.openView("ManufacturerDisplayView");
    }
    public void populateEditForm(Manufacturer manufacturer, TextField nameField, TextField phoneField, TextField emailField, TextField cityField) {
        nameField.setText(manufacturer.getName());
        phoneField.setText(manufacturer.getPhone());
        emailField.setText(manufacturer.getEmail());
        cityField.setText(manufacturer.getCity_name());
    }

    @FXML private void updatePressed() {
        // Retrieve the manufacturer_id from the transactionItemId variable
        DatabaseConnector databaseConnector = new DatabaseConnector();
        int manufacturerId = databaseConnector.getTransactionItemId();

        String name = NameTextField.getText();
        String phone = PhoneTextField.getText();
        String email = EmailTextField.getText();
        String country = CountryField.getText();
        String city = CityField.getText();

        Manufacturer updatedManufacturer = new Manufacturer(name, phone, email, city, country);
        DatabaseConnector.updateManufacturer(updatedManufacturer);
        // Call the updateManufacturer method to update the Manufacturer in the database
        boolean success = DatabaseConnector.updateManufacturer(updatedManufacturer);

        if (success) {
            // Show a success message or perform any other desired action
            System.out.println("Succesfull update");
        } else {
            // Show an error message or perform any other desired action
            System.out.println("No sirvio bro");
        }
    }





    @FXML public void BackPressed(){
        viewHandler.openView("Home Page");
    }
    @FXML public void DisplayPressed(){
        viewHandler.openView("Manufacturer Display");
    }
}