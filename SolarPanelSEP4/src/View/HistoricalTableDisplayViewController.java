package View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class HistoricalTableDisplayViewController {

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TableView<?> historicalDataTable;

    @FXML
    private TableColumn<?, ?> location;


    @FXML
    private TableColumn<?, ?>panelID;


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
    @FXML public void DisplayPressed(){
        viewHandler.openView("Panel Series Display");
    }
}

