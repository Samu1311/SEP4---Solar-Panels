import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.Optional;

public class ManageFactorsViewController
{

    private Region root;
    private ViewHandler2 viewHandler2;
    private ModelIMS model;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView <Factor> qualiTableMA;

    @FXML
    private TableColumn<Factor,String> qualiFactorNameMA;
    @FXML
    private TableColumn<Factor,Double> qualiFactorWeightMA;
    @FXML
    private TableView <Factor> quantiTableMA;

    @FXML
    private TableColumn<Factor,String> quantiFactorNameMA;
    @FXML
    private TableColumn<Factor,Double> quantiFactorWeightMA;

    @FXML
    private Label labelCS;

    @FXML
    private Label labelFactor;

    @FXML
    private Label labelMA;

    @FXML
    private Label labelWeight;

    @FXML
    private Button saveCSButton;

    @FXML
    private Button saveMAButton;

    @FXML
    private Button edit;


    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldWeight;
    @FXML
    private TableView<Factor> qualiTableCS;

    @FXML
    private TableColumn<Factor, String> qualiFactorNameCS;

    @FXML
    private CheckBox qualitativeCheck;

    @FXML
    private CheckBox quantitativeCheck;

    @FXML
    private TableColumn<Factor, Double> qualiFactorWeightCS;

    @FXML
    private TableView<Factor> quantiTableCS;

    @FXML
    private TableColumn<Factor, String> quantiFactorNameCS;
    @FXML
    private TableColumn<Factor, Double> quantiFactorWeightCS;

    @FXML
    private Label percentageLabelMA;
    @FXML
    private Label percentageLabelCS;


    public ManageFactorsViewController(){};

    public void init(ViewHandler2 viewHandler2, Region root, ModelIMS model){
        this.viewHandler2 = viewHandler2;
        this.root = root;
        this.model = model;
        quantiTableMA.getItems().clear();
        quantiTableCS.getItems().clear();
        qualiTableMA.getItems().clear();
        qualiTableCS.getItems().clear();

    }


    public Region getRoot(){
        return root;
    }

    @FXML public void saveMA(){
        if(quantitativeCheck.isSelected()){
            if (!(quantiTableMA.getSelectionModel().isSelected(quantiTableMA.getSelectionModel().getSelectedIndex()))){
                double weight = Double.parseDouble(textFieldWeight.getText());
                QuantitativeFactor factor = new QuantitativeFactor(textFieldName.getText(), weight, true);
                model.getList1().addFactor(factor);
                System.out.println(model.getList1());
            }
            else
            {
                model.getList1().getMAFactors().get(quantiTableMA.getSelectionModel().getSelectedIndex()).setName(textFieldName.getText());
                model.getList1().getMAFactors().get(quantiTableMA.getSelectionModel().getSelectedIndex()).setWeight(Double.parseDouble(textFieldWeight.getText()));
                System.out.println(model.getList1());
            }
            quantiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
            quantiTableMA.getItems().clear();

            for (int i = 0; i < model.getList1().getMAFactors().size(); i++)
            {
                quantiTableMA.getItems().add(model.getList1().getMAFactors().get(i));
            }
            resetFields();
        }
        else if(qualitativeCheck.isSelected()){
            if (!(qualiTableMA.getSelectionModel().isSelected(qualiTableMA.getSelectionModel().getSelectedIndex()))){
                double weight = Double.parseDouble(textFieldWeight.getText());
                QualitativeFactor factor2 = new QualitativeFactor(textFieldName.getText(), weight, true);
                model.getList2().addFactor(factor2);
                System.out.println(model.getList2());
            }
            else
            {
                model.getList2().getMAFactors().get(qualiTableMA.getSelectionModel().getSelectedIndex()).setName(textFieldName.getText());
                model.getList2().getMAFactors().get(qualiTableMA.getSelectionModel().getSelectedIndex()).setWeight(Double.parseDouble(textFieldWeight.getText()));
                System.out.println(model.getList2());
            }
            qualiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
            qualiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
            qualiTableMA.getItems().clear();

            for (int i = 0; i < model.getList2().getMAFactors().size(); i++)
            {
                qualiTableMA.getItems().add(model.getList2().getMAFactors().get(i));
            }
            resetFields();
        }
        else{
            Alert type = new Alert(AlertType.ERROR, "Type of factor not selected, please select value",ButtonType.CLOSE);
            type.showAndWait();
            if(type.getResult() == ButtonType.CLOSE)
                type.close();
        }
        percentageLabelMA.setText(String.valueOf(100 - ((model.getList1().calculateMAPercentage()) + model.getList2().calculateMAPercentage())));

    }

