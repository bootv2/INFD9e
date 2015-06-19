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
public class Map {

    private Scanner m;//used to load the mapfile
    private Tile[][] tMap = new Tile[40][20];//the map used by the game
    
    int perfectRouteSteps = 0;

    private String resPath;//the path to the resources
    private Player p;//The player thats on this map
    private Vriend v;//the finish that determines if the level is done

    private Al al;//The players actionlistener
    private StappenTeller stappenTeller;

    public StappenTeller getStappenTeller() {
        return stappenTeller;
    }

    public boolean isFinished() {
        return v.isPickedUp();
    }

    public Al getAl() {
        return al;
    }

    public void setTMap(Tile[][] tMap) {
        this.tMap = tMap.clone();
    }

    public Tile[][] getTMap() {
        return tMap.clone();
    }

    public void setAl(Al al) {
        this.al = al;
    }

    /**
     * get the tile at x, y
     *
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @return The tile at x, y
     */
    public Tile getTile(int x, int y) {
        return tMap[x][y];
    }

    /**
     * Create and load a new map from the file passed.
     *
     * @param file
     */
    public Map(String file) {

        findResourcePath();//find the resource folder path
        openFile(file);//open the file
        loadFile(readFile());//read the file and load it into the tilemap
        closeFile();//close the file
    }

    /**
     * return the path of the resources folder
     */
    private void findResourcePath() {
        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Opens file f
     *
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
     *
     * @param rawMap
     */
    private void loadFile(String[] rawMap) {
        Tile t;
        Item item;
        Item v = null;
        Helper pa = null;
        ValsSpeler vs = null;
        Helper idealStepsCalc = null;
        for (int x = 0; x < 40; x++)//for every row
        {
            for (int y = 0; y < 20; y++)//and every column
            {
                t = new Tile(x, y);//create a new tile at x, y
                if (rawMap[y].charAt(x) == 'p')//x and y flipped because every String is a row of tiles on the x axis
                {
                    item = new Player(t, this);//create a new player
                    idealStepsCalc = new Helper(t);
                    p = (Player) item;//set the player object to this player for later reference
                } else if (rawMap[y].charAt(x) == 'w') {
                    item = new Wall(t);//create a new wall
                } else if (rawMap[y].charAt(x) == 'f') {
                    item = new Vriend(t);//create a new Vriend
                    v = item;//store this Vriend in v for later reference
                } else if (rawMap[y].charAt(x) == '@') {
                    pa = new Helper(t);//create padvinder, use .setVriend() before starting the game.
                    item = pa;//set padvinder to item
                } else if (rawMap[y].charAt(x) == 'b') {
                    item = new Bazooka(t);//create a new bazooka
                } else if (rawMap[y].charAt(x) == 'v') {
                    item = new ValsSpeler(t);//create a new ValsSpeler
                    vs = (ValsSpeler) item;//store this ValsSpeler in vs for later reference
                } else {
                    item = null;//item not supported, leave it empty
                }

                t.setMyItem(item);//set this tiles item to the new item
                tMap[x][y] = t;//add this tile to the map
            }
        }
        pa.setVriend(v.getMyTile());//using .setVriend()
        pa.prepareDijkstra(this);//prepare the dijkstra algorithm
        vs.setStappenTeller(p.getStappenTeller());//give ValsSpeler access to stappenteller so it can manipulate StappenTeller
        idealStepsCalc.setVriend(v.getMyTile());
        idealStepsCalc.prepareDijkstra(this);
        stappenTeller = p.getStappenTeller();
        perfectRouteSteps = idealStepsCalc.getShortestPathSteps();
        System.out.println("The perfect route is " + perfectRouteSteps + " steps long!");
        this.v = (Vriend)v;
    }

    /**
     * Reads the open file into a String[]
     *
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
     * closes the current file DO NOT FORGET THIS!!! only one file can be open
     * at a time
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
