package View;
import Model.Manufacturer;
import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ModelEditViewController {

    @FXML
    private Button BackButton;

    @FXML
    private Button AddButton;
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

    @FXML
    private TextField SolarCelAreaTextField;

    @FXML
    private Label SolarCellAreaLabel;

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

    @FXML
    public void AddButtonPressed() {
        String name = TextFieldModel.getText();
        String manufacturer_name = ManufacturerTextField.getText();
        String dimensions = DimensionsTextField.getText();
        String panel_type = PanelTypeTextField.getText();
        String price = PriceTextField.getText();
        String solar_cell_area = SolarCelAreaTextField.getText();


        Model model = new Model();
        model.setName(name);
        model.setManufacturer_name(manufacturer_name);
        model.setDimensions(dimensions);
        model.setPanel_type(panel_type);
        model.setPrice(Integer.parseInt(price));
        model.setSolar_cell_area(solar_cell_area);


        // Insert the manufacturer into the database
        databaseConnector.insertModel(model);


        //    viewHandler.openView("ManufacturerDisplayView");
    }


    @FXML public void BackPressed(){
        viewHandler.openView("Home Page");
    }

    @FXML  public void DisplayPressed(){
        viewHandler.openView("Model Display");
    }


}