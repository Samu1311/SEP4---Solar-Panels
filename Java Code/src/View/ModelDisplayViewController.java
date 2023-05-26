//package View;
import Model.Manufacturer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.List;
/*
public class ModelDisplayViewController {

    @FXML
    private Button AddButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button DeleteButton;

    @FXML
  //  private TableColumn<Model, String> DimensionsColumn;

    @FXML
    private Button EditButton;

    @FXML
  //  private TableColumn<Model, String> ManufacturerColumn;

    @FXML
   // private TableColumn<Model, Integer> ModelColumn;

    @FXML
 //   private TableColumn<Model, Integer> PriceColumn;

    @FXML
    private TableColumn<Model, String> TypeColumn;

    @FXML
    private TableView<Model> modelTable;

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

    @FXML  public void AddPressed(){
        viewHandler.openView("Model Edit");
    }
    @FXML public void EditPressed(){
        viewHandler.openView("Model Edit");
    }

    public void initialize() {
        // Call the method to populate the manufacturer table during initialization
        populateModelTable();
    }

    private void populateModelTable() {
        // Retrieve all manufacturers from the database
        List<Manufacturer> manufacturers = databaseConnector.getAllManufacturers();

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
*/
