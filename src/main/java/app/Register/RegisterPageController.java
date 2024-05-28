package app.Register;

import app.Classes.User;
import app.Database.DB;
import app.tastewizard.TasteWizardHome;
import app.tastewizard.TasteWizardHomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class RegisterPageController {
    private User user;
    @FXML
    private Button LoginButton;
    @FXML
    private Button RegisterButton;
    @FXML
    private TextField Name;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Address;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private ComboBox<String> Gender;
    @FXML
    private DatePicker BirthDate;
    @FXML
    private Button choosePfpButton;
    @FXML
    private Label PhotoName;
    public void initialize() {
        Gender.getItems().add("Male");
        Gender.getItems().add("Female");
        LoginButton.setOnAction(LoginButtonClicked());
        RegisterButton.setOnAction(RegisterButtonClicked());
        choosePfpButton.setOnAction(e -> handleChoosePfp());
    }
    public void handleChoosePfp() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) choosePfpButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            // Copy image to resources folder and store relative path
            try {
                String relativePath = copyImageToResources(file);
                user = new User();
                user.setProfilePicture(relativePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String copyImageToResources(File file) throws IOException {
        // Define the destination folder (e.g., src/main/resources/images)
        Path destinationDir = Paths.get("src/main/resources/app/images");
        if (!Files.exists(destinationDir)) {
            Files.createDirectories(destinationDir);
        }
        // Copy the file to the destination folder
        Path destinationPath = destinationDir.resolve(file.getName());
        Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        PhotoName.setText(file.getName());
        // Return the relative path to be stored in the database
        return "images/" + file.getName();
    }
    private EventHandler<ActionEvent> RegisterButtonClicked() {
        return e -> {
            String name = Name.getText();
            String email = Email.getText();
            String password = Password.getText();
            String confirmPassword = ConfirmPassword.getText();
            String address = Address.getText();
            LocalDate birthDate = BirthDate.getValue();
            String Gender = this.Gender.getValue();
            String profilePicture = user.getProfilePicture();
            if (password.equals(confirmPassword)) {
                user = new User(name, email, password, "user", address,Gender,birthDate,profilePicture);
                DB db = new DB();
                if (db.AddUser(user)) {
                    System.out.println("User added successfully");
                } else {
                    System.out.println("Error in adding user");
                }
            } else {
                System.out.println("Passwords do not match");
            }
        };
    }

    private EventHandler<ActionEvent> LoginButtonClicked() {
        return e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(TasteWizardHome.class.getResource("TasteWizardHome.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                TasteWizardHomeController controller = fxmlLoader.getController();
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