import javax.swing.*;

public class FlappyBirdFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterName registerName = new RegisterName();
            registerName.createAndShowGUI();
        });
    }

    public static void startGame(String playerName) {
        int width = 360;
        int height = 640;
        JFrame flappy = new JFrame("Flappy Bird Game");
        flappy.setSize(width, height);
        flappy.setLocationRelativeTo(null);
        flappy.setResizable(false);
        flappy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird game = new FlappyBird();
        flappy.add(game);
        flappy.pack();
        game.requestFocus();

        flappy.setVisible(true);

    }
}