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
public class Dot extends Item{

    public Dot(Tile myTile) {
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "dot.png");
        
        setMySprite(img.getImage());
    }
    
}
