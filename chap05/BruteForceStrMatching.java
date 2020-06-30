/**
 * Brute force way to find string matches
 * <p>
 * Time Complexity: O(MN), where the M is the length of pattern, N is the length
 * of the text
 */
public class BruteForceStrMatching {

    public static void main(String[] args) {
        String pat = "aba";
        String text = "abcabbbabssbababbbbaabaa";
        int index = 0;
        System.out.println((index = search(pat, text)));
        System.out.println(text.substring(index, index + pat.length()));
    }

    /**
     * Return first index of {@code pat} in {@code txt} if found, else return -1
     * 
     * @param pat
     * @param txt
     * @return
     */
    public static int search(String pat, String txt) {
        int pLen = pat.length();
        int tLen = txt.length();
        char firstp = pat.charAt(0);
        for (int i = 0; i <= tLen - pLen; i++) {
            if (firstp == txt.charAt(i)) {
                int j;
                for (j = 1; j < pLen && pat.charAt(j) == txt.charAt(i + j); j++)
                    ;
                if (j == pLen)
                    return i;
            }
        }
        return -1;
    }
}