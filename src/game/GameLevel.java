package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Abstract level of the game.
 */
public abstract class GameLevel extends World {
    private Player player;

    /** Get the player. */
    public Player getPlayer() {
        return player;
    }

    /** Set the player. */
    public void setPlayer(Player player) {
        this.player = player;
    }


    /**
     * Populate the world of this level.
     */
    public void populate(Game game) {

//        // player doesn't get created and remains null???
//        player = new Player(this);
//        player.setPosition(startPosition());

    }

    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    /** The initial position of the enemy. */
    public abstract Vec2 enemyPosition();

    /** The initial position of the ghost. */
    public abstract Vec2 ghostPosition();

    /** The position of the exit door. */
    public abstract Vec2 doorPosition();

    /** Is this level complete? */
    public abstract boolean isCompleted();

    /** Get the level. */
    public abstract int getLevelNumber();

}
