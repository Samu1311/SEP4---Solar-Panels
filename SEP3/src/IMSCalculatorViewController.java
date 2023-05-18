import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class IMSCalculatorViewController implements Initializable
{

  private Region root;
  private ModelIMS model;
  private ViewHandler2 viewHandler2;

  @FXML private NumberAxis CSaxis;

  @FXML private NumberAxis MAaxis;

  @FXML private BubbleChart<Double, Double> bubbleChart;

  public IMSCalculatorViewController() {}

  public Region getRoot()
  {
    return root;
  }

  public void init(ViewHandler2 viewHandler2, Region root, ModelIMS model)
  {
    this.viewHandler2 = viewHandler2;
    this.root = root;
    this.model = model;
    model.getList3().defineRange();
    model.getList3().assignQuantitativePoints();
    model.getList3().assignQualitativePoints();
    model.getList3().weightedMAPointsCalculation();
    model.getList3().weightedCSPointsCalculation();
    System.out.println(model.getList3().getCountries().get(0).getCompetitiveStrengthPunctuation());
    System.out.println(model.getList3().getCountries().get(0).getMarketAttractivenessPunctuation());
    System.out.println(model.getList3().getCountries().get(1).getCompetitiveStrengthPunctuation());
    System.out.println(model.getList3().getCountries().get(1).getMarketAttractivenessPunctuation());
    System.out.println(model.getList3().getCountries().get(1).getQuantitativeFactors());

    for (int i = 0; i < model.getList3().getCountries().size(); i++)
    {
      XYChart.Series series = new XYChart.Series();
      series.setName(model.getList3().getCountry(i).getName());
      series.getData().add(new XYChart.Data(
          model.getList3().getCountries().get(i).getMarketAttractivenessPunctuation(),
          model.getList3().getCountries().get(i).getCompetitiveStrengthPunctuation(),0.2));
      bubbleChart.getData().add(series);
    }
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
  }

  @FXML public void back(){
    viewHandler2.openView("Home Page 2");
  }
}
