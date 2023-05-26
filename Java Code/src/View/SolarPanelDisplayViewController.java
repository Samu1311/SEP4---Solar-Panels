package View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class SolarPanelDisplayViewController {

    @FXML
    private Button BackButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private TableColumn<?, ?> DimensionsColumn;

    @FXML
    private Button EditButton;

    @FXML
    private TableColumn<?, ?> LocationColumn;

    @FXML
    private TableColumn<?, ?> PanelIDCoulmn;

    @FXML
    private TableColumn<?, ?> SeriesColumn;

    @FXML
    private TableView<?> Table;
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

    @FXML public void EditPressed(){
        viewHandler.openView("Solar Panel Display Edit");
    }
}
