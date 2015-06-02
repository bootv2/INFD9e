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
public class Board extends JPanel{
    private Timer timer;
    
    private Map m;
    //private Player p;
    //private String Message = "";
    
    public Board(){
        
        m = new Map();
        
        //p = new Player();
        addKeyListener(m.getAl());
        setFocusable(true); //adds to frame
        
        timer = new Timer(25, m);
        timer.start();
    }
    
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        for(int x = 0;x < 14;x++){
            for(int y = 0;y < 14;y++){
                g.drawImage(m.getTile(x, y).getMyItem().getMySprite(), x * 32, y * 32, this);
            }
        }
        //g.drawString(Message, 50, 50);
        
        //g.drawImage(p.getMySprite(), p.getMyTile().getTileX() * 32, p.getMyTile().getTileY() * 32, this);
        //NEED NEW CODE TO DRAW PLAYER
    }
    
    
}
