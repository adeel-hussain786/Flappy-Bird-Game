import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    static final int BOARD_WIDTH = 340;
    static final int BOARD_HEIGHT = 640;
    static final int PIPE_WIDTH = 64;
    static final int PIPE_HEIGHT = 512;
    static final int GRAVITY = 1;

    private BackgroundMusic backgroundMusic;
    private boolean gameOverMusicPlaying = false;

    class Bird {
        LinkedList<Integer> positionHistory;
        int x = 42;
        int y = 320;
        int width = 34;
        int height = 24;
        int velocityY = 0;
        Image img;

        Bird(Image img) {
            this.img = img;
            this.positionHistory = new LinkedList<>();
        }

        void flap() {
            velocityY = -9;
        }

        void applyGravity() {
            velocityY += GRAVITY;
            y += velocityY;
            y = Math.max(y, 0);
            positionHistory.add(y);
            if (positionHistory.size() > 100) {
                positionHistory.removeFirst();
            }
        }
    }

    class Pipe {
        int x;
        int y;
        boolean passed = false;
        Image img;

        Pipe(Image img, int x, int y) {
            this.img = img;
            this.x = x;
            this.y = y;
        }

        void move(int velocityX) {
            x += velocityX;
        }

        boolean isOutOfBounds() {
            return x + PIPE_WIDTH < 0;
        }
    }

    Bird bird;
    ArrayList<Pipe> pipes;
    Timer gameLoop;
    Timer pipeTimer;
    Image flappyBg, birdImage, topPipe, bottomPipe;
    boolean gameOver = false;
    double score = 0;
    int velocityX = -4;
    Random random = new Random();

    public FlappyBird() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);

        // Initialize music
        backgroundMusic = new BackgroundMusic();
        backgroundMusic.playMusic("D:\\THIRD SEMESTER MATERIAL\\DSA Theory and Lab\\DSA PROJECT\\Music2.wav"); // Replace with the actual path

        // Load images
        flappyBg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappyBackground.png"))).getImage();
        birdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/flappyBird.png"))).getImage();
        topPipe = new ImageIcon(Objects.requireNonNull(getClass().getResource("/topPipe.png"))).getImage();
        bottomPipe = new ImageIcon(Objects.requireNonNull(getClass().getResource("/bottomPipe.png"))).getImage();

        bird = new Bird(birdImage);
        pipes = new ArrayList<>();

        pipeTimer = new Timer(1500, e -> placePipe());
        pipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(flappyBg, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, PIPE_WIDTH, PIPE_HEIGHT, null);
        }

        g.setColor(Color.blue);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        if (gameOver) {
            showGameOverScreen();
        } else {
            g.drawString("Score: " + score, 10, 35);
        }
    }

    private void showGameOverScreen() {
        if (!gameOverMusicPlaying) {
            // Stop the background music
            backgroundMusic.stopMusic();
         

            // Play the game-over sound
            BackgroundMusic gameOverMusic = new BackgroundMusic();
            gameOverMusic.playMusic("D:\\THIRD SEMESTER MATERIAL\\DSA Theory and Lab\\DSA PROJECT\\game-over.wav"); // Replace with actual path
            gameOverMusicPlaying = true;
        }

        // Game over screen code
        JFrame gameOverFrame = new JFrame("Game Over!");
        gameOverFrame.setSize(300, 250);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        JButton restartButton = new JButton("Click Here to Restart");
        JButton closeButton = new JButton("Close Game");

        restartButton.addActionListener(e -> {
            gameOverFrame.dispose();
            restartGame();
        });

        closeButton.addActionListener(e -> System.exit(0));

        panel.add(scoreLabel);
        panel.add(restartButton);
        panel.add(closeButton);

        gameOverFrame.add(panel);
        gameOverFrame.setVisible(true);
    }



    private void placePipe() {
        int randomY = -PIPE_HEIGHT / 4 - random.nextInt(PIPE_HEIGHT / 2);
        pipes.add(new Pipe(topPipe, BOARD_WIDTH, randomY));
        pipes.add(new Pipe(bottomPipe, BOARD_WIDTH, randomY + PIPE_HEIGHT + BOARD_HEIGHT / 4));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.applyGravity();
            for (Pipe pipe : pipes) {
                pipe.move(velocityX);
                if (!pipe.passed && bird.x > pipe.x + PIPE_WIDTH) {
                    pipe.passed = true;
                    score += 1;
                }
                if (collision(bird, pipe)) {
                    gameOver = true;
                }
            }
            pipes.removeIf(Pipe::isOutOfBounds);
            if (bird.y > BOARD_HEIGHT) {
                gameOver = true;
            }
            repaint();
        }
    }

    private boolean collision(Bird bird, Pipe pipe) {
        return bird.x < pipe.x + PIPE_WIDTH &&
                bird.x + bird.width > pipe.x &&
                bird.y < pipe.y + PIPE_HEIGHT &&
                bird.y + bird.height > pipe.y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.flap();
        }
    }

    private void restartGame() {
        bird.y = 320;
        bird.velocityY = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
        gameOverMusicPlaying = false;

        backgroundMusic.stopMusic();
        backgroundMusic.playMusic("D:\\THIRD SEMESTER MATERIAL\\DSA Theory and Lab\\DSA PROJECT\\Music2.wav"); // Replace with actual path

        gameLoop.start();
        pipeTimer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
