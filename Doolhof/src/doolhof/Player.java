/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Player extends Item {

    Map map;
    boolean paused;

    public Player(Tile t, Map map) {
        super(t);

        paused = false;

        this.map = map;

        String resPath = "";

        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageIcon img = new ImageIcon(resPath + "player.png");
        setMySprite(img.getImage());
        map.setAl(new Al(this));
    }

    // if direction x -1  = left x +1 = right
    //move this move function to the map class so a tile can have an item, which can be a player
    public void move(int dx, int dy) {
        getMyTile().setMyItem(null);
        if (map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem() instanceof Finish) {
            System.out.println("FINISH!!!!!");
            paused = true;
        }
        setMyTile(map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy));
        getMyTile().setMyItem(this);
    }

    //ActionListener
    public class Al extends KeyAdapter {

        Player p;

        public Al(Player p) {
            this.p = p;
        }

        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            if (keycode == KeyEvent.VK_P) {
                paused = !paused;
                System.out.println("Player pos: " + p.getMyTile().getTileX() + ", " + p.getMyTile().getTileX());
            }

            if (!paused) {
                if (keycode == KeyEvent.VK_W) {
                //voorlopige Collision met if

                    if (p.getMyTile().getTileY() != 0) {
                        if (!(map.getTile(p.getMyTile().getTileX(), p.getMyTile().getTileY() - 1).getMyItem() instanceof Wall)) {
                            p.move(0, -1);

                        }
                    }

                }
                if (keycode == KeyEvent.VK_S) {
                    if (p.getMyTile().getTileY() != 14) {
                        if (!(map.getTile(p.getMyTile().getTileX(), p.getMyTile().getTileY() + 1).getMyItem() instanceof Wall)) {
                            p.move(0, 1);
                        }
                    }
                }
                if (keycode == KeyEvent.VK_A) {
                    if (p.getMyTile().getTileX() != 0) {
                        if (!(map.getTile(p.getMyTile().getTileX() - 1, p.getMyTile().getTileY()).getMyItem() instanceof Wall)) {
                            p.move(-1, 0);
                        }
                    }
                }
                if (keycode == KeyEvent.VK_D) {
                    if (p.getMyTile().getTileX() != 14) {
                        if (!(map.getTile(p.getMyTile().getTileX() + 1, p.getMyTile().getTileY()).getMyItem() instanceof Wall)) {
                            p.move(1, 0);
                        }
                    }
                }
            }
        }

        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }
    }
}
