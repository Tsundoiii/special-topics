package exam;

public class TwitterSplitter {

    public static void main(String[] args) {
        String tweet = "#photography#pets#funny#cs161isamazing#tbt";

        /*
         * Write a code segment that separates the following
         * Twitter hashtags String into individual hashtags.
         * Print out each hashtag to the standard output,
         * one per line.
         */

        for (String hashtag : tweet.split("#")) {
            System.out.println(hashtag);
        }
    }


}
