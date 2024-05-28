package app.HomePage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.almasb.fxgl.app.GameApplication.launch;

public class HomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
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