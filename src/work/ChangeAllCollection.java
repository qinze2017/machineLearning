package work;

import java.util.HashMap;
import java.util.Map;

public class ChangeAllCollection {

		public static Map<String, Integer> findChangeCollection(Map<String, Integer> collection, String word) {

			Map<String, Integer> newposition = new HashMap<>();
			
			Character a = word.charAt(0);

			if (word.length() > 1) {

				word = word.substring(1);

				Map<String, Integer> permSet = findChangeCollection(collection, word);

				for (String x : permSet.keySet()) {
					for (int i = 0; i <= x.length(); i++) {
						if (collection.containsKey(x.substring(0, i) + a + x.substring(i))) {
							newposition.put(x.substring(0, i) + a + x.substring(i),
									collection.get(x.substring(0, i) + a + x.substring(i)));
						} else {
							newposition.put(x.substring(0, i) + a + x.substring(i), 0);
						}
					}
				}
			} else {
				newposition.put(a + "", 0);
			}

			//System.out.println("here newposition is " + newposition);
			return newposition;
		}

}
