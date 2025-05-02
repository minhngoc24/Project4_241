/**
 * Name: Minh Ngoc Le
 * Class : COMP241
 * Description : Implement own Map type using a binary search tree, and make a sentiment analysis
 * Pledge : I have neither given nor received unauthorized aid on this program.
 */
import java.io.InputStream;
import java.util.*;

public class SentimentAnalysis {
    public static void main(String[] args)
    {
        final boolean PRINT_TREES = false;  // whether or not to print extra info about the maps.

        BSTMap<String, Integer> wordFreqs = new BSTMap<String, Integer>();
        BSTMap<String, Integer> wordTotalScores = new BSTMap<String, Integer>();
        Set<String> stopwords = new TreeSet<String>();

        System.out.print("Enter filename: ");
        Scanner scan = new Scanner(System.in);
        String filename = scan.nextLine();

        processFile(filename, wordFreqs, wordTotalScores);

        System.out.println("Number of words is: " + wordFreqs.size());
        System.out.println("Height of the tree is: " + wordFreqs.height());

        if (PRINT_TREES)
        {
            System.out.println("Preorder:  " + wordFreqs.preorderKeys());
            System.out.println("Inorder:   " + wordFreqs.inorderKeys());
            System.out.println("Postorder: " + wordFreqs.postorderKeys());
            printFreqsAndScores(wordFreqs, wordTotalScores);
        }

        removeStopWords(wordFreqs, wordTotalScores, stopwords);

        System.out.println("After removing stopwords:");
        System.out.println("Number of words is: " + wordFreqs.size());
        System.out.println("Height of the tree is: " + wordFreqs.height());

        if (PRINT_TREES)
        {
            System.out.println("Preorder:  " + wordFreqs.preorderKeys());
            System.out.println("Inorder:   " + wordFreqs.inorderKeys());
            System.out.println("Postorder: " + wordFreqs.postorderKeys());
            printFreqsAndScores(wordFreqs, wordTotalScores);
        }

        while (true)
        {
            System.out.print("\nEnter a new review to analyze: ");
            String line = scan.nextLine();
            if (line.equals("quit")) break;

            String[] words = line.split(" ");

            // Your code here:
            // The words[] array holds the new movie review to analyze.
            // Get the average sentiment for each word (skipping stop words),
            double sum = 0;
            int count = 0;
            // then calculate the average of those sentiments.  This "average of averages"
            for(int i = 0; i < words.length; i++){
                String word = words[i];
                word = word.toLowerCase();
                // Ignore stop words
                if(stopwords.contains(word)) {
                    System.out.println("Stopword: " + word);
                    continue;
                }
                if(!wordFreqs.containsKey(word)) {
                    System.out.println("Never seen before: " + word);
                    continue;
                }

                // Calculate the avg sentiment of the word
                int freq = wordFreqs.get(word);
                int total = wordTotalScores.get(word);
                double avgScore = (double)total/freq;
                System.out.println("The average sentiment of " + word + " is: " + avgScore);

                sum += avgScore;
                count++;

            }
            ;
            // becomes your overall sentiment analysis score.
            if(count > 0){
                double avgSentiment = sum/count;
                System.out.println("Sentiment score for this review is: " + avgSentiment);
            }
            else{
                System.out.println("No sentiment");
            }
            
        }
    }

    /**
     * Read the file specified to add proper items to the word frequencies and word scores maps.
     */
    private static void processFile(String filename,
                                    BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores)
    {
        InputStream is = SentimentAnalysis.class.getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Bad filename: " + filename);
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(" ");

            // Your code here.
            // You have an array of words.  The first word in the array is the score.
            int score = Integer.parseInt(words[0]);
            // Process all the remaining words to add them to the wordFreqs and wordTotalScores maps
            // in an appropriate way.
            for(int i = 1; i < words.length; i++){
                String word = words[i].toLowerCase(); // Making sure it does not affect by the capital letters. For ex,"The" and "the" should be the same
                // Check and update wordFreq map:
                if(wordFreqs.containsKey(word)){
                    wordFreqs.put(word, wordFreqs.get(word) + 1);
                }
                else{
                    wordFreqs.put(word, 1);
                }
                if(wordTotalScores.containsKey(word)){
                    wordTotalScores.put(word, wordTotalScores.get(word) + score);
                }
                else{
                    wordTotalScores.put(word, score);
                }
            }
        }
        scan.close();
    }

    /**
     * Print a table of the words found in the movie reviews, along with their frequencies and total scores.
     * Hint: Call wordFreqs.inorderKeys() to get a list of the words, and then loop over that list.
     */
    private static void printFreqsAndScores(BSTMap<String, Integer> wordFreqs, BSTMap<String, Integer> wordTotalScores)
    {
        List<String> words = wordFreqs.inorderKeys(); // The order of the map
        //Prints a table of all the words in the two maps, along with their frequencies and total scores.
        for(int i = 1; i < words.size(); i++){
            String word = words.get(i);
            int frequency = wordFreqs.get(word); // get the frequencies from wordFreqs map
            int totalScore = wordTotalScores.get(word); // get the total scores from wordTotalScores map
            System.out.println("Word: " + word + " Frequency: " + frequency + " Total Score: " + totalScore);
        }
    }

    /**
     * Read the stopwords.txt file and add each word to the stopwords set.  Also remove each word from the
     * word frequencies and word scores maps.
     */
    private static void removeStopWords(BSTMap<String, Integer> wordFreqs,
                                        BSTMap<String, Integer> wordTotalScores, Set<String> stopwords)
    {
        InputStream is = SentimentAnalysis.class.getResourceAsStream("stopwords.txt");
        if (is == null) {
            System.err.println("Bad filename: " + "stopwords.txt");
            System.exit(1);
        }
        Scanner scan = new Scanner(is);

        while (scan.hasNextLine()) {
            String word = scan.nextLine();

            // Your code here.
            // You should add the word to the stopwords set, and also remove it from the
            // two maps.

            stopwords.add(word);
            wordFreqs.remove(word);
            wordTotalScores.remove(word);
        }
        scan.close();
    }
}
