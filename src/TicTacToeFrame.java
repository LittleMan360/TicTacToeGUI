import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToeFrame
{
    int boardWidth = 600;
    int boardHeight = 600;
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel( );
    JPanel textPanel = new JPanel();

    JPanel boardPanel = new JPanel();

    JPanel controlPnl = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    int XWins = 0;
    int OWins = 0;
    int Ties = 0;
    String letter = "";

    boolean GameOver = false;
    int turns = 0;


    TicTacToeFrame()
    {
        frame.setTitle("Tic Tac Toe");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.WHITE);
        frame.add(boardPanel);

        createControlPanel();
        frame.add(controlPnl, BorderLayout.SOUTH);
        controlPnl.setBackground(Color.BLACK);
        controlPnl.setForeground(Color.WHITE);

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                JButton tile = new JButton();
                board[row][col] = tile;
                boardPanel.add (tile);
                
                tile.setBackground(Color.GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Ariel", Font.BOLD, 100));
                tile.setFocusable(false);

                //tested to make sure text was here
                //tile.setText(currentPlayer);

                tile.addActionListener(e -> {
                    if (GameOver) return;
                    JButton tile1 = (JButton) e.getSource();
                    if (Objects.equals(tile1.getText(), ""))
                    {
                        tile1.setText(currentPlayer);
                        turns++;
                        checkWin();
                        if (!GameOver)
                        {
                        currentPlayer = (currentPlayer.equals(playerX)) ? playerO : playerX;
                        textLabel.setText(currentPlayer + " Turn");
                        }
                    }
                });
            }
        }
    }
    void checkWin()
    {
        //horizontal win
        for(int row = 0; row < 3; row++)
        {
            if (board[row][0].getText().equals(currentPlayer) &&
                    board[row][1].getText().equals(currentPlayer) &&
                    board[row][2].getText().equals(currentPlayer))
            {
                for (int i = 0; i < 3; i++)
                {
                    setWin(board [row][i]);
                }
                textLabel.setText(currentPlayer + " Wins!");
                if (currentPlayer.equals(playerX))
                {
                    XWins++;
                }
                else
                {
                    OWins++;
                }
                GameOver = true;
            }
        }

        //vertical win
        for (int col = 0; col < 3; col++)
        {
            if (board[0][col].getText().equals(currentPlayer) &&
                    board[1][col].getText().equals(currentPlayer) &&
                    board[2][col].getText().equals(currentPlayer))
            {
                for (int i = 0; i < 3; i++) {
                    setWin(board[i][col]);
                }
                textLabel.setText(currentPlayer + " Wins!");
                if (currentPlayer.equals(playerX))
                {
                    XWins++;
                }
                else
                {
                    OWins++;
                }
                GameOver = true;
            }
        }

        //diagonal top left bottom right diagonal
        if (board[0][0].getText().equals(currentPlayer) &&
                board[1][1].getText().equals(currentPlayer) &&
                board[2][2].getText().equals(currentPlayer))
        {
            setWin(board[0][0]);
            setWin(board[1][1]);
            setWin(board[2][2]);

            textLabel.setText(currentPlayer + " Wins!");
            if (currentPlayer.equals(playerX))
            {
                XWins++;
            }
            else
            {
                OWins++;
            }
            GameOver = true;
        }
        //diagonal top right bottom left diagonal
        if (board[0][2].getText().equals(currentPlayer) &&
                board[1][1].getText().equals(currentPlayer) &&
                board[2][0].getText().equals(currentPlayer))
        {
            setWin(board[0][2]);
            setWin(board[1][1]);
            setWin(board[2][0]);

            textLabel.setText(currentPlayer + " Wins!");
            if (currentPlayer.equals(playerX))
            {
                XWins++;
            }
            else
            {
                OWins++;
            }
            GameOver = true;
        }

        if (turns == 9)
        {
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 3; col++)
                {
                    setTie(board[row][col]);
                }
            }
            Ties++;
            GameOver = true;
        }

    }


    void setWin(JButton tile)
    {
        tile.setBackground(Color.GRAY);
        tile.setForeground(Color.green );

    }

    void setTie(JButton tile)
    {
        tile.setBackground(Color.GRAY);
        tile.setForeground(Color.red);
        textLabel.setText("Tie");
    }

    void createControlPanel()
    {
        controlPnl.setLayout(new GridLayout(1, 3));
        controlPnl.setBackground(Color.BLACK);

        JButton newGame = new JButton("New Game");
        newGame.setBackground(Color.BLACK);
        newGame.setForeground(Color.WHITE);
        JButton score = new JButton("Score");
        score.setBackground(Color.BLACK);
        score.setForeground(Color.WHITE);


        newGame.addActionListener(e -> newGame());
        score.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(null, "Player X's number of Wins" + " " + XWins + " \nPlayer O's number of Wins" + " " + OWins + "\nTies" + " " + Ties);
        });


        controlPnl.add(newGame);
        controlPnl.add(score);

    }

    private void newGame()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col].setText("");
                board[row][col].setBackground(Color.GRAY);
                board[row][col].setForeground(Color.WHITE);
                GameOver = false;
                currentPlayer = playerX;
                textLabel.setText(currentPlayer + " Turn");
                turns = 0;
            }
        }
    }


}