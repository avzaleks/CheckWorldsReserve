package avz.bz.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import avz.bz.ua.dao.Manager;

@Service("dictionamryServise")
@Component
public class DictionaryService {

	@Autowired
	@Qualifier("dbManager")
	private Manager manager;

	public String checkContent(String directOfTrans, String value) {
		return manager.checkContent(directOfTrans, value);
	}

	public void discoverMessage(String message) {
		message = message.replaceAll(" ", "");
		String[] array = message.split(":");
		manager.putValueInDict(array[0], array[1], array[2]);
	}

}