package View;

import Model.PhotovoltaicSeries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.List;

public class PVSeriesDisplayViewController {

    private Region root;
    private ViewHandler viewHandler;

    @FXML
    private Button backButton;

    @FXML
    private TableView<PhotovoltaicSeries> seriesTable;

    @FXML
    private TableColumn<PhotovoltaicSeries, Integer> seriesIDColumn;

    @FXML
    private TableColumn<PhotovoltaicSeries, Double> resistanceColumn;

    @FXML
    private TableColumn<PhotovoltaicSeries, Double> voltageColumn;

    @FXML
    private TableColumn<PhotovoltaicSeries, Double> currentColumn;

    @FXML
    private TableColumn<PhotovoltaicSeries, Double> solarFluxColumn;

    @FXML
    private TableColumn<PhotovoltaicSeries, Double> efficiencyColumn;

    private DatabaseConnector databaseConnector;

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
        populatePvSeriesTable(DatabaseConnector.getInstance().getFirstSeries(), DatabaseConnector.getInstance().getFinalSeries());
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    public void BackPressed() {
        viewHandler.openView("Home Page");
    }

    public void populatePvSeriesTable(int firstSeries, int lastSeries) {
        // Clear the table before populating with new data
        seriesTable.getItems().clear();

        // Get the latest PV measurements in the specified range
        List<PhotovoltaicSeries> pvMeasurements = databaseConnector.getLatestPVMeasurementsInRange(firstSeries, lastSeries);

        // Set cell value factories for each column to specify how the data should be displayed
        seriesIDColumn.setCellValueFactory(new PropertyValueFactory<>("series_id"));
        resistanceColumn.setCellValueFactory(new PropertyValueFactory<>("resistance"));
        voltageColumn.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("current"));
        solarFluxColumn.setCellValueFactory(new PropertyValueFactory<>("solar_flux"));
        efficiencyColumn.setCellValueFactory(new PropertyValueFactory<>("efficiency"));

        // Populate the PV series table with the retrieved data
        seriesTable.getItems().addAll(pvMeasurements);
    }
}