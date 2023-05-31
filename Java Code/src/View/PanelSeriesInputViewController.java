package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class PanelSeriesInputViewController
{

    private Region root;
    private ViewHandler viewHandler;
    @FXML private Button BackButton;

    @FXML private Button DisplayButton;

    @FXML private ComboBox<Integer> FromComboBox;

    @FXML private Label FromLabel;

    @FXML private Label LocationLabel;

    @FXML private Label PanelTypeLabel;

    @FXML private ComboBox<Integer> ToComboBox;

    @FXML private Label ToLabel;

    @FXML private ChoiceBox<String> TypeChoiceBox;

    private DatabaseConnector databaseConnector;
    private String seriesType;

    public void init(ViewHandler viewHandler, Region root, DatabaseConnector databaseConnector)
    {
        this.viewHandler = viewHandler;
        this.root = root;
        this.databaseConnector = DatabaseConnector.getInstance();

        // Set the options for the TypeChoiceBox
        TypeChoiceBox.getItems().addAll("Thermo", "Photovoltaic");

        // Set an event listener for the TypeChoiceBox selection change
        TypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Update the range for the FromComboBox and ToComboBox based on the selected series type
            updateRange(newValue);
        });
    }

    private void updateRange(String seriesType)
    {
        // Clear the existing options
        FromComboBox.getItems().clear();
        ToComboBox.getItems().clear();

        // Get the range of series based on the selected series type
        int minSeriesId = databaseConnector.getMinSeriesId(seriesType);
        int maxSeriesId = databaseConnector.getMaxSeriesId(seriesType);

        // Set the range options in the FromComboBox and ToComboBox
        for (int i = minSeriesId; i <= maxSeriesId; i++)
        {
            FromComboBox.getItems().add(i);
            ToComboBox.getItems().add(i);
        }

        // Select the first option as the default
        if (!FromComboBox.getItems().isEmpty())
        {
            FromComboBox.getSelectionModel().selectFirst();
        }
        if (!ToComboBox.getItems().isEmpty())
        {
            ToComboBox.getSelectionModel().selectFirst();
        }
    }

    public Region getRoot()
    {
        return root;
    }

    @FXML public void BackPressed()
    {
        viewHandler.openView("Home Page");
    }

    @FXML
    public void DisplayPressed()
    {
        int firstSeries = FromComboBox.getValue();
        int finalSeries = ToComboBox.getValue();

        if (TypeChoiceBox.getValue() == "Photovoltaic") {
            databaseConnector.setFirstSeries(firstSeries);
            databaseConnector.setFinalSeries(finalSeries);
            viewHandler.openView("PV Series Display");
        } else if (TypeChoiceBox.getValue() == "Thermo"){
            databaseConnector.setFirstSeries(firstSeries);
            databaseConnector.setFinalSeries(finalSeries);
            viewHandler.openView("Thermo Series Display");
        }

    }
}
