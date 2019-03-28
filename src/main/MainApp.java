package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import work.ChangeOneChar;
import work.DuplicatedCollection;
import work.SimilarCollection;
import work.SuperfluousCollection;
import work.AddNewChar;
import work.ChangeAllCollection;
import work.TreatFile;

public class MainApp {

	public static void main(String[] args) {

		new MainApp().launch();

	}

	private void launch() {

		Map<String, Integer> collection;
		TreatFile treatfile = new TreatFile();
		boolean flg = true;

		collection = treatfile.frequencyWords(treatfile.removeLetters(treatfile.readWords()));

		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter the word: ");

		while (!reader.hasNext("end")) {
			String n = reader.nextLine();
			n = n.toLowerCase().replaceAll("\\s","");
			ArrayList<String> wordsList = new ArrayList<String>();

			//add a letter
			wordsList.add(findMaxCollection(findclosestCollection(AddNewChar.setnewChar(collection, n), n), collection, n));

			//change a letter
			wordsList.add(findMaxCollection(findclosestCollection(ChangeOneChar.setchangeoneChar(collection, n), n), collection, n));

			//find more similar
			wordsList.add(findMaxCollection(findclosestCollection(SimilarCollection.findSimilarCollection(collection, n), n),
					collection, n));
			
			//change all collection
			wordsList.add(findChangingMaxCollection(findclosestCollection(ChangeAllCollection.findChangeCollection(collection, n), n),
					collection, n));

			//remove a letter
			wordsList.add(findMissingCorrectCollection(
					findclosestCollection(SuperfluousCollection.findSuperfluousCollection(collection, n, flg), n), collection, n));
			
			//find dupicate
			wordsList.add(findMaxCollection(findclosestCollection(DuplicatedCollection.findDuplicatedCollection(collection, n, flg), n),
					collection, n));

			// System.out.println(wordsList);
			doResult(collection, wordsList, n);

		}
		reader.close();

	}

	public String findMaxCollection(List<String> list, Map<String, Integer> collection, String word) {

		Map<String, Integer> newcollection = new HashMap<>();

		if (list.contains(word)) {
			return word;
		} else {
			for (String element : list) {

				newcollection.put(element, collection.get(element));
			}

			newcollection = miniDistance(newcollection, word);

			return Collections.max(newcollection.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
		}
	}

	public List<String> findclosestCollection(Map<String, Integer> collection, String word) {

		List<String> list = new ArrayList<String>();
		if (collection.isEmpty()) {
			collection.put(word, 0);
		}

		int maxValueInMap = (Collections.max(collection.values()));

		for (Map.Entry<String, Integer> entry : collection.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				list.add(entry.getKey().toLowerCase());
			}
		}

		return list;
	}

