/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

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
        Doolhof doolhof = new Doolhof();
        doolhof.main(new String[1]);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStappenTeller method, of class Player.
     */
    @Test
    public void testGetStappenTeller() {
        System.out.println("getStappenTeller");
        Player instance = null;
        StappenTeller expResult = null;
        StappenTeller result = instance.getStappenTeller();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
