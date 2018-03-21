/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    /**
     * Testing strategy
     * bridge word : 1. no bridge word between two input words,
     * 2. only one bridge word
     * 3. more than one bridge word
     * <p>
     * a two-edge-long path weight:
     * all same
     * all different
     * have different value and same value
     **/

    //
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    File file = new File("./test/P1/poet/data.txt");
    GraphPoet data;

    @Before
    public void before() throws IOException {
        data = new GraphPoet(file);
    }

    @Test
    public void testGraphPoet() {
        assertEquals("Seek to explore strange new life and exciting synergies!", data.poem("Seek to explore new and exciting synergies!"));
    }

    @Test
    public void testGetBrigde() {
        assertEquals("strange", data.getBridge("explore", "new"));
        assertEquals("life", data.getBridge("new", "and"));
    }

}
