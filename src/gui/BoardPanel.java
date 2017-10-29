package gui;

import game.State;

import javax.swing.*;
import java.awt.*;


public class BoardPanel extends JPanel {

    private final static int BOARDSIZE = 5; // number of hexagons of every side of hexagonal grid
    private final static int HEXWIDTH = 70; // width of hex in pixels
    private final static int HEXHEIGHT = (int) ((Math.sqrt(3) / 2) * HEXWIDTH); // height of hex in pixels
    private final static int FRAMESIZE = (2*BOARDSIZE+1)*HEXHEIGHT;
    final static int XORIGIN = FRAMESIZE / 2; // x coordinate origin in pixels
    final static int YORIGIN = FRAMESIZE / 2; // y coordinate origin in pixels

    @Override
    public void paint(Graphics g){

        /* Draw the hexagons: */
        int num = 6;
        g.drawPolygon(createHex(XORIGIN, YORIGIN));
        g.drawString("F"+Integer.toString(num), XORIGIN - (HEXWIDTH / 8) , YORIGIN - (HEXHEIGHT/4));
        for (int i = 1; i < BOARDSIZE+1; i++) {
            g.drawPolygon(createHex(XORIGIN, YORIGIN + (HEXHEIGHT * i)));
            g.drawPolygon(createHex(XORIGIN, YORIGIN - (HEXHEIGHT * i)));
            g.drawString("F"+Integer.toString(num+i), XORIGIN - (HEXWIDTH / 8) ,
                    YORIGIN + (HEXHEIGHT * i) - (HEXHEIGHT/4));
            g.drawString("F"+Integer.toString(num-i), XORIGIN - (HEXWIDTH / 8) ,
                    YORIGIN - (HEXHEIGHT * i) - (HEXHEIGHT/4));
        }
        String[] minLabels = {"E","D","C","B","A"};
        String[] plusLabels = {"G","H","I","J","K"};
        for (int i = 1; i < BOARDSIZE+1; i++) {
            int xMin = XORIGIN - (HEXWIDTH*i*3/4);
            int xPlus = XORIGIN + (HEXWIDTH*i*3/4);
            int y = (int) (YORIGIN - (HEXHEIGHT * (5-(i/2.0))));
            int numUp = 5-(i/2)-1;
            for (int j = 0; j < (BOARDSIZE*2+1) - i; j++) {
                g.drawPolygon(createHex(xMin, y + (HEXHEIGHT * j)));
                g.drawPolygon(createHex(xPlus, y + (HEXHEIGHT * j)));
                g.drawString(minLabels[i-1]+Integer.toString(BOARDSIZE - numUp + j),
                        xMin - (HEXWIDTH / 8) ,
                        (y + (HEXHEIGHT * j)) - (HEXHEIGHT/4));
                g.drawString(plusLabels[i-1]+Integer.toString(BOARDSIZE - numUp + j),
                        xPlus - (HEXWIDTH / 8) ,
                        (y + (HEXHEIGHT * j)) - (HEXHEIGHT/4));
            }
        }
    }

    static Polygon createHex(int x, int y){
        return new Polygon(
                new int[]{x - (HEXWIDTH / 4)
                        , x - (HEXWIDTH / 2)
                        , x - (HEXWIDTH / 4)
                        , x + (HEXWIDTH / 4)
                        , x + (HEXWIDTH / 2)
                        , x + (HEXWIDTH / 4)
                },
                new int[]{y + (HEXHEIGHT / 2)
                        , y
                        , y - (HEXHEIGHT / 2)
                        , y - (HEXHEIGHT / 2)
                        , y
                        , y + (HEXHEIGHT / 2)
                },6);
    }
}
