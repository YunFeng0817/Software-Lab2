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
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    // the vertex list represent the node in graph
    // the vertex class contain the out coming edge and the in coming edge

    // Representation invariant:
    // the weight must be non-negative
    // the number of  out coming edges must be the same with the in coming edges
    // the label of the vertex can't be null

    // Safety from rep exposure:
    // the vertex class can't be gotten by outside
    // make the vertices be private and final and immutable

    ConcreteVerticesGraph() {
    }

    private void checkRep() {
        for (Vertex<L> vertex : vertices) {
            assert (vertex.getLabel() != null);
        }
    }

    @Override
    public boolean add(L vertex) {
        for (Vertex item : vertices) {
            if (item.getLabel().equals(vertex))
                return false;
        }
        Vertex newVertex = new Vertex(vertex);
        vertices.add(newVertex);
        checkRep();
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        int lastWeight = 0;
        if (weight > 0) {
            add(source);
            add(target);
        } else if (weight < 0)
            throw new RuntimeException("weight can't be negative");
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(source)) {
                if (weight > 0)
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
        checkRep();
        return lastWeight;
    }

    @Override
    public boolean remove(L vertex) {
        Map<L, Integer> sources;
        Map<L, Integer> targets;
        if (!vertices.removeIf(vertexIterator -> vertexIterator.getLabel().equals(vertex)))
            return false;
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
        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        Set<L> vertices = new HashSet<>();
        for (Vertex vertex : this.vertices) {
            vertices.add((L) vertex.getLabel());
        }
        checkRep();
        return new HashSet<>(vertices);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        for (Vertex vertex : vertices)
            if (vertex.getLabel().equals(target))
                return new HashMap<>(vertex.getSources());
        checkRep();
        return new HashMap<>();
    }

    @Override
    public Map<L, Integer> targets(L source) {
        for (Vertex vertex : vertices)
            if (vertex.getLabel().equals(source))
                return new HashMap<>(vertex.getTargets());
        checkRep();
        return new HashMap<>();
    }

    @Override
    public String toString() {
        return "This graph has " + vertices.size() + " vertices";
    }
}

/**
 * This class is mutable ,
 * the number of  out coming edges must be the same with the in coming edges
 * the weight must be non-negative
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * <p>
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {

    private L label;
    private Map<L, Integer> inEdges = new HashMap<>();
    private Map<L, Integer> outEdges = new HashMap<>();

    // Abstraction function:
    // the label represent the vertex
    // the inEdges represent the in coming edges,
    // the outEdges represent the out coming edges
    // one edge contain the source vertex and the weight of the edge

    // Representation invariant:
    // the number of  out coming edges must be the same with the in coming edges
    // the weight must be non-negative
    // the label can't be null

    // Safety from rep exposure:
    // the inEdges and outEdges can't be gotten outside

    Vertex(L name) {
        label = name;
    }

    private void checkRep() {
        for (Integer weight : inEdges.values()) {
            assert (weight > 0);
        }
        for (Integer weight : outEdges.values()) {
            assert (weight > 0);
        }
        assert (label != null);
    }


    public L getLabel() {
        return label;
    }

    public int setInEdge(L source, int weight) {
        int lastWeight;
        for (Map.Entry<L, Integer> entry : inEdges.entrySet()) {
            if (entry.getKey().equals(source)) {
                lastWeight = entry.getValue();
                inEdges.put(source, weight);
                return lastWeight;
            }
        }
        inEdges.put(source, weight);
        checkRep();
        return 0;
    }

    public int setOutEdge(L target, int weight) {
        int lastWeight;
        for (Map.Entry<L, Integer> entry : outEdges.entrySet()) {
            if (entry.getKey().equals(target)) {
                lastWeight = entry.getValue();
                outEdges.put(target, weight);
                return lastWeight;
            }
        }
        outEdges.put(target, weight);
        checkRep();
        return 0;
    }

    public Map<L, Integer> getSources() {
        Map<L, Integer> sources = new HashMap<>();
        sources.putAll(inEdges);
        checkRep();
        return sources;
    }

    public Map<L, Integer> getTargets() {
        Map<L, Integer> targets = new HashMap<>();
        targets.putAll(outEdges);
        checkRep();
        return targets;
    }

    public int removeInEdge(L source) {
        for (Map.Entry<L, Integer> entry : inEdges.entrySet()) {
            if (entry.getKey().equals(source)) {
                inEdges.remove(entry);
                checkRep();
                return entry.getValue();
            }
        }
        checkRep();
        return 0;
    }

    public int removeOutEdge(L target) {
        for (Map.Entry<L, Integer> entry : outEdges.entrySet()) {
            if (entry.getKey().equals(target)) {
                outEdges.remove(entry);
                checkRep();
                return entry.getValue();
            }
        }
        checkRep();
        return 0;
    }

    @Override
    public String toString() {
        return label.toString();
    }
}
