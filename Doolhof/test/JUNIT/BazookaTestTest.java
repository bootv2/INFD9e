/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUNIT;

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
public class BazookaTestTest {
    
    public BazookaTestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class BazookaTest.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String s1 = "2";
        String s2 = "2";
        BazookaTest instance = new BazookaTest();
        int expResult = 4;
        int result = instance.add(s1, s2);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testAdd2() {
        System.out.println("add");
        String s1 = "2";
        String s2 = "3";
        BazookaTest instance = new BazookaTest();
        int expResult = 5;
        int result = instance.add(s1, s2);
        assertEquals(expResult, result);
        
    }
    
    @Test(expected=NumberFormatException.class)
    public void testAdd3() {
        System.out.println("add");
        String s1 = "foo";
        String s2 = "3";
        BazookaTest instance = new BazookaTest();
        int expResult = 5;
        int result = instance.add(s1, s2);
        assertEquals(expResult, result);
        
    }
    
}
