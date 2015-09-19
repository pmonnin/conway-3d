package conway3d;

/**
 * Controller of the application
 */
public class WorldController {
    private World model;
    private Thread executionThread;
    private boolean stop = false;

    public WorldController(World model) {
        this.model = model;

        this.executionThread = new Thread() {
            public void run () {
                while(!WorldController.this.stop) {
                    WorldController.this.model.evolve();
                    try {
                        this.sleep(3000);
                    } catch (InterruptedException e) {
                        // Do nothing
                    }
                }
            }
        };
        this.executionThread.start();
    }

    public void restart() {
        this.model.restart();
    }

    public void stop() {
        this.stop = true;
    }
}
