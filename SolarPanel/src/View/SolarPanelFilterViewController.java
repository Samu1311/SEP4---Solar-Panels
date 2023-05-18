package View;

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
    private ChoiceBox<?> ChoiceBox;

    @FXML
    private Label FromLabel;

    @FXML
    private Label LocationLabel;

    @FXML
    private Label ModelLabel;

    @FXML
    private Label PanelTypeLabel;

    @FXML
    private Label SeriesLabel;

    @FXML
    private TextField TextFieldFrom;

    @FXML
    private TextField TextFieldModel;

    @FXML
    private TextField TextFieldSeries;

    @FXML
    private TextField TextFieldTo;

    @FXML
    private Label ToLabel;
    public void init (ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }
}
