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

/**
 *
 * @author TTT
 */
public class PadVinder extends Item {
    
    private Tile vriendTile;

    //Tile[] allTiles = new Tile[16 * 16];

    public PadVinder(Tile myTile) {
        super(myTile);
    }
    
    public void setVriend(Tile v)
    {
        vriendTile = v;
    }

    public void computePaths(Tile source) {
        source.setMinDistance(0.);
        PriorityQueue<Tile> tileQueue = new PriorityQueue<Tile>();
        tileQueue.add(source);

        while (!tileQueue.isEmpty()) {
            Tile u = tileQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.getAdjacencies()) {
                Tile v = e.getTarget();
                double weight = e.getWeight();
                double distanceThroughU = u.getMinDistance() + weight;
                if (distanceThroughU < v.getMinDistance()) {
                    tileQueue.remove(v);
                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    tileQueue.add(v);
                }
            }
        }
    }

    public static List<Tile> getShortestPathTo(Tile target) {
        List<Tile> path = new ArrayList<Tile>();
        for (Tile vertex = target; vertex != null; vertex = vertex.getPrevious()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public void prepareDijkstra(Map map) {
        for(Tile[] ta : map.getTMap())
        {
            for(Tile t : ta)
            {
                if(t != null)
                {
                    t.calculateAdjacencies(t ,map.getTMap());
                }
            }
        }
        
    }

    
    public void printPath() {

        computePaths(getMyTile());
        List<Tile> path = getShortestPathTo(vriendTile);//myTile moet vriendTile zijn.
        for(Tile t : path)
        {
            t.makeDot();
        }
    }
}


