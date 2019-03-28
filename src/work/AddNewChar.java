package work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewChar {

	public static Map<String, Integer> setnewChar(Map<String, Integer> collection, String n) {

		Map<String, Integer> newcollection = new HashMap<>();
		List<String> list = new ArrayList<>();

		for (char c = 'a'; c < 'z'; c++) {
			for (int i = 0; i < n.length(); i++) {
				StringBuffer myWord = new StringBuffer(n.toLowerCase());
				list.add(myWord.insert(i, c).toString());
			}
		}

		for (String element : list) {
			if (element.length() == n.length() + 1) {
				if (collection.get(element) == null) {
					newcollection.put(element, 0);
				} else {
					newcollection.put(element, collection.get(element));
				}
			}

		}

		return newcollection;
	}
}
