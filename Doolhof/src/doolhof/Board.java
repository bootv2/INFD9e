/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Singh
 */
public class Board extends JPanel implements ActionListener {
    private Timer timer;
    
    private Map m;
    private Player p;
    //private String Message = "";
    
    public Board(){
        
        m = new Map();
        p = new Player();
        addKeyListener(new Al());
        setFocusable(true); //adds to frame
        
        timer = new Timer(25, this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e){
        if(m.getMap(p.getTileX(), p.getTileY()).equals("f"))
        {
            JOptionPane.showMessageDialog(this, "You have completed the first level!");
            //Message = "You have completed this level";
        }
        repaint();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        for(int y = 0;y < 14;y++){
            for(int x = 0;x < 14;x++){
                if(m.getMap(x, y).equals("f")){
                    g.drawImage(m.getFinish(), x * 32, y * 32, this);
                }
                if(m.getMap(x, y).equals("g")){
                    g.drawImage(m.getGrass(), x * 32, y * 32, this); //32 pixels
                }
                if(m.getMap(x, y).equals("w")){
                    g.drawImage(m.getWall(), x * 32, y * 32, this); //32 pixels
                }
            }
        }
        //g.drawString(Message, 50, 50);
        
        g.drawImage(p.getPlayer(), p.getTileX() * 32, p.getTileY() * 32, this);
    }
    
    //ActionListener
    public class Al extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            int keycode = e.getKeyCode();
            
            if(keycode == KeyEvent.VK_W){
                //voorlopige Collision met if
                if(!m.getMap(p.getTileX(), p.getTileY() - 1).equals("w"))
                {
                    p.move(0, -1);
                }
                
            }
            if(keycode == KeyEvent.VK_S){
                if(!m.getMap(p.getTileX(), p.getTileY() + 1).equals("w"))
                {
                    p.move(0, 1);
                }
            }
            if(keycode == KeyEvent.VK_A){
                if(!m.getMap(p.getTileX() - 1, p.getTileY()).equals("w"))
                {
                    p.move(-1, 0);
                }
            }
            if(keycode == KeyEvent.VK_D){
                if(!m.getMap(p.getTileX() + 1, p.getTileY()).equals("w"))
                {
                    p.move(1, 0);
                }
            }
        }
        public void keyReleased(KeyEvent e)
        {
        
        }
        public void keyTyped(KeyEvent e)
        {
        
        }
    }
}
