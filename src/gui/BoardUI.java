package gui;

import game.State;

import javax.swing.*;
import java.awt.*;

import static gui.BoardPanel.XORIGIN;
import static gui.BoardPanel.YORIGIN;
import static gui.BoardPanel.createHex;


public class BoardUI extends JFrame{

    //public static void main(String[] args) {
    //    SwingUtilities.invokeLater(BoardUI::createAndShowGUI);
    //}

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

    public void drawSign(State s){
        Graphics g = getGraphics();
        g.drawOval(40, 40, 60, 60);
        //g.drawPolygon(createHex(XORIGIN, YORIGIN));
        //g.setColor(Color.cyan);
        //g.fillPolygon(createHex(XORIGIN, YORIGIN));
        //g.setColor(Color.red);
        //g.drawPolygon(createHex(XORIGIN, YORIGIN));
    }
}
