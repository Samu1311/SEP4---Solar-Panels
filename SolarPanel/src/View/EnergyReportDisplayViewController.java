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


    public void init (ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

}
