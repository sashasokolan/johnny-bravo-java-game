package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import java.io.IOException;

/**
 * A coin.
 */
public class Coin extends DynamicBody {
    private static final Shape shape = new CircleShape(0.5f);
    private static final BodyImage image = new BodyImage("data/coin.png", 1);
    private static SoundClip coinSound;

    static {
        try {
            coinSound = new SoundClip("data/coinSound.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void destroy()
    {
        coinSound.play();
        super.destroy();
    }

    public Coin(World world) {
        super(world, shape);
        addImage(image);
    }

}
