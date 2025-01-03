    import javax.swing.*;
    import java.awt.*;
    import javax.imageio.ImageIO;
    import java.io.File;
    import java.io.IOException;

    public class RegisterName extends JPanel {

        BackgroundMusic music;
        public RegisterName(){
            music=new BackgroundMusic();}

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new RegisterName().createAndShowGUI());
        }
        private boolean isMusicEnabled = true;


        public void createAndShowGUI() {
            JFrame frame = new JFrame("Game Registration");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(354, 675);
            frame.setLocationRelativeTo(null);




            BackgroundPanel backgroundPanel = new BackgroundPanel("D:\THIRD SEMESTER MATERIAL\DSA Theory and Lab\Flappy Bird Game\BG.jpg");

            backgroundPanel.setLayout(null);
            frame.add(backgroundPanel);


            JLabel nameLabel = new JLabel("Enter your name to Play Game");
            nameLabel.setBounds(35, 150, 300, 30);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
            nameLabel.setForeground(new Color(200, 100, 255));
            backgroundPanel.add(nameLabel);



            JTextField nameField = new JTextField();
            nameField.setBounds(70, 200, 200, 30);
            nameField.setFont(new Font("Arial", Font.PLAIN, 16));
            nameField.setBackground(new Color(240, 240, 240));
            nameField.setForeground(new Color(0, 0, 0));
            nameField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
            backgroundPanel.add(nameField);
            music.playMusic("D:\THIRD SEMESTER MATERIAL\DSA Theory and Lab\Flappy Bird Game\Music2.wav");


            JButton startGameButton = new JButton("Start Game");
            startGameButton.setBounds(100, 300, 140, 40);
            startGameButton.setFont(new Font("Arial", Font.BOLD, 16));
            startGameButton.setForeground(new Color(255, 255, 255));
            startGameButton.setBackground(new Color(0, 102, 204));
            startGameButton.setFocusPainted(false);
            startGameButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
            startGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            startGameButton.addActionListener(e -> {
                String playerName = nameField.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter your name to continue.");
                } else {
                    frame.dispose();
                    showGameWindow(playerName);
                }
            });
            backgroundPanel.add(startGameButton);


            JButton settingsButton = new JButton("Settings");
            settingsButton.setBounds(100, 360, 140, 40);
            settingsButton.setFont(new Font("Arial", Font.BOLD, 16));
            settingsButton.setForeground(new Color(255, 255, 255));
            settingsButton.setBackground(new Color(0, 102, 204));
            settingsButton.setFocusPainted(false);
            settingsButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
            settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            settingsButton.addActionListener(e -> showSettingsWindow());
            backgroundPanel.add(settingsButton);


            JButton howToPlayButton = new JButton("Help");
            howToPlayButton.setBounds(100, 420, 140, 40);
            howToPlayButton.setFont(new Font("Arial", Font.BOLD, 16));
            howToPlayButton.setForeground(new Color(255, 255, 255));
            howToPlayButton.setBackground(new Color(0, 102, 204));
            howToPlayButton.setFocusPainted(false);
            howToPlayButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
            howToPlayButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            howToPlayButton.addActionListener(e -> showHowToPlayDialog());
            backgroundPanel.add(howToPlayButton);


            JButton exitButton = new JButton("Exit");
            exitButton.setBounds(100, 470, 140, 40); // Moved exit button down to 470
            exitButton.setFont(new Font("Arial", Font.BOLD, 16));
            exitButton.setForeground(new Color(255, 255, 255));
            exitButton.setBackground(new Color(0, 102, 204));
            exitButton.setFocusPainted(false);
            exitButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
            exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            exitButton.addActionListener(e -> System.exit(0)); // Exit the game
            backgroundPanel.add(exitButton);
            frame.setVisible(true);
        }

        private void showGameWindow(String playerName) {
            JFrame gameFrame = new JFrame("Game Window");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(358, 675);
            gameFrame.setLocationRelativeTo(null);
            BackgroundPanel backgroundPanel = new BackgroundPanel("D:\THIRD SEMESTER MATERIAL\DSA Theory and Lab\Flappy Bird Game\BG.jpg);
            backgroundPanel.setLayout(null);
            gameFrame.setContentPane(backgroundPanel);

            JLabel welcomeLabel = new JLabel("Welcome, to Flappy Bird Game " + playerName + "!", SwingConstants.CENTER);
            welcomeLabel.setBounds(20, 190, 320, 50);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 17));
            welcomeLabel.setForeground(new Color(255, 255, 255));
            welcomeLabel.setBackground(new Color(0, 102, 204));
            welcomeLabel.setOpaque(true);
            backgroundPanel.add(welcomeLabel);


            new Thread(() -> {
                int x = 20;
                int direction = 1;
                float opacity = 1.0f;
                boolean fadeOut = true;

                while (true) {
                    // Update position
                    x += direction;
                    if (x >= 200 || x <= 20) {
                        direction *= -1;}


                    if (fadeOut) {
                        opacity -= 0.05f;
                        if (opacity <= 0.0f) fadeOut = false;
                    } else {
                        opacity += 0.05f;
                        if (opacity >= 1.0f) fadeOut = true;
                    }


                    int finalX = x;
                    float finalOpacity = opacity;
                    SwingUtilities.invokeLater(() -> {
                        welcomeLabel.setBounds(finalX, 190, 320, 50);
                        welcomeLabel.setForeground(new Color(255, 255, 255, (int) (255 * finalOpacity)));
                    });

                    try {
                        Thread.sleep(10); // Smooth animation
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();










            JButton startGameButton = new JButton("Start Game");
            startGameButton.setBounds(100, 300, 140, 40);
            startGameButton.setFont(new Font("Arial", Font.PLAIN, 20));
            startGameButton.setForeground(new Color(255, 255, 255));
            startGameButton.setBackground(new Color(0, 0, 128));
            startGameButton.setFocusPainted(false);
            startGameButton.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0), 2));
            startGameButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            startGameButton.addActionListener(e -> {
                gameFrame.dispose();
                FlappyBirdFrame.startGame(playerName);

            });

            gameFrame.add(startGameButton, BorderLayout.SOUTH);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
        }

        private void showHowToPlayDialog() {
            // Show a dialog with instructions on how to play
            JOptionPane.showMessageDialog(null,
                    "Flappy Bird Game Instructions:\n\n" +
                            "1. Press the space bar to make the bird fly.\n" +
                            "2. Avoid hitting pipes.\n" +
                            "3. Try to keep the bird in the air for as long as possible!\n" +
                            "4. The game ends if the bird hits a pipe or falls to the ground.\n\n" +
                            "Good Luck!",
                    "How to Play", JOptionPane.INFORMATION_MESSAGE);
        }

        private void showSettingsWindow() {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            settingsFrame.setSize(300, 200);
            settingsFrame.setLocationRelativeTo(null);

            JPanel settingsPanel = new JPanel();
            settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

            JLabel settingsLabel = new JLabel("Game Settings");
            settingsLabel.setFont(new Font("Arial", Font.BOLD, 18));
            settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            settingsPanel.add(settingsLabel);

            JCheckBox musicCheckBox = new JCheckBox("Enable Music");
            musicCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            musicCheckBox.setSelected(isMusicEnabled);
            musicCheckBox.addActionListener(e -> {
                isMusicEnabled = musicCheckBox.isSelected();
                if (isMusicEnabled) {
                   music.playMusic("D:\\THIRD SEMESTER MATERIAL\\DSA Theory and Lab\\DSA PROJECT\\Music2.wav");
                } else {
                    music.stopMusic();
                }
            });
            settingsPanel.add(musicCheckBox);
            JButton closeButton = new JButton("Close");
            closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeButton.addActionListener(e -> settingsFrame.dispose());
            settingsPanel.add(closeButton);

            settingsFrame.add(settingsPanel);
            settingsFrame.setVisible(true);
        }

        class BackgroundPanel extends JPanel {
            private Image image;

            public BackgroundPanel(String imagePath) {
                try {
                    image = ImageIO.read(new File(imagePath));
                } catch (IOException e) {
                    System.out.println("Error loading image: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, this);
                }
            }
        }
    }
