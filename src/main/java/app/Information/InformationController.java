package app.Information;

import app.Classes.User;
import app.Classes.UserHealthGoals;
import app.Classes.UserMedicalProfile;
import app.Classes.UserPreferences;
import app.Database.DB;
import app.HomePage.HomePage;
import app.HomePage.HomePageController;
import app.tastewizard.TasteWizardHome;
import app.tastewizard.TasteWizardHomeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;

public class InformationController {
    @FXML
    private Label UserName;
    @FXML
    private Button ProceedButton;
    @FXML
    private TextField cultural_dietary_restrictions;
    @FXML
    private TextField organic_food_awareness;
    @FXML
    private ComboBox<String> food_preference;
    @FXML
    private TextField medical_conditions;
    @FXML
    private TextField allergies;
    @FXML
    private CheckBox anemia;
    @FXML
    private CheckBox cancer;
    @FXML
    private CheckBox crohns_disease;
    @FXML
    private CheckBox diabetes;
    @FXML
    private CheckBox heart_disease;
    @FXML
    private CheckBox hepatitis;
    @FXML
    private CheckBox high_blood_fats;
    @FXML
    private CheckBox high_blood_pressure;
    @FXML
    private CheckBox thyroid_disease;
    @FXML
    private CheckBox tobacco_use;
    @FXML
    private TextField special_dietary_plan;
    @FXML
    private ComboBox<String> weight_goal;
    @FXML
    private CheckBox digestive_symptoms;
    @FXML
    private TextField additional_information;
    @FXML
    private Label WelcomeSentence;
    public void initialize() {
        food_preference.getItems().add("Locally made");
        food_preference.getItems().add("Imported");
        food_preference.getItems().add("Both");
        weight_goal.getItems().add("Lose Weight");
        weight_goal.getItems().add("Maintain Weight");
        weight_goal.getItems().add("Gain Weight");
        weight_goal.getItems().add("Not interested");
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            if(checkRequiredFields())
            {
                return;
            }
            String cultural_dietary_restrictions = this.cultural_dietary_restrictions.getText();
            String organic_food_awareness = this.organic_food_awareness.getText();
            String food_preference = this.food_preference.getValue().toString();
            String medical_conditions = this.medical_conditions.getText();
            String allergies = this.allergies.getText();
            boolean anemia = this.anemia.isSelected();
            boolean cancer = this.cancer.isSelected();
            boolean crohns_disease = this.crohns_disease.isSelected();
            boolean diabetes = this.diabetes.isSelected();
            boolean heart_disease = this.heart_disease.isSelected();
            boolean hepatitis = this.hepatitis.isSelected();
            boolean high_blood_fats = this.high_blood_fats.isSelected();
            boolean high_blood_pressure = this.high_blood_pressure.isSelected();
            boolean thyroid_disease = this.thyroid_disease.isSelected();
            boolean tobacco_use = this.tobacco_use.isSelected();
            String special_dietary_plan = this.special_dietary_plan.getText();
            String weight_goal = this.weight_goal.getValue().toString();
            boolean digestive_symptoms = this.digestive_symptoms.isSelected();
            String additional_information = this.additional_information.getText();
            UserPreferences userPreferences = new UserPreferences(user.getUser_id(),cultural_dietary_restrictions, organic_food_awareness, food_preference);
            UserMedicalProfile userMedicalProfile = new UserMedicalProfile(user.getUser_id(), medical_conditions, allergies, anemia, cancer, crohns_disease, diabetes, heart_disease, hepatitis, high_blood_fats, high_blood_pressure, thyroid_disease, tobacco_use, special_dietary_plan);
            UserHealthGoals userHealthGoals = new UserHealthGoals(user.getUser_id(), weight_goal, digestive_symptoms, additional_information);
            user.setHealthGoals(userHealthGoals);
            user.setPreferences(userPreferences);
            user.setMedicalProfile(userMedicalProfile);
            DB db = new DB();
            if(db.updateUser(user))
            {

            FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                HomePageController controller = fxmlLoader.getController();
                controller.setUser(user);
            } catch (Exception exception) {
                System.out.println("Error in loading HomePage.fxml: " + exception.getMessage());
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
            }
            else
            {
                System.out.println("Error in updating user");
            }
        };
    }

    private boolean checkRequiredFields() {
        if(cultural_dietary_restrictions.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cultural Dietary Restrictions is required");
            alert.showAndWait();
            return true;
        }
        if(organic_food_awareness.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Organic Food Awareness is required");
            alert.showAndWait();
            return true;
        }
        if(food_preference.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Food Preference is required");
            alert.showAndWait();
            return true;
        }
        if(medical_conditions.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Medical Conditions is required");
            alert.showAndWait();
            return true;
        }
        if(allergies.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Allergies is required");
            alert.showAndWait();
            return true;
        }
        if(special_dietary_plan.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Special Dietary Plan is required");
            alert.showAndWait();
            return true;
        }
        if(weight_goal.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Weight Goal is required");
            alert.showAndWait();
            return true;
        }
        if(additional_information.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Additional Information is required");
            alert.showAndWait();
            return true;
        }
        if (digestive_symptoms.isSelected() && additional_information.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Additional Information is required");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    private User user;
    public void setUserInfo(User user) {
        this.user = user;
        UserName.setText(user.getUsername());
    }
    private boolean viewOnly;
    public void setViewOnly(boolean b) {
        viewOnly = b;
        DisableFields();
    }

    private void DisableFields() {
//        ProceedButton.setVisible(false);
//        ProceedButton.setManaged(false);
        ProceedButton.setText("Back");
        ProceedButton.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                HomePageController controller = fxmlLoader.getController();
                controller.setUser(user);
            } catch (Exception exception) {
                System.out.println("Error in loading TasteWizardHome.fxml: " + exception.getMessage());
                exception.printStackTrace();
            }
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();
        });
        cultural_dietary_restrictions.setEditable(false);
        organic_food_awareness.setEditable(false);
