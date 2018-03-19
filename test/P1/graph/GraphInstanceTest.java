/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for instance methods of Graph.
 * <p>
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    /* Testing strategy
     * in every function,I will generate an empty GraphInstance and use the method in it
     * add one new point,add one point that has existed
     * weight: <0 0 >0
     * vertex: exist , don't exist ,two same vertices
     * vertex: String type,int type char type
     * then combine vertex cases and weight cases to test
     */

    /**
     * Overridden by implementation-specific test classes.
     *
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testInitialVerticesAdd() {
        /* get a new empty instance */
        Graph<String> emptyInstance = emptyInstance();
        assertEquals(true, emptyInstance.add("first"));
        assertEquals(false, emptyInstance.add("first"));
    }

    @Test
    public void testInitialVerticesSet() {
        /* get a new empty instance */
        Graph<String> emptyInstance = emptyInstance();
        /* test if weight is zero,if the edge is added to the graph */
        assertEquals(0, emptyInstance.set("first", "second", 0));
        /* to the if the "first" to "second" edge is add to the graph */
        assertEquals(true, emptyInstance.add("second"));
        /* test if weight is non-zero ,if the specific edge is add to the graph */
        assertEquals(0, emptyInstance.set("first", "second", 10));
        /* test if the edge is already exist,if the weight of the edge is modified */
        assertEquals(10, emptyInstance.set("first", "second", 21));
        /* test if weight is zero, if the specific edge is removed */
        assertEquals(21, emptyInstance.set("first", "second", 0));
        assertEquals(0, emptyInstance.set("first", "first", 1));
        assertEquals(1, emptyInstance.set("first", "first", 0));
        try {
            emptyInstance().set("first", "fifth", -2);
            fail("when weight is negative , no exception");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().equals("weight can't be negative"));
        }
    }

    @Test
    public void testInitialVerticesRemove() {
        /* get a new empty instance */
        Graph<String> emptyInstance = emptyInstance();
        /*
         * add some init data to the graph for this test
         */
        assertEquals(true, emptyInstance.add("first"));
        assertEquals(0, emptyInstance.set("first", "second", 10));
        assertEquals(0, emptyInstance.set("third", "fourth", 100));
        assertEquals(0, emptyInstance.set("third", "second", 20));

        /* to remove the vertex that doesn't exist */
        assertEquals(false, emptyInstance.remove("fifth"));
        assertEquals(true, emptyInstance.remove("second"));
        /* to remove the vertex that exist */
        assertEquals(true, emptyInstance.add("second"));
        /* to test if the edge is removed */
        assertEquals(0, emptyInstance.set("third", "second", 20));
    }

    @Test
    public void testInitialVerticesVertices() {
        /* get a new empty instance */
        Graph<String> emptyInstance = emptyInstance();
        /*
         * add some init data to the graph for this test
         */
        assertEquals(true, emptyInstance.add("first"));
        assertEquals(0, emptyInstance.set("first", "second", 10));
        assertEquals(0, emptyInstance.set("third", "fourth", 100));
        assertEquals(0, emptyInstance.set("third", "second", 20));

        Set<String> vertices = new HashSet<>();
        vertices.add("first");
        vertices.add("second");
        vertices.add("third");
        vertices.add("fourth");
        assertEquals(vertices, emptyInstance.vertices());
    }

    @Test
    public void testInitialVerticesSources() {
        /* get a new empty instance */
        Graph<String> emptyInstance = emptyInstance();
        /*
         * add some init data to the graph for this test
         */
        assertEquals(true, emptyInstance.add("first"));
        assertEquals(0, emptyInstance.set("first", "second", 10));
        assertEquals(0, emptyInstance.set("third", "fourth", 100));
        assertEquals(0, emptyInstance.set("third", "second", 20));

        Map<String, Integer> sources = new HashMap<>();
        sources.put("first", 10);
        sources.put("third", 20);
        assertEquals(sources, emptyInstance.sources("second"));
    }

    @Test
    public void testInitialVerticesTargets() {
        /* get a new empty instance */
        Graph<String> emptyInstance = emptyInstance();
        /*
         * add some init data to the graph for this test
         */
        assertEquals(true, emptyInstance.add("first"));
        assertEquals(0, emptyInstance.set("first", "second", 10));
        assertEquals(0, emptyInstance.set("third", "fourth", 100));
        assertEquals(0, emptyInstance.set("third", "second", 20));
        assertEquals(0, emptyInstance.set("first", "third", 120));
        assertEquals(0, emptyInstance.set("first", "fourth", 119));

        Map<String, Integer> targets = new HashMap<>();
        targets.put("second", 10);
        targets.put("third", 120);
        targets.put("fourth", 119);
        assertEquals(targets, emptyInstance.targets("first"));
        /* this case is to test if the source vertex has no out coming Edges */
        assertTrue("expect empty map", emptyInstance.targets("six").isEmpty());
    }
}
