package avz.bz.ua.dao.file;

import java.util.HashSet;
import java.util.Map;

public abstract class Dict {

	abstract Map<String, HashSet<String>> getDict();

	abstract void writeToDisk(Map<String, HashSet<String>> map);

}
