package avz.bz.ua.dao.file;

import java.util.HashSet;

public class MyRusTreeMap extends MyTreeMap {

	private static final long serialVersionUID = -4048679433639404138L;

	@Override
	public void puttig(String key, HashSet<String> value) {
		put(key, value);
	};

}
