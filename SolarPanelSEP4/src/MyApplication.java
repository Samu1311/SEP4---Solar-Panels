import View.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.ModelSP;

public class MyApplication extends Application
{
    public void start(Stage primaryStage)
    {
        ModelSP model = new ModelSP();

        ViewHandler view = new ViewHandler(model);
        view.start(primaryStage);
    }
}