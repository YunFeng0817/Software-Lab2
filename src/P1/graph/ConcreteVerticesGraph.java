/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * <p>
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor

    // TODO checkRep

    @Override
    public boolean add(String vertex) {
        for (Vertex item : vertices) {
            if (item.getLabel().equals(vertex))
                return false;
        }
        Vertex newVertex = new Vertex(vertex);
        vertices.add(newVertex);
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        int lastWeight = 0;
        if (weight != 0) {
            add(source);
            add(target);
        }
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(source)) {
                if (weight != 0)
                    lastWeight = vertex.setOutEdge(target, weight);
                else {
                    lastWeight = vertex.removeOutEdge(target);
                }
            }
            if (vertex.getLabel().equals(target)) {
                if (weight != 0)
                    lastWeight = vertex.setInEdge(source, weight);
                else
                    lastWeight = vertex.removeInEdge(source);
            }
        }
        return lastWeight;
    }

    @Override
    public boolean remove(String vertex) {
        Map<String, Integer> sources;
        Map<String, Integer> targets;
        Vertex deleteVertex = null;
        boolean vertexExist = false;
        for (Vertex item : vertices) {
            if (item.getLabel().equals(vertex)) {
                vertexExist = true;
                deleteVertex = item;
                break;
            }
        }
        if (vertexExist) {
            sources = sources(vertex);
            targets = targets(vertex);
            for (Vertex item : vertices) {
                if (sources.get(item.getLabel()) != null) {
                    item.removeOutEdge(vertex);
                }
                if (targets.get(item.getLabel()) != null) {
                    item.removeInEdge(vertex);
                }
            }
            vertices.remove(deleteVertex);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> vertices() {
        Set<String> vertices = new HashSet<>();
        for (Vertex vertex : this.vertices) {
            vertices.add(vertex.getLabel());
        }
        return vertices;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        for (Vertex vertex : vertices)
            if (vertex.getLabel().equals(target))
                return vertex.getSources();
        return new HashMap<>();
    }

    @Override
    public Map<String, Integer> targets(String source) {
        for (Vertex vertex : vertices)
            if (vertex.getLabel().equals(source))
                return vertex.getTargets();
        return new HashMap<>();
    }

    // TODO toString()

}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * <p>
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {

    // TODO fields
    private String label;
    private Map<String, Integer> inEdges = new HashMap<>();
    private Map<String, Integer> outEdges = new HashMap<>();

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    // TODO constructor
    Vertex(String name) {
        label = name;
    }
    // TODO checkRep

    // TODO methods
    public String getLabel() {
        return label;
    }

    public int setInEdge(String source, int weight) {
        int lastWeight;
        for (Map.Entry<String, Integer> entry : inEdges.entrySet()) {
            if (entry.getKey().equals(source)) {
                lastWeight = entry.getValue();
                inEdges.put(source, weight);
                return lastWeight;
            }
        }
        inEdges.put(source, weight);
        return 0;
    }

    public int setOutEdge(String target, int weight) {
        int lastWeight;
        for (Map.Entry<String, Integer> entry : outEdges.entrySet()) {
            if (entry.getKey().equals(target)) {
                lastWeight = entry.getValue();
                outEdges.put(target, weight);
                return lastWeight;
            }
        }
        outEdges.put(target, weight);
        return 0;
    }

    public Map<String, Integer> getSources() {
        Map<String, Integer> sources = new HashMap<>();
        sources.putAll(inEdges);
        return sources;
    }

    public Map<String, Integer> getTargets() {
        Map<String, Integer> targets = new HashMap<>();
        targets.putAll(outEdges);
        return targets;
    }

    public int removeInEdge(String source) {
        for (Map.Entry<String, Integer> entry : inEdges.entrySet()) {
            if (entry.getKey().equals(source)) {
                inEdges.remove(entry);
                return entry.getValue();
            }
        }
        return 0;
    }

    public int removeOutEdge(String target) {
        for (Map.Entry<String, Integer> entry : outEdges.entrySet()) {
            if (entry.getKey().equals(target)) {
                outEdges.remove(entry);
                return entry.getValue();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return label;
    }
}
