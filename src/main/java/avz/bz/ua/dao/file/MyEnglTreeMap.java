package avz.bz.ua.dao.file;

import java.util.HashSet;

public class MyEnglTreeMap extends MyTreeMap {

	private static final long serialVersionUID = 7402728594184991758L;

	@Override
	public void puttig(String key, HashSet<String> value) {
		put(key, value);
		// new SaverSound(key).run();
	}

}
