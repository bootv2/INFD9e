/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author farelia
 */
public class PlayerTest {
    
    private Board b;
    private Player p;
    private Map m;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Doolhof doolhof = new Doolhof("Test speler beweging project d 92");
        b = doolhof.getB();
        m = b.getM();
        p = m.getP();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStappenTeller method, of class Player.
     */
    @Test
    public void testPlayerWMovement() {
        System.out.println("starting west player movement test");
        int x = p.getMyTile().getTileX();
        x--;
        
        p.movementTestingHelper(-1, 0);
        
        assertEquals(x, p.getMyTile().getTileX());
    }
    
    @Test
    public void testPlayerNMovement() {
        System.out.println("starting north player movement test");
        int y = p.getMyTile().getTileY();
        y--;
        
        p.movementTestingHelper(0,-1);
        
        assertEquals(y, p.getMyTile().getTileY());
    }
    
    @Test
    public void testPlayeEMovement() {
        System.out.println("starting east player movement test");
        int x = p.getMyTile().getTileX();
        x++;
        
        p.movementTestingHelper(1, 0);
        
        assertEquals(x, p.getMyTile().getTileX());
    }
    
    @Test
    public void testPlayerSMovement() {
        System.out.println("starting south player movement test");
        int y = p.getMyTile().getTileY();
        y++;
        
        p.movementTestingHelper(0, 1);
        
        assertEquals(y, p.getMyTile().getTileY());
    }
    
}
