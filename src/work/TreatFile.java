package work;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreatFile {

	public Map<String, Integer> frequencyWords(ArrayList<String> wordsList) {
		
		Map<String, Integer> collect = wordsList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));

		Map<String, Integer> sortResult = new LinkedHashMap<>();
		collect.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> sortResult.put(x.getKey(), x.getValue()));
		sortResult.remove("");
		//System.out.println(sortResult);
		//System.out.println(wordsList);
		return collect;
	}

	public ArrayList<String> readWords() {

		ArrayList<String> wordsList = new ArrayList<String>();

		String fileName = "corpus-challenge.txt";

		// read file into stream, try-with-resources
		try {
			Stream<String> stream = Files.lines(Paths.get(fileName)).map(line -> line.split("[\\s]+"))
					.flatMap(Arrays::stream).distinct();

			// stream.forEach(System.out::println);
			stream.sequential().collect(Collectors.toCollection(() -> wordsList));
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordsList;
	}
	
	public ArrayList<String> removeLetters(ArrayList<String> wordsList) {
		//remove the last element from the list
		wordsList.remove(wordsList.size() - 1);
		
		ArrayList<String> newwordsList = new ArrayList<String>();
		for (String element: wordsList) {
			element = element.replaceAll("[^a-zA-Z0-9]+", "");
			newwordsList.add(element);
		}
		return newwordsList;
	}
	
}
