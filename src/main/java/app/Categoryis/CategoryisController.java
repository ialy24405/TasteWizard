package app.Categoryis;

import app.Classes.User;
import app.Classes.product;
import app.Database.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class CategoryisController {
    @FXML
    private ComboBox<String> beverages;
    @FXML
    private Button ProceedButton;
    @FXML
    private ToggleGroup ColorGroup;
    @FXML
    private ToggleGroup ClarityGroup;
    @FXML
    private ToggleGroup ConsistencyGroup;
    @FXML
    private ToggleGroup AppearanceGroup;
    @FXML
    private ToggleGroup AromaIntensityGroup;
    @FXML
    private ToggleGroup AromaSweetnessGroup;
    @FXML
    private ToggleGroup AromaFruitinessGroup;
    @FXML
    private ToggleGroup OffAromasGroup;
    @FXML
    private ToggleGroup SweetnessGroup;
    @FXML
    private ToggleGroup SournessGroup;
    @FXML
    private ToggleGroup BitternessGroup;
    @FXML
    private ToggleGroup FlavorIntensityGroup;
    @FXML
    private ToggleGroup AftertasteGroup;
    @FXML
    private ToggleGroup OverAllFlavorGroup;
    @FXML
    private ToggleGroup BodyThicknessGroup;
    @FXML
    private ToggleGroup CarbonationGroup;
    @FXML
    private ToggleGroup SmoothnessGroup;
    @FXML
    private ToggleGroup AcceptanceGroup;
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    public void initialize() {
        DB db = new DB();
        List<product> beveragesList = db.getBevereages();
        for (product product : beveragesList) {
            beverages.getItems().add(product.getProduct_name());
        }
        ProceedButton.setOnAction(proceedButtonClicked());
    }

    private EventHandler<ActionEvent> proceedButtonClicked() {
        return e -> {
            checkRadioButtons();
        };
    }

    private void checkRadioButtons() {
        if(beverages.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a product.");
            alert.showAndWait();
            return;
        }
        if(ColorGroup.getSelectedToggle() == null || ClarityGroup.getSelectedToggle() == null || ConsistencyGroup.getSelectedToggle() == null || AppearanceGroup.getSelectedToggle() == null ||
                AromaIntensityGroup.getSelectedToggle() == null || AromaSweetnessGroup.getSelectedToggle() == null || AromaFruitinessGroup.getSelectedToggle() == null || OffAromasGroup.getSelectedToggle() == null ||
                SweetnessGroup.getSelectedToggle() == null || SournessGroup.getSelectedToggle() == null || BitternessGroup.getSelectedToggle() == null || FlavorIntensityGroup.getSelectedToggle() == null ||
                AftertasteGroup.getSelectedToggle() == null || OverAllFlavorGroup.getSelectedToggle() == null || BodyThicknessGroup.getSelectedToggle() == null || CarbonationGroup.getSelectedToggle() == null ||
                SmoothnessGroup.getSelectedToggle() == null || AcceptanceGroup.getSelectedToggle() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields.");
            alert.showAndWait();
            return;
        }

        String color = getSelectedToggleData(ColorGroup);
        String clarity = getSelectedToggleData(ClarityGroup);
        String consistency = getSelectedToggleData(ConsistencyGroup);
        String appearance = getSelectedToggleData(AppearanceGroup);
        String aromaIntensity = getSelectedToggleData(AromaIntensityGroup);
        String aromaSweetness = getSelectedToggleData(AromaSweetnessGroup);
        String aromaFruitiness = getSelectedToggleData(AromaFruitinessGroup);
        String offAromas = getSelectedToggleData(OffAromasGroup);
        String sweetness = getSelectedToggleData(SweetnessGroup);
        String sourness = getSelectedToggleData(SournessGroup);
        String bitterness = getSelectedToggleData(BitternessGroup);
        String flavorIntensity = getSelectedToggleData(FlavorIntensityGroup);
        String aftertaste = getSelectedToggleData(AftertasteGroup);
        String overAllFlavor = getSelectedToggleData(OverAllFlavorGroup);
        String bodyThickness = getSelectedToggleData(BodyThicknessGroup);
        String carbonation = getSelectedToggleData(CarbonationGroup);
        String smoothness = getSelectedToggleData(SmoothnessGroup);
        String acceptance = getSelectedToggleData(AcceptanceGroup);
//
//        System.out.println("Color: " + color);
//        System.out.println("Clarity: " + clarity);
//        System.out.println("Consistency: " + consistency);
//        System.out.println("Appearance: " + appearance);
//        System.out.println("Aroma Intensity: " + aromaIntensity);
//        System.out.println("Aroma Sweetness: " + aromaSweetness);
//        System.out.println("Aroma Fruitiness: " + aromaFruitiness);
//        System.out.println("Off Aromas: " + offAromas);
//        System.out.println("Sweetness: " + sweetness);
//        System.out.println("Sourness: " + sourness);
//        System.out.println("Bitterness: " + bitterness);
//        System.out.println("Flavor Intensity: " + flavorIntensity);
//        System.out.println("Aftertaste: " + aftertaste);
//        System.out.println("Over All Flavor: " + overAllFlavor);
//        System.out.println("Body Thickness: " + bodyThickness);
//        System.out.println("Carbonation: " + carbonation);
//        System.out.println("Smoothness: " + smoothness);
//        System.out.println("Acceptance: " + acceptance);
        DB db = new DB();
        String product_id = "";
        for (product product : db.getBevereages()) {
            if(product.getProduct_name().equalsIgnoreCase(beverages.getValue())){
                product_id = String.valueOf(product.getProduct_id());
            }
        }
        db.insertBeverageReview(user.getUser_id(), product_id, color, clarity, consistency, appearance, aromaIntensity, aromaSweetness, aromaFruitiness, offAromas, sweetness, sourness, bitterness, flavorIntensity, aftertaste, overAllFlavor, bodyThickness, carbonation, smoothness, acceptance);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Review submitted successfully.");
        alert.showAndWait();
    }

    private String getSelectedToggleData(ToggleGroup group) {
        if (group.getSelectedToggle() != null) {
            Object userData = group.getSelectedToggle().getUserData();
            if (userData != null) {
                return userData.toString();
            }
        }
        return "No selection"; // or you can return null or any default value as needed
    }

    private String category;
    public void setCategory(String category) {
        this.category = category;
    }
}
