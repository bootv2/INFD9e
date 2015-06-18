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

    private int tileX, tileY;//tile coordinates

    private Item myItem = null;//tile's item

    private Image bgImg;//the standard grass image

    private ArrayList<Edge> adjacencies;//this tiles adjacencies, used by the dijkstra algorithm
    private double minDistance = Double.POSITIVE_INFINITY;//this tiles minimum distance, manipulated and used by the dijkstra algorithm
    private Tile previous;//the previous tile used when calculating the shortest path using the dijkstra algorithm

    public ArrayList<Edge> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(Edge[] adjacencies) {//set the adjacencies
        ArrayList<Edge> traversables = new ArrayList<Edge>();
        for (Edge e : adjacencies) {
            if (e.getTarget() != null) {//if the target tile isnt null
                if (e.getTarget().getMyItem() != null) {//and the tile's item also isnt null
                    if (!(e.getTarget().getMyItem() instanceof Wall)) {//and if the tile's item isnt a wall
                        traversables.add(e);//add this edge to the edges that can be walked on, and thus used to calculate the shortest path
                    }
                }
            }
        }
        this.adjacencies = traversables;//save
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
        if (t.getTileY() != 0) {//if y = 0, we cannot add -1 to the adjacencies
            if (t.getTileY() != 19) {//if y > 19 we have already reached the 21th row, while there are only 20.
                if (t.getTileX() != 0) {//if x = 0, we cannot add -1 to the adjacencies
                    if (t.getTileX() != 39) {//if y > 39 we have already reached the 41th column, while there are only 40.
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),//set all adjacencies, n, e, s, w cause we're not at map edges
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                    } else {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),//at eastern map edge, do not add eastern adjacency
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                    }
                } else {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),//at western map edge, do not add western adjacency
                        new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                        new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                }
            } else {
                if (t.getTileX() != 0) {//if x = 0, we cannot add -1 to the adjacencies
                    if (t.getTileX() != 39) {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),//at southern map edge, do not add southern adjacency
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1)});
                    } else {//at southeast map edge, do not add south and east adjacency
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1)});
                    }
                } else {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),//at south west map edge, do not add south and west adjacency
                        new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1)});
                }
            }
        } else {
            if (t.getTileX() != 0) {//if x = 0, we cannot add -1 to the adjacencies
                if (t.getTileX() != 39) {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),//at northern map edge, do not add northern adjacency
                        new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                        new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                } else {
                    t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),//at south east map edge, do not add south and east adjacency
                        new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                }
            } else {
                t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),//at north west map edge, do not add north and west adjacency
                    new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
            }
        }
    }

    public void makeDot() {
        if (myItem != null) {
            if (myItem instanceof Helper) {//if helper on path, do not replace helper
                return;
            } else if (myItem instanceof Vriend)//if Vriend on path, do not replace Vriend
            {
                return;
            } else if (myItem instanceof ValsSpeler)//if ValsSpeler on path, do not replace ValsSpeler
            {
                return;
            } else if (myItem instanceof Bazooka)//if Bazooka on path, do not Bazooka helper
            {
                return;
            } else if (myItem instanceof Item) {//put this at the end of the if else chain otherwise some if statements may not be reached
                myItem = new Dot(this);
                return;
            } else {
                return;
            }
        } else {
            myItem = new Dot(this);//if this is all alright, create new dot.
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
