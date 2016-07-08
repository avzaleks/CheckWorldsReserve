package avz.bz.ua.dao.file;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.apache.derby.tools.sysinfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import avz.bz.ua.dao.Manager;

@Component
public class FileManager implements Manager {

	private RusEngl rusEngl = RusEngl.getRusEngl();
	private EnglRus englRus = EnglRus.getEnglRus();
	private int counter;
	private MyTreeMap dictionary;
	private MyTreeMap oppositeDictionary;

	public FileManager() {
	}

	@Override
	public String checkContent(String directOfTrans, String value) {
		chooseDictionary(directOfTrans);
		String contentString = null;
		if (dictionary.containsKey(value)) {
			contentString = dictionary.get(value).toString();
		}
		return contentString;
	}

	@Override
	public void putValueInDict(String directOfTrans, String word, String value) {
		chooseDictionary(directOfTrans);
		String[] array = value.split(",");
		HashSet<String> prepare = new HashSet<String>(Arrays.asList(array));
		insertIntoDict(word, prepare);
		sinchronOfDicts(word, prepare);
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
		System.out.println(dictionary.toString());
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
		dictionary = directOfTrans.equals("en-ru") ? englRus.getDict() : rusEngl.getDict();
		oppositeDictionary = directOfTrans.equals("en-ru") ? rusEngl.getDict() : englRus.getDict();
	}

}
