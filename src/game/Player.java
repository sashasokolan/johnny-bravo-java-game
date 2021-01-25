package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Main player.
 */
public class Player extends Walker {

    /** Coin counter. */
    private int coinCount;

    /** Lives counter. */
    private int livesCount;

    /** Player constructor. */
    public Player(World world) {
        super(world);
        Shape shape = new PolygonShape(-1.75f,0.97f, -1.3f,-0.44f, 0.0f,-1.78f, 1.33f,-1.81f, 1.71f,1.87f, -1.74f,1.05f);
        SolidFixture fixture = new SolidFixture(this, shape);
        fixture.setDensity(3);
        fixture.setFriction(0.8f);
        fixture.setRestitution(0);
        addImage(new BodyImage("data/player-right.png", 4));
        coinCount = 0; // initial coin number
        livesCount = 3; // initial lives number
    }

    /** Coins getter. */
    public int getCoinCount() { return coinCount; }

    /** Coins setter. */
    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    /** Increment coins. */
    public void incrementCoins() {
        coinCount++;
        System.out.println("One more coin! Coins = " + coinCount);
    }

    /** Lives getter. */
    public int getLivesCount() { return livesCount; }

    /** Lives setter. */
    public void setLivesCount(int livesCount) {
        this.livesCount = livesCount;
    }

    /** Decrement lives if number of lives more than 0 and print it to the console. */
    public void decrementLives() {
        if (livesCount >= 1) {
            livesCount--;
            System.out.println("You lost a life! Lives = " + livesCount);
        }
        else
            System.out.println("You died!");
    }

}
