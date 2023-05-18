package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class HomeScreenViewController {

    private Region root;
    private ViewHandler viewHandler;

    @FXML
    private Button EnergyReportButton;

    @FXML
    private Button HistoricalButton;

    @FXML
    private Button ManufacturersButton;

    @FXML
    private Button ModelsButton;

    @FXML
    private Button PanelSeriesButton;

    @FXML
    private Button SolarPnaelButton;

    public void init (ViewHandler viewHandler, Region root){
        this.viewHandler = viewHandler;
        this.root = root;
    }

    @FXML public void EnergyReportPressed(){
        viewHandler.openView("Energy Report Edit");
    }

    @FXML public void PanelSeriesPressed(){
        viewHandler.openView("Panel Series Input");
    }
    @FXML public void SolarPanelPressed(){
        viewHandler.openView("Solar Panel Filter");
    }
    @FXML public void ManufacturersPressed(){
        viewHandler.openView("Manufacturer Display");
    }
    @FXML public void ModelsPressed(){
        viewHandler.openView("Model Display");
    }
    @FXML public void HistoricalDataPressed(){
        viewHandler.openView("Historical Table Edit");
    }
}

