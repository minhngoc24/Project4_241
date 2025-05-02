/**
 * Name: Minh Ngoc Le
 * Class : COMP241
 * Description : Implement own Map type using a binary search tree, and make a sentiment analysis
 * Pledge : I have neither given nor received unauthorized aid on this program.

 */
import java.util.ArrayList;
import java.util.List;

/**
 * A map implemented with a binary search tree.
 */
public class BSTMap<K extends Comparable<K>, V> {

    private Node<K, V> root;    // points to the root of the BST.

    /**
     * Create a new, empty BST.
     */
    public BSTMap() {
        root = null;
    }

    /**
     * Put (add a key-value pair) into this BST.  If the key already exists, the old
     * value will be overwritten with the new one.
     */
    public void put(K newKey, V newValue) {
        if (root == null) {
            Node<K, V> newNode = new Node<>();
            //newNode.key = newKey;
            root = new Node<>();
            root.key = newKey;
            root.value = newValue;
        }
            //System.out.println("Root1");
        else{
                put(root, newKey, newValue);
                //System.out.println("Root2");

            }
            // System.out.println("Put to root");

        }

        /**
         * Helper function for put.
         */
        private void put (Node < K, V > curr, K newKey, V newValue)
        {
            // If the newKey is smaller than current, then put it to the left
            if (newKey.compareTo(curr.key) < 0) {
                if (curr.left == null) {
                    Node<K, V> newNode = new Node<>();
                    newNode.key = newKey;
                    newNode.value = newValue;
                    curr.left = newNode;
                } else {
                    put(curr.left, newKey, newValue);
                }
                //System.out.println("Put to the left");
            }

            // If the newKey is bigger than current, then put it to the right
            else if (newKey.compareTo(curr.key) > 0) {
                if (curr.right == null) {
                    Node<K, V> newNode = new Node<>();
                    newNode.key = newKey;
                    newNode.value = newValue;
                    curr.right = newNode;
                } else {
                    put(curr.right, newKey, newValue);
                }
                //System.out.println("Put to the right");
            }
            // If newKey is already exists then just overwrite it with the new Value.
            else if (newKey.compareTo(curr.key) == 0) {
                curr.value = newValue;
            }
        }

        /**
         * Get a value from this BST, based on its key.  If the key doesn't already exist in the BST,
         * this method returns null.
         */
        public V get (K searchKey)
        {
            return get(root, searchKey);
        }

        /**
         * Helper function for put.
         */
        public V get (Node < K, V > curr, K searchKey){
            if (curr == null) {
                return null;
            } else if (searchKey.compareTo(curr.key) == 0) {
                return curr.value;
            } else if (searchKey.compareTo(curr.key) < 0) {
                return get(curr.left, searchKey);
            } else {
                return get(curr.right, searchKey);
            }
        }

        /**
         * Test if a key is present in this BST.  Returns true if the key is found, false if not.
         */
        public boolean containsKey (K searchKey)
        {
            return containsKey(root, searchKey);
        }

        /**
         * Helper function for put.
         */
        private boolean containsKey (Node < K, V > curr, K searchKey){
            // If curr is null then not found
            if (curr == null) {
                return false;
            } else if (searchKey.compareTo(curr.key) == 0) {
                return true; // key found
            } else if (searchKey.compareTo(curr.key) < 0) {
                return containsKey(curr.left, searchKey); // search key too small -> go left
            } else {
                return containsKey(curr.right, searchKey); // search key too big -> go right
            }
        }

        /**
         * Given a key, remove the corresponding key-value pair from this BST.  Returns true
         * for a successful deletion, or false if the key wasn't found in the tree.
         */
        public boolean remove (K removeKey)
        {
            Node<K, V> curr = root;
            Node<K, V> parent = null;
            while (curr != null && curr.key.compareTo(removeKey) != 0) {

                // Descend through the tree, looking for the node that contains the removeKey.
                // Stop when find it, or when encounter a null pointer
                parent = curr;
                if (removeKey.compareTo(curr.key) < 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }

            if (curr == null) {
                return false; // The removeKey was not found
            }

            // Handle the 2-child situation
            if (curr.left != null && curr.right != null) {

                // Find the inorder successor of curr
                Node<K, V> successor = curr.right;
                Node<K, V> parentSuccessor = curr;
                while (successor.left != null) {
                    parentSuccessor = successor;
                    successor = successor.left;
                }

                // Copy the inorder successor's key into curr
                curr.key = successor.key;
                curr.value = successor.value;

                // Delete the successor node, which guaranteed to have 0 or 1 child
                curr = successor;
                parent = parentSuccessor;
            }

            // Handle 0 or 1 child situation
            Node<K, V> subtree;

            if (curr.left == null && curr.right == null) { // 0 children
                subtree = null;
            } else if (curr.left != null) {
                subtree = curr.left;
            } else {
                subtree = curr.right;
            }

            if (parent == null) {
                root = subtree;
            } else if (parent.left == curr) {
                parent.left = subtree;
            } else {
                parent.right = subtree;
            }

            return true;
        }

        /**
         * Return the number of key-value pairs in this BST.
         */
        public int size ()
        {
            return size(root);
        }

        private int size (Node < K, V > curr){
            if (curr == null) {
                return 0;
            }
            return size(curr.left) + size(curr.right) + 1;
        }

        /**
         * Return the height of this BST.
         */
        public int height ()
        {
            return height(root);  // remove this.
            // Your code here.
        }
        private int height (Node < K, V > curr){
            if (curr == null) {
                return -1;
            }

            int leftHeight = height(curr.left);
            int rightHeight = height(curr.right);
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }

        /**
         * Return a List of the keys in this BST, ordered by a preorder traversal.
         */
        public List<K> preorderKeys ()
        {
            List<K> preorderKeys = new ArrayList<>();
            preorder(root, preorderKeys);
            return preorderKeys;
        }

        private void preorder (Node < K, V > curr, List < K > keys ){
            if (curr == null) {
                return;
            }
            keys.add(curr.key);
            preorder(curr.left, keys);
            preorder(curr.right, keys);
        }

        /**
         * Return a List of the keys in this BST, ordered by a inorder traversal.
         */
        public List<K> inorderKeys ()
        {
            List<K> inorderKeys = new ArrayList<>();
            inorder(root, inorderKeys);
            return inorderKeys;
        }
        private void inorder (Node < K, V > curr, List < K > keys ){
            if (curr == null) {
                return;
            }
            inorder(curr.left, keys);
            keys.add(curr.key);
            inorder(curr.right, keys);
        }

        /**
         * Return a List of the keys in this BST, ordered by a postorder traversal.
         */
        public List<K> postorderKeys ()
        {
            List<K> postorderKeys = new ArrayList<>();
            postorder(root, postorderKeys);
            return postorderKeys;
        }

        private void postorder (Node < K, V > curr, List < K > keys ){
            if (curr == null) {
                return;
            }
            postorder(curr.left, keys);
            postorder(curr.right, keys);
            keys.add(curr.key);
        }


        /**
         * It is very common to have private classes nested inside other classes.  This is most commonly used when
         * the nested class has no meaning apart from being a helper class or utility class for the outside class.
         * In this case, this Node class has no meaning outside of this BSTMap class, so we nest it inside here
         * so as to not prevent another class from declaring a Node class as well.
         *
         * Note that even though the members of node are public, because the class itself is private
         */
        private static class Node<K extends Comparable<K>, V> {
            public K key = null;
            public V value = null;
            public Node<K, V> left = null;     // you may initialize member variables of a class when they are defined;
            public Node<K, V> right = null;    // this behaves as if they were initialized in a constructor.
        }

    }
