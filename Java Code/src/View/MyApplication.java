package View;

import View.DatabaseConnector;
import View.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
    public void start(Stage primaryStage)
    {

        ViewHandler view = new ViewHandler( DatabaseConnector.getInstance());
        view.start(primaryStage);
    }
}