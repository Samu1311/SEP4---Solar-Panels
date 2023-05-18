package View;
import Model.ModelSP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class PanelSeriesDisplayViewController {

    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button AddButton;

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
