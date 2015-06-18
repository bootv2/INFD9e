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

/**
 *
 * @author TTT
 */
public class Item {
    //default item class, to be extended by subclasses

    private Image mySprite;//the sprite to be painted on this item
    private Tile myTile;//the tile this item is placed on

    /**
     * Create a new item, and reference a tile so the item knows where it's
     * placed
     *
     * @param myTile
     */
    public Item(Tile myTile) {
        this.myTile = myTile;
    }

    public Tile getMyTile() {
        return myTile;
    }

    public void setMyTile(Tile myTile) {
        this.myTile = myTile;
    }

    public Image getMySprite() {
        return mySprite;
    }

    public void setMySprite(Image mySprite) {
        this.mySprite = mySprite;
    }

    /**
     * used by various items to load their sprites.
     *
     * @return String the path to the resources folder
     */
    public String getResPath() {
        String path = Item.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        String resPath = "";
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resPath;
    }
}
