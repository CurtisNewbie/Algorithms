import java.util.*;

/**
 * A R-Tree for Strings. R refers to the possible number of char, e.g., ASCII
 * has a R of 256. For each node, there are R number of nodes it can connect to.
 * A "Trie" is created by adding strings into it, with which a path in the
 * R-Tree is created to represent each string.
 * <p>
 * At the end of the node for the string, a number is assigned to it, which
 * simply indicates the order in which it's added. E..g, 1 for the first string
 * added, 2 for the second, etc. Such number is meaningless, but it's used to
 * identify whether the given key exists in the ADT, and it helps records the
 * size of strings.
 * <p>
 * Space Complexity: O(R * n * W), where W is the average key length
 * <p>
 * Time Complexity: O(W), where W is the average key length
 * <p>
 * Time Complexity is irrelavant to the number of strings in the trie
 * 
 * 
 */
public class TrieSt {
    private static final int R = 256;
    private Node root = null;
    private int size;

    public static void main(String[] args) {
        TrieSt st = new TrieSt();
        System.out.printf("Size of TrieSt: %d\n", st.size());
        String[] data = new String[] { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells", "sh", "sheep" };
        System.out.println("Data: " + Arrays.toString(data));

        for (String s : data)
            st.put(s);
        System.out.printf("Size of TrieSt: %d\n", st.size());

        for (String s : data)
            System.out.printf("Contains: '%s': %b\n", s, st.contains(s));

        System.out.printf("\nContains: '%s': %b\n", "NoExist", st.contains("NoExist"));
        System.out.println("In TrieSt: " + st.keys().toString());
        System.out.printf("Keys that matches 's..': %s\n", st.keysThatMatch("s..").toString());
        System.out.printf("Longest prefix of seashellssort: '%s'\n", st.longestPrefixOf("seashellssort"));
        System.out.printf("Delete '%s'\n", data[1]);
        st.delete(data[1]);
        System.out.printf("Contains '%s': %b\n", data[1], st.contains(data[1]));
        System.out.println("In TrieSt: " + st.keys().toString());
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node n, String key, int d) {
        // not exists at all
        if (n == null || (d == key.length() && n.v != 0))
            return null;
        if (d < key.length()) {
            n.next[key.charAt(d)] = delete(n.next[key.charAt(d)], key, d + 1);
        }
        if (n.v > 0) // current node is the end of a key
            return n;
        for (int i = 0; i < R; i++) {
            if (n.next[i] != null) // current node shouldn't be deleted
                return n;
        }
        return null;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new LinkedList<>();
        Node nodeAtPrefix = get(root, prefix, 0);
        keys(nodeAtPrefix, prefix, q);
        return q;
    }

    /**
     * Return longest prefix of {@code s} that exists in the TrieSt
     * 
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int lpf = searchLpf(root, s, 0, 0);
        return s.substring(0, lpf);
    }

    /**
     * 
     * @param n      node
     * @param s      string whose longest prefix is being searched
     * @param d      index
     * @param length length of longest prefix
     * @return
     */
    private int searchLpf(Node n, String s, int d, int length) {
        if (n == null)
            return length;
        if (n.v > 0) // update longest key found
            length = d;
        if (d == s.length()) // end of s
            return s.length(); // end of s
        return searchLpf(n.next[s.charAt(d)], s, d + 1, length);
    }

    /** Keys that partial or fully matches, simple regex that allows '.' */
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        keysThatMatch(root, "", pat, q);
        return q;
    }

    private void keysThatMatch(Node n, String prefix, String pat, Queue<String> q) {
        int preLen = prefix.length();
        int patLen = pat.length();
        if (n == null)
            return;
        if (preLen == patLen && n.v > 0)
            q.offer(prefix);
        if (preLen == patLen)
            return;

        char nc = pat.charAt(preLen);
        for (char c = 0; c < R; c++) {
            if (nc == '.' || nc == c)
                keysThatMatch(n.next[c], prefix + c, pat, q);
        }
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private void keys(Node n, String prefix, Queue<String> q) {
        if (n == null)
            return;

        if (n.v > 0)
            q.offer(prefix);
        for (int i = 0; i < n.next.length; i++) {
            if (n.next[i] != null)
                keys(n.next[i], prefix + (char) i, q);
        }
    }

    /** Return number of unique items */
    public int size() {
        return size;
    }

    /**
     * Return whether the key exists
     */
    public boolean contains(String key) {
        return get(key) != -1;
    }

    /**
     * Get the value of key, return -1 if not exists
     */
    public int get(String key) {
        Node n = get(root, key, 0);
        return n == null ? -1 : n.v;
    }

    /**
     * Return last node of key or Null if not exists
     */
    private Node get(Node n, String key, int i) {
        if (n == null) {
            return null;
        }
        if (i == key.length())
            return n;
        else
            return get(n.next[key.charAt(i)], key, i + 1);
    }

    public void put(String key) {
        put(key, ++size);
    }

    private void put(String key, int v) {
        int i = 0;
        root = put(root, key, v, i);
    }

    private Node put(Node n, String key, int v, int d) {
        if (n == null)
            n = new Node();

        if (d == key.length()) { // no duplicates
            if (n.v == 0) {
                n.v = v;
            } else {
                size--;
            }
            // n.v = n.v == 0 ? v : n.v;
            return n;
        }
        n.next[key.charAt(d)] = put(n.next[key.charAt(d)], key, v, d + 1);
        return n;
    }

    private static class Node {
        private int v = 0;
        private Node[] next = new Node[R];
    }
}