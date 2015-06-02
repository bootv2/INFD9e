/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author TTT
 */
public class Tile 
{
    private int tileX, tileY;
    
    Item myItem;
    
    private Image bgImg;

    public Item getMyItem() 
    {
        if(myItem == null)  myItem = new Item(this);
        myItem.setMySprite(bgImg);
        return myItem;
    }

    public void setMyItem(Item myItem) {
        this.myItem = myItem;
    }
    
    public int getTileX(){
        return tileX;
    }
    public int getTileY(){
        return tileY;
    }
    
    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }
    
    /**
     * this constructor initialises the tile and places it on the board for drawing
     * @param x
     * @param y 
     */
    
    public Tile(int x, int y)
    {
        String resPath = "";
        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageIcon img = new ImageIcon(resPath + "grass.png");
        bgImg = img.getImage();
    }
    
}
