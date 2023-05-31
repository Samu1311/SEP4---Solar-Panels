package View;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;

import java.sql.Timestamp;
import java.time.LocalDate;

public class EnergyReportDisplayViewController {

    private Region root;
    private ViewHandler viewHandler;
    @FXML
    BarChart<String, Number> voltageHistogram = new BarChart<>(new CategoryAxis(), new NumberAxis());

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private String panelType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private DatabaseConnector databaseConnector;

    private Timestamp timestamp;

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
        displayVoltageHistogram();
    }

    public Region getRoot() {
        return root;
    }

    private void displayVoltageHistogram() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (int i = 0; i < databaseConnector.getVoltageData().size(); i++) {
            LocalDate date = fromDate.plusDays(i);
            String formattedDate = date.toString();
            double voltageSum = databaseConnector.getVoltageData().get(i);

            series.getData().add(new XYChart.Data<>(formattedDate, voltageSum));
        }

        // Add series to the histogram
        voltageHistogram.getData().add(series);
    }

    @FXML
    public void BackPressed() {
        viewHandler.openView("Energy Report Edit");
    }
}