    @FXML public void saveCS(){
        if(quantitativeCheck.isSelected()){
            if (!(quantiTableCS.getSelectionModel().isSelected(quantiTableCS.getSelectionModel().getSelectedIndex()))){
                double weight = Double.parseDouble(textFieldWeight.getText());
                QuantitativeFactor factor = new QuantitativeFactor(textFieldName.getText(), weight, false);
                model.getList1().addFactor(factor);
                System.out.println(model.getList1());
            }
            else
            {
                model.getList1().getCSFactors().get(quantiTableCS.getSelectionModel().getSelectedIndex()).setName(textFieldName.getText());
                model.getList1().getCSFactors().get(quantiTableCS.getSelectionModel().getSelectedIndex()).setWeight(Double.parseDouble(textFieldWeight.getText()));
                System.out.println(model.getList1());
            }
            quantiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
            quantiTableCS.getItems().clear();

            for (int i = 0; i < model.getList1().getCSFactors().size(); i++)
            {
                quantiTableCS.getItems().add(model.getList1().getCSFactors().get(i));
            }
            resetFields();
        }
        else if(qualitativeCheck.isSelected()){
            if (!(qualiTableCS.getSelectionModel().isSelected(qualiTableCS.getSelectionModel().getSelectedIndex()))){
                double weight = Double.parseDouble(textFieldWeight.getText());
                QualitativeFactor factor2 = new QualitativeFactor(textFieldName.getText(), weight, false);
                model.getList2().addFactor(factor2);
                System.out.println(model.getList2());
            }
            else
            {
                model.getList2().getCSFactors().get(qualiTableCS.getSelectionModel().getSelectedIndex()).setName(textFieldName.getText());
                model.getList2().getCSFactors().get(qualiTableCS.getSelectionModel().getSelectedIndex()).setWeight(Double.parseDouble(textFieldWeight.getText()));
                System.out.println(model.getList2());
            }
            qualiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
            qualiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
            qualiTableCS.getItems().clear();

            for (int i = 0; i < model.getList2().getCSFactors().size(); i++)
            {
                qualiTableCS.getItems().add(model.getList2().getCSFactors().get(i));
            }
            resetFields();
        }
        else{
            Alert type = new Alert(AlertType.ERROR, "Type of factor not selected, please select value",ButtonType.CLOSE);
            type.showAndWait();
            if(type.getResult() == ButtonType.CLOSE)
                type.close();
        }

        percentageLabelCS.setText(String.valueOf(100 - ((model.getList1().calculateCSPercentage()) + model.getList2().calculateCSPercentage())));
    }
    @FXML public void editFactor(){
        if(quantiTableMA.getSelectionModel().isSelected(quantiTableMA.getSelectionModel().getSelectedIndex()))
        {
            textFieldName.setText(quantiTableMA.getSelectionModel().getSelectedItem().getName());
            textFieldWeight.setText(String.valueOf(quantiTableMA.getSelectionModel().getSelectedItem().getWeight()));
            quantitativeCheck.setSelected(true);
        }
        else if (qualiTableMA.getSelectionModel().isSelected(qualiTableMA.getSelectionModel().getSelectedIndex()))
        {
            textFieldName.setText(qualiTableMA.getSelectionModel().getSelectedItem().getName());
            textFieldWeight.setText(String.valueOf(qualiTableMA.getSelectionModel().getSelectedItem().getWeight()));
            qualitativeCheck.setSelected(true);

        }
        if(quantiTableCS.getSelectionModel().isSelected(quantiTableCS.getSelectionModel().getSelectedIndex()))
        {
            textFieldName.setText(quantiTableCS.getSelectionModel().getSelectedItem().getName());
            textFieldWeight.setText(String.valueOf(quantiTableCS.getSelectionModel().getSelectedItem().getWeight()));
            quantitativeCheck.setSelected(true);
        }
        else if (qualiTableCS.getSelectionModel().isSelected(qualiTableCS.getSelectionModel().getSelectedIndex()))
        {
            textFieldName.setText(qualiTableCS.getSelectionModel().getSelectedItem().getName());
            textFieldWeight.setText(String.valueOf(qualiTableCS.getSelectionModel().getSelectedItem().getWeight()));
            qualitativeCheck.setSelected(true);
        }

    }

