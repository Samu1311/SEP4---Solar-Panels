package View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class MalfunctioningPanelsViewController {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<?, ?> LocationColumn;

    @FXML
    private TableView<?> MalfunctioningTable;

    @FXML
    private TableColumn<?, ?> ModelColumn;

    @FXML
    private TableColumn<?, ?> PanelIDColumn;

    @FXML
    private Slider Slider;

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
}
