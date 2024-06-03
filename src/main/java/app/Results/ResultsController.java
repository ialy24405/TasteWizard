package app.Results;

import app.Classes.Recommendation;
import app.Classes.Similarty;
import app.Classes.User;
import app.Classes.product;
import app.Database.DB;
import app.HomePage.HomePage;
import app.HomePage.HomePageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResultsController {
    @FXML
    private Button ProceedButton;
    @FXML
    private TableView<Recommendation> ResultsTable;
    @FXML
    private TableColumn<Recommendation, String> RecommendationColumn;
    @FXML
    private TableColumn<Recommendation, String> SimilartyColumn;
    public void initialize() {
        ProceedButton.setOnAction(ProceedButtonClicked());
        RecommendationColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        SimilartyColumn.setCellValueFactory(new PropertyValueFactory<>("similarityScore"));
    }

    private EventHandler<ActionEvent> ProceedButtonClicked() {
        return e -> {
            //back to the home page
            FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                HomePageController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.setprofile();
            } catch (Exception exception) {
                System.out.println("Error in loading HomePage.fxml: " + exception.getMessage());
                exception.printStackTrace();
            }
            // Set the scene for the stage
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            // Set the scene for the stage
            stage.setScene(scene);
            // Show the stage
            stage.show();

        };

    }

    private User user;
    public void setUser(User user) {
        this.user = user;
    }
    private ObservableList<Recommendation> recommendations = null;
    private String category;
    public void setCategory(String categoryName) {
        this.category = categoryName;
        recommendations = FXCollections.observableArrayList();
        DB db = new DB();
        Similarty similarty = new Similarty(db.getConnection());
        List<Recommendation> recommendationsList = similarty.recommendBeverages(user.getUser_id(),6);
        for (Recommendation recommendation : recommendationsList) {
//            System.out.println(recommendation.getProductId());
            product productName = db.getProduct(recommendation.getProductId());
            if(productName.getCategory_id()!=1)
                continue;
            recommendation.setProductName(productName.getProduct_name());
            recommendations.add(recommendation);
        }

        ResultsTable.setItems(recommendations);


    }
}
