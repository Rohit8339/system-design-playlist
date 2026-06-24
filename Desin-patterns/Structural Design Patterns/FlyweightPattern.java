import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// =====================================================
// PROBLEM CODE (WITHOUT FLYWEIGHT PATTERN)
// =====================================================

/*
Problem:

Suppose we have 100,000 Oak trees.

Each Tree object stores:

1. x
2. y
3. name
4. color
5. texture

The problem is that name, color and texture
are the same for all Oak trees.

So we are storing duplicate data
100,000 times which wastes memory.
*/

class Tree {

    // Unique data (changes for every tree)
    private int x;
    private int y;

    // Common data (same for many trees)
    private String name;
    private String color;
    private String texture;

    Tree(int x, int y, String name, String color, String texture) {

        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw() {

        System.out.println("Drawing Tree at (" + x + ", " + y + ") " + name);
    }
}

class Forest {

    private List<Tree> trees = new ArrayList<>();

    public void addTree(int x, int y, String name, String color, String texture) {

        trees.add(new Tree(x, y, name, color, texture));
    }
}

/*
 * Forest forest = new Forest();
 * 
 * for(int i = 0; i < 100000; i++){
 * 
 * forest.addTree(
 * i,
 * i,
 * "Oak",
 * "Green",
 * "Rough");
 * }
 * 
 * Problem:
 * 
 * 100,000 Tree objects
 * 
 * Each object stores:
 * 
 * Oak
 * Green
 * Rough
 * 
 * again and again.
 * 
 * Huge memory waste.
 */

// =====================================================
// SOLUTION CODE (FLYWEIGHT PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Separate shared data from unique data.
 * 
 * Shared Data
 * ------------
 * name
 * color
 * texture
 * 
 * Unique Data
 * -----------
 * x
 * y
 * 
 * Store shared data only once and reuse it.
 */

// Flyweight Object
class TreeType {

    private String name;
    private String color;
    private String texture;

    TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {

        System.out.println("Drawing Tree at (" + x + ", " + y + ") " + name);
    }
}

// Tree stores only unique state
class FlyweightTree {

    private int x;
    private int y;

    // Shared object reference
    private TreeType treeType;

    FlyweightTree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void draw() {
        treeType.draw(x, y);
    }
}

// Factory creates and reuses TreeType objects
class TreeFactory {

    private static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(
            String name,
            String color,
            String texture) {

        String key = name + "_" +
                color + "_" +
                texture;

        if (!treeTypes.containsKey(key)) {

            treeTypes.put(
                    key,
                    new TreeType(
                            name,
                            color,
                            texture));
        }

        return treeTypes.get(key);
    }
}

class FlyweightForest {

    private List<FlyweightTree> trees = new ArrayList<>();

    public void addTree(int x,
            int y,
            String name,
            String color,
            String texture) {

        // Get shared TreeType object
        TreeType treeType = TreeFactory.getTreeType(
                name,
                color,
                texture);

        trees.add(
                new FlyweightTree(
                        x,
                        y,
                        treeType));
    }

    public void draw() {

        for (FlyweightTree tree : trees) {
            tree.draw();
        }
    }
}

public class FlyweightPattern {

    public static void main(String[] args) {

        FlyweightForest forest = new FlyweightForest();

        for (int i = 0; i < 100000; i++) {

            forest.addTree(
                    i,
                    i,
                    "Oak",
                    "Green",
                    "Rough");
        }

        System.out.println(
                "100000 Trees Created");
    }
}

/*
 * CLASS DIAGRAM
 * 
 * 
 * TreeType
 * (Shared Flyweight)
 * -----------------
 * name
 * color
 * texture
 * ^
 * |
 * |
 * FlyweightTree
 * -------------
 * x
 * y
 * TreeType
 * 
 * 
 * TreeFactory
 * |
 * |
 * Reuses TreeType
 * 
 * 
 * FLOW
 * 
 * WITHOUT FLYWEIGHT
 * 
 * Tree
 * |
 * +--> x
 * +--> y
 * +--> Oak
 * +--> Green
 * +--> Rough
 * 
 * 100,000 times
 * 
 * 
 * WITH FLYWEIGHT
 * 
 * TreeType
 * |
 * +--> Oak
 * +--> Green
 * +--> Rough
 * 
 * Only ONE object
 * 
 * Tree
 * |
 * +--> x
 * +--> y
 * +--> TreeType reference
 * 
 * 
 * BENEFITS
 * 
 * 1. Reduces memory usage
 * 2. Avoids duplicate data
 * 3. Reuses shared objects
 * 4. Improves performance
 * 5. Useful when creating large numbers of objects
 */