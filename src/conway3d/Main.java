package conway3d;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class starting the application
 */
public class Main extends Application {

    public void start(Stage primaryStage) {

        Button restart = new Button("Restart");
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO do something useful later
            }
        });

        Label generation = new Label("Generation: 0");

        HBox controlBox = new HBox(8);
        controlBox.setAlignment(Pos.BASELINE_CENTER);
        controlBox.getChildren().add(restart);
        controlBox.getChildren().add(generation);

        WorldPane pane = new WorldPane();
        SubScene subScene = new SubScene(pane, 800, 550, true, SceneAntialiasing.BALANCED);

        VBox root = new VBox(1);
        root.getChildren().add(subScene);
        root.getChildren().add(controlBox);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("JavaFX 3D Conway life game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
