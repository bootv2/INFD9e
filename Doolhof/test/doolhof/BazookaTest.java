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
public class BazookaTest {

    private Board b;
    private Player p;
    private Map m;
    private Bazooka bazooka = new Bazooka();
    Tile[][] tmap;

    public BazookaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Doolhof doolhof = new Doolhof("Test bazooka schieten project d 92");
        b = doolhof.getB();
        m = b.getM();
        p = m.getP();
        tmap = m.getTMap();
        p.testingGiveBazooka(bazooka);
        bazooka.setPlayer(p);
        bazooka.setMap(m);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of shoot method, of class Bazooka.
     */
    @Test
    public void testShootWest() {
        System.out.println("shoot west");
        short aimdir = 0;
        p.testSetAimDirection(aimdir);
        int curx = p.getMyTile().getTileX();
        int cury = p.getMyTile().getTileY();
        int expXShot = 0;
        int expYShot = 0;
        for (int i = 0; curx - i > 0; i++) {
            if (tmap[curx - i][cury].getMyItem() instanceof Wall) {
                expXShot = curx - i;
                expYShot = cury;
                break;
            }
        }
        System.out.println("Expected wall at " + expXShot + " | " + expYShot + " to be shot");
        System.out.println("if 0, 0, the rocket didnt hit anything");
        bazooka.shoot(aimdir);
        if (!(tmap[expXShot][expYShot].getMyItem() instanceof Dot)) {
            fail("The wall found at " + expXShot + " | " + expYShot + "wasnt destroyed");
        }
    }

    @Test
    public void testShootNorth() {
        System.out.println("shoot North");
        short aimdir = 1;
        p.testSetAimDirection(aimdir);
        int curx = p.getMyTile().getTileX();
        int cury = p.getMyTile().getTileY();
        int expXShot = 0;
        int expYShot = 0;
        for (int i = 0; cury - i > 0; i++) {
            if (tmap[curx][cury - i].getMyItem() instanceof Wall) {
                expXShot = curx;
                expYShot = cury - i;
                break;
            }
        }
        System.out.println("Expected wall at " + expXShot + " | " + expYShot + " to be shot");
        System.out.println("if 0, 0, the rocket didnt hit anything");
        bazooka.shoot(aimdir);
        if (!(tmap[expXShot][expYShot].getMyItem() instanceof Dot)) {
            fail("The wall found at " + expXShot + " | " + expYShot + "wasnt destroyed");
        }
    }

    @Test
    public void testShootEast() {
        System.out.println("shoot East");
        short aimdir = 2;
        p.testSetAimDirection(aimdir);
        int curx = p.getMyTile().getTileX();
        int cury = p.getMyTile().getTileY();
        int expXShot = 0;
        int expYShot = 0;
        for (int i = 0; curx + i < 40; i++) {
            if (tmap[curx + i][cury].getMyItem() instanceof Wall) {
                expXShot = curx + i;
                expYShot = cury;
                break;
            }
        }
        System.out.println("Expected wall at " + expXShot + " | " + expYShot + " to be shot");
        System.out.println("if 0, 0, the rocket didnt hit anything");
        bazooka.shoot(aimdir);
        if (!(tmap[expXShot][expYShot].getMyItem() instanceof Dot)) {
            fail("The wall found at " + expXShot + " | " + expYShot + "wasnt destroyed");
        }
    }

    @Test
    public void testShootSouth() {
        System.out.println("shoot South");
        short aimdir = 3;
        p.testSetAimDirection(aimdir);
        int curx = p.getMyTile().getTileX();
        int cury = p.getMyTile().getTileY();
        int expXShot = 0;
        int expYShot = 0;
        for (int i = 0; cury + i < 20; i++) {
            if (tmap[curx][cury + i].getMyItem() instanceof Wall) {
                expXShot = curx;
                expYShot = cury + i;
                break;
            }
        }
        System.out.println("Expected wall at " + expXShot + " | " + expYShot + " to be shot");
        System.out.println("if 0, 0, the rocket didnt hit anything");
        bazooka.shoot(aimdir);
        if (!(tmap[expXShot][expYShot].getMyItem() instanceof Dot)) {
            fail("The wall found at " + expXShot + " | " + expYShot + "wasnt destroyed");
        }
    }

}
