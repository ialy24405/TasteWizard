package app.SweetSnackes;

import app.Classes.User;
import app.Classes.product;
import app.Database.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class SweetSnacksController {
    @FXML
    private ToggleGroup ColorGroup;
    @FXML
    private ToggleGroup ColorGroup1;
    @FXML
    private ToggleGroup ColorGroup2;
    @FXML
    private ToggleGroup ColorGroup3;
    @FXML
    private ToggleGroup ColorGroup31;
    @FXML
    private ToggleGroup ColorGroup32;
    @FXML
    private ToggleGroup ColorGroup33;
    @FXML
    private ToggleGroup ColorGroup34;
    @FXML
    private ToggleGroup ColorGroup35;
    @FXML
    private ToggleGroup ColorGroup36;
    @FXML
    private ToggleGroup ColorGroup37;
    @FXML
    private ToggleGroup ColorGroup371;
    @FXML
    private ToggleGroup ColorGroup372;
    @FXML
    private ToggleGroup ColorGroup373;
    @FXML
    private ToggleGroup ColorGroup374;
    @FXML
    private ToggleGroup ColorGroup3741;
    @FXML
    private ToggleGroup ColorGroup3742;
    @FXML
    private ToggleGroup ColorGroup37421;
    @FXML
    private Button ProceedButton;
    @FXML
    private ComboBox<String> Snacks;
    @FXML
    private Label surfaceTextureLabel;
    @FXML
    private HBox textureGroup;
    @FXML
    private VBox Form;
    public void initialize() {
        DB db = new DB();
        List<product> products = db.getSweetSnacks();
        for (product product : products) {
            Snacks.getItems().add(product.getProduct_name());
        }
        ProceedButton.setOnAction(ProceedButtonClicked());
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            CheckToggleGroups();
        };
    }

    private void CheckToggleGroups() {
//        System.out.println("ColorGroup: " + ColorGroup.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup1: " + ColorGroup1.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup2: " + ColorGroup2.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup3: " + ColorGroup3.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup31: " + ColorGroup31.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup32: " + ColorGroup32.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup33: " + ColorGroup33.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup34: " + ColorGroup34.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup35: " + ColorGroup35.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup36: " + ColorGroup36.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup37: " + ColorGroup37.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup371: " + ColorGroup371.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup372: " + ColorGroup372.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup373: " + ColorGroup373.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup374: " + ColorGroup374.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup3741: " + ColorGroup3741.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup3742: " + ColorGroup3742.getSelectedToggle().getUserData().toString());
//        System.out.println("ColorGroup37421: " + ColorGroup37421.getSelectedToggle().getUserData().toString());
        if(Snacks.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please choose a product.");
            alert.showAndWait();
            return;
        }
        if(ColorGroup.getSelectedToggle()==null || ColorGroup1.getSelectedToggle()==null || ColorGroup2.getSelectedToggle()==null || ColorGroup3.getSelectedToggle()==null || ColorGroup31.getSelectedToggle()==null || ColorGroup32.getSelectedToggle()==null || ColorGroup33.getSelectedToggle()==null || ColorGroup34.getSelectedToggle()==null || ColorGroup35.getSelectedToggle()==null || ColorGroup36.getSelectedToggle()==null || ColorGroup37.getSelectedToggle()==null || ColorGroup371.getSelectedToggle()==null || ColorGroup372.getSelectedToggle()==null || ColorGroup373.getSelectedToggle()==null || ColorGroup374.getSelectedToggle()==null || ColorGroup3741.getSelectedToggle()==null || ColorGroup3742.getSelectedToggle()==null || ColorGroup37421.getSelectedToggle()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields.");
            alert.showAndWait();
        }
         else {
            DB db = new DB();
            String productId = "";
            List<product> products = db.getSweetSnackesList();
            for (product product : products) {
                if (product.getProduct_name().equalsIgnoreCase(Snacks.getValue())) {
                    productId = String.valueOf(product.getProduct_id());
                    break;
                }
            }

            if (productId.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Product not found.");
                alert.showAndWait();
                return;
            }
            db.addSweetSnacks(user, productId, ColorGroup.getSelectedToggle().getUserData().toString(), ColorGroup1.getSelectedToggle().getUserData().toString(), ColorGroup2.getSelectedToggle().getUserData().toString(), ColorGroup3.getSelectedToggle().getUserData().toString(), ColorGroup31.getSelectedToggle().getUserData().toString(), ColorGroup32.getSelectedToggle().getUserData().toString(), ColorGroup33.getSelectedToggle().getUserData().toString(), ColorGroup34.getSelectedToggle().getUserData().toString(), ColorGroup35.getSelectedToggle().getUserData().toString(), ColorGroup36.getSelectedToggle().getUserData().toString(), ColorGroup37.getSelectedToggle().getUserData().toString(), ColorGroup371.getSelectedToggle().getUserData().toString(), ColorGroup372.getSelectedToggle().getUserData().toString(), ColorGroup373.getSelectedToggle().getUserData().toString(), ColorGroup374.getSelectedToggle().getUserData().toString(), ColorGroup3741.getSelectedToggle().getUserData().toString(), ColorGroup3742.getSelectedToggle().getUserData().toString(), ColorGroup37421.getSelectedToggle().getUserData().toString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Sweet Snacks added successfully.");
            alert.showAndWait();
//            db.addSnacks(user, category, product_id, ColorGroup.getSelectedToggle().getUserData().toString(), ColorGroup1.getSelectedToggle().getUserData().toString(), ColorGroup2.getSelectedToggle().getUserData().toString(), ColorGroup3.getSelectedToggle().getUserData().toString(), ColorGroup31.getSelectedToggle().getUserData().toString(), ColorGroup32.getSelectedToggle().getUserData().toString(), ColorGroup33.getSelectedToggle().getUserData().toString(), ColorGroup34.getSelectedToggle().getUserData().toString(), ColorGroup35.getSelectedToggle().getUserData().toString(), ColorGroup36.getSelectedToggle().getUserData().toString(), ColorGroup37.getSelectedToggle().getUserData().toString(), ColorGroup371.getSelectedToggle().getUserData().toString(), ColorGroup372.getSelectedToggle().getUserData().toString(), ColorGroup373.getSelectedToggle().getUserData().toString(), ColorGroup374.getSelectedToggle().getUserData().toString(), ColorGroup3741.getSelectedToggle().getUserData().toString(), ColorGroup3742.getSelectedToggle().getUserData().toString());
        }
    }
    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    private String category;
    public void setCategory(String category) {
        this.category = category;

    }
}
