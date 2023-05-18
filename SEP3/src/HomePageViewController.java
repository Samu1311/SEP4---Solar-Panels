import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
public class HomePageViewController
{
  private Region root;
  private ViewHandler2 viewHandler2;
  private ModelIMS model;

  @FXML
  private Button manageFactors;

  @FXML
  private Button manageCountries;

  @FXML
  private Button calculateIMS;
  @FXML
  private Button deleteAllData;
  @FXML
  private Label title;


  public HomePageViewController(){}

  public void init (ViewHandler2 viewHandler2, Region root, ModelIMS model){
    this.viewHandler2 = viewHandler2;
    this.root = root;
    this.model = model;

  }

  public Region getRoot(){
    return root;
  }

  @FXML public void ManageFactorsPressed(){
    viewHandler2.openView("Manage Factors");
  }

  @FXML public void ManageCountriesPressed(){
    viewHandler2.openView("Manage Countries");
  }
  @FXML public void calculatorPressed(){
    viewHandler2.openView("IMS Calculator");
  }

  @FXML public void deleteAllData(){
    model.getList1().removeAllFactors();
    model.getList2().removeAllFactors();
    model.getList3().clear();
    viewHandler2.openView("Home Page");
  }


}
