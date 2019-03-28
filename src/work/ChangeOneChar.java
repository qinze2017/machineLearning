package work;

import java.util.HashMap;
import java.util.Map;

public class ChangeOneChar {

	public static Map<String, Integer> setchangeoneChar(Map<String, Integer> collection, String n) {
		Map<String, Integer> newcollection = new HashMap<>();
		for (char c = 'a'; c < 'z'; c++) {
			for (int i = 0; i < n.length(); i++) {
				StringBuilder myWord = new StringBuilder(n.toLowerCase());
				myWord.setCharAt(i, c);
				if (collection.get(myWord.toString()) == null) {
					newcollection.put(myWord.toString(), 0);
				} else {
					newcollection.put(myWord.toString(), collection.get(myWord.toString()));
				}
			}

		}

		// System.out.println(newcollection);

		return newcollection;
	}
}
