import Model.Country;
import Model.ModelIMS;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ManageCountriesViewController {

  private Region root;
  private ViewHandler2 viewHandler2;
  private ModelIMS model;

  @FXML private Button addCountryButton;

  @FXML private Button backButton;

  @FXML private Label countryLabel;

  @FXML private TextField countryTextField;

  @FXML private Button deleteCountryButton;

  @FXML private Button manageFactorsButton;
  @FXML private Button editButton;

  @FXML private ListView<String> listView;

  public ManageCountriesViewController(){

  }
  public void init (ViewHandler2 viewHandler2, Region root, ModelIMS model){
    this.viewHandler2 = viewHandler2;
    this.root = root;
    this.model = model;

    listView.getItems().clear();
    for (int i = 0; i < model.getList3().getCountries().size(); i++)
    {
      listView.getItems().add(model.getList3().getCountry(i).getName());
    }
  }

  @FXML public void addCountry()
  {
    if (!(listView.getSelectionModel().isSelected(listView.getSelectionModel().getSelectedIndex())))
    {
      Country country = new Country(countryTextField.getText(),model.getList2(),model.getList1());
      model.getList3().addCountry(country);
      for (int i = 0; i < model.getList3().getCountries().size(); i ++ )
        System.out.println(model.getList3().getCountries().get(i));
    }
    else
    {
      model.getList3().getCountry(listView.getSelectionModel().getSelectedIndex()).setName(countryTextField.getText());
      System.out.println(model.getList3().getCountries());
    }
    listView.getItems().clear();

    for (int i = 0; i < model.getList3().getCountries().size(); i++)
    {
      listView.getItems().add(model.getList3().getCountries().get(i).getName());
    }
    resetFields();
  }

  @FXML public void editCountry()
  {

    if (listView.getSelectionModel().isSelected(listView.getSelectionModel().getSelectedIndex()))
    {
      countryTextField.setText(model.getList3().getCountry(listView.getSelectionModel().getSelectedIndex()).getName());
    }

  }
     
  public void resetFields()
  {
    countryTextField.setText("");
  }


  public void deleteCountry(){
  if(listView.getSelectionModel().isSelected(listView.getSelectionModel().getSelectedIndex())){
    model.getList3().removeCountry(listView.getSelectionModel().getSelectedIndex());
    System.out.println(model.getList3());
    listView.getItems().clear();

    for (int i = 0; i < model.getList3().getCountries().size(); i++)
    {
      listView.getItems().add(model.getList3().getCountry(i).getName());
    }
}
  }

  public Region getRoot(){
    return root;
  }

  @FXML public void manageFactors()
  {
      if(listView.getSelectionModel().isSelected(listView.getSelectionModel().getSelectedIndex())){
        model.getList3().getCountry(listView.getSelectionModel().getSelectedIndex());
        model.setSelectedCountry(listView.getSelectionModel().getSelectedIndex());
        System.out.println(model.getSelectedCountryIndex());
      }

      viewHandler2.openView("Edit Country");


  }

  @FXML public void back(){
    viewHandler2.openView("Home Page 2");
  }

}



