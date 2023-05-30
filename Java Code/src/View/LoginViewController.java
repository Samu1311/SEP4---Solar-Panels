package View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class LoginViewController {

    private Region root;
    private ViewHandler viewHandler;
    private DatabaseConnector databaseConnector;

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

    public Region getRoot() {
        return root;
    }

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector) {
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

    public void reset() {
        TextFieldUserID.clear();
        PasswordTextField.clear();
    }
}
