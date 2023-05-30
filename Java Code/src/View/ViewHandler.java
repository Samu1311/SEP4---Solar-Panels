package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {
    private static ViewHandler instance;
    private final Scene currentScene;
    private Stage primaryStage;
    private final DatabaseConnector databaseConnector;

    private LoginViewController loginViewController;
    private HomeScreenViewController homeScreenViewController;
    private EnergyReportEditViewController energyReportEditViewController;
    private EnergyReportDisplayViewController energyReportDisplayViewController;
    private PanelSeriesInputViewController panelSeriesInputViewController;
    private PanelSeriesDisplayViewController panelSeriesDisplayViewController;
    private SolarPanelEditViewController solarPanelEditViewController;
    private SolarPanelFilterViewController solarPanelFilterViewController;
    private SolarPanelDisplayViewController solarPanelDisplayViewController;
    private ModelEditViewController modelEditViewController;
    private ModelDisplayViewController modelDisplayViewController;
    private ManufacturerEditViewController manufacturerEditViewController;
    private ManufacturerDisplayViewController manufacturerDisplayViewController;
    private HistoricalTableEditViewController historicalTableEditViewController;
    private HistoricalTableDisplayViewController historicalTableDisplayViewController;
    private MalfunctioningPanelsViewController malfunctioningPanelsViewController;

    private Region root;

    public ViewHandler(DatabaseConnector databaseConnector) {
        this.currentScene = new Scene(new Region());
        this.databaseConnector = databaseConnector;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("Login Page");
        primaryStage.show();
    }

    public void openView(String window) {
        root = null;
        switch (window) {
            case "Login Page":
                root = loadLoginView("LoginViewController.fxml");
                break;
            case "Home Page":
                root = loadHomePageView("HomeScreenViewController.fxml");
                break;
            // Series
            case "Panel Series Display":
                root = loadPanelSeriesDisplayView("PanelSeriesDisplayViewController.fxml");
                break;
            case "Panel Series Input":
                root = loadPanelSeriesInputView("PanelSeriesInputViewController.fxml");
                break;
            // Energy Report
            case "Energy Report Edit":
                root = loadEnergyReportEdit("EnergyReportEditViewController.fxml");
                break;
            case "Energy Report Display":
                root = loadEnergyReportDisplayView("EnergyReportDisplayViewController.fxml");
                break;
            // Historical Tables
            case "Historical Table Edit":
                root = loadHistoricalTableEditView("HistoricalTableEditViewController.fxml");
                break;
            case "Historical Table Display":
                root = loadHistoricalTableDisplay("HistoricalTableDisplayViewController.fxml");
                break;
            // Malfunctioning Panels
            case "Panel Display":
                root = loadMalfunctioningView("MalfunctioningPanelsViewController.fxml");
                break;
            // Manufacturers
            case "Manufacturer Edit":
                root = loadManufacturerEditView("ManufacturerEditViewController.fxml");
                break;
            case "Manufacturer Display":
                root = loadManufacturerDisplayView("ManufacturerDisplayViewController.fxml");
                break;
            // Models
            case "Model Edit":
                root = loadModelEditView("ModelEditViewController.fxml");
                break;
            case "Model Display":
                root = loadModelDisplayView("ModelDisplayViewController.fxml");
                break;
            // Solar Panels
            case "Solar Panel Edit":
                root = loadSolarPanelEditView("SolarPanelEditViewController.fxml");
                break;
            case "Solar Panel Display":
                root = loadSolarPanelDisplayView("SolarPanelDisplayViewController.fxml");
                break;
            case "Solar Panel Filter":
                root = loadSolarPanelFilterView("SolarPanelFilterViewController.fxml");
                break;
        }
        if (root != null) {
            currentScene.setRoot(root);
            primaryStage.setScene(currentScene);
            primaryStage.setTitle(window);
            primaryStage.setWidth(root.getPrefWidth());
            primaryStage.setHeight(root.getPrefHeight());
            primaryStage.centerOnScreen();
        }
    }

    // Load methods for each view

    private Region loadLoginView(String fxmlFile) {
        if (loginViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loginViewController.reset();
        }
        return loginViewController.getRoot();
    }

    private Region loadHomePageView(String fxmlFile) {
        if (homeScreenViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                homeScreenViewController = loader.getController();
                homeScreenViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return homeScreenViewController.getRoot();
    }

    private Region loadEnergyReportEdit(String fxmlFile) {
        if (energyReportEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                energyReportEditViewController = loader.getController();
                energyReportEditViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return energyReportEditViewController.getRoot();
    }

    private Region loadEnergyReportDisplayView(String fxmlFile) {
        if (energyReportDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                energyReportDisplayViewController = loader.getController();
                energyReportDisplayViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return energyReportDisplayViewController.getRoot();
    }

    private Region loadPanelSeriesInputView(String fxmlFile) {
        if (panelSeriesInputViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                panelSeriesInputViewController = loader.getController();
                panelSeriesInputViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return panelSeriesInputViewController.getRoot();
    }

    private Region loadPanelSeriesDisplayView(String fxmlFile) {
        if (panelSeriesDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                panelSeriesDisplayViewController = loader.getController();
                panelSeriesDisplayViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return panelSeriesDisplayViewController.getRoot();
    }

    private Region loadSolarPanelEditView(String fxmlFile) {
        if (solarPanelEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                solarPanelEditViewController = loader.getController();
                solarPanelEditViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return solarPanelEditViewController.getRoot();
    }

    private Region loadSolarPanelFilterView(String fxmlFile) {
        if (solarPanelFilterViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                solarPanelFilterViewController = loader.getController();
                solarPanelFilterViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return solarPanelFilterViewController.getRoot();
    }

    private Region loadSolarPanelDisplayView(String fxmlFile) {
        if (solarPanelDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                solarPanelDisplayViewController = loader.getController();
                solarPanelDisplayViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return solarPanelDisplayViewController.getRoot();
    }

    private Region loadModelEditView(String fxmlFile) {
        if (modelEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                modelEditViewController = loader.getController();
                modelEditViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelEditViewController.getRoot();
    }

    private Region loadModelDisplayView(String fxmlFile) {
        if (modelDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                modelDisplayViewController = loader.getController();
                modelDisplayViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelDisplayViewController.getRoot();
    }

    private Region loadManufacturerEditView(String fxmlFile) {
        if (manufacturerEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                manufacturerEditViewController = loader.getController();
                manufacturerEditViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return manufacturerEditViewController.getRoot();
    }

    private Region loadManufacturerDisplayView(String fxmlFile) {
        if (manufacturerDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                manufacturerDisplayViewController = loader.getController();
                manufacturerDisplayViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return manufacturerDisplayViewController.getRoot();
    }

    private Region loadHistoricalTableEditView(String fxmlFile) {
        if (historicalTableEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                historicalTableEditViewController = loader.getController();
                historicalTableEditViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return historicalTableEditViewController.getRoot();
    }

    private Region loadHistoricalTableDisplay(String fxmlFile) {
        if (historicalTableDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                historicalTableDisplayViewController = loader.getController();
                historicalTableDisplayViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return historicalTableDisplayViewController.getRoot();
    }

    private Region loadMalfunctioningView(String fxmlFile) {
        if (malfunctioningPanelsViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                Region root = loader.load();
                malfunctioningPanelsViewController = loader.getController();
                malfunctioningPanelsViewController.init(this, root, databaseConnector);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return malfunctioningPanelsViewController.getRoot();
    }
}
