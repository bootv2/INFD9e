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

        private final Tile target;
        private final double weight;

        public Tile getTarget() {
            return target;
        }

        public double getWeight() {
            return weight;
        }

        public Edge(Tile argTarget, double argWeight) {
            target = argTarget;
            weight = argWeight;
        }
    }
