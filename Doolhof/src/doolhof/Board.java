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

    private Map m;//the map the board uses to draw tiles

    
    private int mapnum = 1;//the number of the current map(level)
    private String mapname = "map" + mapnum;//the name of the current map
    private final String filetype = ".txt";//the filetype of the map
    private boolean reset = false;
    //Timer timer;
    //private String Message = "";

    public void setReset(boolean reset) {
        this.reset = reset;
    }
    
    public Map getM() {
        return m;
    }
    
    public int getStappen()
    {
        return m.getStappenTeller().getStappen();
    }
    
    public void togglePaused()
    {
        m.getP().togglePaused();
        requestFocusInWindow();
    }

    /**
     * creates a new Board to be used for drawing 32*32 sprites creates a map,
     * and loads the map thats defined in mapname adds the players keylistener
     * to the panel
     */
    public Board() {

        m = new Map(mapname + filetype);//create and load map
        addKeyListener(m.getAl());//add the players keylistener to the JPanel
        setFocusable(true); //adds to frame
        

        //timer = new Timer(25, this);
        //timer.start();
    }

    /**
     * paints every tile
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 40; x++) {//40 columns
            for (int y = 0; y < 20; y++) {//20 rows | paint every tile in the map
                g.drawImage(m.getTile(x, y).getMyItem().getMySprite(), x * 32, y * 32, this);//paint the tile at position x, y
            }
        }
    }

    /**
     * resets the map
     */
    private void reset() {
        reset = false;
        removeKeyListener(m.getAl());//remove the old keyListener
        m = new Map(mapname + filetype);//reload map
        addKeyListener(m.getAl());//set new keyListener
        requestFocusInWindow();
    }

    private void nextMap() {
        if (mapnum != 3) {
            mapnum++;//increase the map number(lvl number)
            mapname = "map" + mapnum;//construct the new mapname
            removeKeyListener(m.getAl());//remove the old keyListener
            m = new Map(mapname + filetype);//load a new map with the new mapname
            addKeyListener(m.getAl());//set new keyListener
        } else {
            JOptionPane.showMessageDialog(null, "Spel uitgespeeld! Gefeliciteerd!");
            System.exit(0);
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (reset) {
            reset();//if the game needs a reset, reset.

        } else if (m.isFinished()) {
            nextMap();
        }
        repaint();//every time something happens, like a pressed key, repaint the JPanel
    }

}
