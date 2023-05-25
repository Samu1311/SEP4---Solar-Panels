package View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class SolarPanelEditViewController {

    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button BackButton;

    @FXML
    private Button ButtonDisplay;

    @FXML
    private Label LocationLabel;

    @FXML
    private Label ModelLabel;

    @FXML
    private Label PanelIDLabel;

    @FXML
    private Label SeriesLabel;

    @FXML
    private TextField TextFieldLocation;

    @FXML
    private TextField TextFieldModel;

    @FXML
    private TextField TextFieldPanel;

    @FXML
    private TextField TextFieldSeries;

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
    @FXML public void DisplayPressed(){
        viewHandler.openView("Solar Panel Display");
    }

}
