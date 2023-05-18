package View;
import Model.ModelSP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ModelEditViewController {

    @FXML
    private Button BackButton;

    @FXML
    private Label DimensionsLabel;

    @FXML
    private TextField DimensionsTextField;

    @FXML
    private Button DisplayButton;

    @FXML
    private Label ManufacturerLabel;

    @FXML
    private TextField ManufacturerTextField;

    @FXML
    private Label ModelLabel;

    @FXML
    private Label PanelTypeLabel;

    @FXML
    private TextField PanelTypeTextField;

    @FXML
    private Label PriceLabel;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField TextFieldModel;

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
