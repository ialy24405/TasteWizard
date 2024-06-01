package app.HomePage;

import app.Classes.Category;
import app.Classes.User;
import app.Database.DB;
import app.Information.InformationApplication;
import app.Information.InformationController;
import app.Que.QueApplication;
import app.Que.QueController;
import app.tastewizard.TasteWizardHome;
import app.tastewizard.TasteWizardHomeController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class HomePageController {
    User user;
    @FXML
    private ImageView ProfilePicture;
    @FXML
    private Label Name;
    @FXML
    private HBox LogOutBox;
    @FXML
    private Label LogOutLabel;
    @FXML
    private ImageView LogOutImage;
    @FXML
    private HBox Qustionnare;
    @FXML
    private HBox ProfilePage;

    public void initialize() {
        LogOutBox.setOnMouseClicked(LogOutBoxClicked());
        Qustionnare.setOnMouseClicked(QustionnareClicked());
        ProfilePage.setOnMouseClicked(ProfilePageClicked());
    }

    private EventHandler<? super MouseEvent> ProfilePageClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(InformationApplication.class.getResource("Information.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                InformationController controller = fxmlLoader.getController();
                controller.setUserInfo(user);
                controller.setViewOnly(true);

            } catch (Exception exception) {
                System.out.println("Error in loading TasteWizardHome.fxml: " + exception.getMessage());
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        };
    }

    private EventHandler<? super MouseEvent> QustionnareClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(QueApplication.class.getResource("Que.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                QueController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.setHomePageController(this);
            } catch (Exception exception) {
                System.out.println("Error in loading TasteWizardHome.fxml: " + exception.getMessage());
                exception.printStackTrace();
            }
            Stage stage = new Stage();
            // Set the scene for the stage
            stage.initModality(Modality.APPLICATION_MODAL); // This line makes the new window modal
            stage.setScene(scene);
            // Show the stage
            stage.showAndWait();
    };
    }


    private EventHandler<? super MouseEvent> LogOutBoxClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(TasteWizardHome.class.getResource("TasteWizardHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                TasteWizardHomeController tasteWizardHome = fxmlLoader.getController();
            } catch (Exception exception) {
                System.out.println("Error in loading TasteWizardHome.fxml: " + exception.getMessage());
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
//            System.out.println("Log out successful");

        };
    }

    private void previewData() {
        ProfilePicture.setFitWidth(90);
        ProfilePicture.setFitHeight(90);
        ProfilePicture.setPreserveRatio(true);
        Circle clip = new Circle(45, 45, 45);
        ProfilePicture.setClip(clip);
        DB db = new DB();
        user = db.getUser(user);
        Image image = null;
        if(user.getProfilePicture()==null || user.getProfilePicture().isEmpty()){
            if(user.getGender().equalsIgnoreCase("Female"))
            {
                File imageFile = new File("src/main/resources/app/Images/Female3.jpg");
                if (imageFile.exists()) {
                    image = new Image(imageFile.toURI().toString());
                }
            }else{
                File imageFile = new File("src/main/resources/app/Images/Male.jpg");
                if (imageFile.exists()) {
                    image = new Image(imageFile.toURI().toString());
                }
            }
        }
        else {
            File imageFile = new File("src/main/resources/app/"+user.getProfilePicture());
            if (imageFile.exists()) {
                image = new Image(imageFile.toURI().toString());
            }
        }
        ProfilePicture.setImage(image);
        Name.setText(user.getUsername());
        db.getAverageOfUserBeverages(user.getUser_id());

    }

    public void setUser(User user) {
        this.user = user;
        previewData();

    }
    private Category category;
    public void setCategory(String category) {
        DB db = new DB();
        this.category = db.getCategory(category);
        if (category.equalsIgnoreCase("Beverage")) {

        } else if (category.equalsIgnoreCase("SweetSnakes")) {
            category = "Sweet Snacks";
        } else if (category.equalsIgnoreCase("SaltySnakes")) {
            category = "Salty Snacks";
        }
//        System.out.println("Category: " + this.category.getCategory_name());
    }
}
