/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Map {
    
    private Scanner m;
    private String Map[] = new String[16];
    
    private Image grass, wall, finish;
    
    public Map(){
        ImageIcon img = new ImageIcon("C://grass.png");
        grass = img.getImage();
        img = new ImageIcon("C://wall.png");
        wall = img.getImage();
        img = new ImageIcon("C://finish.png");
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
            m = new Scanner(new File("C://Map.txt"));
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