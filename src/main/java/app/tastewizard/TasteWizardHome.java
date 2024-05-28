package app.tastewizard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.almasb.fxgl.app.GameApplication.launch;

public class TasteWizardHome extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(TasteWizardHome.class.getResource("TasteWizardHome.fxml"));
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load());

        }catch (Exception exception) {
            System.out.println("Error in loading TasteWizardHome.fxml: "+exception.getMessage());
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}