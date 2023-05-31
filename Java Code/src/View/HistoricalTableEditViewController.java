package View;
import Model.Manufacturer_log;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HistoricalTableEditViewController {

    @FXML
    private TableColumn<Manufacturer_log, String> ActionColumn;

    @FXML
    private TableColumn<Manufacturer_log, String> City_nameColumn;

    @FXML
    private TableColumn<Manufacturer_log, String> EmailColumn;

    @FXML
    private TableColumn<Manufacturer_log, String> InfoColumn;

    @FXML
    private Button ManufacturerButton;

    @FXML
    private TableView<Manufacturer_log> Manufacturer_logTable;

    @FXML
    private Button ModelButton;

    @FXML
    private TableColumn<Manufacturer_log, String> NameColumn;

    @FXML
    private TableColumn<Manufacturer_log, String> PhoneColumn;

    @FXML
    private TableColumn<Manufacturer_log, Timestamp> TimestampColumn;

    @FXML
    private Button backButton;


    private List<Manufacturer_log> manufacturer_logs = new ArrayList<>();
    private Region root;
    private ViewHandler viewHandler;

    private DatabaseConnector databaseConnector;


    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    public Region getRoot() {
        return root;
    }


    public void ManufacturerPressed() {
        populateManufacturer_logTable();
    }

    public void populateManufacturer_logTable() {


        DatabaseConnector.getInstance().getAllHistoricalManufacturer();
        // Set cell value factories for each column to specify how the data should be displayed
        TimestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        City_nameColumn.setCellValueFactory(new PropertyValueFactory<>("city_name"));
        InfoColumn.setCellValueFactory(new PropertyValueFactory<>("info"));
        ActionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Populate the manufacturer table with the retrieved data
        Manufacturer_logTable.getItems().setAll(DatabaseConnector.getInstance().getManufacturer_logs());
    }


    @FXML
    public void BackPressed() {
        viewHandler.openView("Home Page");
    }
}
