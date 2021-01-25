package game;

import city.cs.engine.Body;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Load the last saved state of the game.
 */
public class GameLoader {

    private String fileName;
    private Game game;
    private GameLevel levelNumber;

    /**
     * Initialise a new GameLoader
     * @param fileName the name of the game loader file
     * @param game the main entry point into the program
     */
    public GameLoader(String fileName, Game game) {
        this.fileName = fileName;
        this.game = game;
    }

    /**
     * Read the saved games data from the saves.txt file and load the game.
     * @return level, the level of the game
     */
    public GameLevel loadGame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;

        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);

            // read level number using buffered reader
            String line = reader.readLine();
            int levelNumber = Integer.parseInt(line);

            // create levels based on the level number read from the file
            // !!! level variable isn't updated !!!
            GameLevel level = null;
            if (levelNumber == 1) {
                level = new Level1();
            } else if (levelNumber == 2) {
                level = new Level2();
            } else if (levelNumber == 3) {
                level = new Level3();
            }

            // on a line read class name as the first data value,
            // then x position and y position,
            // all separated by commas
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String className = tokens[0];
                float xPlayer = Float.parseFloat(tokens[1]);
                float yPlayer = Float.parseFloat(tokens[2]);
                Vec2 posPlayer = new Vec2(xPlayer, yPlayer);

                // to transfer all game states to the loaded game
                // for each class create a new object of that class,
                // set its position and if applicable its collision listener
                if (className.equals("Johnny")) {
                    Player b = new Player(level);
                    b.setPosition(posPlayer);
                    int count = Integer.parseInt(tokens[3]);
                    b.setCoinCount(count); // read and set coin count
                    level.setPlayer(b); // NULL!!!

                }
                if (className.equals("Enemy")) {
                    Body b = new Soldier(level);
                    b.setPosition(posPlayer);
                    b.addCollisionListener(new Pickup(level.getPlayer())); // NULL!!!
                }
                if (className.equals("Ghost")) {
                    Body b = new Ghost(level);
                    b.setPosition(posPlayer);
                    b.addCollisionListener(new Pickup(level.getPlayer()));
                }
                if (className.equals("Coin")) {
                    Body b = new Coin(level);
                    b.setPosition(posPlayer);
                    b.addCollisionListener(new Pickup(level.getPlayer()));
                }
                if (className.equals("Ground")) {
                    Body b = new Ground(level);
                    b.setPosition(posPlayer);
                }
                if (className.equals("Platform")) {
                    Body b = new Platform(level);
                    b.setPosition(posPlayer);
                }
                if (className.equals("Door")) {
                    Body b = new Door(level);
                    b.setPosition(posPlayer);
                    b.addCollisionListener(new DoorListener(game));
                }
            }

            return level;

        } finally {
            // close the file
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
