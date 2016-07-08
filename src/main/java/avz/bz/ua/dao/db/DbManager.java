package avz.bz.ua.dao.db;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.apache.derby.impl.jdbc.EmbedCallableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;

import avz.bz.ua.dao.Manager;
import avz.bz.ua.model.EngWord;
import avz.bz.ua.model.RusWord;
import avz.bz.ua.model.Word;


@Component
public class DbManager implements Manager {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@PostConstruct
	public void inder(){
		System.out.println(em + "    =================");
			
	}
	
	
	@Override
	public String checkContent(String directOfTrans, String value) {
		String content = em.createQuery("from RusWord where id = 1", RusWord.class ).getSingleResult().getWord();
		System.out.println(content + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
		return content;
	}

	@Override
	public void putValueInDict(String directOfTrans, String word, String value) {
		Class<?> entityClass = chooseEntityClass(directOfTrans);
		String contentString = null;
		
		
		
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Word> chooseEntityClass(String directOfTrans) {
		try {
			return (Class<? extends Word>) Class.forName(directOfTrans.equals("en-ru") ? "RusWord"
					: "EngWord");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}
}
