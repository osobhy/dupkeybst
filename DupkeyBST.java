// Adapted from "Problem Solving with Algorithms and Data Structures using
// Java", by Miller, Ranum, Yasinovskyy, and Eisenberg
import java.util.ArrayList;

public class DupkeyBST<K extends Comparable<K>,
                                        V extends Comparable<V>> {
    // setting up instance variables for TreeNode class

    class TreeNode {
        private K key;
        private V value;
        private TreeNode leftChild;
        private TreeNode rightChild;
        // creating a TreeNode object
        TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }

        /* Is this a leaf node? (Leaf nodes have no children) */
        boolean isLeaf() {
            return (leftChild == null && rightChild == null);
        }
    }
    // setting up instance variables for DupkeyBST class
    private TreeNode root;
    private int size;
    // setting their initial state
    public DupkeyBST() {
        this.root = null;
        size = 0;
    }
    // finding the number of items, aka the size, of the tree
    public int size() {
        return size;
    }
    // finding the item at the very top of the tree

    public TreeNode getRoot() {
        return root;
    }

    // putting items in the tree by checking for the tree's root. this function works 
    // recursively and is extended below

    public void put(K key, V value) {
        if (this.root != null) {
            put(key, value, this.root);
        } else {
            this.root = new TreeNode(key, value);
        }
        this.size = this.size + 1;
    }

    // the extension of the put function, only called when this.root does not equal null. the root could represent one that of a subtree.
    // it essentially adds items  to the tree when a root exists
    void put(K key, V value, TreeNode currentNode) {
        if (key.compareTo(currentNode.key) < 0) {
            if (currentNode.leftChild != null) {
                put(key, value, currentNode.leftChild);
            } else {
                currentNode.leftChild = new TreeNode(key, value);
            }
        } else {
            if (currentNode.rightChild != null) {
                put(key, value, currentNode.rightChild);
            } else {
                currentNode.rightChild = new TreeNode(key, value);
            }
        }
    }
    // returns the specific value in a form of an arraylist of a key when called, traverses
    // its way down from the root and is extended below 
    public ArrayList<V> get(K key) {
        ArrayList<V> values = new ArrayList<>();
        get(key, this.root, values);
        return values;
    }
    
    // the extension of the get method. It continues the tree traversion by cutting off the top 
    // of the tree and traversing through another subtree 
    void get(K key, TreeNode currentNode, ArrayList<V> values) {
        if (currentNode == null) {
            return;
        }
        if (key.compareTo(currentNode.key) == 0) {
            values.add(currentNode.value);
        }
        get(key, currentNode.leftChild, values);
        get(key, currentNode.rightChild, values);
    }
    // returns True if tree constains a specific key
    public boolean containsKey(K key) {
        if (get(key).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    // turns the root to a string to be later utilized in printing out
    // the tree in a nice way
    public String toString() {
        return stringify(this.root, 0);
    }

    // I modified this from the Runestone version, I think it's easier to read
    String stringify(TreeNode node, int indent) {
        String result = "";
        if (node != null) {
            if (node.isLeaf()) {
                result = "  ".repeat(indent) +  node.key + ":" + node.value + "\n";
            } else {
                result = "  ".repeat(indent) +  node.key + ":" + node.value + "\n"
                    + stringify(node.leftChild, indent+1)
                    + stringify(node.rightChild, indent+1);
            }
        } else {
            result = "  ".repeat(indent) + "[]\n";
        }
        return result;
    }
}
