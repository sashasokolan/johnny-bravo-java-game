package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Key handler to control a Walker.
 */
public class Controller extends KeyAdapter {
    private static final float JUMPING_SPEED = 10;
    private static final float WALKING_SPEED = 9;
    private Walker body;
    private GameLevel currentLevel;
    private Game game;
    
    public Controller(Walker body, GameLevel level, Game game) {
        this.body = body;
        currentLevel = level;
        this.game = game;
    }
    
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_SPACE) { // SPACE = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.05f) {
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_A) {
            body.startWalking(-WALKING_SPEED); // A = walk left
        } else if (code == KeyEvent.VK_D) {
            body.startWalking(WALKING_SPEED); // D = walk right
        }
        else if (code == KeyEvent.VK_S) { // S = save current level & player position
            GameSaver sw = new GameSaver("data/saves.txt");
            try {
                sw.saveGame(currentLevel);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (code == KeyEvent.VK_L) { // L = load the game
            GameLoader sr = new GameLoader("data/saves.txt", game);
            try {
                GameLevel loadedGame = sr.loadGame();
                game.goToLevel(loadedGame);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (code == KeyEvent.VK_X) { // X = save score
            HighScoreWriter sw = new HighScoreWriter("data/scores.txt");
            try {
                sw.writeHighScore("player", ((Player)body).getCoinCount());
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (code == KeyEvent.VK_R) { // R = display scores to the console
            HighScoreReader sr = new HighScoreReader("data/scores.txt");
            try {
                sr.readScores();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
        }
    }

    public void setBody(Walker body) {
        this.body = body;
    }

    public void setWorld(GameLevel level) {
        this.currentLevel = level;
    }

}
