/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import javax.swing.ImageIcon;

/**
 * This class uses the dijkstra algorithm
 *
 * @author TTT
 */
public class Helper extends Item {//the pathfinder

    private Tile vriendTile;

    /**
     * create a new helper and load the appropriate image
     *
     * @param myTile
     */
    public Helper(Tile myTile) {
        super(myTile);

        ImageIcon img = new ImageIcon(getResPath() + "helper.png");

        setMySprite(img.getImage());
    }

    /**
     * sets the vriend tile.
     *
     * @param v the tile Vriend is located on
     */
    public void setVriend(Tile v) {
        vriendTile = v;
    }

    /**
     *
     * @param source the tile the paths should originate from
     */
    private void computePaths(Tile source) {
        source.setMinDistance(0.);//sets the minimal distance to 0.
        PriorityQueue<Tile> tileQueue = new PriorityQueue<Tile>();//creates a queue of tiles where the path will be stored during calculation
        tileQueue.add(source);//add the source tile as the first tile before starting

        while (!tileQueue.isEmpty()) {//while the queue isnt empty
            Tile u = tileQueue.poll();//get the next tile

            // Visit each edge exiting u
            for (Edge e : u.getAdjacencies()) {
                Tile v = e.getTarget();//get the tile this adjacency points to
                double weight = e.getWeight();//get the weight of travelling here
                double distanceThroughU = u.getMinDistance() + weight;//calculates the distance(weight) to this tile.
                if (distanceThroughU < v.getMinDistance()) {
                    tileQueue.remove(v);//remove this adjacency from the queue
                    v.setMinDistance(distanceThroughU);//set the minimal distance to the distance the last adjacency had
                    v.setPrevious(u);//set the previous tile;
                    tileQueue.add(v);//add the new tile to the queue
                }
            }
        }
    }

    public static List<Tile> getShortestPathTo(Tile target) {
        List<Tile> path = new ArrayList<Tile>();
        for (Tile tile = target; tile != null; tile = tile.getPrevious()) {//try all paths and find the shortest one
            path.add(tile);//adds this tile to the path
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Prepare the dijkstra algorithm for use by calculating the adjacencies for
     * every tile in the map
     *
     * @param map
     */
    public void prepareDijkstra(Map map) {
        for (Tile[] ta : map.getTMap()) {
            for (Tile t : ta) {
                if (t != null) {
                    t.calculateAdjacencies(t, map.getTMap());
                }
            }
        }

    }
    
    public int getShortestPathSteps()
    {
        computePaths(getMyTile());//compute all paths
        List<Tile> path = getShortestPathTo(vriendTile);//Gets the shortest path to vriendTile.
        return path.size();
    }

    /**
     * shows the path by placing dots along the path on the map
     */
    public void showPath() {

        computePaths(getMyTile());//compute all paths
        List<Tile> path = getShortestPathTo(vriendTile);//Gets the shortest path to vriendTile.
        for (Tile t : path)//for every tile in the path, place a dot
        {
            t.makeDot();
        }
    }
}
