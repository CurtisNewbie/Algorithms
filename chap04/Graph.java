import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author yongjie.zhuang
 */
public interface Graph {

    /**
     * Return number of vertices
     */
    int vertices();

    /**
     * Return number of edges
     */
    int edges();

    /**
     * Add Edge between v-w. O(1)
     */
    void addEdge(int v, int w);

    /**
     * Return neighbours (adjacent vertices)
     */
    List<Integer> adjacent(int v);

    /**
     * Degree of a vertex
     */
    int degree(int v);
}
