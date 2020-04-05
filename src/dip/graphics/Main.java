package dip.graphics;

import dipfx.common.LogUtil;
import dipfx.graphics.AppLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;

public class Main extends Application {
    public static void main(String[] args) {
        LogUtil.defaultLevel = Level.INFO;
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
