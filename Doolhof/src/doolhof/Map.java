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
public class Map{
    
    private Scanner m;
    private Tile[][] tMap = new Tile[16][16];

    private String resPath;
    
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
        
        findResourcePath();
        openFile("map.txt");
        loadFile(readFile());
        closeFile();
    }
    
    public void findResourcePath()
    {
        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Opens file f
     * @param f 
     */
    public void openFile(String f) {

        try {
            m = new Scanner(new File(resPath + f));
        } catch (Exception e) {
            System.out.println("Error Map");
        }
        
        

    }
    
    /**
     * Loads the tilemap from argument String[] rawMap
     * @param rawMap 
     */
    public void loadFile(String[] rawMap)
    {
        Item.setResPath(resPath);
        Tile t;
        Item item;
        for(int x = 0; x < 14; x++)
        {
            for(int y = 0; y < 14; y++)
            {
                t = new Tile(x, y);
                if(rawMap[x].charAt(y) == 'p')
                {
                    item = new Player(t, this);
                    p = (Player)item;
                }
                else if(rawMap[x].charAt(y) == 'w')
                {
                    item = new Wall(t);
                }
                else if(rawMap[x].charAt(y) == 'f')
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

    /**
     * Reads the open file into a String[]
     * @return String[14] rawMap
     */
    public String[] readFile() {
        String[] map = new String[14];
        while (m.hasNext()) {
            for (int i = 0; i < 14; i++) {
                map[i] = m.next(); //pakt per lijn
            }
        }
        return map;
    }

    /**
     * closes the current file
     * DO NOT FORGET THIS!!!
     * only one file can be open at a time
     */
    public void closeFile() {
        m.close();
    }

   

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

}
