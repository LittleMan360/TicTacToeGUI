import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class TicTacToeFrame
{
    int boardWidth = 600;
    int boardHeight = 600;
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel( );
    JPanel textPanel = new JPanel();

    JPanel boardPanel = new JPanel();


    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean GameOver = false;
    int turns = 0;


    TicTacToeFrame()
    {
        frame.setTitle("Tic Tac Toe");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
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



}