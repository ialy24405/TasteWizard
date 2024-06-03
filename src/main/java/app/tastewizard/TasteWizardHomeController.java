package app.tastewizard;

import app.Classes.User;
import app.Database.DB;
import app.HomePage.HomePage;
import app.HomePage.HomePageController;
import app.Register.RegisterPage;
import app.Register.RegisterPageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TasteWizardHomeController {
    @FXML
    private Button RegisterButton;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField ID;
    @FXML
    private TextField Password;
    public void initialize() {
        RegisterButton.setOnAction(RegisterButtonClicked());
        LoginButton.setOnAction(LoginButtonClicked());
    }

    private EventHandler<ActionEvent> LoginButtonClicked() {
        return e -> {
            String id = ID.getText();
            String password = Password.getText();
            User user = new User(id, password);
            DB db = new DB();
            if (db.login(user)) {
                FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                    HomePageController controller = fxmlLoader.getController();
                    controller.setUser(user);
                    controller.setprofile();
                } catch (Exception exception) {
                    System.out.println("Error in loading TasteWizardHome.fxml: " + exception.getMessage());
                    exception.printStackTrace();
                }
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                // Set the scene for the stage
                stage.setScene(scene);
                // Show the stage
                stage.show();
//                System.out.println("Login successful");
            } else {
                ID.setText("");
                Password.setText("");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid ID or Password");
                alert.setContentText("Please enter a valid ID and Password");
                alert.showAndWait();

            }
        };
    }

    private EventHandler<ActionEvent> RegisterButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(RegisterPage.class.getResource("RegisterPage.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                RegisterPageController controller = fxmlLoader.getController();
            } catch (Exception exception) {
                System.out.println("Error in loading RegisterPage.fxml: "+exception.getMessage());
            }
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        };
    }

}