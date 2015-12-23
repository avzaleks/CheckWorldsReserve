package avz.bz.ua.dao;

import java.util.Arrays;
import java.util.HashSet;

public class Manager {

	private RusEngl rusEngl = RusEngl.getRusEngl();
	private EnglRus englRus = EnglRus.getEnglRus();
	private int counter;
	private MyTreeMap dictionary;
	private MyTreeMap oppositeDictionary;

	public Manager() {
	}

	public String checkContent(String directOfTrans, String value) {
		chooseDictionary(directOfTrans);
		String contentString = null;
		if (dictionary.containsKey(value)) {
			contentString = dictionary.get(value).toString();
		}
		return contentString;
	}

	public void putValueInDict(String directOfTrans, String world, String value) {
		chooseDictionary(directOfTrans);
		String[] array = value.split(",");
		HashSet<String> prepare = new HashSet<String>(Arrays.asList(array));
		insertIntoDict(world, prepare);
		sinchronOfDicts(world, prepare);
		counter = counter + 1;
		if (counter >= 10) {
			saveAll();
			counter = 0;
		}
	}

	public void saveAll() {
		pushDictToStorage(englRus);
		pushDictToStorage(rusEngl);
	}

	private void pushDictToStorage(Dict stor) {
		stor.writeToDisk(stor.getDict());
	}

	private void insertIntoDict(String word, HashSet<String> prepare) {
		if (dictionary.containsKey(word)) {
			for (String string : prepare) {
				dictionary.get(word).add(string);
			}
		} else {
			dictionary.puttig(word, prepare);
		}
	}

	private void sinchronOfDicts(String wordForSinc, HashSet<String> prepare) {
		for (String word : prepare) {
			if (oppositeDictionary.containsKey(word)) {
				oppositeDictionary.get(word).add(wordForSinc);
			} else {
				HashSet<String> oneWorld = new HashSet<String>();
				oneWorld.add(wordForSinc);
				oppositeDictionary.puttig(word, oneWorld);
			}
		}

	}

	private void chooseDictionary(String directOfTrans) {
		dictionary = directOfTrans.equals("en-ru") ? englRus.getDict()
				: rusEngl.getDict();
		oppositeDictionary = directOfTrans.equals("en-ru") ? rusEngl.getDict()
				: englRus.getDict();
	}

}
