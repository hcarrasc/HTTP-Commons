package cl.hcarrasco.httpcommons.http;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.log4j.Logger;

/**
 * @author Hector Carrasco
 * @category library
 */
public class ServerData {
    
	final static Logger logger = Logger.getLogger(ServerData.class);
	
	/**
	 * @param hostName
	 * @return IP string representation in format x.x.x.x
	 */
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
	
	/**
	 * @param IPstr
	 * @return Host Name
	 */
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
