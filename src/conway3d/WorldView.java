package conway3d;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.Observable;
import java.util.Observer;

/**
 * View for the 3D world
 */
public class WorldView extends VBox implements Observer {
    private World model;
    private WorldController controller;
    private WorldPane pane;
    private Camera camera;

    private Label generationLabel;

    // Mouse controls info
    private double oldMouseX;
    private double oldMouseY;

    public WorldView(WorldController controller, World model) {
        this.controller = controller;
        this.model = model;
        this.model.addObserver(this);

        // Control box construction
        HBox controlBox = new HBox(8);
        controlBox.setAlignment(Pos.BASELINE_CENTER);
        Button restart = new Button("Restart");
        restart.setOnAction(event -> WorldView.this.controller.restart());

        this.generationLabel = new Label("Generation : 0");
        controlBox.getChildren().add(restart);
        controlBox.getChildren().add(generationLabel);

        // Pane & Camera construction
        this.pane = new WorldPane(model);
        SubScene subscene = new SubScene(this.pane, 800, 550, true, SceneAntialiasing.BALANCED);
        this.camera = new PerspectiveCamera(true);
        this.camera.setFarClip(10000);
        this.camera.getTransforms().add(new Translate(model.getWidth() / 2, model.getHeight() / 2, -2 * model.getHeight()));
        subscene.setCamera(this.camera);

        this.getChildren().add(subscene);
        this.getChildren().add(controlBox);

        // Keyboard controls
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.LEFT) {
                    WorldView.this.camera.getTransforms().add(new Translate(-1.0, 0.0, 0.0));
                }

                else if(event.getCode() == KeyCode.RIGHT) {
                    WorldView.this.camera.getTransforms().add(new Translate(1.0, 0.0, 0.0));
                }

                else if(event.getCode() == KeyCode.UP) {
                    WorldView.this.camera.getTransforms().add(new Translate(0.0, 0.0, 1.0));
                }

                else if(event.getCode() == KeyCode.DOWN) {
                    WorldView.this.camera.getTransforms().add(new Translate(0.0, 0.0, -1.0));
                }

                else if(event.getCode() == KeyCode.PAGE_UP) {
                    WorldView.this.camera.getTransforms().add(new Translate(0.0, -1.0, 0.0));
                }

                else if(event.getCode() == KeyCode.PAGE_DOWN) {
                    WorldView.this.camera.getTransforms().add(new Translate(0.0, 1.0, 0.0));
                }
            }
        });


        // Mouse controls
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                WorldView.this.oldMouseX = event.getX();
                WorldView.this.oldMouseY = event.getY();
            }
        });


        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                WorldView.this.camera.getTransforms().add(new Rotate(0.1*(event.getX() - oldMouseX), new Point3D(0.0, 1.0, 0.0)));
                WorldView.this.camera.getTransforms().add(new Rotate(-0.1*(event.getY() - oldMouseY), new Point3D(1.0, 0.0, 0.0)));

                WorldView.this.oldMouseX = event.getX();
                WorldView.this.oldMouseY = event.getY();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WorldView.this.generationLabel.setText("Generation: " + WorldView.this.model.getGeneration());
            }
        });
    }
}
