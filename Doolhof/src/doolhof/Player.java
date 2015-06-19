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

    private StappenTeller stappenTeller = new StappenTeller();

    private Map map;
    private boolean paused;
    private Bazooka myBazooka;
    private short aimDirection = 0;

    //private String Message = "";
    /**
     * This creates a new player, loads all nessesary files, and places it on
     * the map.
     *
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

    public StappenTeller getStappenTeller() {
        return stappenTeller;
    }
    
    public void movementTestingHelper(int dx, int dy)
    {
        move(dx, dy);
    }

    /**
     * This function moves the player
     *
     * @param dx amount of tiles to move on the x axis(-1 = left, 1 = right)
     * @param dy amount of tiles to move on the y axis(-1 = up, 1 = down)
     */
    private void move(int dx, int dy) {
        System.out.println(stappenTeller.getStappen());//show amount of steps
        stappenTeller.setStappen(stappenTeller.getStappen() + 1);//increase amount of steps
        getMyTile().setMyItem(null);//empty the tile the player was standing on before moving
        if (map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem() instanceof Vriend) {//if you touch vriend
            Vriend v = (Vriend) map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem();
            v.pickup();
        } else if (map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem() instanceof Helper) {//if you touch helper
            Helper p = (Helper) map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem();//get the helper
            p.showPath();//show the shortest path to Vriend
        } else if (map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem() instanceof Bazooka) {//if you touch bazooka
            pickupBazooka(dx, dy);//pick up this bazooka
        } else if (map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem() instanceof ValsSpeler)//if you touch valsspeler
        {
            ValsSpeler va = (ValsSpeler) map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem();//get valsspeler
            va.pickup();//use valsspeler
        }
        setMyTile(map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy));//set the players tile to the tile he moved to
        getMyTile().setMyItem(this);//set the item of the tile the player moved to to the player.
    }

    private void pickupBazooka(int dx, int dy) {
        System.out.println("Picked up Bazooka, press space to fire");//info
        ImageIcon img = new ImageIcon(getResPath() + "player2.png");//load an image from the jar path
        setMySprite(img.getImage());//sets the player sprite to the sprite when holding a bazooka
        myBazooka = (Bazooka) map.getTile(getMyTile().getTileX() + dx, getMyTile().getTileY() + dy).getMyItem();//assign the bazooka to myBazooka so the player can use it
        myBazooka.setPlayer(this);//set the bazooka's player to this player
        myBazooka.setMap(map);//set the bazooka's map so it can actually manipulate the map
    }

    //ActionListener
    public class Al extends KeyAdapter {

        private Player p;

        /**
         * Al needs a reference to the player so it can call Player.move()
         *
         * @param p The player to call move on
         */
        public Al(Player p) {
            this.p = p;
        }

        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
            int keycode = e.getKeyCode();

            if (keycode == KeyEvent.VK_P) {
                paused = !paused;//pause/unpause the game
                if (paused) {
                    System.out.println("Game Paused");
                }
                if (!paused) {
                    System.out.println("Game Resumed");
                }

            }

            if (keycode == KeyEvent.VK_R) {
                map.setReset(true);
            }

            if (!paused) {
                if (keycode == KeyEvent.VK_W) {
                    //voorlopige Collision met if
                    aimDirection = 1;
                    if (p.getMyTile().getTileY() != 0) {
                        if (!(map.getTile(p.getMyTile().getTileX(), p.getMyTile().getTileY() - 1).getMyItem() instanceof Wall)) {//<--collision detection
                            p.move(0, -1);//move player

                        }
                    }

                }
                if (keycode == KeyEvent.VK_S) {
                    aimDirection = 3;
                    if (p.getMyTile().getTileY() != 19) {
                        if (!(map.getTile(p.getMyTile().getTileX(), p.getMyTile().getTileY() + 1).getMyItem() instanceof Wall)) {
                            p.move(0, 1);
                        }
                    }
                }
                if (keycode == KeyEvent.VK_A) {
                    aimDirection = 0;
                    if (p.getMyTile().getTileX() != 0) {
                        if (!(map.getTile(p.getMyTile().getTileX() - 1, p.getMyTile().getTileY()).getMyItem() instanceof Wall)) {
                            p.move(-1, 0);
                        }
                    }
                }
                if (keycode == KeyEvent.VK_D) {
                    aimDirection = 2;
                    if (p.getMyTile().getTileX() != 39) {
                        if (!(map.getTile(p.getMyTile().getTileX() + 1, p.getMyTile().getTileY()).getMyItem() instanceof Wall)) {
                            p.move(1, 0);
                        }
                    }
                }
                if (keycode == KeyEvent.VK_SPACE) {
                    //if (p.getMyTile().getTileX() != 40) {
                    if (myBazooka != null) {
                        ImageIcon img = new ImageIcon(getResPath() + "player.png");//load an image from the jar path
                        setMySprite(img.getImage());
                        myBazooka.shoot(aimDirection);
                        myBazooka = null;
                    }
                    //}
                }
            }
        }

        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }
    }
}
