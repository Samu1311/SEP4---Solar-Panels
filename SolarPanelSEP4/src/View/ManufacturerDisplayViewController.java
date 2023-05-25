package View;
import Model.Manufacturer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

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



    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = databaseConnector;
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
    @FXML public void EditPressed(){
        viewHandler.openView("Manufacturer Edit");
    }

    private void populateManufacturerTable() {
        // Retrieve all manufacturers from the database
        List<Manufacturer> manufacturers = databaseConnector.getAllManufacturers();

        // Populate the manufacturer table with the retrieved data
        manufacturerTable.getItems().setAll(manufacturers);
    }

}
