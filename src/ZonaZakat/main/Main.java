package ZonaZakat.main;

import ZonaZakat.data.Sistem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        Sistem.inisialisasi();

        Parent root = FXMLLoader.load(getClass().getResource("/ZonaZakat/fxmlFiles/MainMenu.fxml"));
        primaryStage.setTitle("Zona Zakat");
        primaryStage.setScene(new Scene(root, 600, 420));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxml));

        stage.getScene().setRoot(parent);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
