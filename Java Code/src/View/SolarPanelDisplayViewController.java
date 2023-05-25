package View;
import Model.ModelSP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class SolarPanelDisplayViewController {

    @FXML
    private Button AddButton;

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
