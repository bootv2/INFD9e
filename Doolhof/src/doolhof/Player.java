/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Player extends Item {
            
    public Player(){
        
        String resPath = "";
        
        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageIcon img = new ImageIcon(resPath + "player.png");
        setMySprite(img.getImage());
    }
    
    
    
    
    // if direction x -1  = left x +1 = right
    //move this move function to the map class so a tile can have an item, which can be a player
    public void move(int dx, int dy){               
        getMyTile().setTileX(getMyTile().getTileX() + dx);
        getMyTile().setTileY(getMyTile().getTileY() + dy);
    }
}
