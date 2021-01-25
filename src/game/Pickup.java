package game;

import city.cs.engine.*;

/**
 * Collision listener that allows the player to collect things.
 */


public class Pickup implements CollisionListener {
    private Player player;
    
    public Pickup(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Coin && e.getOtherBody() == player) {
            player.incrementCoins();
            e.getReportingBody().destroy();
        }
        if (e.getReportingBody() instanceof Soldier && e.getOtherBody() == player) {
            player.decrementLives();
            e.getReportingBody().destroy();
        }
        if (e.getReportingBody() instanceof Ghost && e.getOtherBody() == player) {
            player.decrementLives();
            e.getReportingBody().destroy();

        }
    }
}
