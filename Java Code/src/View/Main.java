package View;

import Generators.PvGenerator;
import Generators.ThermoGenerator;
import View.MyApplication;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Create and start a thread for data generation
        /*Thread dataGenerationThread = new Thread(() -> {
            PvGenerator pvGenerator = new PvGenerator();
            ThermoGenerator thermoGenerator = new ThermoGenerator();

            pvGenerator.run();
            thermoGenerator.run();
        });
        dataGenerationThread.start();*/
        // Launch the application
        Application.launch(MyApplication.class);
    }
}
