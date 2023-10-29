package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
import javafx.scene.image.Image;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml")));

        Scene scene = new Scene(rootNode);
        Image icon = new Image("/img/logo/logo.png");

        stage.setTitle("Optimax VIsions");
        stage.getIcons().add(icon);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}