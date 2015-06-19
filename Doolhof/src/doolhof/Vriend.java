/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author TTT
 */
public class Vriend extends Item {//Finish, aka vriend, if the player touches this the level is won
    
    private boolean pickedUp = false;

    public boolean isPickedUp() {
        return pickedUp;
    }
    
    public void pickup()
    {
        JOptionPane.showMessageDialog(null, "Uitstekend, op naar de volgende level");
        pickedUp = true;
    }

    /**
     * create a new Finish and load the appropriate picture
     *
     * @param myTile
     */
    public Vriend(Tile myTile) {
        super(myTile);

        ImageIcon img = new ImageIcon(getResPath() + "finish.png");

        setMySprite(img.getImage());
    }

}
