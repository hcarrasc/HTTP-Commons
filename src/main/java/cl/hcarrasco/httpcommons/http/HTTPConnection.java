package cl.hcarrasco.httpcommons.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.binary.Base64;

import org.apache.log4j.Logger;

/**
 * @author Héctor Carrasco
 */
public class HTTPConnection {
	
	final static Logger logger = Logger.getLogger(HTTPConnection.class);
	
	public static void main(String[] args) {
		doHTTPAuthRequest("http://cpanel.hcarrasco.cl","hcarrasc","progressive**");
	}

	/**
	 * @param urlString
	 * @param user
	 * @param password
	 * @return Full content response of request.
	 */
	public static String doHTTPAuthRequest(String urlString, String user, String password){
		
		try {
            URL url = new URL (urlString);

            Base64 b = new Base64();
            String encoding = b.encodeAsString(new String(user+":"+password).getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty  ("Authorization", encoding);
            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in =  new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
            	logger.info("HostResponse: "+line);
            }
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
		return "";
	}
	
	
	/**
	 * @param host
	 * @param urlParams 
	 * @return Full content response of request.
	 */
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
