package View;
import Model.Manufacturer;
import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.sql.SQLException;
import java.util.List;

public class ModelDisplayViewController {

    @FXML
    private Button AddButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button DeleteButton;

    @FXML
      private TableColumn<Model, String> DimensionsColumn;

    @FXML
    private Button EditButton;

    @FXML
      private TableColumn<Model, String> ManufacturerColumn;

    @FXML
    private TableColumn<Model, String> ModelColumn;

    @FXML
       private TableColumn<Model, Integer> PriceColumn;

    @FXML
    private TableColumn<Model, String> SolarCellAreaColumn;

    @FXML
    private TableColumn<Model, String> TypeColumn;



    @FXML
    private TableView<Model> modelTable;

    private Region root;
    private ViewHandler viewHandler;

    private DatabaseConnector databaseConnector;
    List<Model> models ;


    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = databaseConnector;

    }

    public Region getRoot() {
        return root;
    }

    @FXML
    public void BackPressed() {
        viewHandler.openView("Home Page");
    }

    @FXML
    public void AddPressed() {
        viewHandler.openView("Model Edit");
    }

    @FXML
    public void EditPressed() {
        viewHandler.openView("Model Edit");
    }

    public void initialize() throws SQLException {
        // Call the method to populate the manufacturer table during initialization
        populateModelTable();
    }

    public void populateModelTable() throws SQLException {
        // Retrieve all manufacturers from the database
        models = databaseConnector.getAllModels();

        // Set cell value factories for each column to specify how the data should be displayed
        ModelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ManufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer_name"));
        DimensionsColumn.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("panel_type"));
        SolarCellAreaColumn.setCellValueFactory(new PropertyValueFactory<>("solar_cell_area"));


        // Populate the manufacturer table with the retrieved data
        modelTable.getItems().setAll(models);
    }

    @FXML
    private void deletePressed() throws SQLException {
        // Get the selected manufacturer from the table view
        Model selectedModel = modelTable.getSelectionModel().getSelectedItem();

        if (selectedModel != null) {
            // Delete the selected manufacturer from the database and remove it from the table view
            databaseConnector.deleteModel(selectedModel);

            // Refresh the manufacturer table view
            populateModelTable();
        }
    }
}
