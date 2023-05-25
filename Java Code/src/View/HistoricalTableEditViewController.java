package View;
import Model.ModelSP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;

public class HistoricalTableEditViewController {

    @FXML
    private MenuButton category;

    @FXML
    private Button displayButton;

    @FXML
    private DatePicker fromdatepicker;

    @FXML
    private MenuItem manufacturerItem;

    @FXML
    private MenuItem modelsItem;

    @FXML
    private MenuItem panelItem;

    @FXML
    private DatePicker toDatePicker;

    private Region root;
    private ViewHandler viewHandler;

    private ModelSP model;



    public void init (ViewHandler viewHandler, Region root, ModelSP model){
        this.viewHandler = viewHandler;
        this.root = root;
        this.model = model;
    }

    public Region getRoot() {
        return root;
    }
}