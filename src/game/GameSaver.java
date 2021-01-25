package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Save the state of the game to saves.txt.
 */
public class GameSaver {

    /** File name field. */
    private String fileName;

    /** Save the game in a file.
     * @param fileName the name of the game saver file
     */
    public GameSaver(String fileName) {
        this.fileName = fileName;
    }

    /** Save the game state.
     * @param gameWorld the current game level
     */
    public void saveGame(GameLevel gameWorld) throws IOException {
        FileWriter writer = null;

        try {
            // create new file
            writer = new FileWriter(fileName);

            // write the level number to the file
            writer.write(gameWorld.getLevelNumber() + "\n");

            // write the Player position and coin count to the file
            writer.write(gameWorld.getPlayer().getClass().getSimpleName() + "," +
                    gameWorld.getPlayer().getPosition().x + "," +
                    gameWorld.getPlayer().getPosition().y + "," +
                    gameWorld.getPlayer().getCoinCount() + "\n");

            // for the remaining dynamic bodies get the class and position of that body
            for (DynamicBody body : gameWorld.getDynamicBodies()) {
                if (!(body instanceof Player))
                writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
            }
            // for the static bodies get the class and position of that body
            for (StaticBody body : gameWorld.getStaticBodies()) {
                writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
            }

        } finally {
            // close the file
            if (writer != null) {
                writer.close();
            }
        }
    }

}
