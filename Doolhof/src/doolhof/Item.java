/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Image;

/**
 *
 * @author TTT
 */
public class Item 
{

    public Item(Tile myTile) {
        this.myTile = myTile;
    }
    
    private Image mySprite;
    private Tile myTile;

    public Tile getMyTile() {
        return myTile;
    }

    public void setMyTile(Tile myTile) {
        this.myTile = myTile;
    }

    public Image getMySprite() {
        return mySprite;
    }

    public void setMySprite(Image mySprite) {
        this.mySprite = mySprite;
    }
}
