package dayPrototype;

import java.util.ArrayList;
import java.util.Random;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.jsoup.helper.*;
import org.jsoup.internal.*;
import org.jsoup.parser.*;
import org.jsoup.safety.*;



public class p1 {
	public static void main(String[] args) {
		
		Document doc;
		try {
			doc = Jsoup.connect("https://en.wikipedia.org/wiki/November_30").get();
			Elements temp = doc.select("u1");
			ArrayList<String> eventHolder = new ArrayList<String>();
			ArrayList<String> eventPrint = new ArrayList<String>();
			Random selector = new Random();
			int events = 0;
			//int howMany; TODO selector for the number of events to be given to the user
			int i = 1;
			
			for(Element foo: temp) {
				String s = foo.getElementsByTag("l1").first() + "";
				eventHolder.add(s);
			}
			
			while(events < 3) { //TODO or howMany
				int temp1 = selector.nextInt(eventHolder.size()) + 1;
				eventPrint.add(eventHolder.get(temp1));
				eventHolder.remove(temp1);
				events++;
			}
			
			System.out.println(eventPrint.toString());

		} catch(Exception e) {
			//TODO find the website not found exception
		}
	}

}
