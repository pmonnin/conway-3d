package conway3d;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * View for the 3D world
 */
public class WorldView extends VBox {
    private WorldController controller;
    private WorldPane pane;

    public WorldView(WorldController controller, World model) {
        this.controller = controller;
        this.pane = new WorldPane(model);

        this.getChildren().add(this.pane);

        HBox controlBox = new HBox(8);
        controlBox.setAlignment(Pos.BASELINE_CENTER);
        Button restart = new Button("Restart");
        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });

        Label generationLabel = new Label("Generation : 0");
        controlBox.getChildren().add(restart);
        controlBox.getChildren().add(generationLabel);

        this.getChildren().add(controlBox);



    }

    public WorldPane get3DPane() {
        return null;
    }
}
