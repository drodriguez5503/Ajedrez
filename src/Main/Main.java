package Main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(94, 63, 40, 123));
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(900,900));
        frame.setLocationRelativeTo(null);

        Tablero tablero = new Tablero();
        frame.add(tablero);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}