package work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DuplicatedCollection {

	public static Map<String, Integer> findDuplicatedCollection(Map<String, Integer> collection, String word, boolean flg) {

		Map<String, Integer> newposition = new HashMap<>();
		Set<Character> set = new LinkedHashSet<Character>();
		Set<Character> setDuplicated = new LinkedHashSet<Character>();
		List<String> list = new ArrayList<String>();
		int cnt = 0;

		char[] chars = word.toLowerCase().toCharArray();

		for (char c : chars) {
			set.add(c);
		}

		for (int i = 0; i < word.length(); i++) {
			for (int j = i + 1; j < word.length(); j++) {
				if (chars[i] == chars[j]) {
					// System.out.println(chars[j]);
					setDuplicated.add(chars[j]);
					cnt++;
					break;
				}
			}
		}

		for (Character characterD : setDuplicated) {
			StringBuilder sb = new StringBuilder();
			for (Character character : set) {
				sb.append(character);
				if (characterD == character) {
					sb.append(character);
				}
			}
			list.add(sb.toString());
		}

		for (String element : list) {
			for (Map.Entry<String, Integer> entry : collection.entrySet()) {
				if (element.toString().toLowerCase().length() == entry.getKey().toLowerCase().length()) {
					for (int loop = 0; loop < element.toString().toLowerCase().length(); loop++) {
						if (element.toString().toLowerCase().charAt(loop) != entry.getKey().toLowerCase()
								.charAt(loop)) {
							flg = false;
							break;
						} else {
							newposition.put(entry.getKey().toLowerCase(), loop);
						}
					}
				}
			}
		}

		return newposition;
	}
}
