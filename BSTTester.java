/**
 * Name: Minh Ngoc Le
 * Class : COMP241
 * Description : Implement own Map type using a binary search tree, and make a sentiment analysis
 * Pledge : I have neither given nor received unauthorized aid on this program.
 */
import java.util.ArrayList;
import java.util.List;

public class BSTTester {
    public static void main(String[] args)
    {
        testPutAndGet1();
        //testPutAndGet2();
        //testTraversals1();
        //testTraversals2();
        //testDeeper();
        //testContainsRemove();
        //testDup();

        // Add more testing code to test things like
        // - traversals/size/height for deeper or more complex trees
        // - containsKey, remove
        // - putting a duplicate key value (should replace the old value with the new one)
    }

    // One put and one get per map (tree won't be deeper than the root).
    private static void testPutAndGet1()
    {
        System.out.println("Testing put/get 1");
        // Simulate a bank account map.
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        System.out.println("Get: " + accounts.get(123)); // get the balance for account 123

        // Simulate an address book.
        BSTMap<String, String> emails = new BSTMap<String, String>();
        emails.put("Elvis Presley", "elvis@graceland.com");
        System.out.println("Get: " + emails.get("Elvis Presley")); // get Elvis's email address

    }

    // Multiple puts and gets, testing making deeper trees.
    private static void testPutAndGet2()
    {
        System.out.println("Testing put/get 2");
        BSTMap<Integer, Double> accounts = new BSTMap<Integer, Double>();
        accounts.put(123, 50.23);
        accounts.put(456, 100.81);
        accounts.put(78, 72.11);
        System.out.println("Get: " + accounts.get(123));
        System.out.println("Get: " + accounts.get(456));
        System.out.println("Get: " + accounts.get(78));

        BSTMap<String, String> emails = new BSTMap<String, String>();
        emails.put("Elvis Presley", "elvis@graceland.com");
        emails.put("Albus Dumbledore", "dumbledore@hogwarts.edu");
        emails.put("Dorothy Gale", "dorothy@oz.org");
        emails.put("Grace Hopper", "hopper@navy.mil");  // look her up!
        System.out.println("Get: " + emails.get("Elvis Presley"));
        System.out.println("Get: " + emails.get("Albus Dumbledore"));
        System.out.println("Get: " + emails.get("Dorothy Gale"));
        System.out.println("Get: " + emails.get("Grace Hopper"));

        // Test delete function
        System.out.println("Testing deletion");
        System.out.println("Removing 'Dorothy Gale': " + emails.remove("Dorothy Gale"));
        System.out.println("Get: " + emails.get("Dorothy Gale"));

        // Delete with 1 child
        System.out.println("Removing 456: " + accounts.remove(456));
        System.out.println("Get: " + accounts.get(456));

        System.out.println("Removing 'Elvis Presley': " + emails.remove("Elvis Presley"));
        System.out.println("Get: " + emails.get("Elvis Presley"));

        System.out.println("Get: " + emails.get("Albus Dumbledore"));
        System.out.println("Get: " + accounts.get(123));

    }

    private static void testTraversals1()
    {
        System.out.println("Testing traversals 1");
        BSTMap<Integer, Integer> map = createBst(List.of(4, 2, 6, 1, 3, 5, 7));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    private static void testTraversals2()
    {
        System.out.println("Testing traversals 2");
        BSTMap<Integer, Integer> map = createBst(List.of(1, 2, 3, 4, 5, 6, 7));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    // Test deeper trees
    private static void testDeeper(){
        System.out.println("Testing deeper trees");
        BSTMap<Integer, Integer> map = createBst(List.of(10, 5, 15, 3, 7, 12, 18, 2, 4, 6, 9, 11, 13, 16, 35));
        System.out.println("Preorder:  " + map.preorderKeys());
        System.out.println("Inorder:   " + map.inorderKeys());
        System.out.println("Postorder: " + map.postorderKeys());
        System.out.println("size=" + map.size() + " height=" + map.height());
    }

    private static void testContainsRemove(){
        System.out.println("Testing contains");
        BSTMap<Integer, String> map = new BSTMap<>();
        map.put(1, "HAHAHA");
        map.put(2, "HIHIHI");
        map.put(3, "HOHOHO");
        map.put(7, "OOPSIE");


        System.out.println("Contains 1: " + map.containsKey(1));
        System.out.println("Contains 2: " + map.containsKey(2));
        System.out.println("Contains 3: " + map.containsKey(3));
        System.out.println("Contains 4: " + map.containsKey(4));
        System.out.println("Contains 5: " + map.containsKey(5));

        System.out.println("Testing remove");
        System.out.println("Remove 1: " + map.remove(1));
        System.out.println("Remove 2: " + map.remove(2));
        System.out.println("Contains 1 after remove: " + map.containsKey(1));
        System.out.println("Contains 3: " + map.containsKey(3));

    }

    private static void testDup(){
        System.out.println("Testing Duplicate");
        BSTMap<Integer, String> map = new BSTMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        System.out.println("Get 1: " + map.get(1));
        map.put(1, "L");
        System.out.println("Get 1: " + map.get(1));


    }


    /**
     * Helper method to create a BST map from the numbers in a list.  It just uses zero
     * for every value, since this is mostly used to test the traversal methods.
     */
    private static BSTMap<Integer, Integer> createBst(List<Integer> list)
    {
        BSTMap<Integer, Integer> map = new BSTMap<>();
        for (int x = 0; x < list.size(); x++)
        {
            map.put(list.get(x), 0);
        }
        return map;
    }
}
