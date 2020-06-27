// Represents a charset, e.g., ASCII
public interface Alphabet {
    char toChar(int index);

    int toIndex(char c);

    boolean contains(char c);

    // Number of characters in charset, e.g., 256 for extended ASCII
    int R();

    // Number of bits use for each character
    int lgR();

    int[] toIndices(String s);

    String toChars(int[] indices);
}