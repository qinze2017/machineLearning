package work;

import java.util.HashMap;
import java.util.Map;

public class SuperfluousCollection {

		public static Map<String, Integer> findSuperfluousCollection(Map<String, Integer> collection, String word, boolean flg) {

			Map<String, Integer> newposition = new HashMap<>();

			StringBuilder myWord = new StringBuilder(word.toLowerCase());

			for (int loop = 0; loop < word.length(); loop++) {

				word = myWord.deleteCharAt(loop).toString();

				if (collection.containsKey(word)) {
					newposition.put(word.toLowerCase(), collection.get(word));
				}

			}
			return newposition;
		}

}
