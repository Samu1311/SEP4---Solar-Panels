import Model.ModelIMS;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    ModelIMS model = new ModelIMS();

    ViewHandler2 view = new ViewHandler2(model);
    view.start(primaryStage);
  }
}