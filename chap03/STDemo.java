/**
 * @author yongjie.zhuang
 */
public class STDemo {

    public static void main(String[] args) {
        betterDemo(new SequentialSearchST<>());
        betterDemo(new BinarySearchST<>());
        betterDemo(new LinearProbingHashST<>());
        betterDemo(new SeparateChainingHashST<>());
        betterDemo(new BinarySearchTree<>());
    }

    private static void betterDemo(SymbolTable<String, String> st) {
        System.out.println("Demo: >>>> " + st.getClass().getName() + "\n");

        st.put("Curtis", "newbie");
        System.out.println(st.toString());
        st.put("Banana", "mie");
        System.out.println(st.toString());
        st.put("Doggy", "dog");
        System.out.println(st.toString());
        st.put("Apple", "juice");
        System.out.println(st.toString());
        st.put("France", "Ecnarf");
        System.out.println(st.toString());
        st.put("Good", "Dog");
        System.out.println(st.toString());
        System.out.println("Size: " + st.size());

        System.out.println("\nLook for Banana: " + st.get("Banana"));
        System.out.println("Look for Apple: " + st.get("Apple"));

        System.out.println("\nSize: " + st.size());
        System.out.println("Delete France");
        st.delete("France");
        System.out.println(st.toString());
        System.out.println("Size: " + st.size());

        if (st instanceof BinarySearchTree) {
            BinarySearchTree bst = BinarySearchTree.class.cast(st);
            System.out.println("Min: " + bst.min());
            System.out.println("Max: " + bst.max());
            System.out.println("\nSize: " + bst.size());
            System.out.println("Delete Min: " + bst.min());
            bst.delMin();
            System.out.println(st.toString());
            System.out.println("Size: " + st.size());
            System.out.println("Keys: " + bst.keys().toString());
            System.out.println("Keys From Apple to Doggy: " + ((BinarySearchTree) st).keys("Aapple", "Doggy").toString());
        }
        System.out.println(st.toString());
        System.out.println("-------------------------------\n");
    }

}

