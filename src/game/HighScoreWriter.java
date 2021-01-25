package game;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * High-score data is written to a text file.
 */
public class HighScoreWriter extends JComponent {

    private String fileName;

    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeHighScore(String name, int score) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            writer.write(name + "," + score + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HighScoreWriter hsWriter = new HighScoreWriter("sample.hs");
        for (int i = 0; i < args.length; i += 2) {
            String name = args[i];
            int score = Integer.parseInt(args[i + 1]);
            hsWriter.writeHighScore(name, score);
        }
    }
}
