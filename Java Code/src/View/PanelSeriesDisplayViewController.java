package View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class PanelSeriesDisplayViewController {

    private Region root;
    private ViewHandler viewHandler;


    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<?, ?> CurrentColumn;

    @FXML
    private Button DeleteButton;

    @FXML
    private TableColumn<?, ?> EffiencyColumn;

    @FXML
    private TableColumn<?, ?> ResistanceColumn;

    @FXML
    private TableColumn<?, ?> SeriesIDColumn;

    @FXML
    private TableView<?> SeriesTable;

    @FXML
    private TableColumn<?, ?> SolarFluxColumn;

    @FXML
    private TableColumn<?, ?> VoltageColumn;


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

}
