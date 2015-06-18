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

    /**
     * set the stappenteller before using or you will get a nullpointer exception
     * @param stappenTeller 
     */
    public void setStappenTeller(StappenTeller stappenTeller) {
        this.stappenTeller = stappenTeller;
    }
    
    /**
     * create a new valsspeler and load appropriate image
     * @param myTile 
     */
    public ValsSpeler(Tile myTile){
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "valsspeler.png");
        
        setMySprite(img.getImage());
        
    }
    
    /**
     * pick up this item, and use its effect
     */
    public void pickup()
    {
        stappenTeller.setStappen(stappenTeller.getStappen() - 10);
        
        
        
    }
    
}
