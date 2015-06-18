/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import javax.swing.ImageIcon;

/**
 *
 * @author TTT
 */
public class Dot extends Item {//used by pathfinder to show the path, and by bazooka to replace a shot wall with

    /**
     * create a new dot load the dots image
     *
     * @param myTile
     */
    public Dot(Tile myTile) {
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "dot.png");

        setMySprite(img.getImage());
    }

}
