package View;

import Model.ThermoSeries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.List;

public class ThermoSeriesDisplayViewController {

  private Region root;
  private ViewHandler viewHandler;
  private DatabaseConnector databaseConnector;
  @FXML
  private TableView<ThermoSeries> SeriesTable;
  @FXML
  private TableColumn<ThermoSeries, Integer> SeriesIDColumn;
  @FXML
  private TableColumn<ThermoSeries, Integer> CollectionTimeColumn;
  @FXML
  private TableColumn<ThermoSeries, Double> HotWaterColumn;
  @FXML
  private TableColumn<ThermoSeries, Double> SolarFluxColumn;
  @FXML
  private TableColumn<ThermoSeries, Double> WaterFlowColumn;
  @FXML
  private TableColumn<ThermoSeries, Double> EfficiencyColumn;
  @FXML
  private Button BackButton;

  private Stage stage;

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
    this.viewHandler = viewHandler;
    this.root = root;
    this.databaseConnector = DatabaseConnector.getInstance();
  }

  public void populateThermoSeriesTable(List<ThermoSeries> measurements) {
    SeriesTable.getItems().addAll(measurements);
  }
}

