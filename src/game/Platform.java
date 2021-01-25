package game;

import city.cs.engine.*;

/**
 * A platform.
 */
public class Platform extends StaticBody {

    private static final Shape shape = new BoxShape(4.5f,0.5f);
    private static final BodyImage image = new BodyImage("data/platform.png", 1.5f);

    public Platform(World world) {
        super(world, shape);
        addImage(image);
    }

}
