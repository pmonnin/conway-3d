package conway3d;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class starting the application
 */
public class Main extends Application {

    public void start(Stage primaryStage) {
        World world = new World(30, 30, 30);
        WorldController controller = new WorldController(world);
        WorldView view = new WorldView(controller, world);

        Scene scene = new Scene(view, 800, 600);

        primaryStage.setTitle("JavaFX 3D Conway life game");
        primaryStage.setOnCloseRequest(event -> controller.stop());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