	public String findMissingCorrectCollection(List<String> list, Map<String, Integer> collection, String word) {

		Map<String, Integer> newcollection = new HashMap<>();

		int length = word.length() + 1;

		for (String element : list) {
			if (element.length() > word.length() && element.length() < length) {
				length = element.length();
			}
		}
		
		for (String element : list) {
			if (element.length() == length) {
				for (int loop = 0; loop < word.length(); loop++) {
					if (word.charAt(loop) != element.charAt(loop)) {
						break;
					} else {
						newcollection.put(element, loop);
					}
				}
			}
		}

		newcollection = miniDistance(newcollection, word);

		if (newcollection.isEmpty()) {
			newcollection.put(word, 0);
		}

		return Collections.max(newcollection.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

	}

	public Map<String, Integer> miniDistance(Map<String, Integer> newcollection, String word) {

		String words = "";

		for (Map.Entry<String, Integer> entry : newcollection.entrySet()) {

			if (word.length() <= entry.getKey().toLowerCase().length()) {
				for (int loop = 0; loop < word.length(); loop++) {
					if (word.charAt(loop) != entry.getKey().toLowerCase().charAt(loop)) {
						break;
					} else {
						newcollection.put(entry.getKey().toLowerCase(), loop);
					}
				}
			} else {
				for (int loop = 0; loop < entry.getKey().toLowerCase().length(); loop++) {
					if (word.charAt(loop) != entry.getKey().toLowerCase().charAt(loop)) {
						break;
					} else {
						newcollection.put(entry.getKey().toLowerCase(), loop);
					}
				}
			}

		}

		for (Map.Entry<String, Integer> entry : newcollection.entrySet()) {
			if (entry.getValue() == null) {
				entry.setValue(0);
			}
		}

		// System.out.println(newcollection);
		return newcollection;

	}

	public String findChangingMaxCollection(List<String> list, Map<String, Integer> collection, String word) {

		Map<String, Integer> newcollection = new HashMap<>();

		for (String element : list) {
			int compter = 0;
			for (int i = 0; i < element.length(); i++) {
				for (int j = 0; j < word.length(); j++) {
					if (element.charAt(i) == word.charAt(j)) {
						compter += 1;
					}
				}
				newcollection.put(element, compter);
			}
			// System.out.println(newcollection);

		}

		return Collections.max(newcollection.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();

	}

	public void doResult(Map<String, Integer> collection, List<String> list, String word) {

		Map<String, Integer> newcollection = new HashMap<>();
		Map<String, Integer> newwordcollection = new HashMap<>();
		
		int num;

		for (String element : list) {

			char[] char_element = element.toLowerCase().toCharArray();
			int compter = 0;

			if (element.length() >= word.length()) {
				for (int i = 0; i < word.length(); i++) {
					if (element.charAt(i) == word.charAt(i)) {

						compter += 1;
					}

					if (element.charAt(word.length() - i - 1) == word.charAt(word.length() - i - 1)
							|| word.lastIndexOf(char_element[element.length() - i - 1]) > -1) {
						compter += 1;
					}
				}
			} else {
				for (int i = 0; i < element.length(); i++) {
					if (element.charAt(i) == word.charAt(i)) {
						compter += 1;
					}
					if (element.charAt(element.length() - i - 1) == word.charAt(element.length() - i)
							|| word.lastIndexOf(char_element[element.length() - i - 1]) > -1) {
						compter += 1;
					}
				}
			}
			
			if (!collection.containsKey(element)) {
				compter -= 999;
			}
			if (element.equals(word) && collection.containsKey(element)) {
				compter += 999;
			} else {
				compter -= 999;
			}

			newcollection.put(element, compter);

		}

		String key = null;

		int maxValueInMap = (Collections.max(newcollection.values()));

		for (Entry<String, Integer> entry : newcollection.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				System.out.println(entry.getKey());
				key = entry.getKey();
			}
		}

		newcollection.remove(key);
		
		int maxsecondValueInMap = (Collections.max(newcollection.values()));
		int valuesecond = maxValueInMap - 10;
		if (maxsecondValueInMap >= valuesecond) {
			for (Entry<String, Integer> entry : newcollection.entrySet()) {
				if (entry.getValue() == maxsecondValueInMap) {
					if (findDifferents (word, entry.getKey())) {
						System.out.println(entry.getKey());
					}
				}
			}
		}

	}
	
	public boolean findDifferents (String word, String entry) {
		int compter = 0;
		
	    for( int i = 0; i < word.length() && i < entry.length(); i++ ) {

	        if( word.charAt(i) != entry.charAt(i) ) {
	            compter = compter + 1;
	        }
	    }
	    
	    if (compter > 2) {
	    	return false;
	    } else {
		
		return true;
	    }
	}

	public Map<String, Integer> mapLowercase(Map<String, Integer> map) {
		Map<String, Integer> lowerCaseMap = new HashMap<>(map.size());
		for (Entry<String, Integer> entry : map.entrySet()) {
			lowerCaseMap.put(entry.getKey().toLowerCase().toLowerCase(), entry.getValue());
		}
		return lowerCaseMap;
	}

}
