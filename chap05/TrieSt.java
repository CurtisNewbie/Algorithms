
import java.util.*;

/**
 * A R-Tree for Strings. R refers to the possible number of char, e.g., ASCII has a R of 256. For each node, there are R
 * number of nodes it can connect to. A "Trie" is created by adding strings into it, with which a path in the R-Tree is
 * created to represent each string.
 * <p>
 * At the end of the node for the string, a number is assigned to it, which simply indicates the order in which it's
 * added. E..g, 1 for the first string added, 2 for the second, etc. Such number is meaningless, but it's used to
 * identify whether the given key exists in the ADT, and it helps record the size of strings.
 * <p>
 * Space Complexity: O(R * n * W), where W is the average key length
 * <p>
 * Time Complexity: O(W), where W is the average key length
 * <p>
 * Time Complexity is irrelavant to the number of strings in the Trie
 */
public class TrieSt {
    private static final int R = 256;
    private TNode root = null;
    private int size;

    public static void main(String[] args) {
        TrieSt st = new TrieSt();
        System.out.printf("Size of TrieSt: %d\n", st.size());
        String[] data = new String[]{"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells", "sh", "sheep"};
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
        System.out.printf("Size of TrieSt: %d\n", st.size());
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private TNode delete(TNode n, String key, int idx) {
        // not found
        if (n == null)
            return null;

        // remove current node
        if (idx == key.length() && n.isWordEnd) {
            size--;
            return null;
        }

        // we are not there yet, continue traversing
        if (idx < key.length()) {
            TNode next = n.next[key.charAt(idx)];
            n.next[key.charAt(idx)] = delete(next, key, idx + 1);
        }

        if (n.isWordEnd)
            return n;

        for (int i = 0; i < R; i++) {
            if (n.next[i] != null) {
                // has children
                return n;
            }
        }
        return null;
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new LinkedList<>();
        TNode TNodeAtPrefix = get(root, prefix, 0);
        keys(TNodeAtPrefix, prefix, q);
        return q;
    }

    /**
     * Return longest prefix of {@code s} that exists in the TrieSt
     *
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int lpf = longestPrefixOf(root, s, 0, 0);
        return s.substring(0, lpf);
    }

    /**
     * @param n      node
     * @param s      string whose longest prefix is being searched
     * @param idx    index
     * @param length length of longest prefix
     * @return
     */
    private int longestPrefixOf(TNode n, String s, int idx, int length) {
        if (n == null)
            return length;
        if (n.isWordEnd) // update longest key found
            length = idx;
        if (idx == s.length()) // end of s
            return s.length(); // end of s
        return longestPrefixOf(n.next[s.charAt(idx)], s, idx + 1, length);
    }

    /** Keys that match a simple regex that allows '.' */
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        keysThatMatch(root, "", pat, q);
        return q;
    }

    private void keysThatMatch(TNode n, String prefix, String pat, Queue<String> q) {
        int preLen = prefix.length();
        int patLen = pat.length();
        if (n == null)
            return;
        if (preLen == patLen && n.isWordEnd)
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

    private void keys(TNode n, String prefix, Queue<String> q) {
        if (n == null)
            return;

        if (n.isWordEnd)
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
        TNode n = get(root, key, 0);
        return n == null ? false : n.isWordEnd;
    }

    /**
     * Return last node of key or Null if not exists
     */
    private TNode get(TNode n, String key, int idx) {
        if (n == null) {
            return null;
        }
        if (idx == key.length())
            return n;
        else
            return get(n.next[key.charAt(idx)], key, idx + 1);
    }

    public void put(String key) {
        int i = 0;
        ++size;
        root = put(root, key, i);
    }

    private TNode put(TNode n, String key, int d) {
        if (n == null)
            n = new TNode();

        if (d == key.length()) {
            if (!n.isWordEnd) {
                n.isWordEnd = true;
            } else {
                size--; // there is one already, duplicate found
            }
            return n;
        }

        TNode next = n.next[key.charAt(d)];
        n.next[key.charAt(d)] = put(next, key, d + 1);
        return n;
    }

    private static class TNode {
        private boolean isWordEnd = false;
        private TNode[] next = new TNode[R];
    }
}