/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * <p>
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * <p>
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 * <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 *
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 *
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 *
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO

    /**
     * Create a new poet with the graph from corpus (as described above).
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader data = new BufferedReader(new FileReader(corpus));
        String line;
        String[] words;
        while ((line = data.readLine()) != null) {
            words = line.split(" ");
            for (int i = 1; i < words.length; i++) {
                int originWeight = graph.set(words[i - 1].toLowerCase(), words[i].toLowerCase(), 1);
                if (originWeight != 0) {
                    graph.set(words[i - 1].toLowerCase(), words[i].toLowerCase(), originWeight + 1);
                }
            }
        }
    }

    // TODO checkRep

    /**
     * Generate a poem.
     *
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        List<String> inputWords;
        String[] test = input.split(" ");
        inputWords = new LinkedList<>(Arrays.asList(test));
        ListIterator<String> iterator = inputWords.listIterator();
        if (!iterator.hasNext()) {
            return " ";
        }
        String previous = iterator.next();
        String next, result;
        while (iterator.hasNext()) {
            next = iterator.next();
            result = getBridge(previous, next);
            if (!result.equals("")) {
                iterator.previous();
                iterator.add(result);
                iterator.next();
            }
            previous = next;
        }
        result = "";
        for (String words : inputWords) {
            result += words + " ";
        }
        return result.substring(0, result.length() - 1);
    }

    public String getBridge(String source, String target) {
        source = source.toLowerCase();
        target = target.toLowerCase();
        Map<String, Integer> bridges = new HashMap<>();
        Map<String, Integer> middleTargets;
        Map<String, Integer> finalTargets;
        middleTargets = graph.targets(source);
        for (Map.Entry<String, Integer> entry : middleTargets.entrySet()) {
            finalTargets = graph.targets(entry.getKey());
            if (finalTargets.keySet().contains(target)) {
                bridges.put(entry.getKey(), entry.getValue() + finalTargets.get(target));
            }
        }
        // use to sort the bridges by weight
        List<Map.Entry<String, Integer>> sortedBridges = new ArrayList<>(bridges.entrySet());
        // use sort and lambda expression
        sortedBridges.sort((Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> o2.getValue().compareTo(o1.getValue()));
        if (!sortedBridges.isEmpty())
            return sortedBridges.get(0).getKey();
        return "";
    }

    // TODO toString()

}
