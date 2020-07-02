import java.util.*;

/**
 * Boyer Moore algorithm, similar to KMP, also constructs a table that guides
 * the movement of pointer. KMP maintains a DFA (in this book), that tracks the
 * state we are in, such that we are traversing in different states towards
 * final state.
 * <p>
 * Differently, Boyer Moore Algorithm maintains a table, which aggresively moves
 * the pointer, by tracking the left-most position of each character. Say, we
 * have a text of N length, and a pattern of M length, we are always moving from
 * 0 -> N-1 in text, and from M-1 -> 0. E.g., When we found that char M-1
 * doesn't match, we skill over M characters directly. However, if we found a
 * mismatch at j, but the character j is indeed a character in pattern and it
 * has a right-most position at 2, if we are checking pth character in pattern,
 * then we can move forward p-2 steps (currIndexOfPattern-rightMostPos), such
 * that c is matched already, and we are ready for another check.
 * <p>
 * E.g.,
 * <p>
 * For text T: "F I N D I N A H A Y S T A C K N E E D L E" and
 * <P>
 * _______________________|_________|___________|_______|____
 * <p>
 * pattern P: "N E E D L E"
 * <p>
 * <p>
 * 1. Compare T[5] with P[5], not matched('N' != 'E'), N is a character in
 * pattern, and N's right position is at 0, move forward 5 character. We are
 * comparing P[5], so 5 - 0 = 0 steps.
 * <p>
 * 2. Compare T[10] with P[5], not matched('S' != 'E'), 'S' doesn't exist, move
 * forward 6 character
 * <p>
 * 3. Compare T[16] with P[5], matched('E' == 'E'), continues comparing
 * <p>
 * 4. Compare T[15] with p[4], not matched('N' != 'L'), since 'N' is a character
 * in the pattern, and its' fight-most position is 0, so we move forward 4
 * steps. We are comparing P[4], so 4 - 0 = 4;
 * <p>
 * 5. Compare T[20] with P[5], fully matched.
 * <p>
 * Time complexity: O(N/M)
 */
public class BoyerMoore {

    private static final int R = 256;
    private final String pattern;
    private int[] right;

    public BoyerMoore(String pattern) {
        this.pattern = pattern;
        this.right = new int[R];
        Arrays.fill(right, -1);
        // record the right-most position for each char
        for (int i = 0; i < pattern.length(); i++) {
            right[pattern.charAt(i)] = i;
        }
    }

    public static void main(String[] args) {
        String txt = "FINDINAHAYSTACKNEEDLE";
        String pattern = "NEEDLE";
        BoyerMoore bm = new BoyerMoore(pattern);
        System.out.println("Pattern: " + pattern);
        System.out.println("Text: " + txt);
        int res;
        System.out.println("Search: " + (res = bm.search(txt)));
        System.out.println("Found: " + txt.substring(res));
    }

    public int search(String txt) {
        int n = txt.length();
        int m = pattern.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1)
                        skip = 1;
                    break;
                }
            }
            if (skip == 0)
                return i;
        }
        return n;
    }
}