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

/**
 *
 * @author TTT
 */
public class Finish extends Item {

    public Finish(Tile myTile) {
        super(myTile);
        
        ImageIcon img = new ImageIcon(getResPath() + "finish.png");
        
        setMySprite(img.getImage());
    }
    
}
