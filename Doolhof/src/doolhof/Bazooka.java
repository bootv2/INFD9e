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

    private Player p = null;
    private Tile[][] tMap;

    public void setMap(Map m) {
        this.tMap = m.getTMap();
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public Bazooka(Tile myTile) {
        super(myTile);
        ImageIcon img = new ImageIcon(getResPath() + "bazooka.png");

        setMySprite(img.getImage());
    }

    public void shoot(short aimdir) {
        System.out.println(p.getMyTile().getTileX() + " | " + p.getMyTile().getTileY());
        switch (aimdir) {
            case 0://shoot naar links
                for (int x = p.getMyTile().getTileX(); x > 0; x--) {
                    if (tMap[x][p.getMyTile().getTileY()].getMyItem() != null) {
                        if (tMap[x][p.getMyTile().getTileY()].getMyItem() instanceof Wall) {
                            tMap[x][p.getMyTile().getTileY()].setMyItem(new Dot(tMap[x][p.getMyTile().getTileY()]));
                            break;
                        }
                    }
                }
                break;
            case 1://shoot naar boven
                for (int y = p.getMyTile().getTileY(); y > 0; y--) {
                    if (tMap[p.getMyTile().getTileX()][y].getMyItem() != null) {
                        if (tMap[p.getMyTile().getTileX()][y].getMyItem() instanceof Wall) {
                            tMap[p.getMyTile().getTileX()][y].setMyItem(new Dot(tMap[p.getMyTile().getTileX()][y]));
                            break;
                        }
                    }
                }
                break;
            case 2: //shoot naar rechts
                for (int x = p.getMyTile().getTileX(); x < 39; x++) {
                    if (tMap[x][p.getMyTile().getTileY()].getMyItem() != null) {
                        if (tMap[x][p.getMyTile().getTileY()].getMyItem() instanceof Wall) {
                            tMap[x][p.getMyTile().getTileY()].setMyItem(new Dot(tMap[x][p.getMyTile().getTileY()]));
                            break;
                        }
                    }
                }
                break;
            case 3: //shoot naar beneden
                for (int y = p.getMyTile().getTileY(); y < 19; y++) {
                    if (tMap[p.getMyTile().getTileX()][y].getMyItem() != null) {
                        if (tMap[p.getMyTile().getTileX()][y].getMyItem() instanceof Wall) {
                            tMap[p.getMyTile().getTileX()][y].setMyItem(new Dot(tMap[p.getMyTile().getTileX()][y]));
                            break;
                        }
                    }
                }
                break;
            default:
                System.out.println("aiming error");
                break;

        }

    }

}
