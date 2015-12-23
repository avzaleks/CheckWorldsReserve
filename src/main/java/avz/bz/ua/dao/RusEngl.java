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
import java.util.TreeMap;

public class RusEngl extends Dict {

	private static RusEngl rusEngl = new RusEngl();
	private String path = "D:\\rusEngDict";
	private MyTreeMap rusEngDict;

	private RusEngl() {
		if (new File(path).exists()) {
			readFromDisk(path);
		} else {
			rusEngDict = new MyRusTreeMap();
			writeToDisk(rusEngDict);
		}
	}

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
			objInpStr = new ObjectInputStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			rusEngDict = (MyTreeMap) objInpStr
					.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static RusEngl getRusEngl() {
		return rusEngl;
	}

	@Override
	public MyTreeMap getDict() {
		return rusEngDict;
	}

}
