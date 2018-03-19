/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * <p>
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {

    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    @Test
    public void testOtherType() {
        Graph<Integer> emptyInstance = Graph.empty();
        assertEquals(true, emptyInstance.add(1));
        assertEquals(0, emptyInstance.set(1, 2, 10));
        assertEquals(0, emptyInstance.set(3, 4, 100));
        assertEquals(0, emptyInstance.set(3, 2, 20));
        assertEquals(0, emptyInstance.set(1, 3, 120));
        assertEquals(0, emptyInstance.set(1, 4, 119));

        Map<Integer, Integer> targets = new HashMap<>();
        targets.put(2, 10);
        targets.put(3, 120);
        targets.put(4, 119);
        assertEquals(targets, emptyInstance.targets(1));
        /* this case is to test if the source vertex has no out coming Edges */
        assertTrue("expect empty map", emptyInstance.targets(6).isEmpty());
    }

}
