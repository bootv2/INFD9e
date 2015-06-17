/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import javax.swing.ImageIcon;

/**
 *
 * @author Singh
 */
public class Bazooka extends Item {
    
    public Bazooka(Tile myTile){
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "bazooka.png");
        
        setMySprite(img.getImage());
    }
    
}
