package conway3d;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main class starting the application
 */
public class Main extends Application {

    public void start(Stage primaryStage) {
        World world = new World(25, 25, 25);
        WorldController controller = new WorldController(world);
        WorldView view = new WorldView(controller, world);

        Scene scene = new Scene(view, 800, 600, Color.WHITESMOKE);

        primaryStage.setTitle("JavaFX 3D Conway life game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
