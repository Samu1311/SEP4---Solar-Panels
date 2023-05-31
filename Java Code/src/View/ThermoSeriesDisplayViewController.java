package View;

import Model.ThermoSeries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.List;

public class ThermoSeriesDisplayViewController {

  private Region root;
  private ViewHandler viewHandler;
  private DatabaseConnector databaseConnector;

  @FXML
  private TableView<ThermoSeries> seriesTable;

  @FXML
  private TableColumn<ThermoSeries, Integer> seriesIDColumn;

  @FXML
  private TableColumn<ThermoSeries, Integer> collectionTimeColumn;

  @FXML
  private TableColumn<ThermoSeries, Double> hotWaterColumn;

  @FXML
  private TableColumn<ThermoSeries, Double> solarFluxColumn;

  @FXML
  private TableColumn<ThermoSeries, Double> waterFlowColumn;

  @FXML
  private TableColumn<ThermoSeries, Double> efficiencyColumn;

  @FXML
  private Button backButton;

  public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
    this.viewHandler = viewHandler;
    this.root = root;
    this.databaseConnector = DatabaseConnector.getInstance();
    populateThermoSeriesTable(DatabaseConnector.getInstance().getFirstSeries(), DatabaseConnector.getInstance().getFinalSeries());;
  }

  public Region getRoot() {
    return root;
  }

  @FXML
  public void BackPressed() {
    viewHandler.openView("Home Page");
  }

  public void populateThermoSeriesTable(int firstSeries, int lastSeries) {
    // Clear the table before populating with new data
    seriesTable.getItems().clear();

    // Get the latest ThermoSeries measurements in the specified range
    List<ThermoSeries> thermoMeasurements = databaseConnector.getLatestThermoMeasurementsInRange(firstSeries, lastSeries);

    // Set cell value factories for each column to specify how the data should be displayed
    seriesIDColumn.setCellValueFactory(new PropertyValueFactory<>("series_id"));
    collectionTimeColumn.setCellValueFactory(new PropertyValueFactory<>("collection_time_period"));
    hotWaterColumn.setCellValueFactory(new PropertyValueFactory<>("hot_water_temperature"));
    solarFluxColumn.setCellValueFactory(new PropertyValueFactory<>("solar_flux"));
    waterFlowColumn.setCellValueFactory(new PropertyValueFactory<>("water_flow"));
    efficiencyColumn.setCellValueFactory(new PropertyValueFactory<>("efficiency"));

    // Populate the ThermoSeries table with the retrieved data
    seriesTable.getItems().addAll(thermoMeasurements);
  }

}
