
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;


 
public class XandO_GUI  extends JFrame {

    private JPanel currentPanel;

    public XandO_GUI() {
    	
        setTitle("Xs and Os");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 520);
        setLocationRelativeTo(null);
        setResizable(false);
        showMenu();
    }

    /* ---------- Navigation ---------- */

    private void showMenu() {
    	
        setContent(new StartMenuPanel(this));
        
    }

    private void startGame(boolean vsAI, char humanSymbol, boolean humanStarts) {
    	
        setContent(new GamePanel(this, vsAI, humanSymbol, humanStarts));
    }

    private void setContent(JPanel panel) { 
    	
        if (currentPanel != null) remove(currentPanel);
        currentPanel = panel;
        add(currentPanel);
        revalidate();
        repaint();
    }

    /* ---------- Start Menu Panel ---------- */

    private static class StartMenuPanel extends JPanel {
    	
        StartMenuPanel(XandO_GUI app) {
        	
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = 0; gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(8, 16, 8, 16);

            JLabel title = new JLabel("Xs and Os", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 28f));

            JButton vsAI = new JButton("Play vs AI");
            JButton twoP = new JButton("Two Players");
            JButton exit = new JButton("Exit");

            vsAI.addActionListener(e -> {
                // Dialogs for symbol + first move
                Object symbol = JOptionPane.showInputDialog(
                        app,
                        "Choose your symbol:",
                        "Symbol",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"X", "O"},
                        "X"
                );
                if (symbol == null) return; // cancelled

                int first = JOptionPane.showConfirmDialog(
                        app,
                        "Do you want to play first?",
                        "First Move",
                        JOptionPane.YES_NO_OPTION
                );
                if (first == JOptionPane.CLOSED_OPTION) return;

                char human = symbol.toString().charAt(0);
                boolean humanStarts = (first == JOptionPane.YES_OPTION);
                app.startGame(true, human, humanStarts);
            });

            twoP.addActionListener(e -> {
                // In 2P, X starts by default
                app.startGame(false, 'X', true);
            });

            exit.addActionListener(e -> System.exit(0));

            JPanel box = new JPanel();
            box.setLayout(new GridLayout(0, 1, 10, 10));
            box.add(title);
            box.add(vsAI);
            box.add(twoP);
            box.add(exit);

            add(box, gc);
        }
    }

    /* ---------- Game Panel ---------- */

    private static class GamePanel extends JPanel {
        private final XandO_GUI app;
        private final boolean vsAI;
        private final char human;
        private final char aiPlayer;
        private boolean humanTurn; // true if human to move (in AI mode), else AI
        private char current2P = 'X';

        private Board board;
        private AI ai;

        private final JButton[] cells = new JButton[9];
        private final JLabel status = new JLabel("Ready", SwingConstants.CENTER);

        private boolean gameOver = false;

        GamePanel(XandO_GUI app, boolean vsAI, char humanSymbol, boolean humanStarts) {
            this.app = app;
            this.vsAI = vsAI;
            this.human = humanSymbol;
            this.aiPlayer = (humanSymbol == 'X') ? 'O' : 'X';
            this.humanTurn = humanStarts;

            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Top bar with actions
            JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            JButton btnRestart = new JButton("Restart");
            JButton btnMenu = new JButton("Back to Menu");
            top.add(btnRestart);
            top.add(btnMenu);

            btnRestart.addActionListener(e -> restart());
            btnMenu.addActionListener(e -> app.showMenu());

            // Grid
            JPanel grid = new JPanel(new GridLayout(3, 3, 10, 10));
            Font cellFont = new Font("Arial", Font.BOLD, 40);
            for (int i = 0; i < 9; i++) {
                JButton b = new JButton("");
                b.setFont(cellFont);
                final int number = i + 1;
                b.addActionListener(e -> handleCellClick(number));
                b.setFocusPainted(false);
                cells[i] = b;
                grid.add(b);
            }

            status.setFont(status.getFont().deriveFont(Font.PLAIN, 16f));
            status.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

            add(top, BorderLayout.NORTH);
            add(grid, BorderLayout.CENTER);
            add(status, BorderLayout.SOUTH);

            startNewGame();
        }

        private void startNewGame() {
            board = new Board();
            ai = new AI(board);
            gameOver = false;
            if (vsAI) {
                if (!humanTurn) {
                    status.setText("AI thinking…");
                    // Tiny delay so the UI updates before AI moves
                    Timer t = new Timer(220, (ActionEvent e) -> {
                        ((Timer) e.getSource()).stop();
                        aiMove();
                    });
                    t.setRepeats(false);
                    t.start();
                } else {
                    status.setText("Your turn (" + human + ")");
                }
            } else {
                current2P = 'X';
                status.setText("Two Players: X to move");
            }
            render();
        }

        private void restart() {
            // Keep same settings; just reset the board and turn
            if (vsAI) {
                startNewGame();
            } else {
                startNewGame();
            }
        }

        private void render() {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    char ch = board.getCell(r, c); // must exist in your Board
                    JButton b = cells[r * 3 + c];
                    b.setText(ch == ' ' ? "" : String.valueOf(ch));
                    b.setEnabled(!gameOver && ch == ' ');
                }
            }
        }

        private void handleCellClick(int number) {
            if (gameOver) return;

            if (vsAI) {
                if (!humanTurn) return; // ignore clicks while AI to move

                if (!board.isAvailable(number)) return;
                if (!board.placeByNumber(number, human)) return;

                render();

                if (board.checkWin(human)) {
                    gameOver = true;
                    status.setText("You win! (" + human + ")");
                    render();
                    return;
                }
                if (board.isFull()) {
                    gameOver = true;
                    status.setText("Draw");
                    render();
                    return;
                }

                // AI's turn
                humanTurn = false;
                status.setText("AI thinking…");
                Timer t = new Timer(220, (ActionEvent e) -> {
                    ((Timer) e.getSource()).stop();
                    aiMove();
                });
                t.setRepeats(false);
                t.start();

            } else {
                // Two-player mode
                char player = current2P;
                if (!board.isAvailable(number)) return;
                if (!board.placeByNumber(number, player)) return;

                render();

                if (board.checkWin(player)) {
                    gameOver = true;
                    status.setText(player + " wins!");
                    return;
                }
                if (board.isFull()) {
                    gameOver = true;
                    status.setText("Draw");
                    return;
                }

                current2P = (current2P == 'X') ? 'O' : 'X';
                status.setText(current2P + " to move");
            }
        }

        private void aiMove() {
            if (gameOver) return;

            int move = ai.getMove();     // 1..9 or -1
            if (move == -1) {
                gameOver = true;
                status.setText("Draw");
                render();
                return;
            }

            board.placeByNumber(move, aiPlayer);
            render();

            if (board.checkWin(aiPlayer)) {
                gameOver = true;
                status.setText("AI wins! (" + aiPlayer + ")");
                return;
            }
            if (board.isFull()) {
                gameOver = true;
                status.setText("Draw");
                return;
            }

            humanTurn = true;
            status.setText("Your turn (" + human + ")");
        }
    }

    /* ---------- Entry point ---------- */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new XandO_GUI().setVisible(true));
    }
}