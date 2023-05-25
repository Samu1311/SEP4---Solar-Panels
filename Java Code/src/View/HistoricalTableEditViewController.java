package View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;

public class HistoricalTableEditViewController {

    @FXML
    private Button backButton;

    @FXML
    private MenuButton category;

    @FXML
    private Button displayButton11;

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

    private DatabaseConnector databaseConnector;



    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = databaseConnector;
    }

    public Region getRoot() {
        return root;
    }

    @FXML public void BackPressed(){
        viewHandler.openView("Home Page");
    }

    @FXML  public void DisplayPressed(){
        viewHandler.openView("Historical Table Display");
    }
}