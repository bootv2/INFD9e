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

    private Item myItem = null;

    private Image bgImg;

    private ArrayList<Edge> adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Tile previous;

    public ArrayList<Edge> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(Edge[] adjacencies) {
        ArrayList<Edge> traversables = new ArrayList<Edge>();
        for (Edge e : adjacencies) {
            if (e.getTarget() != null) {
                if (e.getTarget().getMyItem() != null) {
                    if (!(e.getTarget().getMyItem() instanceof Wall)) {
                        traversables.add(e);
                    }
                }
            }
        }
        this.adjacencies = traversables;
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

    public void calculateAdjacencies(Tile t, Tile[][] tMap) {
        if (t.getTileY() != 0) {
            if (t.getTileY() != 19) {
                if (t.getTileX() != 0) {
                    if (t.getTileX() != 39) {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                    } else {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                    }
                } else {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                        new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                        new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                }
            } else {
                if (t.getTileX() != 0) {
                    if (t.getTileX() != 39) {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1)});
                    } else {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1)});
                    }
                } else {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                        new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1)});
                }
            }
        } else {
            if (t.getTileX() != 0) {
                if (t.getTileX() != 39) {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                        new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                        new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                } else {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                        new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                }
            } else {
                t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                    new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
            }
        }
    }

    public void makeDot() {
        if (myItem != null) {
            if (myItem instanceof Wall) {
                return;
            } else if (myItem instanceof PadVinder) {
                return;
            } 
            else if(myItem instanceof Finish)
            {
                return;
            }
            else if(myItem instanceof ValsSpeler)
            {
                return;
            }
            else if (myItem instanceof Bazooka)
            {
                return;
            }
            else if(myItem instanceof Item){//put this at the end of the if else chain otherwise some if statements may not be reached
                myItem = new Dot(this);
                return;
            }
            else return;
        } else {
            myItem = new Dot(this);
            return;
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
