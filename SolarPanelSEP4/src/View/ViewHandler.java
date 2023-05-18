package View;

import Model.ModelSP;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;
    private ModelSP model;

    private LoginViewController loginViewController;
    private HomeScreenViewController homeScreenViewController;
    private EnergyReportEditViewController energyReportEditViewController;
    private EnergyReportDisplayViewController energyReportDisplayViewController;
    private AddSeriesViewController addSeriesViewController;
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

     public ViewHandler(ModelSP model)
        {
            this.currentScene = new Scene(new Region());
            this.model = model;
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
            case "Add Series":
                root = loadAddSeriesView("AddSeriesViewController.fxml");
                break;
            case "Panel Series Display":
                root = loadPanelSeriesDisplayView("PanelSeriesDisplayViewController.fxml");
                break;
            case "Panel Series Input":
                root = loadPanelSeriesInputView("PanelSeriesInputViewController.fxml");
                break;
            //Energy Report
            case "Energy Report Edit":
                root = loadEnergyReportEdit("EnergyReportEditViewController.fxml");
                break;
            case "Energy Report Display":
                root = loadEnergyReportDisplayView("EnergyReportDisplayViewController.fxml");
                break;
            //Historical Tables
            case "Historical Table Edit":
                root = loadHistoricalTableEditView("HistoricalTableEditViewController.fxml");
                break;
            case "Historical Table Display":
                root = loadHistoricalTableDisplay("HistoricalTableDisplayViewController.fxml");
                break;
            //Malfunctioning Panels
            case "Panel Display":
                root = loadMalfunctioningView("MalfunctioningPanelsViewController.fxml");
                break;
            //Manufacturers
            case "Manufacturer Edit":
                root = loadManufacturerEditView("ManufacturerEditViewController.fxml");
                break;
            case "Manufacturer Display":
                root = loadManufacturerDisplayView("ManufacturerDisplayViewController.fxml");
                break;
            //Models
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
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
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

    public void closeView() {
        primaryStage.close();
    }

    private Region loadLoginView(String fxmlFile) {
        if (loginViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                loginViewController = loader.getController();
                loginViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = loginViewController.getRoot();
                loginViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadAddSeriesView(String fxmlFile) {
        if (addSeriesViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                addSeriesViewController = loader.getController();
                addSeriesViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = addSeriesViewController.getRoot();
                addSeriesViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadHomePageView(String fxmFile) {
        if (homeScreenViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                homeScreenViewController = loader.getController();
                homeScreenViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = homeScreenViewController.getRoot();
                homeScreenViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }


    private Region loadEnergyReportEdit(String fxmFile) {
        if (energyReportEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                energyReportEditViewController = loader.getController();
                energyReportEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = energyReportEditViewController.getRoot();
                energyReportEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadEnergyReportDisplayView(String fxmFile) {
        if (energyReportDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                energyReportDisplayViewController = loader.getController();
                energyReportDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = energyReportDisplayViewController.getRoot();
                energyReportDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }


    private Region loadPanelSeriesInputView(String fxmFile) {
        if (panelSeriesInputViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                panelSeriesInputViewController = loader.getController();
                panelSeriesInputViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = panelSeriesInputViewController.getRoot();
                panelSeriesInputViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }


    private Region loadPanelSeriesDisplayView(String fxmFile) {
        if (panelSeriesDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                panelSeriesDisplayViewController = loader.getController();
                panelSeriesDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = panelSeriesDisplayViewController.getRoot();
                panelSeriesDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadSolarPanelEditView(String fxmFile) {
        if (solarPanelEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                solarPanelEditViewController = loader.getController();
                solarPanelEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = solarPanelEditViewController.getRoot();
                solarPanelEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadSolarPanelFilterView(String fxmFile) {
        if (solarPanelFilterViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                solarPanelFilterViewController = loader.getController();
                solarPanelFilterViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = solarPanelFilterViewController.getRoot();
                solarPanelFilterViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadSolarPanelDisplayView(String fxmFile) {
        if (solarPanelDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                solarPanelDisplayViewController = loader.getController();
                solarPanelDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = solarPanelDisplayViewController.getRoot();
                solarPanelDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadModelDisplayView(String fxmFile) {
        if (modelDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                modelDisplayViewController = loader.getController();
                modelDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = modelDisplayViewController.getRoot();
                modelDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadModelEditView(String fxmFile) {
        if (modelEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                modelEditViewController = loader.getController();
                modelEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = modelEditViewController.getRoot();
                modelEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadManufacturerDisplayView(String fxmFile) {
        if (manufacturerDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                manufacturerDisplayViewController = loader.getController();
                manufacturerDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = manufacturerDisplayViewController.getRoot();
                manufacturerDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }
    private Region loadManufacturerEditView(String fxmFile) {
        if (manufacturerEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                manufacturerEditViewController = loader.getController();
                manufacturerEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = manufacturerEditViewController.getRoot();
                manufacturerEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadHistoricalTableEditView(String fxmFile) {
        if (historicalTableEditViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                historicalTableEditViewController = loader.getController();
                historicalTableEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = historicalTableEditViewController.getRoot();
                historicalTableEditViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadHistoricalTableDisplay(String fxmFile) {
        if (historicalTableDisplayViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                historicalTableDisplayViewController = loader.getController();
                historicalTableDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = historicalTableDisplayViewController.getRoot();
                historicalTableDisplayViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private Region loadMalfunctioningView(String fxmFile) {
        if (malfunctioningPanelsViewController== null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                malfunctioningPanelsViewController = loader.getController();
                malfunctioningPanelsViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = malfunctioningPanelsViewController.getRoot();
                malfunctioningPanelsViewController.init(this, root, model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return root;
    }
}





