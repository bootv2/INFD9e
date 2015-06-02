/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URLDecoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Map implements ActionListener {

    private Player p;
    
    Al al = new Al();

    public Al getAl() {
        return al;
    }

    public void setAl(Al al) {
        this.al = al;
    }

    private Scanner m;
    private String[] map = new String[16];
    private Tile[][] tMap = new Tile[16][16];

    private String resPath;

    private Image grass, wall, finish;

    public Map() {

        openFile();
        readFile();
        closeFile();
    }

    public Image getGrass() {
        return grass;
    }

    public Image getWall() {
        return wall;
    }

    public Image getFinish() {
        return finish;
    }

    public String getMap(int x, int y) {
        String index = map[y].substring(x, x + 1);
        return index;
    }

    public void openFile() {

        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            m = new Scanner(new File(resPath + "Map.txt"));
        } catch (Exception e) {
            System.out.println("Error Map");
        }

    }

    public void readFile() {
        while (m.hasNext()) {
            for (int i = 0; i < 14; i++) {
                map[i] = m.next(); //pakt per lijn
            }
        }
    }

    public void closeFile() {
        m.close();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (getMap(p.getMyTile().getTileX(), p.getMyTile().getTileY()).equals("f")) {
            //JOptionPane.showMessageDialog(this, "You have completed the first level!");
            //Message = "You have completed this level";
        }
        //repaint();
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

//ActionListener
    public class Al extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            if (keycode == KeyEvent.VK_W) {
                //voorlopige Collision met if
                if (!getMap(p.getMyTile().getTileX(), p.getMyTile().getTileY() - 1).equals("w")) {
                    p.move(0, -1);
                }

            }
            if (keycode == KeyEvent.VK_S) {
                if (!getMap(p.getMyTile().getTileX(), p.getMyTile().getTileY() + 1).equals("w")) {
                    p.move(0, 1);
                }
            }
            if (keycode == KeyEvent.VK_A) {
                if (!getMap(p.getMyTile().getTileX() - 1, p.getMyTile().getTileY()).equals("w")) {
                    p.move(-1, 0);
                }
            }
            if (keycode == KeyEvent.VK_D) {
                if (!getMap(p.getMyTile().getTileX() + 1, p.getMyTile().getTileY()).equals("w")) {
                    p.move(1, 0);
                }
            }
        }

        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }
    }

}
