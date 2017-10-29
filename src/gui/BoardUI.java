package gui;

import game.State;

import javax.swing.*;
import java.awt.*;

import static gui.BoardPanel.XORIGIN;
import static gui.BoardPanel.YORIGIN;
import static gui.BoardPanel.createHex;


public class BoardUI extends JFrame{

    public static void main(String[] args) {
        BoardUI boardUI = new BoardUI();
        boardUI.createAndShowGUI();
    }

    public void createAndShowGUI() {
        //JFrame frame = new JFrame();
        getContentPane().add(new BoardPanel());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700,700);
        //frame.pack();
        setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
