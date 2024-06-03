package app.ResultCategory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultCategoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ResultCategoryApplication.class.getResource("ResultCategory.fxml"));
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load());

        }catch (Exception exception) {
            System.out.println("Error in loading HomePage.fxml: "+exception.getMessage());
            exception.printStackTrace();
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}