package conway3d;

import java.util.Observable;
import java.util.Random;

/**
 * Class representing the 3D world
 */
public class World extends Observable {
    public int width;
    public int height;
    public int depth;
    public int generation;
    public boolean[][][] world;

    public World(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.generation = 0;

        world = new boolean[width][height][depth];

        // Random initialization
        Random r = new Random();
        for(int i = 0 ; i < this.width ; i++) {
            for(int j = 0 ; j < this.height ; j++) {
                for(int k = 0 ; k < this.depth ; k++) {
                    world[i][j][k] = r.nextBoolean();
                }
            }
        }
    }

    public void restart() {
        Random r = new Random();
        for(int i = 0 ; i < this.width ; i++) {
            for(int j = 0 ; j < this.height ; j++) {
                for(int k = 0 ; k < this.depth ; k++) {
                    world[i][j][k] = r.nextBoolean();
                }
            }
        }

        this.generation = 0;
    }

    public void evolve() {
        boolean[][][] newWorld = new boolean[this.width][this.height][this.depth];

        for(int i = 0 ; i < this.width ; i++) {
            for(int j = 0 ; j < this.height ; j++) {
                for(int k = 0 ; k < this.depth ; k++) {
                    int neighborsCounter = 0;

                    for(int ni = -1 ; ni <= 1 ; ni++) {
                        for(int nj = -1 ; nj <= 1 ; nj++) {
                            for(int nk = -1 ; nk <= 1 ; nk++) {
                                if(i + ni >= 0 && i + ni < this.width && j + nj >= 0 && j + nj < this.height &&
                                        k + nk >= 0 && k + nk < this.depth) {
                                    if(!(i + ni == i && j + nj == j && k + nk ==k))
                                        neighborsCounter += this.world[i+ni][j+nj][k+nk] ? 1 : 0;
                                }

                            }
                        }
                    }

                    newWorld[i][j][k] = (neighborsCounter == 10) || (world[i][j][k] && (neighborsCounter >= 7) && (neighborsCounter <= 10));
                }
            }
        }

        this.world = newWorld;
        this.generation++;

        setChanged();
        notifyObservers();
    }

    public int getGeneration() {
        return this.generation;
    }
}
