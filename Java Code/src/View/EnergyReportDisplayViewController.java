package View;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class EnergyReportDisplayViewController {

    @FXML
    private Button BackButton;

    @FXML
    private BarChart<?, ?> BarChar;

    @FXML
    private Label Date1Label;

    @FXML
    private Label Date2Label;

    @FXML
    private Button DonwloadButton;

    @FXML
    private LineChart<?, ?> LineChart;

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

}
