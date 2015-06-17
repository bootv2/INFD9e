/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Board extends JPanel implements ActionListener {

    private Timer timer;

    private Map m;
    private int mapnum = 1;
    private String mapname = "map" + mapnum;
    private String filetype = ".txt";
    //private String Message = "";

    /**
     * creates a new Board to be used for drawing 32*32 sprites
     */
    public Board() {

        m = new Map(mapname + filetype);//create and load map
        addKeyListener(m.getAl());//add the players keylistener to the JPanel
        setFocusable(true); //adds to frame

        timer = new Timer(25, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 20; y++) {//paint every tile in the map
                g.drawImage(m.getTile(x, y).getMyItem().getMySprite(), x * 32, y * 32, this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (m.needsReset()) {
            removeKeyListener(m.getAl());//remove the old keyListener
            m = new Map(mapname + filetype);//reload map
            addKeyListener(m.getAl());//set new keyListener

        } else if (m.isFinished()) {
            m.setFinished(false);
            if (mapnum != 3) {
                mapnum++;
                mapname = mapname.substring(0, 3) + mapnum;
                System.out.println(mapname);
                removeKeyListener(m.getAl());//remove the old keyListener
                m = new Map(mapname + filetype);
                addKeyListener(m.getAl());//set new keyListener
            } else {
                m.setReset(true);
            }
        }
        repaint();//every time something happens, like a pressed key, repaint the JPanel
    }

}
