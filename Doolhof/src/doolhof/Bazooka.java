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
    
    public void shoot(){
        /*Tile myTile = getMyTile();
        Item collidingObject = myTile.compareTo(previous).getCurrentObject(); //Check buren bij previous?
                if(collidingObject instanceof Wall){
                    Wall wall = (Wall) collidingObject;*/
                    /*Optie 1
                     wall.remove();
                     */ //Remove methode in Item class
                    /*Optie 2 removing walls
                     * ImageIcon img = new ImageIcon(getResPath() + "grass.png");
                    wall.setMySprite(img.getImage()); */
                    
                }
        
    }
    
