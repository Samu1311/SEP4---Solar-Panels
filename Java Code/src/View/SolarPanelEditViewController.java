package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import javax.xml.crypto.Data;

public class SolarPanelEditViewController {

    private Region root;
    private ViewHandler viewHandler;
    private DatabaseConnector databaseConnector;

    @FXML
    private Button backButton;

    @FXML
    private Button displayButton;

    @FXML
    private Label locationLabel;

    @FXML
    private Label panelIDLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label seriesLabel;

    @FXML
    private TextField textFieldLocation;

    @FXML
    private TextField textFieldModel;

    @FXML
    private TextField textFieldPanel;

    @FXML
    private TextField textFieldSeries;

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    public void backPressed() {
        viewHandler.openView("Home Page");
    }

    @FXML
    public void displayPressed() {
        viewHandler.openView("Solar Panel Display");
    }
}
