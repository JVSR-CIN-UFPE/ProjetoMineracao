import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class LaftFm {
	
	private static final String PARAM_API_KEY = "api_key";
	private static final String PARAM_METHOD = "method";

	private static final String DEFAULT_API_ROOT = "http://ws.audioscrobbler.com/2.0/";
	
	public static void main(String[] args) throws IOException {
		
		String key = "066c80658001d27d24106418ad959695";
		
		String xml = Jsoup.connect("http://ws.audioscrobbler.com/2.0/?method=user.getshouts&user=rj&api_key=066c80658001d27d24106418ad959695").request().toString();
		
		System.out.println(xml);
		
	}
}
