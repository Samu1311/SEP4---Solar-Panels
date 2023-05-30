package View;

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
  private List<Model> models;
  private DatabaseConnector databaseConnector;

  public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
    this.viewHandler = viewHandler;
    this.root = root;
    this.databaseConnector = DatabaseConnector.getInstance();
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
  /*public void EditPressed() {
    // Get the selected model from the table view
    Model selectedModel = modelTable.getSelectionModel().getSelectedItem();

    if (selectedModel != null) {
      // Open the Model Edit view and pass the selected model for editing
      viewHandler.openView("Model Edit", selectedModel);
    }
  }*/

  public void initialize() {
    try {
      // Call the method to populate the model table during initialization
      populateModelTable();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void populateModelTable() throws SQLException {
    // Retrieve all models from the database using the singleton instance of DatabaseConnector
    models = DatabaseConnector.getInstance().getAllModels();

    // Set cell value factories for each column to specify how the data should be displayed
    ModelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ManufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer_name"));
    DimensionsColumn.setCellValueFactory(new PropertyValueFactory<>("dimensions"));
    PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    TypeColumn.setCellValueFactory(new PropertyValueFactory<>("panel_type"));
    SolarCellAreaColumn.setCellValueFactory(new PropertyValueFactory<>("solar_cell_area"));

    // Populate the model table with the retrieved data
    modelTable.getItems().setAll(models);
  }

  @FXML
  private void deletePressed() {
    // Get the selected model from the table view
    Model selectedModel = modelTable.getSelectionModel().getSelectedItem();

    if (selectedModel != null) {
      try {
        // Delete the selected model from the database and remove it from the table view
        DatabaseConnector.getInstance().deleteModel(selectedModel);

        // Refresh the model table view
        populateModelTable();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
