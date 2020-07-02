/**
 * Rabin-Karp algorithm find string matches by calculating the hash, Hashing
 * function is:
 * <p>
 * Persudo (Horner Hashing Algorithm):
 * <p>
 * > long hash;
 * <p>
 * > For each c in pattern:
 * <p>
 * >> hash += (R * hash + c ) % Q;
 * <p>
 * R is the base number for the system, e.g., R = 256 for ASCII.
 * <p>
 * Q is the distribution of hashes, which is usually a very large prime number.
 * <p>
 * So, hash() is O(M) calculation, then when adopting such hashing function for
 * N char in text, the complexity will be O(MN). But the core feature of
 * Robin-Karp algorithm, is how it updates the hash without recalculating, that
 * takes O(M) time.
 * <p>
 * > char firstChar <- txt.charAt(...) // char to be removed
 * <p>
 * > char nextChar <- txt.charAt(...); // char to be added
 * <p>
 * > long hash <- prevHash;
 * <p>
 * >> hash = (hash + Q - (R ^ (M - 1)) * firstChar % Q) % Q;
 * <p>
 * >> hash = (hash * R + nextChar) % Q;
 * <p>
 * Time Complexity: O(N)
 */
public class RabinKarp {
    private String pat;
    private long patHash;
    private int M;
    private long Q;
    private int R = 256;
    private long RM; // R ^ (M-1) % Q

    public static void main(String[] args) {
        String txt = "FINDINAHAYSTACKNEEDLE";
        String pattern = "NEEDLE";
        RabinKarp rk = new RabinKarp(pattern);
        System.out.println("Pattern: " + pattern);
        System.out.println("Text: " + txt);
        int res;
        System.out.println("Search: " + (res = rk.search(txt)));
        System.out.println("Found: " + txt.substring(res));
    }

    public RabinKarp(String pat) {
        this.pat = pat;
        M = pat.length();
        Q = 997; // this should be random prime
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM % Q);
        }
        patHash = hash(pat, M);
    }

    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = (R * h + key.charAt(j)) % Q;
        }
        return h;
    }

    private int search(String txt) {
        int n = txt.length();
        long txtHash = hash(txt, M); // calculate the first time, and update it afterwards
        if (patHash == txtHash) {
            return 0;
        }
        for (int i = M; i < n; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                return i - M + 1;
        }
        return n;
    }
}