
/**
 * Knuth-Morris-Pratt algorithm
 * <p>
 * KMP uses a concept called Deterministic Finite Automation (DFA), or Finite State Machine. It's essentially taking
 * advantages of a state machine, which optimises the backtracking involved in string matching.
 * <p>
 * While using brute force approach, say we have i in text[i], and j in pattern[j], i is moved from 0 -> text.length,
 * and j is moved from 0 -> pattern.length, but j is moved back and forth for each i. This effectively make the
 * algorithm O(MN).
 * <p>
 * In KMP, DFA is constructed for the pattern to optimise when and where the j moved to. Instead of always moving j back
 * to 0, DFA records the proper 'states', and guides the j's movement. Simply put, the pattern string is considered as a
 * state machine, if a pattern has 6 characters, to match pattern[1], we must match pattern[0], etc. We can consider
 * this as a state, where state 2 relies on the fulfillment of state 1.
 * <p>
 * State:<br> 1 -> 2 -> 3 -> 4 ->.... M
 * <p>
 * When M state is achieved, the pattern is matched. And M will be the length of the pattern string.
 * <p>
 * What makes KMP powerful is that it not only records these states, DFA also records which state the j should move back
 * to when a mismatch is found. E.g., a text can be "BABABABAB", if we are at 'B', then for any B, when a mismatch is
 * found, we would normally like to move back to an 'B' instead of 'A'. KMP solves these sort of problems, by recording
 * states transition in DFA.
 * <p>
 * A Non-Deterministic Finite Automation (NDFA), or Infinite State Machine also exists.
 * <p>
 * Imagine we have pattern "cbcba", and we have text "cbcbc" _________________________01234_____________________01234
 * <p>
 * when we are at 5th ('c') in text, we are at state 4, but we fail to go to state 5 because 'c' != 'a' which is
 * pat.charAt(4), instead of starting over from 0 ('c') or go back to 2 ('c'), we can just pretend that we have always
 * been at state 2, that we started from 2 ('c'), and we are now at 5 ('c'), if the text is "cbcbcba", then we will have
 * found the real match, starting at 2 [2:6].
 * <p>
 * DFA: "cbcba" (DFA is about the pattern not the text)
 * <p>
 * j(index) _0__1__2__3__4 (j(th) char in pattern)
 * <p>
 * pattern: _c__b__c__b__a (pattern string)
 * <p>
 * indices: _0__1__2__3__4 (indices in pattern)
 * <p>
 * a: _____ [0, 0, 0, 0, 5] (dfa['a'][])
 * <p>
 * b: _____ [0, 2, 0, 4, 0] (dfa['b'][])
 * <p>
 * c: _____ [1, 1, 3, 1, 3] (dfa['c'][])
 * <p>
 * x: _______0__0__0__1__2 (restart/failure state)
 * <p>
 * states:0__1__2__3__4__5
 * <p>
 * state 0: means nothing is matched yet, when char[0] is matched, enter state 1
 * <p>
 * To understand how x changes:
 * <p>
 * - At state 0: x0 should be 0, because there is nothing matched
 * <p>
 * - At state 1: x1 should also be 0, because we only matched one char
 * <p>
 * - At state 2: x2 = dfa[pat.charAt(1)][x1] = dfa['b'][0] = 0
 * <p>
 * - At state 3: x3 = dfa['c'][0] = 1
 * <p>
 * - At state 4: x4 = dfa['b'][1] = 2
 * <p>
 * - At state 5: x5 = dfa['a'][2] = 0
 * <p>
 * <p>
 * Time Complexity (without considering R): O(M+N)
 * <p>
 * Taking into consideration the time needed for constructing DFA for R: It will be O(M*R + M + N)
 */
public class KMP {
    private static final int R = 256;
    private final String pat;
    private final int pLen;
    private final int[][] dfa;
    private final int[] lps;

    public KMP(String pat) {
        this.pat = pat;
        this.pLen = pat.length();
        this.dfa = new int[R][pLen];
        this.lps = new int[pLen];
        initDFA(dfa);
        longestPrefixSuffix();
    }

    public static void main(String[] args) {
        String pattern = "cbcba";
        String text = "cbcbcba";

        KMP kmp = new KMP(pattern);

        System.out.println("Pattern: " + pattern);
        System.out.println("Text: " + text);
        System.out.println("Search: " + kmp.search(text));
        System.out.println("Match: " + kmp.match(text));
        System.out.println("Answer: " + text.contains(pattern));
    }

    // initialise DFA
    private void initDFA(int[][] dfa) {
        // if c equals current char, current state will always +1, so 0 + 1 = 1
        // the line below denotes a match at the 1st character for pattern, thus state 1
        dfa[pat.charAt(0)][0] = 1;

        // dfa[c][j] = state, where c is the character compared, the correct char should
        // be pat.charAt(j)
        // j is the current index in pattern, which is essentially a pointer
        // x is the restart state
        for (int x = 0, j = 1; j < pLen; j++) {
            for (int c = 0; c < R; c++) {
                // copy failure/restart state for mismatches at j(th) character in
                // pattern such that we can act as if we have always been in this state, i.e.,
                // do whatever the failure state does. Note that x must be less than j, since x
                // is a previous state
                dfa[c][j] = dfa[c][x];
            }

            // overwrite/update the state of match, for all c in R, there can only be one
            // match, and that must be pat.charAt(j), at j(th) in pattern. j + 1, because
            // when jth char is matched, we are at j+1 state. E.g., pattern is of length 5,
            // when we match the 4th char, we should be at state 5.
            dfa[pat.charAt(j)][j] = j + 1;

            // update restart state x, this can be imagined as a LPS Longest Prefix String
            // for current char j
            x = dfa[pat.charAt(j)][x];
            // System.out.println("x: " + x);
        }
        // for (int i = 'a'; i < 'z'; i++) {
        // System.out.println((char) i + " " + Arrays.toString(dfa[i]));
        // }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M)
            return i - M;
        else
            return M;
    }

    public int match(String txt) {
        int j = 0;

        for (int i = 0; i < txt.length(); i++) {
            while (j > 0 && pat.charAt(j) != txt.charAt(i)) {
                j = lps[j - 1];
            }
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
            }
            if (j == pat.length()) {
                return i - pat.length() + 1;
            }
        }
        return -1;
    }

    // compute suffix of previous longest prefix
    // ababc, if we fail at c, lps[4] = 2, because 2th is next character of previous "ab"
    private void longestPrefixSuffix() {
        int len = 0;
        for (int i = 1; i < pat.length(); i++) {
            while (len > 0 && pat.charAt(len) != pat.charAt(i)) {
                len = lps[len - 1];
            }
            if (pat.charAt(len) == pat.charAt(i)) {
                len++;
            }
            lps[i] = len;
        }
    }

}