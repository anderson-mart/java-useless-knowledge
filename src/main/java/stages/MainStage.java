package stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStage extends Application {

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainStage.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 600, 400));
        primaryStage.setTitle("Useless Knowledge");
        primaryStage.show();
    }

}