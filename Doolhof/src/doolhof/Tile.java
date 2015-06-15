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
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author TTT
 */
public class Tile implements Comparable<Tile> {

    private int tileX, tileY;

    private Item myItem;

    private Image bgImg;

    private Edge[] adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Tile previous;
    
    public Edge[] getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(Edge[] adjacencies) {
        this.adjacencies = adjacencies;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public Tile getPrevious() {
        return previous;
    }

    public void setPrevious(Tile previous) {
        this.previous = previous;
    }

    public String toString() {
        if (myItem != null) {
            if (myItem instanceof Wall) {
                return "Wall" + getTileX() + getTileY();
            } else if (myItem instanceof PadVinder) {
                return "ValsSpeler" + getTileX() + getTileY();
            }
            else return "not yet implemented" + getTileX() + getTileY();
        } else {
            return "empty" + getTileX() + getTileY();
        }

    }

    /**
     *
     * @return this tiles Item, if Item = null return a new item which has grass
     * as a sprite.
     */
    public int compareTo(Tile other) {
        return Double.compare(minDistance, other.minDistance);
    }

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
        myItem = new Item(this);

        ImageIcon img = new ImageIcon(myItem.getResPath() + "grass.png");
        bgImg = img.getImage();

        tileX = x;
        tileY = y;
    }

    

}
