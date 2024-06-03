package app.ResultCategory;

import app.Categoryis.CategoryisApplication;
import app.Categoryis.CategoryisController;
import app.Classes.User;
import app.HomePage.HomePageController;
import app.Results.ResultsApplication;
import app.Results.ResultsController;
import app.Snacks.SnacksApplication;
import app.Snacks.SnacksController;
import app.SweetSnackes.SweetSnacksApplication;
import app.SweetSnackes.SweetSnacksController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResultCategoryController {
    private User user;
    @FXML
    private ComboBox<String> Category;
    @FXML
    private Button ProceedButton;
    public void initialize() {
        Category.getItems().add("Bevarages");
        Category.getItems().add("Sweet Snacks");
        Category.getItems().add("Salty Snacks");
        ProceedButton.setOnAction(ProceedButtonClicked());
    }
    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            String category = Category.getValue();
            if(category == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please select a category");
                alert.showAndWait();
                return;
            }
            if (category.equalsIgnoreCase("Bevarages")) {
                category = "Beverage";
//                FXMLLoader fxmlLoader = new FXMLLoader(CategoryisApplication.class.getResource("Categoryis.fxml"));
//                try {
//                    AnchorPane pane = fxmlLoader.load();
//                    CategoryisController controller = fxmlLoader.getController();
//                    controller.setUser(user);
//                    homePageController.setPane(pane);
//                } catch (Exception ex) {
//                    System.out.println("Error: " + ex.getMessage());
//                    ex.printStackTrace();
//                }
            } else if (category.equalsIgnoreCase("Sweet Snacks")) {
                category = "SweetSnakes";
//                FXMLLoader fxmlLoader = new FXMLLoader(SweetSnacksApplication.class.getResource("SweetSnackes.fxml"));
//                try {
//                    AnchorPane pane = fxmlLoader.load();
//                    SweetSnacksController controller = fxmlLoader.getController();
//                    controller.setUser(user);
//                    homePageController.setPane(pane);
//                } catch (Exception ex) {
//                    System.out.println("Error: " + ex.getMessage());
//                    ex.printStackTrace();
//                }
            } else if (category.equalsIgnoreCase("Salty Snacks")) {
                category = "SaltySnakes";
//                FXMLLoader fxmlLoader = new FXMLLoader(SnacksApplication.class.getResource("Snacks.fxml"));
//                try {
//                    AnchorPane pane = fxmlLoader.load();
//                    SnacksController controller = fxmlLoader.getController();
//                    controller.setUser(user);
//                    homePageController.setPane(pane);
//                } catch (Exception ex) {
//                    System.out.println("Error: " + ex.getMessage());
//                    ex.printStackTrace();
//                }

            }
                FXMLLoader fxmlLoader = new FXMLLoader(ResultsApplication.class.getResource("Results.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                    ResultsController controller = fxmlLoader.getController();
                    controller.setUser(user);
                    controller.setCategory(category);
                } catch (Exception exception) {
                    System.out.println("Error in loading TasteWizardHome.fxml: " + exception.getMessage());
                    exception.printStackTrace();
                }
                homePageController.setScene(scene);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.close();
//            System.out.println("Category: " + category);
//            homePageController.setUser(user);
//            homePageController.setCategory(category);
//            Stage stage = (Stage) ProceedButton.getScene().getWindow();
//            stage.close();
        };
    }

    public void setUser(User user) {
        this.user = user;
    }
    private HomePageController homePageController;
    public void setHomePageController(HomePageController homePageController) {
        this.homePageController = homePageController;
    }
}
