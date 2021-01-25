package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import javax.swing.*;
import java.awt.*;

/**
 * User view.
 */
public class MyView extends UserView {
    Player player;
    private Image background;

    public MyView(World world, Player player, int width, int height) {
        super(world, width, height);
        this.player = player;
        this.background = new ImageIcon("data/background1.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        g.drawString("Score: " + player.getCoinCount(), 20, 20);
        g.drawString("Health: " + player.getLivesCount(), 20, 40);
    }

    public void changeBackground(String levelNo){
        if (levelNo.equals("level1")) {
            background = new ImageIcon("data/background1.png").getImage();
        }
        if (levelNo.equals("level2")) {
            background = new ImageIcon("data/background2.png").getImage();
        }
        if (levelNo.equals("level3")) {
            background = new ImageIcon("data/background3.png").getImage();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
