import Model.ModelIMS;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler2
{
  private Scene currentScene;
  private Stage primaryStage;

  private ManageFactorsViewController manageFactorsViewController;
  private ManageCountriesViewController manageCountriesViewController;
  private HomePageViewController homePageViewController;
  private EditCountryViewController editCountryViewController;
  private IMSCalculatorViewController imsCalculatorViewController;
  private ModelIMS model;
  private Region root;

  public ViewHandler2(ModelIMS model)
  {
    this.currentScene = new Scene(new Region());
    this.model = model;
  }

  public void openView(String window){
    root = null;
    switch(window){
      case "Home Page":
        root = loadHomePageView("HomePageView.fxml");
        break;
      case "Manage Factors":
        root = loadManageFactorsView("ManageFactorsViewController.fxml");
        break;
      case "Manage Countries":
        root = loadManageCountriesView("ManageCountriesView.fxml");
        break;
      case "Edit Country":
        root = loadEditCountryView("EditCountryView.fxml");
        break;
      case "IMS Calculator":
        root = loadIMSCalculatorView("IMSCalculatorViewController.fxml");
        break;
      case "Home Page 2":
        root = loadHomePageView("HomePageView2.fxml");
        break;
      }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null){
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("Home Page");
  }
  public void closeView(){
    primaryStage.close();
  }

  private Region loadManageFactorsView(String fxmlFile)
  {
    if(manageFactorsViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        root = loader.load();
        manageFactorsViewController = loader.getController();
        manageFactorsViewController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else{
      try
      {
        root =  manageFactorsViewController.getRoot();
        manageFactorsViewController.init(this,root,model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }

  private Region loadHomePageView(String fxmFile)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmFile));
      root = loader.load();
      homePageViewController = loader.getController();
      homePageViewController.init(this, root,model);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return root;
  }


  private Region loadManageCountriesView(String fxmFile){
    if (manageCountriesViewController == null){
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmFile));
        root = loader.load();
        manageCountriesViewController = loader.getController();
        manageCountriesViewController.init(this, root,model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else{
      try
      {
        root = manageCountriesViewController.getRoot();
        manageCountriesViewController.init(this, root,model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }

  private Region loadEditCountryView(String fxmFile){
    if (editCountryViewController == null){
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmFile));
        root = loader.load();
        editCountryViewController = loader.getController();
        editCountryViewController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      try
      {
        root = editCountryViewController.getRoot();
        editCountryViewController.init(this, root, model);
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return root;
  }

  private Region loadIMSCalculatorView(String fxmFile){
    if (imsCalculatorViewController == null){
      try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmFile));
        root = loader.load();
        imsCalculatorViewController = loader.getController();
        imsCalculatorViewController.init(this, root, model);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return root;
  }
}


