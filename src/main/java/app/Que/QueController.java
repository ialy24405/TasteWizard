package app.Que;

import app.Categoryis.CategoryisApplication;
import app.Categoryis.CategoryisController;
import app.Classes.User;
import app.HomePage.HomePageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class QueController {
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
            if (category.equalsIgnoreCase("Bevarages")) {
                category = "Beverage";
                FXMLLoader fxmlLoader = new FXMLLoader(CategoryisApplication.class.getResource("Categoryis.fxml"));
                try {
                    AnchorPane pane = fxmlLoader.load();
                    CategoryisController controller = fxmlLoader.getController();
                    controller.setUser(user);
                    homePageController.setPane(pane);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            } else if (category.equalsIgnoreCase("Sweet Snacks")) {
                category = "SweetSnakes";
            } else if (category.equalsIgnoreCase("Salty Snacks")) {
                category = "SaltySnakes";
            }
//            System.out.println("Category: " + category);
            homePageController.setUser(user);
            homePageController.setCategory(category);
            Stage stage = (Stage) ProceedButton.getScene().getWindow();
            stage.close();
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
