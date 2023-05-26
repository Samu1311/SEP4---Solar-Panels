package View;

import View.DatabaseConnector;
import View.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
    public void start(Stage primaryStage)
    {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        ViewHandler view = new ViewHandler(databaseConnector);
        view.start(primaryStage);
    }
}