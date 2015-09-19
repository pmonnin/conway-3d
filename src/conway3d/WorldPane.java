package conway3d;

import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * Represents the JavaFX Pane in which the 3D world is displayed
 */
public class WorldPane extends Pane implements Observer {
    private World world;

    public WorldPane(World w) {
        this.world = w;
        this.world.addObserver(this);

        this.setBackground(Background.EMPTY);

        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WorldPane.this.getChildren().clear();

                Random r = new Random();
                float red = r.nextFloat() % 1;
                float green = r.nextFloat() % 1;
                float blue = r.nextFloat() % 1;
                PhongMaterial material = new PhongMaterial(new Color(red, green, blue, 1.0));

                for(int i = 0 ; i < WorldPane.this.world.getWidth() ; i++) {
                    for(int j = 0 ; j < WorldPane.this.world.getHeight() ; j++) {
                        for(int k = 0 ; k < WorldPane.this.world.getDepth() ; k++) {
                            if(WorldPane.this.world.isAlive(i, j, k)) {
                                Sphere sphere = new Sphere(0.5, 50);
                                sphere.setMaterial(material);
                                sphere.getTransforms().add(new Translate(i, j, k));
                                WorldPane.this.getChildren().add(sphere);
                            }
                        }
                    }
                }
            }
        });
    }
}
