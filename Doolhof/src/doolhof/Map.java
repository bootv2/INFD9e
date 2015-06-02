/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import doolhof.Player.Al;
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

    
    private Scanner m;
    private String[] map = new String[16];
    private Tile[][] tMap = new Tile[16][16];

    private String resPath;

    private Image grass, wall, finish;
    
    private Player p;
    
    private Al al;

    public Al getAl() {
        return al;
    }

    public void setAl(Al al) {
        this.al = al;
    }
    
    public Tile getTile(int x, int y)
    {
        return tMap[x][y];
    }


    public Map() {
        
        

        openFile();
        readFile();
        loadFile();
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
    
    public void loadFile()
    {
        Tile t;
        Item item;
        for(int x = 0; x < 14; x++)
        {
            for(int y = 0; y < 14; y++)
            {
                t = new Tile(x, y);
                if(map[y].charAt(x) == 'p')
                {
                    item = new Player(t, this);
                }
                else if(map[y].charAt(x) == 'w')
                {
                    item = new Wall(t);
                }
                else if(map[y].charAt(x) == 'f')
                {
                    item = new Finish(t);
                }
                else
                {
                    item = null;
                }
                
                t.setMyItem(item);
                tMap[x][y] = t;
            }
        }
    }

    //deprecated
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

        /*if (getTile(p.getMyTile().getTileX(), p.getMyTile().getTileY()).getMyItem() instanceof Finish) {
            //JOptionPane.showMessageDialog(this, "You have completed the first level!");
            //Message = "You have completed this level";
        }*/
        //repaint();
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

}
