/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Player {
    
    private int tileX, tileY;
    private Image player;
            
    public Player(){
        
        ImageIcon img = new ImageIcon("C://player.png");
        player = img.getImage();
               
        tileX = 1; //coordinaten
        tileY = 1;
    }
    
    //return player picture
    public Image getPlayer(){
        return player;
    }
    
    
    public int getTileX(){
        return tileX;
    }
    public int getTileY(){
        return tileY;
    }
    
    // if direction x -1  = left x +1 = right
    public void move(int dx, int dy){               
        tileX += dx;
        tileY += dy;
    }
}
