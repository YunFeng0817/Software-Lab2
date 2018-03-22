package P2;

import P1.graph.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FriendshipGraph {

    /* abstraction function
     * the graph is the represent of friendshipGraph
     * the vertex in the graph is one person
     * the edge in the graph means the two people is friend
     * the graph's edge is directive ,so if two people are friends,the edge must point to each other
     */

    /* rep invariant
     * the person in the graph can't have the same name
     * the weight of the graph can't have weight more than 1
     * the two people must have edge point to each other
     * since this rep invariant is also in Graph,so this class will have the same checkRep function in the Graph
     */

    // Safety from rep exposure:
    // set the graph be private,and set the person class friendly,it can't be access outside the package

    private Graph<Person> graph = Graph.empty();

    public static void main(String[] argv) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
        System.out.println(graph.getDistance(rachel, ben));
        System.out.println(graph.getDistance(rachel, rachel));
        System.out.println(graph.getDistance(rachel, kramer));
    }

    /**
     * add new person to the FriendshipGraph
     *
     * @param newPerson the person of the new one
     */
    public void addVertex(Person newPerson) {
        graph.add(newPerson);
    }

    /**
     * add directive direction between the two person
     *
     * @param personA the first person
     * @param personB the second person
     */
    public void addEdge(Person personA, Person personB) {
        graph.set(personA, personB, 1);
    }

    /**
     * get distance between the two person
     *
     * @param personA the first person
     * @param personB the second person
     * @return the value of the distance
     */
    public int getDistance(Person personA, Person personB) {
        if (personA.equals(personB))
            return 0;
        Queue<Person> BSQueue = new LinkedList<>();  // record the persons' id to be visit
        Set<Person> visited = new HashSet<>(); // record every person if has been visited
        BSQueue.offer(personA);
        visited.add(personA);
        /* record the begin(front) id and the end(rear) id of each BS floor */
        Person front = BSQueue.element(), rear = BSQueue.element();
        int count = 1;
        while (!BSQueue.isEmpty()) {
            for (Person item : graph.targets(BSQueue.element()).keySet()) {
                if (item.equals(personB))
                    return count;
                if (!visited.contains(item)) {
                    BSQueue.offer(item);
                    visited.add(item);
                    front = item;
                }
            }
            /* judge if one BS floor has been visited */
            if (BSQueue.poll().equals(rear)) {
                count++;
                rear = front;
            }
        }
        return -1;
    }
}