package View;
import Model.Manufacturer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.sql.SQLException;
import java.util.List;

public class ManufacturerDisplayViewController {

    @FXML
    private Button AddButton;

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<Manufacturer, String> CityColumn;

    @FXML
    private TableColumn<Manufacturer, String> CountryColumn;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button EditButton;

    @FXML
    private TableColumn<Manufacturer, Integer> EmailColumn;

    @FXML
    private TableColumn<Manufacturer, String> NameColumn;

    @FXML
    private TableColumn<Manufacturer, Integer> PhoneColumn;

    @FXML
    private TableColumn< Manufacturer, Integer> PostCodeColumn;

    @FXML
    private Slider Slider;

    @FXML
    private TableColumn<Manufacturer, Integer> manufacturerIDColumn;

    @FXML
    private TableView<Manufacturer> manufacturerTable;

    private Region root;
    private ViewHandler viewHandler;


    private DatabaseConnector databaseConnector;
    List<Manufacturer> manufacturers ;



    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = databaseConnector;
        manufacturerTable.setEditable(true);
        initialize();
    }

    public Region getRoot() {
        return root;
    }

    @FXML public void BackPressed(){
        viewHandler.openView("Home Page");
    }
    @FXML public void AddPressed(){
        viewHandler.openView("Manufacturer Edit");
    }
    @FXML public void EditPressed() {
        // Get the selected Manufacturer from the table view
        /*Manufacturer selectedManufacturer = manufacturerTable.getSelectionModel().getSelectedItem();

        // Check if a Manufacturer is selected
        if (selectedManufacturer != null) {
            // Store the manufacturer_id in the transactionItemId variable
            int manufacturerId = selectedManufacturer.getManufacturer_id();
            DatabaseConnector databaseConnector = new DatabaseConnector();
            databaseConnector.setTransactionItemId(manufacturerId);

            viewHandler.openView("Manufacturer Edit");*/
        if(manufacturerTable != null){
            manufacturerTable.setEditable(true);
        }

    }

    public void initialize() {
        // Call the method to populate the manufacturer table during initialization
        populateManufacturerTable();
}

    public void populateManufacturerTable() {
        // Retrieve all manufacturers from the database
         manufacturers = databaseConnector.getAllManufacturers();

        // Set cell value factories for each column to specify how the data should be displayed
        manufacturerIDColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer_id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        CountryColumn.setCellValueFactory(new PropertyValueFactory<>("country_name"));
        PostCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postal_code"));

        // Populate the manufacturer table with the retrieved data
        manufacturerTable.getItems().setAll(manufacturers);
}
    @FXML
    private void deletePressed() {
        // Get the selected manufacturer from the table view
        Manufacturer selectedManufacturer = manufacturerTable.getSelectionModel().getSelectedItem();

        if (selectedManufacturer != null) {
            // Delete the selected manufacturer from the database and remove it from the table view
            databaseConnector.deleteManufacturer(selectedManufacturer);

            // Refresh the manufacturer table view
            populateManufacturerTable();
}
    }
}

