package avz.bz.ua.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;

public class EnglRus extends Dict {

	private static EnglRus englRus = new EnglRus();
	private String path = "D:\\engRusDict";
	private MyTreeMap engRusDict;

	private EnglRus() {
		if (new File(path).exists()) {
			readFromDisk(path);
		} else {
			engRusDict = new MyEnglTreeMap();
			writeToDisk(engRusDict);
		}
	}

	@Override
	public void writeToDisk(Map<String, HashSet<String>> map) {
		ObjectOutputStream objOutStr = null;
		try {
			objOutStr = new ObjectOutputStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			objOutStr.writeObject(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFromDisk(String path) {
		ObjectInputStream objInpStr = null;
		try {
			System.out.println(path);
			objInpStr = new ObjectInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			engRusDict = (MyTreeMap) objInpStr
					.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static EnglRus getEnglRus() {
		return englRus;
	}

	@Override
	public MyTreeMap getDict() {

		return engRusDict;
	}

}
