package avz.bz.ua.dao;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class SaverSound implements Runnable {

	private static final String partOfUrl = "http://soundoftext.com/static/sounds/en/";
	private String word;
	private URL url; 
	
	
	public SaverSound(String word) {
		this.word = word;
		try {
			url = new URL(partOfUrl + word + ".mp3");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		 try {
			FileUtils.copyURLToFile(url, new File("D:\\sound\\"+word + ".mp3"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
