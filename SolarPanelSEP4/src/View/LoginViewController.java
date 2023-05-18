package View;
import Model.ModelSP;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class LoginViewController {

    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button LoginButton;

    @FXML
    private Label PasswordLabel;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextField TextFieldUserID;

    @FXML
    private Label UserIDLabel;


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
