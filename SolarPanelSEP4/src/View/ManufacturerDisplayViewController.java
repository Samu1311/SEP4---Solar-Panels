package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;

public class ManufacturerDisplayViewController {

    @FXML
    private Button AddButton;

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<?, ?> CityColumn;

    @FXML
    private TableColumn<?, ?> CountryColumn;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button EditButton;

    @FXML
    private TableColumn<?, ?> EmailColumn;

    @FXML
    private TableColumn<?, ?> NameColumn;

    @FXML
    private TableColumn<?, ?> PhoneColumn;

    @FXML
    private TableColumn<?, ?> PostCodeColumn;

    @FXML
    private Slider Slider;

    @FXML
    private TableColumn<?, ?> manufacturerIDColumn;

    @FXML
    private TableView<?> manufacturerTable;

    private Region root;
    private ViewHandler viewHandler;

    public void init (ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }
}
