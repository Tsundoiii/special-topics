package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class Alliteration {
	
	/**
	 * Determines if the words provided all have the
	 * same first letter (therefore use alliteration)
	 * Tip: Remember a String has the .startsWith() method
	 * @param words An ArrayList of Strings
	 * @param fLetter The letter to match against the first letter of each word
	 * @return True - is alliteration, False - is not alliteration
	 */
	public static boolean isAlliteration(ArrayList<String> words, String fLetter) {
		//Base case (Cannot change the next 3 lines)
		if (words.size() == 1) {
			return true;
		}
		//TODO Complete the rest of the method
		if (!words.get(words.size() - 1).substring(0, 1).equals(fLetter)) {
			return false;
		}
		words.remove(words.size() - 1);
		return isAlliteration(words, words.get(0).substring(0, 1));
	}
	
	//Do not submit main method
    //For testing only
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		words.addAll(Arrays.asList(
				"Complete", "CS161", 
				"Curriculum", "Courageously!"));
		
		System.out.println(isAlliteration(words, words.get(0).substring(0, 1)));
		
		words.clear();
		words.addAll(Arrays.asList(
				"Fixed", "Mindset", 
				"Hinders", "Capability"));
		
		System.out.println(isAlliteration(words, words.get(0).substring(0, 1)));
	}
}
