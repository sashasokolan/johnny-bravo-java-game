package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * An enemy.
 */
public class Soldier extends Walker implements StepListener {

    private static final Shape shape = new PolygonShape(-0.99f,-0.16f, -0.372f,0.92f, 0.612f,0.914f, 0.642f,-1.066f, -0.756f,-1.03f);
    private static final BodyImage image = new BodyImage("data/enemyRight.png", 2.5f);
    private int counter, speed;
    private static SoundClip enemyHit;

    static {
        try {
            enemyHit = new SoundClip("data/enemyHit.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void destroy()
    {
        enemyHit.play();
        super.destroy();
    }

    public Soldier(World world) {
        super(world, shape);
        addImage(image);
        counter = 0;
        speed = 2;

        this.startWalking(speed);
        world.addStepListener(this);
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        counter++;
        if (counter % 240 == 0){
            this.stopWalking();
            this.startWalking(speed*=-1);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
