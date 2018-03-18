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
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

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
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            return true;
        }
        return false;
    }

    @Override
    public int set(String source, String target, int weight) {
        Edge instance = new Edge(source, target, weight);
        int lastWeight = 0; // this variable is to store the previous edge weight of the changed edge
        // to search if the input edge is in edges
        for (Edge edge : edges) {
            if (edge.equal(instance)) {
                lastWeight = edge.getWeight();
                edges.remove(edge);
                if (weight != 0)
                    edges.add(instance);
                return lastWeight;
            }
        }
        // the input edge is not in edges case
        if (weight != 0) {
            vertices.add(source);
            vertices.add(target);
            edges.add(instance);
        }
        return 0;
    }

    @Override
    public boolean remove(String vertex) {
        if (!vertices.contains(vertex))
            return false;
        ListIterator<Edge> iterator = edges.listIterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
                iterator.remove();
            }
        }
        // search for the specific String object to remove it
        for (String i : vertices) {
            if (i.equals(vertex))
                vertices.remove(i);
        }
        return true;
    }

    @Override
    public Set<String> vertices() {
        return vertices;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Edge edge : edges)
            if (target.equals(edge.getTarget()))
                sources.put(edge.getSource(), edge.getWeight());
        return sources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Edge edge : edges)
            if (edge.getSource().equals(source))
                targets.put(edge.getTarget(), edge.getWeight());
        return targets;
    }

    // TODO toString()

}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * <p>
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {

    private String source, target;
    private int weight;

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    // TODO checkRep

    // TODO methods

    public boolean equal(Edge obj) {
        return this.source.equals(obj.getSource()) && this.target.equals(obj.getTarget());
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
    // TODO toString()

}
