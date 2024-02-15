import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeFrame
{
    int boardwidth = 600;
    int boardheight = 600;
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel( );
    JPanel textPanel = new JPanel();

    TicTacToeFrame()
    {
        frame.setTitle("Tic Tac Toe");
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
    }
}