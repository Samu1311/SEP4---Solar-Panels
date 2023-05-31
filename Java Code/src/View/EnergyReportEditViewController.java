package View;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.List;

public class EnergyReportEditViewController {


    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button BackButton;

    @FXML
    private CheckBox CheckBoxEnergy;

    @FXML
    private ChoiceBox<String> ComboBoxPanel;

    @FXML
    private DatePicker DatePickerFrom;

    @FXML
    private DatePicker DatePickerTo;

    @FXML
    private Button DisplayButton;

    @FXML
    private Label FromCalendarLabel;

    @FXML
    private Label FromPanelLabel;

    @FXML
    private Label PanelTypeLabel;

    @FXML
    private Label ScaleTimeLabel;

    @FXML
    private Label ToCalendarLabel;

    @FXML
    private Label ToPanelLabel;

    private DatabaseConnector databaseConnector;

    public Region getRoot() {
        return root;
    }

    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
        ComboBoxPanel.getItems().addAll("Thermo", "Photovoltaic");}



    private void generateReport() {
        String panelType = ComboBoxPanel.getValue();
        databaseConnector.setGraphFromDate(DatePickerFrom.getValue());
        databaseConnector.setGraphToDate(DatePickerTo.getValue());

        // Validate inputs
        if (panelType == null || databaseConnector.getGraphFromDate() == null ||  databaseConnector.getGraphToDate() == null){
            return;
        }

        // Retrieve efficiency data from the database
        if (panelType.equals("Photovoltaic")) {
            databaseConnector.setVoltageData(databaseConnector.filterVoltageDataFromPhotovoltaic( databaseConnector.getGraphFromDate(), databaseConnector.getGraphToDate()));
        } else {
            databaseConnector.setVoltageData(databaseConnector.filterVoltageDataFromThermo( databaseConnector.getGraphFromDate(), databaseConnector.getGraphToDate() ));
        }



    }

    @FXML
    private void displayPressed() {
    viewHandler.openView("Energy Report Display"); generateReport();
}

    @FXML public void BackPressed(){
        viewHandler.openView("Home Page");
    }
}



