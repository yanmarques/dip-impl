package dip.graphics;

import dipfx.graphics.AppLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = AppLoader.load(new Controller());
        primaryStage.setTitle("Processamento Digital de Imagens");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
