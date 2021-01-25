package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Level 1 of the game.
 */
public class Level1 extends GameLevel implements ActionListener {

    /** Number of coins necessary to complete the level. */
    private static final int NUM_COINS = 3;

    /** Player field. */
    private Player player;

    /** Player getter. */
    public Player getPlayer() {
        return player;
    }

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        //super.populate(game);

        // create player
        player = new Player(this);
        player.setPosition(startPosition());

        // create soldier
        Soldier soldier = new Soldier(this);
        soldier.setPosition(new Vec2(enemyPosition()));
        soldier.addCollisionListener(new Pickup(getPlayer()));

        // create ghost
        Ghost ghost = new Ghost(this);
        ghost.setPosition(new Vec2(ghostPosition()));
        ghost.addCollisionListener(new Pickup(getPlayer()));

        // create door
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));

        // create coins
        for (int i = 0; i < 4; i++) {
            Body coin = new Coin(this);
            coin.setPosition(new Vec2(i * 2 + 3, 3));
            coin.addCollisionListener(new Pickup(getPlayer()));
        }

        // create left platform
        Body platform1 = new Platform(this);
        platform1.setPosition(new Vec2(6, -8));

        // create right platform
        Body platform2 = new Platform(this);
        platform2.setPosition(new Vec2(-10, -5));

        // create ground
        Body ground = new Ground(this);
        ground.setPosition(new Vec2(0, -12));

        // timer for new coins to fall down every 5 seconds
        Timer timer = new Timer(5000, this);
        timer.start();

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-10, 0);
    }

    @Override
    public Vec2 enemyPosition() {
        return new Vec2(-14, -10);
    }

    @Override
    public Vec2 ghostPosition() { return new Vec2(-14, -3); }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(16, -10);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getCoinCount() >= NUM_COINS;
    }

    @Override
    public int getLevelNumber() {
        return 1;
    }


    /** Create a coin every 5 seconds in a random x position. */
    @Override
    public void actionPerformed(ActionEvent e) {
        Body coin = new Coin(this);
        coin.setPosition(new Vec2((float) (5 + (Math.random() * - 10)), 3));
        coin.addCollisionListener(new Pickup(player));
    }
}
