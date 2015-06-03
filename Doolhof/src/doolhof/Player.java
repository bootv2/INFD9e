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

    private Map map;
    private boolean paused;
    //private String Message = "";
    /**
     * This creates a new player, loads all nessesary files, and places it on the map.
     * @param t spawn tile
     * @param map the map the player is spawned on
     */
    public Player(Tile t, Map map) {
        super(t);//item needs a tile

        paused = false;

        this.map = map;

        ImageIcon img = new ImageIcon(getResPath() + "player.png");//load an image from the jar path
        setMySprite(img.getImage());//set the players sprite
        map.setAl(new Al(this));//bind actionListener to map so the player can respond to input
    }

    /**
     * This function moves the player
     * @param dx amount of tiles to move on the x axis(-1 = left, 1 = right)
     * @param dy amount of tiles to move on the y axis(-1 = up, 1 = down)
     */
    private void move(int dx, int dy) {
        getMyTile().setMyItem(null);
        if (map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem() instanceof Finish) {
            System.out.println("FINISH!!!!!");
            paused = true;
        }
        setMyTile(map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy));
        getMyTile().setMyItem(this);
    }

    /*public void isPaused(Player p){   //Text Message on field
            if(paused == true){
            Message = "Game Paused";
            }
            
        }*/
    
    //ActionListener
    public class Al extends KeyAdapter {

        private Player p;

        /**
         * Al needs a reference to the player so it can call Player.move()
         * @param p The player to call move on
         */
        public Al(Player p) {
            this.p = p;
        }
        

        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            if (keycode == KeyEvent.VK_P) {
                paused = !paused;//pause/unpause the game
                if(paused)
                {
                    System.out.println("Game Paused");
                }
                if(!paused)
                {
                    System.out.println("Game Resumed");
                }
                
            }

            if (!paused) {
                if (keycode == KeyEvent.VK_W) {
                //voorlopige Collision met if

                    if (p.getMyTile().getTileY() != 0) {
                        if (!(map.getTile(p.getMyTile().getTileX(), p.getMyTile().getTileY() - 1).getMyItem() instanceof Wall)) {//<--collision detection
                            p.move(0, -1);//move player

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
