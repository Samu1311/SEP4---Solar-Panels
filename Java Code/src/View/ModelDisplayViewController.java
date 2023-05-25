package View;
import Model.ModelSP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class ModelDisplayViewController {

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
    private TableColumn<?, ?> ManufacturerColumn;

    @FXML
    private TableColumn<?, ?> ModelColumn;

    @FXML
    private TableColumn<?, ?> PriceColumn;

    @FXML
    private TableColumn<?, ?> TypeColumn;

    @FXML
    private TableView<?> modelTable;

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
