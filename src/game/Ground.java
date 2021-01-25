package game;

import city.cs.engine.*;

/**
 * The ground.
 */
public class Ground extends StaticBody {

    private static final Shape shape = new BoxShape(20, 1);
    private static final BodyImage image = new BodyImage("data/ground.png", 8);

    public Ground(World world) {
        super(world, shape);
        addImage(image);
    }
}
