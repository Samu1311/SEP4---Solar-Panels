package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class PanelSeriesInputViewController {

    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button BackButton;

    @FXML
    private Button DisplayButton;

    @FXML
    private ComboBox<?> FromComboBox;

    @FXML
    private Label FromLabel;

    @FXML
    private Label LocationLabel;

    @FXML
    private Label PanelTypeLabel;

    @FXML
    private ComboBox<?> ToComboBox;

    @FXML
    private Label ToLabel;

    @FXML
    private ChoiceBox<?> TypeComboBox;

    public void init (ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }
}

