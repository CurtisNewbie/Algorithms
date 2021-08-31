import java.awt.print.Book;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author yongjie.zhuang
 */
public class BtreeTest {

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        Btree<String, String> st = new Btree<>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu", "128.112.128.15");
        st.put("www.yale.edu", "130.132.143.21");
        st.put("www.simpsons.com", "209.052.165.60");
        st.put("www.apple.com", "17.112.152.32");
        st.put("www.amazon.com", "207.171.182.16");
        st.put("www.ebay.com", "66.135.192.87");
        st.put("www.cnn.com", "64.236.16.20");
        st.put("www.google.com", "216.239.41.99");
        st.put("www.nytimes.com", "199.239.136.200");
        st.put("www.microsoft.com", "207.126.99.140");
        st.put("www.dell.com", "143.166.224.230");
        st.put("www.slashdot.org", "66.35.250.151");
        st.put("www.espn.com", "199.181.135.201");
        st.put("www.weather.com", "63.111.66.11");
        st.put("www.yahoo.com", "216.109.118.65");


        assertEquals(st.get("www.simpsons.com"), "209.052.165.60");
        assertEquals(st.get("www.apple.com"), "17.112.152.32");
        assertEquals(st.get("www.amazon.com"), "207.171.182.16");
        assertEquals(st.get("www.ebay.com"), "66.135.192.87");
        assertEquals(st.get("www.cnn.com"), "64.236.16.20");
        assertEquals(st.get("www.google.com"), "216.239.41.99");
        assertEquals(st.get("www.nytimes.com"), "199.239.136.200");
        assertEquals(st.get("www.yahoo.com"), "216.109.118.65");
        assertEquals(st.get("www.weather.com"), "63.111.66.11");
        assertEquals(st.get("www.dell.com"), "143.166.224.230");
        assertEquals(st.get("www.slashdot.org"), "66.35.250.151");
        assertEquals(st.get("www.espn.com"), "199.181.135.201");

    }

    public static void assertEquals(Object actual, Object expected) {
        printTest(Objects.equals(actual, expected) == true, actual, expected);
    }

    public static void printTest(boolean isTestPassed, Object actual, Object expected) {
        System.out.printf("Test: '%s' actual: %s - expected: %s\n", isTestPassed ? "pass" : "fail", actual, expected);
    }
}

