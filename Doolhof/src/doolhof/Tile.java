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
public class Tile {

    private int tileX, tileY;

    private Item myItem;

    private Image bgImg;

    /**
     * 
     * @return this tiles Item, if Item = null return a new item which has grass as a sprite.
     */
    public Item getMyItem() {
        if (myItem == null) {
            myItem = new Item(this);
            myItem.setMySprite(bgImg);
        }
        return myItem;
    }

    /**
     * 
     * @param myItem set this tile's item
     */
    public void setMyItem(Item myItem) {
        this.myItem = myItem;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    /**
     * this constructor initialises the tile and places it on the board for
     * drawing
     *
     * @param x
     * @param y
     */
    public Tile(int x, int y) {
       

        ImageIcon img = new ImageIcon(myItem.getResPath() + "grass.png");
        bgImg = img.getImage();
        
        tileX = x;
        tileY = y;
    }

}
