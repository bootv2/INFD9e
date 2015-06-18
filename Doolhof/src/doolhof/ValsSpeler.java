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
public class ValsSpeler extends Item {
    
    private StappenTeller stappenTeller;

    public void setStappenTeller(StappenTeller stappenTeller) {
        this.stappenTeller = stappenTeller;
    }
    
    public ValsSpeler(Tile myTile){
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "valsspeler.png");
        
        setMySprite(img.getImage());
        
    }
    
    public void pickup()
    {
        stappenTeller.setStappen(stappenTeller.getStappen() - 10);
        
        
        
    }
    
}