    @FXML public void deleteFactor(){
        if(quantiTableMA.getSelectionModel().isSelected(quantiTableMA.getSelectionModel().getSelectedIndex())){
            model.getList1().removeFactorByName(model.getList1().getMAFactors().get(quantiTableMA.getSelectionModel


                ().getSelectedIndex()).getName());
            System.out.println(model.getList1());
            quantiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
            quantiTableMA.getItems().clear();

            for (int i = 0; i < model.getList1().getMAFactors().size(); i++)
            {
                quantiTableMA.getItems().add(model.getList1().getMAFactors().get(i));
            }
        }
        else if (qualiTableMA.getSelectionModel().isSelected(qualiTableMA.getSelectionModel().getSelectedIndex())){
            model.getList2().removeFactorByName(model.getList2().getMAFactors().get(qualiTableMA.getSelectionModel().getSelectedIndex()).getName());
            qualiFactorNameMA.setCellValueFactory(new PropertyValueFactory<>("name"));
            qualiFactorWeightMA.setCellValueFactory(new PropertyValueFactory<>("weight"));
            qualiTableMA.getItems().clear();

            for (int i = 0; i < model.getList2().getMAFactors().size(); i++)
            {
                qualiTableMA.getItems().add(model.getList2().getMAFactors().get(i));
            }
        }
        if(quantiTableCS.getSelectionModel().isSelected(quantiTableCS.getSelectionModel().getSelectedIndex())){
            model.getList1().removeFactorByName(model.getList1().getCSFactors().get(quantiTableCS.getSelectionModel().getSelectedIndex()).getName());
            System.out.println(model.getList1());
            quantiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
            quantiTableCS.getItems().clear();

            for (int i = 0; i < model.getList1().getCSFactors().size(); i++)
            {
                quantiTableCS.getItems().add(model.getList1().getCSFactors().get(i));
            }
        }
        else if (qualiTableCS.getSelectionModel().isSelected(qualiTableCS.getSelectionModel().getSelectedIndex())){
            model.getList2().removeFactorByName(model.getList2().getCSFactors().get(qualiTableCS.getSelectionModel().getSelectedIndex()).getName());
            qualiFactorNameCS.setCellValueFactory(new PropertyValueFactory<>("name"));
            qualiFactorWeightCS.setCellValueFactory(new PropertyValueFactory<>("weight"));
            qualiTableCS.getItems().clear();

            for (int i = 0; i < model.getList2().getCSFactors().size(); i++)
            {
                qualiTableCS.getItems().add(model.getList2().getCSFactors().get(i));
            }
        }

    }
    public void resetFields()
    {
        textFieldName.setText("");
        textFieldWeight.setText("");
        qualitativeCheck.setSelected(false);
        quantitativeCheck.setSelected(false);
    }

    @FXML public void back(){
        Alert noMoreEditing = new Alert(AlertType.CONFIRMATION);
        noMoreEditing.setContentText("Are you sure you want to go back? You will not be able to edit the factors");
        Optional<ButtonType> result = noMoreEditing.showAndWait();
        if(result.get() == ButtonType.OK)
            viewHandler2.openView("Home Page 2");
        else if (result.get() == ButtonType.CANCEL)
            noMoreEditing.close();
    }
}