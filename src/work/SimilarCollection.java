package work;

import java.util.HashMap;
import java.util.Map;

public class SimilarCollection {

	public static Map<String, Integer> findSimilarCollection(Map<String, Integer> collection, String word) {

		Map<String, Integer> newposition = new HashMap<>();
		for (Map.Entry<String, Integer> entry : collection.entrySet()) {
			if (word.length() == entry.getKey().toLowerCase().length()) {
				for (int loop = 0; loop < word.length(); loop++) {
					if (word.charAt(loop) != entry.getKey().toLowerCase().charAt(loop)) {
						break;
					} else {
						newposition.put(entry.getKey().toLowerCase(), loop);
					}
				}
			}
		}

		return newposition;
	}
}
