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

    //Tile[] allTiles = new Tile[16 * 16];

    public PadVinder(Tile myTile) {
        super(myTile);
    }

    public void computePath(Tile source) {
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
        Tile[][] tMap = map.getTMap();
        for (Tile[] tl : tMap) {
            for (Tile tile : tl) {

                //allTiles[tile.getTileX() + (tile.getTileY() * 16)] = tile;
            }
        }
    }

    private void calculateAdjacencencies(Tile t, Tile[][] tMap) {
        if (t.getTileY() != 0) {
            if (t.getTileY() != 15) {
                if (t.getTileX() != 0) {
                    if (t.getTileX() != 15) {
                        t.setAdjacencies(new Edge[]{new Edge(tMap[t.getTileX() - 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() - 1], 1),
                            new Edge(tMap[t.getTileX() + 1][t.getTileY()], 1),
                            new Edge(tMap[t.getTileX()][t.getTileY() + 1], 1)});
                    }
                }
            } else {
                if (t.getTileX() != 0) {
                    if (t.getTileX() != 15) {
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
                if (t.getTileX() != 15) {
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

    public void testDijkstra() {

        computePath(getMyTile());
        System.out.println("Distance to " + getMyTile() + ": " + getMyTile().getMinDistance());
        List<Tile> path = getShortestPathTo(getMyTile());//myTile moet vriendTile zijn.
        System.out.println("Path: " + path);
    }
}


