package View;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class EnergyReportEditViewController {


    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button BackButton;

    @FXML
    private CheckBox CheckBoxEnergy;

    @FXML
    private ChoiceBox<?> ComboBoxFrom;

    @FXML
    private ChoiceBox<?> ComboBoxPanel;

    @FXML
    private ChoiceBox<?> ComboBoxScale;

    @FXML
    private ChoiceBox<?> ComboBoxTo;

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


    public void init (ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }
}

