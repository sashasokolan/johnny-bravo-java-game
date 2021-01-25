package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * A ghost enemy.
 */
public class Ghost extends Walker implements StepListener {

    private static final Shape shape = new PolygonShape(-0.597f,0.696f, 0.552f,0.702f, 0.537f,-0.576f, -0.627f,-0.582f);
    private static final BodyImage image = new BodyImage("data/ghost.png", 1.5f);
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

    public Ghost(World world) {
        super(world, shape);
        addImage(image);
        counter = 0;
        speed = 1;

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
