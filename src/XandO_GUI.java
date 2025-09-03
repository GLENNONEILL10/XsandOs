
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;


 
public class XandO_GUI  extends JFrame {

    private JPanel currentPanel;

    //constructor
    public XandO_GUI() {
    	
        //sets the menu, title and window size
        setTitle("Xs and Os");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 520);
        setLocationRelativeTo(null);
        setResizable(false);
        showMenu();
    }

    /* ---------- Navigation ---------- */
    //function that shows the menus
    private void showMenu() {
    	
        //sets the content
        setContent(new StartMenuPanel(this));
        
    }

    //function that starts the game 
    private void startGame(boolean vsAI, char humanSymbol, boolean humanStarts) {
    	
        //sets the content
        setContent(new GamePanel(this, vsAI, humanSymbol, humanStarts));
    }

    //function that sets the content
    private void setContent(JPanel panel) { 
    	
        //if current panel is not null
        if (currentPanel != null) {
            
            //removes the panel
            remove(currentPanel);

        }

        // assigns panel to currentPanel
        currentPanel = panel;

        //adds the currentPanel to screen
        add(currentPanel);
        revalidate();
        repaint();
    }

    /* ---------- Start Menu Panel ---------- */

    //menu class
    private static class StartMenuPanel extends JPanel {
    	
        //contructor
        StartMenuPanel(XandO_GUI app) {

        	//sets layout to grid bag layout
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = 0; gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(8, 16, 8, 16);

            JLabel title = new JLabel("Xs and Os", SwingConstants.CENTER);
            title.setFont(title.getFont().deriveFont(Font.BOLD, 28f));

            JButton vsAI = new JButton("Play vs AI");
            JButton twoP = new JButton("Two Players");
            JButton exit = new JButton("Exit");

            //action listener for versus ai button
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

                //play first option window
                int first = JOptionPane.showConfirmDialog(
                        app,
                        "Do you want to play first?",
                        "First Move",
                        JOptionPane.YES_NO_OPTION
                );

                if (first == JOptionPane.CLOSED_OPTION) return;

                //assigns human symbol
                char human = symbol.toString().charAt(0);

                boolean humanStarts = (first == JOptionPane.YES_OPTION);

                //starts the game
                app.startGame(true, human, humanStarts);
            });

            //action listener for two player button
            twoP.addActionListener(e -> {
                // In 2P, X starts by default
                app.startGame(false, 'X', true);
            });

            //action listener for exit button
            exit.addActionListener(e -> System.exit(0));

            //creates panel and adds the attributes to the panel
            JPanel box = new JPanel();
            box.setLayout(new GridLayout(0, 1, 10, 10));
            box.add(title);
            box.add(vsAI);
            box.add(twoP);
            box.add(exit);

            //adds panel and grid bag layout to screen
            add(box, gc);
        }
    }

    /* ---------- Game Panel ---------- */
    //gamePanel class
    private static class GamePanel extends JPanel {
        private final XandO_GUI app;
        private final boolean vsAI;
        private final char human;
        private final char aiPlayer;
        private boolean humanTurn; // true if human to move (in AI mode), else AI
        private char current2P = 'X';

        private Board board;
        private AI ai;

        //buttons for each square on board
        private final JButton[] cells = new JButton[9];
        private final JLabel status = new JLabel("Ready", SwingConstants.CENTER);

        private boolean gameOver = false;

        //constructor
        GamePanel(XandO_GUI app, boolean vsAI, char humanSymbol, boolean humanStarts) {
            this.app = app;
            this.vsAI = vsAI;
            this.human = humanSymbol;

            //swaps the player symbols
            this.aiPlayer = (humanSymbol == 'X') ? 'O' : 'X';
            this.humanTurn = humanStarts;

            //sets the layout to border layout
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Top bar with actions
            JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            JButton btnRestart = new JButton("Restart");
            JButton btnMenu = new JButton("Back to Menu");
            top.add(btnRestart);
            top.add(btnMenu);

            //action listeners for top bar buttons
            btnRestart.addActionListener(e -> restart());
            btnMenu.addActionListener(e -> app.showMenu());

            //Grid
            JPanel grid = new JPanel(new GridLayout(3, 3, 10, 10));
            Font cellFont = new Font("Arial", Font.BOLD, 40);

            //loops 9 times
            for (int i = 0; i < 9; i++) {
                //creates new empty button
                JButton b = new JButton("");

                //sets the button font
                b.setFont(cellFont);

                final int number = i + 1;

                //adds action listners for each button on grid
                b.addActionListener(e -> handleCellClick(number));


                b.setFocusPainted(false);

                //adds the buttons to the cells array
                cells[i] = b;

                //adds te nutton
                grid.add(b);
            }

            //sets the status label
            status.setFont(status.getFont().deriveFont(Font.PLAIN, 16f));
            status.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

            //adds to screen
            add(top, BorderLayout.NORTH);
            add(grid, BorderLayout.CENTER);
            add(status, BorderLayout.SOUTH);

            //starts new game at end
            startNewGame();
        }

        private void startNewGame() {
            //initialised new board 
            board = new Board();
            ai = new AI(board);
            gameOver = false;

            //if vsAI = true
            if (vsAI) {

                //if ai turn
                if (!humanTurn) {
                    status.setText("AI thinking…");
                    // Tiny delay so the UI updates before AI moves
                    Timer t = new Timer(220, (ActionEvent e) -> {
                        ((Timer) e.getSource()).stop();
                        aiMove();
                    });
                    t.setRepeats(false);
                    t.start();
                } 
                //if human turn
                else {
                    status.setText("Your turn (" + human + ")");
                }
            } 
            // vsAI is false
            else {

                //sets the player symbol
                current2P = 'X';
                status.setText("Two Players: X to move");
            }
            render();
        }

        //function that resets the board
        private void restart() {
            // Keep same settings; just reset the board and turn
            if (vsAI) {
                startNewGame();
            } else {
                startNewGame();
            }
        }

        //function the renders the board
        private void render() {

            for (int r = 0; r < 3; r++) {

                for (int c = 0; c < 3; c++) {

                    //gets the board indexes and stores
                    char ch = board.getCell(r, c);
                    
                    //sets the cells to single indexes , ie 0,0 = 0
                    JButton b = cells[r * 3 + c];

                    //sets the cell button to empty or symbol
                    b.setText(ch == ' ' ? "" : String.valueOf(ch));

                    b.setEnabled(!gameOver && ch == ' ');
                }
            }
        }

        //function the handles click
        private void handleCellClick(int number) {

            //if game over is true
            if (gameOver) return;

            //if against ai
            if (vsAI) {


                if (!humanTurn) return; // ignore clicks while AI to move

                //if is available is false
                if (!board.isAvailable(number)){

                    return;

                } 

                //if place by number is false
                if (!board.placeByNumber(number, human)){

                    return;

                } 


                
                render();

                //checks if human has won
                if (board.checkWin(human)) {
                    gameOver = true;
                    status.setText("You win! (" + human + ")");
                    render();
                    return;
                }

                //checks if board is full
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

            } 
            else {
                // Two-player mode
                char player = current2P;
                if (!board.isAvailable(number)) {
                    
                    
                    return;
                }
                if (!board.placeByNumber(number, player)){ 
                    
                    return;
                }

                render();

                //checks if player has won
                if (board.checkWin(player)) {
                    gameOver = true;
                    status.setText(player + " wins!");
                    return;
                }

                //checks if board is full
                if (board.isFull()) {
                    gameOver = true;
                    status.setText("Draw");
                    return;
                }

                //swaps players
                current2P = (current2P == 'X') ? 'O' : 'X';
                status.setText(current2P + " to move");
            }
        }

        //function that handles aiMove
        private void aiMove() {

            if (gameOver) return;

            //stores the moves from list
            int move = ai.getMove();     // 1..9 or -1
            if (move == -1) {
                gameOver = true;
                status.setText("Draw");
                render();
                return;
            }

            //places the ai move 
            board.placeByNumber(move, aiPlayer);
            render();

            //checks if its a win
            if (board.checkWin(aiPlayer)) {
                gameOver = true;
                status.setText("AI wins! (" + aiPlayer + ")");
                return;
            }
            //checks is board is full
            if (board.isFull()) {
                gameOver = true;
                status.setText("Draw");
                return;
            }

            //swaps to human turn
            humanTurn = true;
            status.setText("Your turn (" + human + ")");
        }
    }

    /* ---------- Entry point ---------- */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new XandO_GUI().setVisible(true));
    }
}