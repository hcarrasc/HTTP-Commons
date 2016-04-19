package cl.hcarrasco.httpcommons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class HTTPConnection {
	
	final static Logger logger = Logger.getLogger(HTTPConnection.class);

	public static String doHTTPGETRequest(String host, Map<String, String> urlParams){
		
		String charset = StandardCharsets.UTF_8.name();
		String query = "";
		URLConnection connection;
		String responseBody = "";
		
		try {
			
			if(!host.contains("http://")){
				host = "http://"+host;
			}
			if (urlParams==null){
				connection = new URL(host).openConnection();
			} else {
				
				Iterator<String> it = urlParams.keySet().iterator();
				while(it.hasNext()){
					  String key = (String) it.next();
					  query = query + String.format(key+"=%s", URLEncoder.encode(urlParams.get(key), charset));
					  if (it.hasNext()){
							query = query + "&";
					  }
				}
				connection = new URL(host + "?" + query).openConnection();
			}
			
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
			
			try (Scanner scanner = new Scanner(response)) {
			    responseBody = scanner.useDelimiter("\\A").next();
			    logger.info(responseBody);
			} catch (Exception e) {}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseBody;
	}
	
}
