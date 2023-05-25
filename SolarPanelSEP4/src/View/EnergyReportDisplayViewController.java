package View;
import Model.ModelSP;
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

    private ModelSP model;



    public void init (ViewHandler viewHandler, Region root, ModelSP model){
        this.viewHandler = viewHandler;
        this.root = root;
        this.model = model;
    }

    public Region getRoot() {
        return root;
    }

}
