package conway3d;

import javafx.scene.layout.Pane;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents the JavaFX Pane in which the 3D world is displayed
 */
public class WorldPane extends Pane implements Observer {
    private World world;

    public WorldPane(World w) {
        this.world = w;
        this.world.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
