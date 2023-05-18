import Model.*;
import Model.ModelIMS;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class EditCountryViewController
{
  private Region root;
  private ViewHandler2 viewHandler2;
  private ModelIMS model;

  @FXML
  private Button backButton;

  @FXML
  private Button deleteButton;

  @FXML
  private TableView<QualitativeFactor> qualiTableMA;

  @FXML
  private TableColumn<QualitativeFactor,String> qualiFactorNameMA;
  @FXML
  private TableColumn<QualitativeFactor,Double> qualiFactorWeightMA;
  @FXML
  private TableColumn<QualitativeFactor,QualitativeFactor> qualiFactorValueMA;
  @FXML
  private TableColumn<QualitativeFactor,String> qualiFactorSourceMA;

  @FXML
  private TableView <QuantitativeFactor> quantiTableMA;

  @FXML
  private TableColumn<QuantitativeFactor,String> quantiFactorNameMA;
  @FXML
  private TableColumn<QuantitativeFactor,Double> quantiFactorWeightMA;
  @FXML
  private TableColumn<QuantitativeFactor,Double> quantiFactorValueMA;
  @FXML
  private TableColumn<QuantitativeFactor,String> quantiFactorSourceMA;

  @FXML
  private Label labelCS;

  @FXML
  private Label labelValue;

  @FXML
  private Label labelMA;

  @FXML
  private Button saveCSButton;

  @FXML
  private Button saveMAButton;

  @FXML
  private Button edit;

  @FXML
  private TextField textFieldValue;

  @FXML
  private TableView<QualitativeFactor> qualiTableCS;

  @FXML
  private TableColumn<QualitativeFactor, String> qualiFactorNameCS;

  @FXML
  private CheckBox qualitativeCheck;

  @FXML
  private CheckBox quantitativeCheck;

  @FXML
  private TableColumn<QualitativeFactor, Double> qualiFactorWeightCS;
  @FXML
  private TableColumn<QualitativeFactor,QualitativeValue.QualitativeValues> qualiFactorValueCS;
  @FXML
  private TableColumn<QualitativeFactor,String> qualiFactorSourceCS;

  @FXML
  private TableView<QuantitativeFactor> quantiTableCS;

  @FXML
  private TableColumn<QuantitativeFactor, String> quantiFactorNameCS;
  @FXML
  private TableColumn<QuantitativeFactor, Double> quantiFactorWeightCS;
  @FXML
  private TableColumn<QuantitativeFactor,Double> quantiFactorValueCS;
  @FXML
  private TableColumn<QuantitativeFactor,String> quantiFactorSourceCS;
  @FXML
  private ChoiceBox<QualitativeValue.QualitativeValues> choiceBox;

  public EditCountryViewController(){}

  @FXML
  public void init(ViewHandler2 viewHandler2, Region root, ModelIMS model){
    this.viewHandler2 = viewHandler2;
    this.root = root;
    this.model = model;
    choiceBox.getItems().add(0,model.getQualitativeValues(0));
    choiceBox.getItems().add(1,model.getQualitativeValues(1));
    choiceBox.getItems().add(2,model.getQualitativeValues(2));
    choiceBox.getItems().add(3,model.getQualitativeValues(3));
    choiceBox.getItems().add(4,model.getQualitativeValues(4));
    refresh();
  }

  public Region getRoot(){
    return root;
  }
  @FXML public void refresh(){
    quantiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
    quantiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
    quantiTableMA.getItems().clear();
    for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getMAFactors().size(); i++)
    {
      quantiTableMA.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getMAFactors().get(i));
    }
    qualiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
    qualiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
    qualiTableMA.getItems().clear();
    for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getMAFactors().size(); i++)
    {
      qualiTableMA.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getMAFactors().get(i));
    }
    quantiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
    quantiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
    quantiTableCS.getItems().clear();
    for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getCSFactors().size(); i++)
    {
      quantiTableCS.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getCSFactors().get(i));
    }
    qualiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
    qualiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
    qualiTableCS.getItems().clear();

    for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getCSFactors().size(); i++)
    {
      qualiTableCS.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getCSFactors().get(i));
    }

  }
  @FXML public void saveMA(){
    if(quantitativeCheck.isSelected()){
      model.getList3().getCountry(model.getSelectedCountryIndex()).setQuantitativeValueMA(quantiTableMA.getSelectionModel().getSelectedIndex(), Double.parseDouble(textFieldValue.getText()));
      System.out.println(model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors());

      quantiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
      quantiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
      quantiFactorValueMA.setCellValueFactory(new PropertyValueFactory<>("value"));
      quantiTableMA.getItems().clear();

      for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getMAFactors().size(); i++)
      {
        quantiTableMA.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getMAFactors().get(i));
      }
    }
    else if(qualitativeCheck.isSelected()){
      model.getList3().getCountry(model.getSelectedCountryIndex()).setQualitativeValueMA(qualiTableMA.getSelectionModel().getSelectedIndex(),choiceBox.getValue());

      System.out.println(model.getList2());


      qualiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
      qualiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
      qualiFactorValueMA.setCellValueFactory(new PropertyValueFactory<>("value"));
      qualiTableMA.getItems().clear();

      for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getMAFactors().size(); i++)
      {
        qualiTableMA.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getMAFactors().get(i));
      }
    }

    resetFields();
  }

  @FXML public void saveCS(){
    if(quantitativeCheck.isSelected()){
      model.getList3().getCountry(model.getSelectedCountryIndex()).setQuantitativeValueCS(quantiTableCS.getSelectionModel().getSelectedIndex(),Double.parseDouble(textFieldValue.getText()));
      System.out.println(model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors());

      quantiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
      quantiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
      quantiFactorValueCS.setCellValueFactory(new PropertyValueFactory<>("value"));
      quantiTableCS.getItems().clear();

      for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getMAFactors().size(); i++)
      {
        quantiTableCS.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQuantitativeFactors().getCSFactors().get(i));
      }
    }
    else if(qualitativeCheck.isSelected()){
      model.getList3().getCountry(model.getSelectedCountryIndex()).setQualitativeValueCS(qualiTableCS.getSelectionModel().getSelectedIndex(),choiceBox.getValue());
      System.out.println(model.getList2());
      qualiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
      qualiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
      qualiFactorValueCS.setCellValueFactory(new PropertyValueFactory<>("value"));
      qualiTableCS.getItems().clear();

      for (int i = 0; i < model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getCSFactors().size(); i++)
      {
        qualiTableCS.getItems().add(model.getList3().getCountry(model.getSelectedCountryIndex()).getQualitativeFactors().getCSFactors().get(i));
      }
    }

    resetFields();
  }

  @FXML
  public void editFactor(){
    if(quantiTableMA.getSelectionModel().isSelected(quantiTableMA.getSelectionModel().getSelectedIndex()))
    {
      textFieldValue.setText(String.valueOf(quantiTableMA.getSelectionModel().getSelectedItem().getValue()));
      quantitativeCheck.setSelected(true);
      textFieldValue.setDisable(false);
      choiceBox.setDisable(true);
    }
    else if (qualiTableMA.getSelectionModel().isSelected(qualiTableMA.getSelectionModel().getSelectedIndex()))
    {
      choiceBox.setValue(qualiTableMA.getSelectionModel().getSelectedItem().getValue());
      qualitativeCheck.setSelected(true);
      textFieldValue.setDisable(true);
      choiceBox.setDisable(false);

    }
    if(quantiTableCS.getSelectionModel().isSelected(quantiTableCS.getSelectionModel().getSelectedIndex()))
    {
      textFieldValue.setText(String.valueOf(quantiTableCS.getSelectionModel().getSelectedItem().getValue()));
      quantitativeCheck.setSelected(true);
      textFieldValue.setDisable(false);
      choiceBox.setDisable(true);
    }
    else if (qualiTableCS.getSelectionModel().isSelected(qualiTableCS.getSelectionModel().getSelectedIndex()))
    {
      choiceBox.setValue(qualiTableCS.getSelectionModel().getSelectedItem().getValue());
      qualitativeCheck.setSelected(true);
      textFieldValue.setDisable(true);
      choiceBox.setDisable(false);
    }
  }
  @FXML
  public void resetFields(){
     textFieldValue.setText("");
     qualitativeCheck.setSelected(false);
     quantitativeCheck.setSelected(false);
  }

  @FXML
  public void back(){
    choiceBox.getItems().clear();
    viewHandler2.openView("Manage Countries");
  }
}