//        food_preference.setDisable(true);
        medical_conditions.setEditable(false);
        allergies.setEditable(false);
        anemia.setDisable(true);
        cancer.setDisable(true);
        crohns_disease.setDisable(true);
        diabetes.setDisable(true);
        heart_disease.setDisable(true);
        hepatitis.setDisable(true);
        high_blood_fats.setDisable(true);
        high_blood_pressure.setDisable(true);
        thyroid_disease.setDisable(true);
        tobacco_use.setDisable(true);
        special_dietary_plan.setEditable(false);
//        weight_goal.setDisable(true);
        digestive_symptoms.setDisable(true);
        additional_information.setEditable(false);
        WelcomeSentence.setText("Here is your information");
        DB db = new DB();
        user = db.getUser(user);
        cultural_dietary_restrictions.setText(user.getPreferences().getCultural_dietary_restrictions());
        organic_food_awareness.setText(user.getPreferences().getOrganic_food_awareness());
        food_preference.setValue(user.getPreferences().getFood_preference());
        medical_conditions.setText(user.getMedicalProfile().getMedical_conditions());
        allergies.setText(user.getMedicalProfile().getAllergies().equalsIgnoreCase("no")?"N/A":user.getMedicalProfile().getAllergies());
        anemia.setSelected(user.getMedicalProfile().getAnemia() == 1);
        cancer.setSelected(!user.getMedicalProfile().getCancer().equalsIgnoreCase("no"));
        crohns_disease.setSelected(user.getMedicalProfile().getCrohns_disease()==1);
        diabetes.setSelected(!user.getMedicalProfile().getDiabetes().equalsIgnoreCase("no"));
        heart_disease.setSelected(user.getMedicalProfile().getHeart_disease().equalsIgnoreCase("yes"));
        hepatitis.setSelected(user.getMedicalProfile().getHepatitis()==1);
        high_blood_fats.setSelected(user.getMedicalProfile().getHigh_blood_fats()==1);
        high_blood_pressure.setSelected(user.getMedicalProfile().getHigh_blood_pressure()==1);
        thyroid_disease.setSelected(!user.getMedicalProfile().getThyroid_disease().equalsIgnoreCase("no"));
        tobacco_use.setSelected(user.getMedicalProfile().getTobacco_use()==1);
        special_dietary_plan.setText(user.getMedicalProfile().getSpecial_dietary_plan());
        weight_goal.getItems().removeAll(weight_goal.getItems());
        weight_goal.setValue(user.getHealthGoals().getWeight_goal());
        digestive_symptoms.setSelected(user.getHealthGoals().getDigestive_symptoms()==1);
        additional_information.setText(user.getHealthGoals().getAdditional_information().equalsIgnoreCase("no")?"N/A":user.getHealthGoals().getAdditional_information());

    }
}
