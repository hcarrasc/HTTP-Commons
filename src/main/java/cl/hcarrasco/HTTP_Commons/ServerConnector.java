package cl.hcarrasco.HTTP_Commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * @author Hector Carrasco
 * @category library
 */
public class ServerConnector {
    
	final static Logger logger = Logger.getLogger(ServerConnector.class);
	
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
	
	public String getIPfromHostName(String hostName){
		
		String IPstr;
		InetAddress address = null;
		boolean serverReachable = false;
		
		try {
			address = InetAddress.getByName(hostName);
		    serverReachable = address.isReachable(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        if (serverReachable){
        	logger.info("IP: "+address.getHostAddress());
        	IPstr = address.getHostAddress();
        	return IPstr;
        } else {
        	IPstr = "couldn't connect to server";
        }
		return IPstr;
	}
	
	public String getHostNameFromIP(String IPstr){
		
		InetAddress address = null;
		String host;
		boolean serverReachable = false;
		
		try {
			address = InetAddress.getByName(IPstr);
			serverReachable = address.isReachable(10000);
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (serverReachable){
        	host = address.getHostName();
        	logger.info("HostName: "+host);
        	return host;
        } else {
        	host = "couldn't connect to server";
        }
		return host;
	}
	
}
