/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author TTT
 */
class Edge {

    private final Tile target;//which tile's edge this is
    private final double weight;//the weight of travelling here used by the dijkstra algorithm

    public Tile getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * creates a new Edge
     *
     * @param argTarget The tile which this edge belongs to
     * @param argWeight The cost to travel to this tile
     */
    public Edge(Tile argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}
