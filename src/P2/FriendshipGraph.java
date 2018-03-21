package P2;

import P1.graph.*;

import java.util.LinkedList;
import java.util.Queue;

public class FriendshipGraph {

    private Graph<Person> graph = Graph.empty();
    private int personNum;

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
        if (graph.add(newPerson)) {
            this.personNum++;
        }
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
        if (personA == personB)
            return 0;
        Queue<Person> BSQueue = new LinkedList<>();  // record the persons' id to be visit
        boolean[] visited = new boolean[this.personNum]; // record every person if has been visited
        for (int i = 1; i < this.personNum; i++) {
            visited[i] = false;
        }
        BSQueue.offer(personA);
        visited[0] = true;
        /* record the begin(front) id and the end(rear) id of each BS floor */
        int count = 1, front = BSQueue.element(), rear = BSQueue.element();
        while (!BSQueue.isEmpty()) {
            for (int i = 0; i < this.personNum; i++) {
                if (Graph.get(BSQueue.element()).get(i).equals(true)) {
                    if (i == personB.getId()) {
                        return count;
                    }
                    if (!visited[i]) {
                        front = i;
                        visited[i] = true;
                        BSQueue.offer(i);
                    }
                }
            }

            /* judge if one BS floor has been visited */
            if (BSQueue.poll() == rear) {
                count++;
                rear = front;
            }
        }
        return -1;
    }

}