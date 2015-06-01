/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
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
    
    private Scanner m;
    private String Map[] = new String[16];
    
    private String resPath;
    
    private Image grass, wall, finish;
    
    public Map(){
        
        String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();//get the path of the jarfile to determine what the path of the resources is.
        try {
            resPath = URLDecoder.decode(path, "UTF-8") + "res/";//decode this path from utf-8 to a regular string.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageIcon img = new ImageIcon(resPath + "grass.png");
        grass = img.getImage();
        img = new ImageIcon(resPath + "wall.png");
        wall = img.getImage();
        img = new ImageIcon(resPath + "finish.png");
        finish = img.getImage();
        
        openFile();
        readFile();
        closeFile();
    }
    
    public Image getGrass(){
        return grass;
    }
    
    public Image getWall(){
        return wall;
    }
    
    public Image getFinish(){
        return finish;
    }
    
    public String getMap(int x, int y){
        String index = Map[y].substring(x, x+1);
        return index;
    }
    
    public void openFile(){
        try{
            m = new Scanner(new File(resPath + "Map.txt"));
        }
        catch(Exception e){
            System.out.println("Error Map");
        }
        
    }
    
    public void readFile(){
        while(m.hasNext()){
            for(int i = 0; i < 14; i++){
                Map[i] = m.next(); //pakt per lijn
            }
        }
    }
    
    public void closeFile(){
        m.close();
    }
    
}