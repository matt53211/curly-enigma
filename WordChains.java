import java.util.*;

public class WordChains {

    private static HashMap<String, Set<String>> neighbours;
    private static Set<String> dictionary;
    private static ArrayList<String []> input;

    /**
     * put dictionary input into a set
     * we want to find all the neighbours/edges
     * for each word in the dictionary
     *  this is done by checking each word in the dictionary and all possible 'one letter differences' it can make,
     *  and seeing if those 'one letter difference' words are in the dictionary set
     *      if those words are, add them to a arraylist as values of a hashmap, the key is the word
     */

    public WordChains(Set<String> dictionary, ArrayList<String []> input) {
        this.dictionary = dictionary;
        this.neighbours = dictionaryInit(dictionary);
        this.input = input;
        solve();
    }

    public void solve() {
        PriorityQueue<Word> queue = new PriorityQueue<>();
        String target;

        for(String [] in: input) {
            target = in[1];
            if(!lengthCheck(in) || dictionaryCheck(in)) {
                output(in, true);
                continue;
            }
            if(in[0].equals(target)){
                output(in, false);
                continue;
            }
           // queue.add();
            while(!queue.isEmpty()) {
                //String pointer = queue.remove();
            }
        }
    }

    public boolean lengthCheck(String [] in) {
        if(in[0].length() != in[1].length()) {
            return false;
        }
        return true;
    }

    public boolean dictionaryCheck(String [] in) {
        if(!dictionary.contains(in[0]) && dictionary.contains(in[1])) {
            return false;
        }
        return true;
    }

    public void output(String [] in, boolean impossible) {
    }

    public HashMap<String, Set<String>> dictionaryInit(Set<String> dictionary) {
        HashMap<String, Set<String>> neighbours = new HashMap<>();
        for(String next : dictionary) {
            StringBuilder sb = new StringBuilder(next);
            Set<String> edges = new HashSet<>();
            for(int i = 0; i < next.length(); i++) {
                for(int j = 1; j < 27; j++) {
                    Character current = next.charAt(i);
                    current = (char) (current+j);
                    if((int) current > 122) {
                        current = (char) ('a' - j);
                    }
                    sb.replace(i, i+1, Character.toString(current+j));
                    if(dictionary.contains(sb)) {
                        edges.add(sb.toString());
                    }
                }
            }
            neighbours.put(next, edges);
        }
        return neighbours;
    }

    private class Word {
        private int distance;
        private boolean seen;
    }
}