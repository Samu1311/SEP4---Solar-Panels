package View;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class LoginViewController {

    private Region root;
    private ViewHandler viewHandler;
    @FXML
    private Button LoginButton;

    @FXML
    private Label PasswordLabel;

    @FXML
    private PasswordField PasswordTextField;

    @FXML
    private TextField TextFieldUserID;

    @FXML
    private Label UserIDLabel;


    private DatabaseConnector databaseConnector;

    public Region getRoot() {
        return root;
    }


    public void init (ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector){
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = databaseConnector;
    }




    @FXML
    private void loginButtonPressed() {
        String username = TextFieldUserID.getText();
        String password = PasswordTextField.getText();

        boolean authenticated = databaseConnector.authenticateUser(username, password);

        if (authenticated) {
            // Proceed to the next screen
            viewHandler.openView("Home Page");
        } else {
            // Show an error message
            showAlert("Invalid credentials", "The entered username or password is incorrect.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}