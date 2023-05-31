package View;

import Model.SolarPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.List;

public class SolarPanelDisplayViewController {
    @FXML
    private TableView<SolarPanel> table;
    @FXML
    private TableColumn<SolarPanel, Integer> PanelIDColumn;
    @FXML
    private TableColumn<SolarPanel, String> LocationColumn;
    @FXML
    private TableColumn<SolarPanel, Integer> SeriesColumn;
    @FXML
    private Button BackButton;

    private Region root;
    private ViewHandler viewHandler;

    private DatabaseConnector databaseConnector;

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
        populatePanelsTable(DatabaseConnector.getInstance().getPanelFromLocation(), DatabaseConnector.getInstance()
            .getPanelToLocation(), DatabaseConnector.getInstance()
            .getPanelType());
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    public void BackPressed() {
        viewHandler.openView("Home Page");
    }

    public void populatePanelsTable(int fromValue, int toValue, String typeValue) {
        // Clear the table before populating with new data
        table.getItems().clear();

        // Get the selected panels based on the specified parameters
        List<SolarPanel> panels = databaseConnector.getSelectedPanels(fromValue, toValue, typeValue);

        // Set cell value factories for each column to specify how the data should be displayed
        PanelIDColumn.setCellValueFactory(new PropertyValueFactory<>("solar_panel_id"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("roof_loc"));
        SeriesColumn.setCellValueFactory(new PropertyValueFactory<>("series"));

        // Populate the panels table with the retrieved data
        table.getItems().addAll(panels);
    }
}
