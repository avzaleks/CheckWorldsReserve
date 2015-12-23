package avz.bz.ua.googleassist;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GoogleAssistent {

	private WebDriver driver = new FantomDriver().getDriver();;
	private static String quot = "\"";
	private static String classNameForPartsOfSpeach = "gt-cd-pos";
	private static String classNameTable = "gt-baf-table";
	private List<String> posDeviders = new ArrayList<String>();

	public GoogleAssistent() {

	}

	public WebElement getSource(String directOfTrans, String word) {
		WebElement source = null;
		driver.get("https://translate.google.ru/" + directOfTrans + word);
		source = driver.findElement(By.className(classNameTable));
		return source;
	}

	private String getDirtyString(WebElement element) {
		StringBuffer dirtyString = new StringBuffer(500);
		List<WebElement> div = element.findElements(By.tagName("div"));
		for (WebElement divElement : div) {
			String textInElem = divElement.getText();
			for (String string : posDeviders) {
				if (string.equals(textInElem)) {
					dirtyString.append("%" + textInElem +": ");
					textInElem = "";
					break;
				}
			}
			if (divElement.getAttribute("title").contains("перевод")) {
				dirtyString.append("&" + divElement.getAttribute("title")+":");
				continue;
			}
			if (!textInElem.equals("")) {
				dirtyString.append("$"+ textInElem );
			}
		}
		driver.quit();
		return dirtyString.toString();
	}

	private void getPosDeviders(WebElement element) {
		List<WebElement> pos = element.findElements(By
				.className(classNameForPartsOfSpeach));
		for (WebElement posElement : pos) {
			posDeviders.add(posElement.getText());
		}

	}

	public String getTranslate(String directOfTrans, String word) {
		getPosDeviders(getSource(directOfTrans, word));
		return getDirtyString(getSource(directOfTrans, word));
    	}


	
	
	public void killDriver() {
		driver.quit();
	}
	
public void rerunDriver() {
	System.out.println(System.currentTimeMillis()+"rrr");
	driver  = new FantomDriver().getDriver();
}


}
