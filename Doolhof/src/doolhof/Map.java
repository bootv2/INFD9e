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
    private Tile[][] tMap = new Tile[40][20];

    

    private String resPath;
    private boolean reset = false;
    private boolean finished = false;
    private Player p;
    
    private Al al;
    
    

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
    }
    

    public Al getAl() {
        return al;
    }
    
    public void setTMap(Tile[][] tMap) {
        this.tMap = tMap.clone();
    }
    
    public Tile[][] getTMap()
    {
        return tMap.clone();
    }

    public void setAl(Al al) {
        this.al = al;
    }
    
    public Tile getTile(int x, int y)
    {
        return tMap[x][y];
    }


    public Map(String file) {
        
        findResourcePath();
        openFile(file);
        loadFile(readFile());
        closeFile();
    }
    
    public boolean needsReset()
    {
        return reset;
    }
    
    public void setReset(boolean r)
    {
        reset = r;
    }
    
    private void findResourcePath()
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
    private void openFile(String f) {

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
    private void loadFile(String[] rawMap)
    {
        Tile t;
        Item item;
        Item v = null;
        Bazooka b = null;
        PadVinder pa = null;
        ValsSpeler vs = null;
        for(int x = 0; x < 40; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                t = new Tile(x, y);
                if(rawMap[y].charAt(x) == 'p')//x and y flipped because every String is a row of tiles on the x axis
                {
                    item = new Player(t, this);
                    p = (Player)item;
                }
                else if(rawMap[y].charAt(x) == 'w')
                {
                    item = new Wall(t);
                }
                else if(rawMap[y].charAt(x) == 'f')
                {
                    item = new Finish(t);
                    v = item;
                }
                else if(rawMap[y].charAt(x) == '@')
                {
                    pa = new PadVinder(t);//create padvinder, use .setVriend() before starting the game.
                    item = pa;
                }
                else if(rawMap[y].charAt(x) == 'b')
                {
                    item = new Bazooka(t);
                    b = (Bazooka)item;
                }
                else if(rawMap[y].charAt(x) == 'v')
                {
                    item = new ValsSpeler(t);
                    vs = (ValsSpeler) item;
                }
                else
                {
                    item = null;
                }
                
                t.setMyItem(item);
                tMap[x][y] = t;
            }
        }
        pa.setVriend(v.getMyTile());//using .setVriend()
        pa.prepareDijkstra(this);//prepare the dijkstra algorithm
        vs.setStappenTeller(p.getStappenTeller());
    }

    /**
     * Reads the open file into a String[]
     * @return String[14] rawMap
     */
    private String[] readFile() {
        String[] map = new String[40];
        
            for (int i = 0; i < 20; i++) {
                map[i] = m.next(); //pakt per lijn
            }
        
        return map;
    }

    /**
     * closes the current file
     * DO NOT FORGET THIS!!!
     * only one file can be open at a time
     */
    private void closeFile() {
        m.close();
    }

   

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

}
