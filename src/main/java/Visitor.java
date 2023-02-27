import java.util.*;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int totalLeavesSum;

    public SumInLeavesVisitor()
    {
        this.totalLeavesSum = 0;
    }

    public int getResult() {
        //implement this
        return totalLeavesSum;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        totalLeavesSum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private int product;

    public ProductOfRedNodesVisitor()
    {
        this.product = 1;
    }

    public int getResult() {
        //implement this
        return product;
    }

    public void visitNode(TreeNode node) {
        if(node.getColor() == Color.RED)
        {
            this.product *= node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED)
        {
            this.product *= leaf.getValue();
        }
    }
}

class FancyVisitor extends TreeVis {

    private int sumOfEvenDepth, sumOfGreenLeafs;
    public FancyVisitor()
    {
        sumOfEvenDepth = 0;
        sumOfGreenLeafs = 0;
    }
    public int getResult() {
        //implement this
        return Math.abs(sumOfEvenDepth - sumOfGreenLeafs);
    }

    public void visitNode(TreeNode node) {
        if(node.getDepth() == 0)
        {
            sumOfEvenDepth += node.getValue();
        }
        else if(node.getDepth() % 2 == 0)
        {
            sumOfEvenDepth += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.GREEN)
        {
            sumOfGreenLeafs += leaf.getValue();
        }
    }
}

public class Visitor {

    private static List<Integer> nodeValueList = new ArrayList<>();
    private static List<Color> nodeColorList = new ArrayList<>();
    private static Map<Integer, List<Integer>> treeConnections = new LinkedHashMap<>();
    public static Tree solve() {
        //read the tree from STDIN and return its root as a return value of this function
        Scanner scanner = new Scanner(System.in);

        int numNodes = Integer.parseInt(scanner.nextLine());


        String[] nodeValaues = scanner.nextLine().replaceAll("\\s+$", "").split(" ");

        for (int i = 0; i < nodeValaues.length; i++) {
            int sItem = Integer.parseInt(nodeValaues[i]);
            nodeValueList.add(sItem);
        }


        String[] nodeColors = scanner.nextLine().replaceAll("\\s+$", "").split(" ");
        for (int i = 0; i < nodeColors.length; i++) {
            Color color = Integer.parseInt(nodeColors[i]) == 0 ? Color.RED : Color.GREEN;
            nodeColorList.add(color);
        }



        for(int i=1; i<=numNodes-1;i++)
        {
            String[] connections = scanner.nextLine().replaceAll("\\s+$", "").split(" ");
            if(!treeConnections.containsKey(Integer.parseInt(connections[0])))
            {
                treeConnections.put(Integer.parseInt(connections[0]), new ArrayList<Integer>());
            }
            List<Integer> childs = treeConnections.get(Integer.parseInt(connections[0]));
            childs.add(Integer.parseInt(connections[1]));

        }
        scanner.close();
        int rootIndex = 1;
        Tree root = createRootNode(rootIndex);
        processChildren(root, treeConnections.get(rootIndex));


        return root;
    }

    public static Tree createRootNode(int index)
    {
        Tree rootNode = new TreeNode(nodeValueList.get(index-1), nodeColorList.get(index-1), index-1);
        return rootNode;
    }

    public static void processChildren(Tree root, List<Integer> children)
    {
        for(int child : children)
        {
            if(treeConnections.containsKey(child))
            {
                Tree node = new TreeNode(nodeValueList.get(child-1), nodeColorList.get(child-1), root.getDepth()+1);
                ((TreeNode) root).addChild(node);

                processChildren(node, treeConnections.get(child));
            }
            else
            {
                Tree leaf = new TreeLeaf(nodeValueList.get(child-1), nodeColorList.get(child-1), root.getDepth()+1);
                ((TreeNode) root).addChild(leaf);
            }
        }
    }


    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}