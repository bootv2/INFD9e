/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author farelia
 */
public class ButtonPanel extends JPanel {
    private JButton resetButton     = new JButton("Reset level");
    private JButton quitButton      = new JButton("Quit game");
    private JButton pauseButton     = new JButton("Toggle pause");
    private boolean needsReset = false;
    private boolean paused = false;
    
    public ButtonPanel()
    {
        setFocusable(true);
        
        resetButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setNeedsReset(true);
                }
            });
        
        quitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        
        pauseButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setPaused(true);
                }
            });
        
        add(resetButton, BorderLayout.EAST);
        add(pauseButton, BorderLayout.CENTER);
        add(quitButton, BorderLayout.WEST);
        setPreferredSize(new Dimension(1296, 27));
    }

    public boolean isNeedsReset() {
        return needsReset;
    }

    public void setNeedsReset(boolean needsReset) {
        this.needsReset = needsReset;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    
}
