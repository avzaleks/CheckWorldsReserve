package avz.bz.ua.dao.file;

import java.util.HashSet;
import java.util.TreeMap;

abstract class MyTreeMap extends TreeMap<String, HashSet<String>> {

	private static final long serialVersionUID = 8299935332871857844L;

	abstract public void puttig(String key, HashSet<String> value);

}
