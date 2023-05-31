package View;
import Model.PhotovoltaicSeries;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

import java.util.List;

public class PVSeriesDisplayViewController
{

    private Region root;
    private ViewHandler viewHandler;

    @FXML
    private Button backButton;

    @FXML
    private TableView<PhotovoltaicSeries> seriesTable;

    @FXML
    private TableColumn<PhotovoltaicSeries, Integer> seriesIdColumn;

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


    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
        populatePvSeriesTable();
    }

    public Region getRoot() {
        return root;
    }

    @FXML public void BackPressed(){
        viewHandler.openView("Home Page");
    }

    public void populatePvSeriesTable() {
        // Clear the table before populating with new data
        seriesTable.getItems().clear();

        // Get the latest PV measurements in the specified range
        List<PhotovoltaicSeries> pvMeasurements = databaseConnector.get

        // Populate the PV series table with the retrieved data
        seriesTable.getItems().setAll(pvMeasurements);
    }



}
