import java.util.*;

/**
 * TrieSt is essentially R-Tree, where R is the total number of all possible,
 * unique nodes.
 * <p>
 * Three way TrieSt or "TST" ("Ternary Search Tree" in book), optimises Trie a
 * bit to reduce space complxity. Instead of being a R-Tree, TST uses a 3-Tree,
 * where each node has three child nodes, left (less than parent), mid (equal to
 * parent), right (greater than parent).
 * <p>
 * The way the TST traverses its nodes are simlilar to binary tree. And the
 * space complexity will be less than Trie since the possibility of having a
 * very long "tail" is reduced, and some of the nodes in prefix are shared among
 * strings.
 * <p>
 * Space Complexity: O(3NW), Recall that in TrieSt, it was O(RNW)
 * <p>
 * Time Complexity: O(logN), or more precisely log3(n) -> logn
 * <p>
 * Notice that TST does not support keysThatMatch() method in TrieSt
 */
public class ThreeWayTrieSt {
    private WNode root = null;
    private int n = 0;

    private static class WNode {
        private int c;
        private int v = 0;
        private WNode left = null;
        private WNode mid = null;
        private WNode right = null;

        WNode(int c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        ThreeWayTrieSt st = new ThreeWayTrieSt();
        System.out.printf("Size of ThreeWayTrieSt: %d\n", st.size());
        String[] data = new String[] { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells", "sh", "sheep" };
        System.out.println("Data: " + Arrays.toString(data));

        // Verifies data in ThreeWayTrieSt
        for (String s : data)
            st.put(s);
        System.out.println("\nIn ThreeWayTrieSt: " + st.keys());
        System.out.printf("Size of ThreeWayTrieSt: %d\n", st.size());

        for (String s : data)
            System.out.printf("Contains: '%s': %b\n", s, st.contains(s));
        System.out.printf("Contains: '%s': %b\n", "NotExist", st.contains("NotExist"));

        System.out.printf("\nDelete '%s'\n", data[1]);
        st.delete(data[1]);
        System.out.printf("Contains '%s': %b\n", data[1], st.contains(data[1]));
        System.out.println("\nIn ThreeWayTrieSt: " + st.keys().toString());
        System.out.printf("Size of ThreeWayTrieSt: %d\n", st.size());
    }

    public int size() {
        return n;
    }

    public boolean contains(String key) {
        return get(key) > 0;
    }

    public void put(String key) {
        root = put(root, key, 0);
    }

    private WNode put(WNode curr, String key, int d) {
        if (curr == null) {
            curr = new WNode(key.charAt(d));
        }
        if (d == key.length()) {
            return curr;
        }
        char c = key.charAt(d);
        if (c < curr.c) {
            curr.left = put(curr.left, key, d);
        } else if (c == curr.c) {
            if (d < key.length() - 1)
                curr.mid = put(curr.mid, key, d + 1);
            else
                curr.v = ++n;
        } else {
            curr.right = put(curr.right, key, d);
        }
        return curr;
    }

    /**
     * Return value of the node for the key or -1 if not found.
     * 
     * @param key
     * @return
     */
    public int get(String key) {
        WNode n = get(root, key, 0);
        return n == null ? -1 : n.v;
    }

    private WNode get(WNode curr, String key, int d) {
        if (curr == null)
            return null;
        if (d == key.length())
            return curr;

        // compare char to decide which way to go
        char c = key.charAt(d);
        if (c < curr.c) {
            return get(curr.left, key, d);
        } else if (c == curr.c) {
            ++d;
            if (d == key.length())
                return curr;
            else
                return get(curr.mid, key, d);
        } else {
            return get(curr.right, key, d);
        }
    }

    public String keys() {
        List<String> ls = new ArrayList<>();
        dfs(root, "", ls);
        return ls.toString();
    }

    private void dfs(WNode curr, String prefix, List<String> ls) {
        if (curr == null) {
            return;
        }
        dfs(curr.left, prefix, ls);
        dfs(curr.right, prefix, ls);

        prefix += (char) curr.c;
        if (curr.v > 0)
            ls.add(prefix);
        dfs(curr.mid, prefix, ls);
    }

    public void delete(String key) {
        root = del(root, key, 0);
    }

    private WNode del(WNode curr, String key, int d) {
        if (curr == null)
            return null;

        char c = key.charAt(d);
        if (curr.c == c) {
            ++d;
            if (d == key.length()) {
                if (curr.v > 0) {
                    curr.v = 0;
                    --n;
                }
            } else {
                curr.mid = del(curr.mid, key, d);
            }
        } else if (curr.c > c) {
            curr.left = del(curr.left, key, d);
        } else {
            curr.right = del(curr.right, key, d);
        }

        // remove current node if all of its children are null
        if (curr.left == null && curr.mid == null && curr.right == null && curr.v == 0)
            return null;
        else
            return curr;
    }
}