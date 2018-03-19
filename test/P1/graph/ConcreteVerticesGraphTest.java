/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;

/**
 * Tests for ConcreteVerticesGraph.
 * <p>
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * <p>
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // Testing strategy for ConcreteEdgesGraph.toString()
    // test the return thing of toString() is the wanted String or not

    Graph<String> emptyInstance = emptyInstance();

    @Test
    public void testConcreteEdgesGraphToString() {
        emptyInstance().add("first");
        emptyInstance.set("first", "second", 10);
        emptyInstance.set("third", "fourth", 100);
        emptyInstance.set("third", "second", 20);

        assertEquals("This graph has 4 vertices", emptyInstance.toString());
    }
    
    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    // vertex: two String type:the different ,the same,the null
    // weight:<0 >0 =0

    @Test
    public void testVertices() {
        // cover vertex :two same String
        // cover weight<0
        Vertex<String> vertex1 = new Vertex<>("first");
        Vertex<String> vertex2 = new Vertex<>("second");
        try {
            vertex1.setInEdge("second", -1);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        try {
            vertex1.setInEdge("second", 0);
            fail("not catch weight<=0 error");
        } catch (AssertionError error) {
        }
        vertex1.setInEdge("second", 1);
        vertex2.setOutEdge("first", 2);
        HashMap<String, Integer> result1 = new HashMap<>(), result2 = new HashMap<>();
        result1.put("second", 1);
        result2.put("first", 2);
        assertEquals(result1, vertex1.getSources());
        assertEquals(new HashMap<>(), vertex1.getTargets());
        assertEquals(new HashMap<>(), vertex2.getSources());
        assertEquals(result2, vertex2.getTargets());
    }

}
