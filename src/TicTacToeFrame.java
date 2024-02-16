import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import java.awt.event.*;

// This class creates the frame for the Tic Tac Toe game
public class TicTacToeFrame
{
    //

    int boardWidth = 600;
    int boardHeight = 600;
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel( );
    JPanel textPanel = new JPanel();

    JPanel boardPanel = new JPanel();

    JPanel controlPnl = new JPanel();

    // 2D array of JButtons
    JButton[][] board = new JButton[3][3];

    // Strings for the players
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    // Variables to keep track of the score
    int XWins = 0;
    int OWins = 0;
    int Ties = 0;

    // Variable to keep track of the number of turns
    boolean GameOver = false;
    int turns = 0;


    // Constructor
    TicTacToeFrame()
    {
        // Set the title of the frame
        frame.setTitle("Tic Tac Toe");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set the text label
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        // Set the layout of the text panel
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Set the layout of the board panel
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.WHITE);
        frame.add(boardPanel);

        // Set the layout of the control panel
        createControlPanel();
        frame.add(controlPnl, BorderLayout.SOUTH);
        controlPnl.setBackground(Color.BLACK);
        controlPnl.setForeground(Color.WHITE);

        // Create the board
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                // Create a new JButton
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add (tile);

                // Set the background, foreground, font, and focusable of the tile
                tile.setBackground(Color.GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Ariel", Font.BOLD, 100));
                tile.setFocusable(false);

                //tested to make sure text was here
                //tile.setText(currentPlayer);

                // Add an action listener to the tile
                tile.addActionListener(e -> {
                    // If the game is over, return
                    if (GameOver) return;
                    // Get the source of the event
                    JButton tile1 = (JButton) e.getSource();
                    // If the tile is empty, set the text of the tile to the current player
                    if (Objects.equals(tile1.getText(), ""))
                    {
                        // Set the text of the tile to the current player
                        tile1.setText(currentPlayer);
                        turns++;
                        checkWin();
                        if (!GameOver)
                        {
                            // Change the current player
                        currentPlayer = (currentPlayer.equals(playerX)) ? playerO : playerX;
                        textLabel.setText(currentPlayer + " Turn");
                        }
                    }
                });
            }
        }
    }

    // This method checks if a player has won the game
    void checkWin()
    {
        //horizontal win
        for(int row = 0; row < 3; row++)
        {
            // If the text of the tile at the current row and column is equal to the current player, set the win
            if (board[row][0].getText().equals(currentPlayer) &&
                    board[row][1].getText().equals(currentPlayer) &&
                    board[row][2].getText().equals(currentPlayer))
            {
                // Set the win
                for (int i = 0; i < 3; i++)
                {
                    setWin(board [row][i]);
                }
                // Set the text of the text label
                textLabel.setText(currentPlayer + " Wins!");
                if (currentPlayer.equals(playerX))
                {
                    // Increment the number of wins for player X
                    XWins++;
                }
                else
                {
                    // Increment the number of wins for player O
                    OWins++;
                }
                // Set the game over to true
                GameOver = true;
            }
        }

        //vertical win
        for (int col = 0; col < 3; col++)
        {
            // If the text of the tile at the current row and column is equal to the current player, set the win
            if (board[0][col].getText().equals(currentPlayer) &&
                    board[1][col].getText().equals(currentPlayer) &&
                    board[2][col].getText().equals(currentPlayer))
            {
                // Set the win
                for (int i = 0; i < 3; i++) {
                    setWin(board[i][col]);
                }
                // Set the text of the text label
                textLabel.setText(currentPlayer + " Wins!");
                if (currentPlayer.equals(playerX))
                {
                    // Increment the number of wins for player X
                    XWins++;
                }
                else
                {
                    // Increment the number of wins for player O
                    OWins++;
                }
                // Set the game over to true
                GameOver = true;
            }
        }


        //diagonal top left bottom right diagonal
        if (board[0][0].getText().equals(currentPlayer) &&
                board[1][1].getText().equals(currentPlayer) &&
                board[2][2].getText().equals(currentPlayer))
        {
            // Set the win
            setWin(board[0][0]);
            setWin(board[1][1]);
            setWin(board[2][2]);

            // Set the text of the text label
            textLabel.setText(currentPlayer + " Wins!");
            if (currentPlayer.equals(playerX))
            {
                // Increment the number of wins for player X
                XWins++;
            }
            else
            {
                // Increment the number of wins for player O
                OWins++;
            }
            // Set the game over to true
            GameOver = true;
        }
        //diagonal top right bottom left diagonal
        if (board[0][2].getText().equals(currentPlayer) &&
                board[1][1].getText().equals(currentPlayer) &&
                board[2][0].getText().equals(currentPlayer))
        {
            // Set the win
            setWin(board[0][2]);
            setWin(board[1][1]);
            setWin(board[2][0]);

            // Set the text of the text label
            textLabel.setText(currentPlayer + " Wins!");
            if (currentPlayer.equals(playerX))
            {
                // Increment the number of wins for player X
                XWins++;
            }
            else
            {
                // Increment the number of wins for player O
                OWins++;
            }
            // Set the game over to true
            GameOver = true;
        }

        //tie
        if (turns == 9)
        {
            // Set the text of the text label
            for (int row = 0; row < 3; row++)
            {
                // If the text of the tile at the current row and column is equal to the current player, set the win
                for (int col = 0; col < 3; col++)
                {
                    // Set the tie
                    setTie(board[row][col]);
                }
            }
            //Add to the number of ties
            Ties++;
            // Set the game over to true
            GameOver = true;
        }

    }

    // This method sets the win
    void setWin(JButton tile)
    {
        // Set the background and foreground of the tile
        tile.setBackground(Color.GRAY);
        tile.setForeground(Color.green );

    }

    // This method sets the tie
    void setTie(JButton tile)
    {
        // Set the background and foreground of the tile
        tile.setBackground(Color.GRAY);
        tile.setForeground(Color.red);
        textLabel.setText("Tie");
    }

    // This method creates the control panel
    void createControlPanel()
    {
        // Set the layout of the control panel
        controlPnl.setLayout(new GridLayout(1, 3));
        controlPnl.setBackground(Color.BLACK);

        // Create the new game and score buttons
        JButton newGame = new JButton("New Game");
        newGame.setBackground(Color.BLACK);
        newGame.setForeground(Color.WHITE);
        JButton score = new JButton("Score");
        score.setBackground(Color.BLACK);
        score.setForeground(Color.WHITE);


        // Add an action listener to the new game button
        newGame.addActionListener(e -> newGame());
        score.addActionListener((ActionEvent ae) -> JOptionPane.showMessageDialog(null, "Player X's number of Wins" + " " + XWins + " \nPlayer O's number of Wins" + " " + OWins + "\nTies" + " " + Ties));


        // Add the new game and score buttons to the control panel
        controlPnl.add(newGame);
        controlPnl.add(score);

    }

    // This method creates a new game
    private void newGame()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                // Set the text of the tile to an empty string
                board[row][col].setText("");
                // Set the background and foreground of the tile
                board[row][col].setBackground(Color.GRAY);
                board[row][col].setForeground(Color.WHITE);
                // Set the game over to false
                GameOver = false;
                // Set the current player to player X
                currentPlayer = playerX;
                // Set the text of the text label to the current player's turn
                textLabel.setText(currentPlayer + " Turn");
                // Set the number of turns to 0
                turns = 0;
            }
        }
    }
}