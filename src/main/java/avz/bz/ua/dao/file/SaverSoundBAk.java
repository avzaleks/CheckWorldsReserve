package avz.bz.ua.dao.file;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class SaverSoundBAk implements Runnable {

	private static final String partOfUrl = "http://soundoftext.com/static/sounds/en/";
	private String word;
	private URL url; 
	
	
	public SaverSoundBAk (String word) {
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
			FileUtils.copyURLToFile(url, new File("D:\\sounds\\"+word + ".mp3"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
