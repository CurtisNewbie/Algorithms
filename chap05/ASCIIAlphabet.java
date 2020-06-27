public class ASCIIAlphabet implements Alphabet {

    private final int R = 256;
    private final int BITS = 8;

    @Override
    public char toChar(int index) {
        return (char) index;
    }

    @Override
    public int toIndex(char c) {
        return c;
    }

    @Override
    public boolean contains(char c) {
        return c >= 0 && c < R();
    }

    @Override
    public int R() {
        return R;
    }

    @Override
    public int lgR() {
        return BITS;
    }

    @Override
    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            indices[i] = (int) s.charAt(i);
        }
        return indices;
    }

    @Override
    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.length; i++) {
            sb.append((char) indices[i]);
        }
        return sb.toString();
    }
}