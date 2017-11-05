import jdk.internal.util.xml.impl.Pair;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Anton Skudarnov on 30.10.2017.
 */
public class WalkParticle {

    enum Direction {UP, DOWN, LEFT, RIGHT}

    private static final Direction[] directions = Direction.values();

    final int genomSize = 8;
    final int mapSize = 3;

    Direction[] genom;
    int fitness;
    boolean[][] map;

    int rowPos;
    int columnPos;


    WalkParticle() {
        Random rand = new Random();
        genom = new Direction[genomSize];
        map = new boolean[mapSize][mapSize];
        fitness = 1;
       for(int i=0;i<mapSize;i++){
           for (int j = 0; j <mapSize ; j++) {
               map[i][j] = false;
           }
       }
        rowPos = 0;
        columnPos = 0;
        map[rowPos][columnPos] = true;
        for (int i = 0; i < genomSize; i++) {
            Direction direction = directions[rand.nextInt(directions.length)];
            genom[i] = direction;
        }
    }

    public int calculateFitness() {
        int visited = 1;
        int i = 0;
        rowPos = 0; columnPos=0;
        for(int k=0;k<mapSize;k++){
            for (int j = 0; j <mapSize ; j++) {
                map[k][j] = false;
            }
        }
        map[0][0] = true;
        while (i<genomSize){
            switch (genom[i]) {
                case UP:
                    if (rowPos > 0) {
                        --rowPos;
                        if (!map[rowPos][columnPos]){ visited++; map[rowPos][columnPos] = true; }
                    }
                    break;
                case DOWN:
                    if (rowPos < mapSize-1) {
                        ++rowPos;
                        if (!map[rowPos][columnPos]){ visited++; map[rowPos][columnPos] = true; }
                    }
                    break;
                case LEFT:
                    if (columnPos > 0) {
                        --columnPos;
                        if (!map[rowPos][columnPos]){ visited++; map[rowPos][columnPos] = true; }
                    }
                    break;
                case RIGHT:
                    if (columnPos < mapSize-1) {
                        ++columnPos;
                        if (!map[rowPos][columnPos]){ visited++; map[rowPos][columnPos] = true; }
                    }
                    break;
            }
            i++;
        }
        fitness = visited;
        return visited;
    }

    public void printPath(){
        for (Direction g: genom) {
            switch (g){
                case UP:
                    System.out.print("UP ");
                    break;
                case DOWN:
                    System.out.print("DOWN ");
                    break;
                case LEFT:
                    System.out.print("LEFT ");
                    break;
                case RIGHT:
                    System.out.print("RIGHT ");
                    break;
            }
        }
        System.out.println();
    }

    public void setGenom(int i, Direction direction){
        genom[i] = direction;
    }

    public WalkParticle produceChildWith(WalkParticle parent){
        WalkParticle result = new WalkParticle();
        Random crossProb = new Random();
        for (int i = 0; i < genomSize ; i++) {
            if (crossProb.nextDouble()<0.5)
            result.setGenom(i,genom[i]);
            else result.setGenom(i,parent.genom[i]);
        }
        return result;
    }

    public void mutate(){
        Random randomGen = new Random();
        genom[randomGen.nextInt(genomSize)] = directions[randomGen.nextInt(directions.length)];
    }
}