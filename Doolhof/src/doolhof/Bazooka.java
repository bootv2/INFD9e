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

    private Player p = null;//create a player and set it to null
    private Tile[][] tMap;//create a tilemap for later reference

    public void setMap(Map m) {//set the tilemap
        this.tMap = m.getTMap();
    }

    public void setPlayer(Player p) {//set the player
        this.p = p;
    }

    /**
     * Create the bazooka and set its image
     *
     * @param myTile The tile the bazooka is on
     */
    public Bazooka(Tile myTile) {
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "bazooka.png");

        setMySprite(img.getImage());
    }
    /**
     * create a bazooka that isnt placed on the map.
     * !ONLY USE WHEN GIVING THE PLAYER A BAZOOKA THAT ISNT PICKED UP!
     */
    public Bazooka()
    {
        super(null);
        ImageIcon img = new ImageIcon(getResPath() + "bazooka.png");

        setMySprite(img.getImage());
    }

    /**
     * shoot the bazooka
     *
     * @param aimdir direction of aiming(0 west, 1 north, 2 east, 3 south)
     */
    public void shoot(short aimdir) {
        switch (aimdir) {//determines which shooting code should be executed for this aiming direction
            case 0://shoot naar links
                for (int x = p.getMyTile().getTileX(); x > 0; x--) {//follow the direction aimed, until a wall is hit
                    if (tMap[x][p.getMyTile().getTileY()].getMyItem() != null) {//if an item is located on this location
                        if (tMap[x][p.getMyTile().getTileY()].getMyItem() instanceof Wall) {//check if it is a wall
                            tMap[x][p.getMyTile().getTileY()].setMyItem(new Dot(tMap[x][p.getMyTile().getTileY()]));//if it is a wall replace it with a dot.
                            break;
                        }
                    }
                }
                break;
            case 1://shoot naar boven
                for (int y = p.getMyTile().getTileY(); y > 0; y--) {//follow the direction aimed, until a wall is hit
                    if (tMap[p.getMyTile().getTileX()][y].getMyItem() != null) {//if an item is located on this location
                        if (tMap[p.getMyTile().getTileX()][y].getMyItem() instanceof Wall) {//check if it is a wall
                            tMap[p.getMyTile().getTileX()][y].setMyItem(new Dot(tMap[p.getMyTile().getTileX()][y]));//if it is a wall replace it with a dot.
                            break;
                        }
                    }
                }
                break;
            case 2: //shoot naar rechts
                for (int x = p.getMyTile().getTileX(); x < 39; x++) {//follow the direction aimed, until a wall is hit
                    if (tMap[x][p.getMyTile().getTileY()].getMyItem() != null) {//if an item is located on this location
                        if (tMap[x][p.getMyTile().getTileY()].getMyItem() instanceof Wall) {//check if it is a wall
                            tMap[x][p.getMyTile().getTileY()].setMyItem(new Dot(tMap[x][p.getMyTile().getTileY()]));//if it is a wall replace it with a dot.
                            break;
                        }
                    }
                }
                break;
            case 3: //shoot naar beneden
                for (int y = p.getMyTile().getTileY(); y < 19; y++) {//follow the direction aimed, until a wall is hit
                    if (tMap[p.getMyTile().getTileX()][y].getMyItem() != null) {//if an item is located on this location
                        if (tMap[p.getMyTile().getTileX()][y].getMyItem() instanceof Wall) {//check if it is a wall
                            tMap[p.getMyTile().getTileX()][y].setMyItem(new Dot(tMap[p.getMyTile().getTileX()][y]));//if it is a wall replace it with a dot.
                            break;
                        }
                    }
                }
                break;
            default:
                System.out.println("aiming error");//if the aiming direction is not 0-3, print an error to the console
                break;

        }

    }

}
