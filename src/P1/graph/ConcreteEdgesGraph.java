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
public class ConcreteEdgesGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction function:
    // use a universe L to represent vertex,use edge class to represent edge
    // Representation invariant:
    /*
     * the vertex should be universe and not null,the weight of the edge must be positive,
     * the two vertices of the edge should not be the same
     */
    // Safety from rep exposure:
    // make the rep be private and final ,don't provide public function to modify the value of these fields

    ConcreteEdgesGraph() {
    }

    private void checkRep() {
        for (L vertex : vertices) {
            assert (vertex != null);
        }
        for (Edge edge : edges) {
            assert (edge.getWeight() > 0);
        }
    }

    @Override
    public boolean add(L vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            return true;
        }
        return false;
    }

    @Override
    public int set(L source, L target, int weight) {
        if(weight<0){
            throw new RuntimeException("weight can't be negative");
        }
        Edge instance;
        int lastWeight = 0; // this variable is to store the previous edge weight of the changed edge
        // to search if the input edge is in edges
        for (Edge edge : edges) {
            if (edge.sameVertex(source, target)) {
                lastWeight = edge.getWeight();
                edges.remove(edge);
                if (weight > 0) {
                    instance = new Edge(source, target, weight);
                    edges.add(instance);
                }
                checkRep();
                return lastWeight;
            }
        }
        // the input edge is not in edges case
        if (weight != 0) {
            instance = new Edge(source, target, weight);
            vertices.add(source);
            vertices.add(target);
            edges.add(instance);
        }
        checkRep();
        return 0;
    }

    @Override
    public boolean remove(L vertex) {
        if (!vertices.contains(vertex))
            return false;
//        ListIterator<Edge> iterator = edges.listIterator();
//        while (iterator.hasNext()) {
//            Edge edge = iterator.next();
//            if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
//                iterator.remove();
//            }
//        }
        // simple expression
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        vertices.remove(vertex);
        checkRep();
        return true;
    }

    @Override
    public Set<L> vertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> sources = new HashMap<>();
        for (Edge edge : edges)
            if (target.equals(edge.getTarget()))
                sources.put((L) edge.getSource(), edge.getWeight());
        checkRep();
        return sources;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Map<L, Integer> targets = new HashMap<>();
        for (Edge edge : edges)
            if (edge.getSource().equals(source))
                targets.put((L) edge.getTarget(), edge.getWeight());
        checkRep();
        return targets;
    }

    @Override
    public String toString() {
        return "the number of vertices is  " + vertices.size() + " ,the number of edges is " + edges.size();
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * <p>
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {

    private L source, target;
    private int weight;

    // Abstraction function:
    /*
     * the source L means the start vertex of the edge,
     * and the target L means the end vertex of the edge,
     * the integer weight means the weight of the edge
     */
    // Representation invariant:
    // the weight of the edge must be positive and the source and the target must be different
    // Safety from rep exposure:
    // make the source ,target,weight private and final,don't provide method to modify them

    Edge(L source, L target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    private void checkRep() {
        assert (weight > 0);
    }

    boolean sameVertex(L source, L target) {
        return this.source.equals(source) && this.target.equals(target);
    }

    L getSource() {
        checkRep();
        return source;
    }

    L getTarget() {
        checkRep();
        return target;
    }

    int getWeight() {
        checkRep();
        return weight;
    }

    @Override
    public String toString() {
        return "the source vertex is " + source + " the target vertex is " + target + " ,the weight is " + weight;
    }
}
