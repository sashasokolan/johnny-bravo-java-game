package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import java.awt.*;

/**
 * The computer game.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameLevel world;

    /** A graphical display of the world (a specialised JPanel). */
    private MyView view;

    /** Current game level. */
    private int level;

    /** Controller for the player. */
    private Controller controller;

    /** Sound clips. */
    private SoundClip gameMusic;

    JFrame debugView;

    /** Initialise a new Game. */
    public Game() {

        // make the world
        level = 1;
        world = new Level1();
        world.populate(this);

        // background music
        try {
            gameMusic = new SoundClip("data/half-price-store.wav");
            gameMusic.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            e.printStackTrace();
        }

        // make a view
        view = new MyView(world, world.getPlayer(), 700, 600);
        // display the view in a frame
        final JFrame frame = new JFrame("Game");
        // display the GUI controls
        ControlPanel buttons = new ControlPanel(this);
        frame.add(buttons.getMainPanel(), BorderLayout.EAST);
        // display the world in the window
        frame.add(view);
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));
        // add a controller
        controller = new Controller(world.getPlayer(), world, this);
        frame.addKeyListener(controller);
        // start!
        world.start();

        // draw a 1-metre grid over the view
        //view.setGridResolution(1);

        // debugging view
        //debugView = new DebugViewer(world, 700, 600);
    }

    /** The player in the current level. */
    public Player getPlayer() {
        return world.getPlayer();
    }

    /** Return the current level's world. */
    public World getWorld() { return world; }

    /** Return the current level. */
    public int getLevel() { return level; }

    /** Pause the game. */
    public void pause() { world.stop(); }

    /** Restart the game. */
    public void restart() { world.start(); }

    /** Quit the game. */
    public void quit() { System.exit(0); }

    /** Is the current level of the game finished? */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /** Jump to the last saved game state. */
    public void goToLevel(GameLevel gameLevel) {
        world.stop();
        level = gameLevel.getLevelNumber();
        world = gameLevel;
        // switch the keyboard control to the new player
        controller.setBody(world.getPlayer());
//        // link controller to the current level
//        controller.setWorld(world);
        // show the new world in the view
        view.setWorld(world);
        // start the game
        world.start();

        if (level == 3) {
            // background music
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/quirky-swing.wav");
                gameMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                e.printStackTrace();
            }
            // change background
            view.changeBackground("level3");
        }
        if (level == 2) {
            // background music
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/welcome-to-the-advanced-mode.wav");
                gameMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                e.printStackTrace();
            }
            // change background
            view.changeBackground("level2");
        } else if (level == 1) {
            // background music
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/half-price-store.wav");
                gameMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                e.printStackTrace();
            }
            // change background
            view.changeBackground("level1");
        }
    }


    /** Advance to the next level of the game. */
    public void goNextLevel() {
        world.stop();
        Player oldPlayer = world.getPlayer();
        if (level == 3)
            System.exit(0);
        if (level == 2) {
            level++;
            // get a new world
            world = new Level3();
            // fill it with bodies
            world.populate(this);
            // background music
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/quirky-swing.wav");
                gameMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                e.printStackTrace();
            }
            // continue screen counter
            view.setPlayer(world.getPlayer());
            // change background
            view.changeBackground("level3");
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // link controller to the current level
            controller.setWorld(world);
            // transfer coin count to next level
            world.getPlayer().setCoinCount(oldPlayer.getCoinCount());
            // transfer lives count to next level
            world.getPlayer().setLivesCount(oldPlayer.getLivesCount());
            // show the new world in the view
            view.setWorld(world);
            // start the game
            world.start();
        } else if (level == 1) {
            level++;
            // get a new world
            world = new Level2();
            // fill it with bodies
            world.populate(this);
            // background music
            gameMusic.stop();
            try {
                gameMusic = new SoundClip("data/welcome-to-the-advanced-mode.wav");
                gameMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                e.printStackTrace();
            }
            // continue screen counter
            view.setPlayer(world.getPlayer());
            // change background
            view.changeBackground("level2");
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // transfer coin count to next level
            world.getPlayer().setCoinCount(oldPlayer.getCoinCount());
            // transfer lives count to next level
            world.getPlayer().setLivesCount(oldPlayer.getLivesCount());
            // show the new world in the view
            view.setWorld(world);
            // start the game
            world.start();
        }
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }

}
