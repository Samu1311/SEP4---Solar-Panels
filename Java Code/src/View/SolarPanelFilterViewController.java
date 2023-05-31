package View;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SolarPanelFilterViewController {

    private Region root;
    private ViewHandler viewHandler;

    @FXML
    private Button BackButton;

    @FXML
    private Button ButtonDisplay;

    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    private Label FromLabel;

    @FXML
    private Label LocationLabel;

    @FXML
    private Label PanelTypeLabel;

    @FXML
    private Label ToLabel;

    @FXML
    private TextField TextFieldFrom;

    @FXML
    private TextField TextFieldTo;

    private DatabaseConnector databaseConnector;

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();

        ChoiceBox.setItems(FXCollections.observableArrayList("Thermo", "Photovoltaic"));
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    public void BackPressed() {
        viewHandler.openView("Home Page");
    }

    @FXML
    public void DisplayPressed() {
        databaseConnector.setPanelFromLocation(
            Integer.parseInt(TextFieldFrom.getText()));
        databaseConnector.setPanelToLocation(
            Integer.parseInt(TextFieldTo.getText()));
        databaseConnector.setPanelType(ChoiceBox.getValue());
        viewHandler.openView("Solar Panel Display");
    }
}
